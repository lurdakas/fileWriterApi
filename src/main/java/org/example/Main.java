package org.example;

import com.google.gson.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static Gson gson;


    public static void main(String[] args) {

        gson = new Gson();


        printCity();
        API(printCityForecast());

    }


    public static void printCity() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter City Name");
        String city = sc.nextLine();
        String url = "https://api.meteo.lt/v1/places/" + city;
        System.out.println(url);
        System.out.println(getPlace(url));

    }


    public static String printCityForecast() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter City Name To See Forecast");
        String city = sc.nextLine();
        String url = "https://api.meteo.lt/v1/places/" + city + "/forecasts/long-term";
        System.out.println(url);
        return url;

    }


    private static void API(String urlString) {
        try {

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String response = "";
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray placeArray = jsonResponse.getAsJsonArray("forecastTimestamps");
            System.out.println(placeArray);
            reader.close();
        } catch (Exception e) {
            System.out.println("Does not work");
            System.out.println(e);


        }

    }

//    public static void deletePlace(Place place) {
//        List<Place> places = getPlaces();
//        Place existingPlace = places.stream()
//                .filter(p -> p.equals(place))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Place with code " + place.getCode() + " not found."));
//        places.remove(existingPlace);
//        updateJson(places);
//    }

//
//    public static void updatePlace(Place place) {
//        List<Place> places = getPlaces();
//        int index = places.indexOf(place);
//        if (index != -1) {
//            places.set(index, place);
//        } else {
//            throw new IllegalArgumentException("Place with code " + place.getCode() + " not found.");
//        }
//        updateJson(places);
//    }
//
//    public static void updateJson(List<Place> places) {
//        try (FileWriter writer = new FileWriter("places.json")) {
//            gson.toJson(places, writer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public static void addPlace(Place place) {
//        List<Place> places = getPlaces();
//        places.add(place);
//        updateJson(places);
//    }


    public static Place getPlace(String urlString) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Place();
    }


    public static Forecast getForecast(String urlString) throws IOException {
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
                String forecastTimeUtc = jsonObject.get("forecastTimeUtc").getAsString();
                String airTemperature = jsonObject.get("airTemperature").getAsString();
                String feelsLikeTemperature = jsonObject.get("feelsLikeTemperature").getAsString();
                String windSpeed = jsonObject.get("windSpeed").getAsString();
                String windGust = jsonObject.get("windGust").getAsString();
                String windDirection = jsonObject.get("windDirection").getAsString();
                String cloudCover = jsonObject.get("cloudCover").getAsString();
                String seaLevelPressure = jsonObject.get("seaLevelPressure").getAsString();
                String relativeHumidity = jsonObject.get("relativeHumidity").getAsString();
                String totalPrecipitation = jsonObject.get("totalPrecipitation").getAsString();
                String conditionCode = jsonObject.get("conditionCode").getAsString();
                return new Forecast(forecastTimeUtc, airTemperature, feelsLikeTemperature, windSpeed, windGust, windDirection, cloudCover, seaLevelPressure, relativeHumidity, totalPrecipitation, conditionCode);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return new Forecast();
        }
    }