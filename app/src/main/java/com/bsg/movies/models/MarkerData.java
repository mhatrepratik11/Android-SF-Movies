package com.bsg.movies.models;

import com.google.android.gms.maps.model.LatLng;

public class MarkerData {

    private double latitude;
    private double longitude;
    private String title;
    private String snippet;
    private int iconResID;
    private LatLng latLng;

    public MarkerData(LatLng latLng, String title, String snippet, int iconResID) {

        this.title = title;
        this.snippet = snippet;
        this.iconResID = iconResID;
        this.latLng = latLng;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getIconResID() {
        return iconResID;
    }

    public void setIconResID(int iconResID) {
        this.iconResID = iconResID;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
