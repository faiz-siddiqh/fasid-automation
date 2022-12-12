package urls;

public enum ApiUrlMapper {

    ACTIVITY("api/activity/"),
    ACTIVITY_BY_KEY("api/activity");


    String url;

    ApiUrlMapper(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
