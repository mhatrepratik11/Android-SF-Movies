package com.bsg.movies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bsg.movies.R;
import com.bsg.movies.api.ApiService;
import com.bsg.movies.api.ApiUrlConstants;
import com.bsg.movies.models.MovieResponse;
import com.bsg.movies.models.MoviesInfo;
import com.bsg.movies.utils.LogUtils;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private Retrofit retrofit = null;
    ApiService apiService;
    static List<MoviesInfo> moviesInfoList;
    static List<MoviesInfo> sanFranciscoMoviesList;
    FloatingActionButton fab;
    Button BT_getSanMovies, BT_getAllMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        BT_getAllMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovieApiData();
            }
        });

        BT_getSanMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSanMoviesData();
            }
        });
    }

    public void initialize() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        BT_getSanMovies = (Button) findViewById(R.id.BT_getSanMovies);
        BT_getAllMovies = (Button) findViewById(R.id.BT_getAllMovies);
    }

    public static List<MoviesInfo> getMovieData() {

        return moviesInfoList;
    }

    public static List<MoviesInfo> getSanMovieList() {

        return sanFranciscoMoviesList;
    }

    public void getSanMoviesData() {

        LogUtils.e("fetching san movie data");

        if (isNetworkAvailable()) {

            loading(MainActivity.this, "loading ...");

            LogUtils.e("network available");
            apiService = getClient(ApiUrlConstants.BASE_URL).create(ApiService.class);
            apiService.getMoviesFromSanFransisco().enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                    pDialog.dismiss();

                    if (response.code() == 200) {
                        final MovieResponse movieResponse = response.body();

                        if (movieResponse.getStatusCode() == 1) {
                            sanFranciscoMoviesList = new ArrayList<>();
                            sanFranciscoMoviesList.addAll(movieResponse.getMoviesInfoList());
                            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                            intent.putExtra("sanIntent", "SAN_MOVIE");
                            startActivity(intent);
                            Log.e("MOVIE", "List size is : " + String.valueOf(sanFranciscoMoviesList.size()));
                        } else {
                            pDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error : " + movieResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                    pDialog.dismiss();
                    if(t instanceof SocketTimeoutException){
                        LogUtils.e("TIMEOUT for Report submission call");
                    }
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "Connect to internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void getMovieApiData() {

        LogUtils.e("fetching movie data");

        if (isNetworkAvailable()) {

            loading(MainActivity.this, "loading ...");

            LogUtils.e("network available");
            apiService = getClient(ApiUrlConstants.BASE_URL).create(ApiService.class);
            apiService.getAllMoviesList().enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                    pDialog.dismiss();

                    if (response.code() == 200) {
                        final MovieResponse movieResponse = response.body();

                        if (movieResponse.getStatusCode() == 1) {
                            moviesInfoList = new ArrayList<>();
                            moviesInfoList.addAll(movieResponse.getMoviesInfoList());
                            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                            intent.putExtra("sanIntent", "ALL_MOVIE");
                            startActivity(intent);
                            Log.e("MOVIE", "List size is : " + String.valueOf(moviesInfoList.size()));
                        } else {
                            pDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error : " + movieResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                    pDialog.dismiss();
                    if(t instanceof SocketTimeoutException){
                        LogUtils.e("TIMEOUT for Report submission call");
                    }
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "Connect to internet", Toast.LENGTH_SHORT).show();
        }
    }

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build();

    public Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
