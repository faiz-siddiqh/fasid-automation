package urls;


public enum WeatherUrlMapper {

    WEATHER("weather/"),
    FORECAST("forecast/");

    String url;

    WeatherUrlMapper(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
