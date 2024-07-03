package org.example;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Gson gson = new Gson();


    public static void main(String[] args) {

//        gson = new Gson();
////
//        Place p = new Place("abromiskes", "Abromiškės", "Elektrėnų savivaldybė", "LT", "55.40348", "24.71032");
//        Place p2 = new Place("acokavai", "Acokavai", "Radviliškio rajono savivaldybė", "LT", "55.72656", "23.34748");
//
//        addPlace(p);
//        addPlace(p2);

        printCity();
//        API();
//        getPlaces();


    }


    public static void printCity() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter City Name");
        String city = sc.nextLine();
        String url = "https://api.meteo.lt/v1/places/" + city;
        Place place = getPlace(url);
        System.out.println(place);


    }


    private static void API() {
        try {
            URL url = new URL("https://api.meteo.lt/v1/places");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String response = "";
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray placeArray = jsonResponse.getAsJsonArray("data");
            Place name = gson.fromJson(placeArray, Place.class);
            System.out.println(name);


            reader.close();
        } catch (Exception e) {

        }

    }

    public static void deletePlace(Place place) {
        List<Place> places = getPlaces();
        Place existingPlace = places.stream()
                .filter(p -> p.equals(place))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Place with code " + place.getCode() + " not found."));
        places.remove(existingPlace);
        updateJson(places);
    }


    public static void updatePlace(Place place) {
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


    public static Place getPlace(String urlString) {
//        try (FileWriter writer = new FileWriter("user.json", true)) {
//            gson.toJson(place, writer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                String code = jsonObject.get("code").getAsString();
                String name = jsonObject.get("name").getAsString();
                String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
                String country = jsonObject.get("country").getAsString();
                String countryCode = jsonObject.get("countryCode").getAsString();
                Coordinates coordinates = gson.fromJson(jsonObject.get("coordinates"), Coordinates.class);

                return new Place(code, name, administrativeDivision, country, countryCode, coordinates);
            } else {
                System.out.println("GET request did not work. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                Coordinates coordinates = gson.fromJson(jsonObject.get("coordinates"), Coordinates.class);
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