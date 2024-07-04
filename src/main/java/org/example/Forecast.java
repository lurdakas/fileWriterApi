package org.example;



public class Forecast {


    private String forecastTimeUtc;
    private String airTemperature;
    private String feelsLikeTemperature;
    private String windSpeed;
    private String windGust;
    private String windDirection;
    private String cloudCover;
    private String seaLevelPressure;
    private String relativeHumidity;
    private String totalPrecipitation;
    private String conditionCode;



    public Forecast() {
    }


    public Forecast(String forecastTimeUtc, String airTemperature, String feelsLikeTemperature, String windSpeed, String windGust, String windDirection, String cloudCover, String seaLevelPressure, String relativeHumidity, String totalPrecipitation, String conditionCode) {
        this.forecastTimeUtc = forecastTimeUtc;
        this.airTemperature = airTemperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windDirection = windDirection;
        this.cloudCover = cloudCover;
        this.seaLevelPressure = seaLevelPressure;
        this.relativeHumidity = relativeHumidity;
        this.totalPrecipitation = totalPrecipitation;
        this.conditionCode = conditionCode;
    }



    public String getForecastTimeUtc() {
        return forecastTimeUtc;
    }

    public void setForecastTimeUtc(String forecastTimeUtc) {
        this.forecastTimeUtc = forecastTimeUtc;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(String airTemperature) {
        this.airTemperature = airTemperature;
    }

    public String getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(String feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getSeaLevelPressure() {
        return seaLevelPressure;
    }

    public void setSeaLevelPressure(String seaLevelPressure) {
        this.seaLevelPressure = seaLevelPressure;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getTotalPrecipitation() {
        return totalPrecipitation;
    }

    public void setTotalPrecipitation(String totalPrecipitation) {
        this.totalPrecipitation = totalPrecipitation;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }




    @Override
    public String toString() {
        return "Forecast{\n" +
                "  forecastTimeUtc='" + forecastTimeUtc + "',\n" +
                "  airTemperature='" + airTemperature + "',\n" +
                "  feelsLikeTemperature='" + feelsLikeTemperature + "',\n" +
                "  windSpeed='" + windSpeed + "',\n" +
                "  windGust='" + windGust + "',\n" +
                "  windDirection='" + windDirection + "',\n" +
                "  cloudCover='" + cloudCover + "',\n" +
                "  seaLevelPressure='" + seaLevelPressure + "',\n" +
                "  relativeHumidity='" + relativeHumidity + "',\n" +
                "  totalPrecipitation='" + totalPrecipitation + "',\n" +
                "  conditionCode='" + conditionCode + "'\n" +
                "}";
    }
}