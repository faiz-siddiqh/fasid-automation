package com.fasid.boredapi.dto;

public class ActivityDTO {

    public String activity;
    public float accessibility;
    public int participant;
    public float price;
    public String type;
    public String link;
    public String key;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(int accessibility) {
        this.accessibility = accessibility;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "activity='" + activity + '\'' +
                ", accessibility=" + accessibility +
                ", participant=" + participant +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
