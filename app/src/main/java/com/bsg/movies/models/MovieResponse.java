package com.bsg.movies.models;

import java.util.List;

public class MovieResponse extends CommonResponse {

    private List<MoviesInfo> moviesInfoList;

    public MovieResponse(int statusCode, String errorMessage, List<MoviesInfo> moviesInfoList) {
        super(statusCode, errorMessage);
        this.moviesInfoList = moviesInfoList;
    }

    public List<MoviesInfo> getMoviesInfoList() {
        return moviesInfoList;
    }

    public void setMoviesInfoList(List<MoviesInfo> moviesInfoList) {
        this.moviesInfoList = moviesInfoList;
    }
}
