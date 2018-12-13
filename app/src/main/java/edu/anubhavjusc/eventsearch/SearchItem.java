package edu.anubhavjusc.eventsearch;

public class SearchItem {

    private String title;
    private String location;
    private String datetime;
    private String category;
    private String eventId;
    private String artist;
    private String artist2;

    public SearchItem() {

    }

    public SearchItem(String title, String location, String datetime, String category, String eventId, String artist, String artist2) {
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.category = category;
        this.eventId = eventId;
        this.artist = artist;
        this.artist2 = artist2;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist2() {
        return artist2;
    }

    public void setArtist2(String artist2) {
        this.artist2 = artist2;
    }
}
