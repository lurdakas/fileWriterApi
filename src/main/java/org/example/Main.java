package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Gson gson;

    public static void main(String[] args) {

        gson = new Gson();

        Place p = new Place("abromiskes", "Abromiškės", "Elektrėnų savivaldybė", "LT", "55.40348");
        Place p2 = new Place("acokavai", "Acokavai", "Radviliškio rajono savivaldybė", "LT", "55.72656");

        addPlace(p);
        addPlace(p2);
    }
    public static void deletePlace(Place place){
        List<Place> places = getPlaces();
        Place existingPlace = places.stream()
                .filter(p -> p.equals(place))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Place with code " + place.getCode() + " not found."));
        places.remove(existingPlace);
        updateJson(places);
    }

    public static void updatePlace(Place place){
        List<Place> places = getPlaces();
        int index = places.indexOf(place);
        if (index != -1) {
            places.set(index, place);
        } else {
            throw new IllegalArgumentException("Place with code " + place.getCode() + " not found.");
        }
        updateJson(places);
    }

    public static void updateJson(List<Place> places) {
        try (FileWriter writer = new FileWriter("places.json")) {
            gson.toJson(places, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addPlace(Place place) {
        List<Place> places = getPlaces();
        places.add(place);
        updateJson(places);
    }

    public static void addpreviousPlace(Place place){
        try(FileWriter writer = new FileWriter("user.json",true)){
            gson.toJson(place,writer);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static Place getPlace(String code) {
        try (FileReader reader = new FileReader("places.json")) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String placeCode = jsonObject.get("code").getAsString();
                if (code.equals(placeCode)) {
                    String name = jsonObject.get("name").getAsString();
                    String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
                    String countryCode = jsonObject.get("countryCode").getAsString();
                    String coordinates = jsonObject.get("coordinates").getAsString();
                    Place place = new Place();
                    place.setCode(code);
                    place.setName(name);
                    place.setAdministrativeDivision(administrativeDivision);
                    place.setCountryCode(countryCode);
                    place.setCoordinates(coordinates);
                    return place;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Somethings off");
        }
        return new Place();
    }
    public static List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        try (FileReader reader = new FileReader("places.json")) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String code = jsonObject.get("code").getAsString();
                String name = jsonObject.get("name").getAsString();
                String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
                String countryCode = jsonObject.get("countryCode").getAsString();
                String coordinates = jsonObject.get("coordinates").getAsString();
                Place place = new Place();
                place.setCode(code);
                place.setName(name);
                place.setAdministrativeDivision(administrativeDivision);
                place.setCountryCode(countryCode);
                place.setCoordinates(coordinates);
                places.add(place);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return places;
    }
}