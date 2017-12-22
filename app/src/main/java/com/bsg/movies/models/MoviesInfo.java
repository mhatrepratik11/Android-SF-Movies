package com.bsg.movies.models;

import com.google.android.gms.maps.model.LatLng;

public class MoviesInfo {

    private String actor_1;
    private String actor_2;
    private String actor_3;
    private String director;
    private String distributor;
    private String fun_facts;
    private String locations;
    private String production_company;
    private String release_year;
    private String title;
    private String writer;
    private double latitude;
    private double longitude;
    private LatLng latLng;
    private String city;

    public MoviesInfo(String actor_1, String actor_2, String actor_3, String director, String distributor, String fun_facts,
                      String locations, String production_company, String release_year, String title, String writer, LatLng latLng,
                      double latitude, double longitude, String city) {
        this.actor_1 = actor_1;
        this.actor_2 = actor_2;
        this.actor_3 = actor_3;
        this.director = director;
        this.distributor = distributor;
        this.fun_facts = fun_facts;
        this.locations = locations;
        this.production_company = production_company;
        this.release_year = release_year;
        this.title = title;
        this.writer = writer;
        this.latLng = latLng;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public String getActor_1() {
        return actor_1;
    }

    public void setActor_1(String actor_1) {
        this.actor_1 = actor_1;
    }

    public String getActor_2() {
        return actor_2;
    }

    public void setActor_2(String actor_2) {
        this.actor_2 = actor_2;
    }

    public String getActor_3() {
        return actor_3;
    }

    public void setActor_3(String actor_3) {
        this.actor_3 = actor_3;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getProduction_company() {
        return production_company;
    }

    public void setProduction_company(String production_company) {
        this.production_company = production_company;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getFun_facts() {
        return fun_facts;
    }

    public void setFun_facts(String fun_facts) {
        this.fun_facts = fun_facts;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
