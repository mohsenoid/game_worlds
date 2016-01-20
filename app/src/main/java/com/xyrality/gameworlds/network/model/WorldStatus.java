package com.xyrality.gameworlds.network.model;

import java.io.Serializable;

/**
 * Created by Mohsen on 1/20/16.
 */
public class WorldStatus implements Serializable {
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
