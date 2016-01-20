package com.xyrality.gameworlds.network.model;

import java.io.Serializable;

/**
 * Created by Mohsen on 1/20/16.
 */
public class WorldsResponse implements Serializable {
    private String facebookLoginSwitchOn;
    private String time;
    private World[] allAvailableWorlds;
    private String serverVersion;
    private String featureHelpshift;
    private String googleLoginSwitchOn;
    private String info;

    public String getFacebookLoginSwitchOn() {
        return facebookLoginSwitchOn;
    }

    public void setFacebookLoginSwitchOn(String facebookLoginSwitchOn) {
        this.facebookLoginSwitchOn = facebookLoginSwitchOn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public World[] getAllAvailableWorlds() {
        return allAvailableWorlds;
    }

    public void setAllAvailableWorlds(World[] allAvailableWorlds) {
        this.allAvailableWorlds = allAvailableWorlds;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getFeatureHelpshift() {
        return featureHelpshift;
    }

    public void setFeatureHelpshift(String featureHelpshift) {
        this.featureHelpshift = featureHelpshift;
    }

    public String getGoogleLoginSwitchOn() {
        return googleLoginSwitchOn;
    }

    public void setGoogleLoginSwitchOn(String googleLoginSwitchOn) {
        this.googleLoginSwitchOn = googleLoginSwitchOn;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
