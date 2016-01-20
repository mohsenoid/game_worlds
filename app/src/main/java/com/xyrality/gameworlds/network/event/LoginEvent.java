package com.xyrality.gameworlds.network.event;

import com.xyrality.gameworlds.network.model.WorldsResponse;

import java.io.Serializable;

/**
 * Created by Mohsen on 1/20/16.
 */
public class LoginEvent {
    boolean success;
    WorldsResponse worldsResponse;
    Throwable throwable;

    public LoginEvent(boolean success, WorldsResponse worldsResponse) {
        this.success = success;
        this.worldsResponse = worldsResponse;
    }

    public LoginEvent(Throwable throwable) {
        success = false;
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public WorldsResponse getWorldsResponse() {
        return worldsResponse;
    }

    public void setWorldsResponse(WorldsResponse worldsResponse) {
        this.worldsResponse = worldsResponse;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
