package urls;

public enum ApiUrlMapper {

    ACTIVITY("api/activity/");


    String url;

    ApiUrlMapper(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
