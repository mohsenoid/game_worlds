package com.xyrality.gameworlds.network.model;

/**
 * Created by Mohsen on 1/20/16.
 */
public class World {
    private String id;
    private String mapURL;
    private String name;
    private String language;
    private String url;
    private String country;
    private WorldStatus worldStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public WorldStatus getWorldStatus() {
        return worldStatus;
    }

    public void setWorldStatus(WorldStatus worldStatus) {
        this.worldStatus = worldStatus;
    }
}
