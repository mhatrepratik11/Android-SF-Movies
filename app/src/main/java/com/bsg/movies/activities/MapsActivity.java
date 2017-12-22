package com.bsg.movies.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import com.bsg.movies.R;
import com.bsg.movies.models.MoviesInfo;
import com.bsg.movies.utils.LogUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<MoviesInfo> moviesInfoList;
    private List<MoviesInfo> filteredMoviesInfoList;
    AutoCompleteTextView autoComplete;
    TextView TV_map;
    List<Marker> markerList;
    private String[] locations;
    String sanIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerList = new ArrayList<>();
        filteredMoviesInfoList = new ArrayList<>();
        initializeView();

        sanIntent = getIntent().getStringExtra("sanIntent");

        if (sanIntent.equals("ALL_MOVIE"))
            moviesInfoList = MainActivity.getMovieData();
        else
            moviesInfoList = MainActivity.getSanMovieList();

        filteredMoviesInfoList.addAll(moviesInfoList);
        locations = new String[filteredMoviesInfoList.size()];

        for (int i = 0; i < filteredMoviesInfoList.size(); i++) {
            locations[i] = filteredMoviesInfoList.get(i).getLocations();
            LogUtils.e("Locations : " + locations[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
        autoComplete.setAdapter(adapter);

        TV_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredText = autoComplete.getText().toString();
                filteredMoviesInfoList = new ArrayList<>();
                LogUtils.e("Filtered list sie is : " + filteredMoviesInfoList.size());

                if (enteredText.isEmpty()) {
                    filteredMoviesInfoList.addAll(moviesInfoList);
                } else {
                    for (MoviesInfo moviesInfo : moviesInfoList) {
                        if (!enteredText.isEmpty() && moviesInfo.getLocations() != null && !moviesInfo.getLocations().isEmpty()) {
                            String locations = moviesInfo.getLocations();
                            if (locations.contains(enteredText)) {
                                LogUtils.e("Added");
                                filteredMoviesInfoList.add(moviesInfo);
                            }
                        }
                    }
                }
                LogUtils.e("Filtered after list size is : " + filteredMoviesInfoList.size());
                mMap.clear();
                markerList.clear();
                onMapReady(mMap);
            }
        });
    }

    public void initializeView() {
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        TV_map = (TextView) findViewById(R.id.TV_map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LogUtils.e("Called" + filteredMoviesInfoList.size());
        addMarkers(filteredMoviesInfoList);
        setMapFocus();
    }

    public void addMarkers(List<MoviesInfo> movies) {
        for (MoviesInfo moviesInfo : movies) {
            createMarker(moviesInfo.getLatitude(), moviesInfo.getLongitude(), moviesInfo.getTitle());
        }
    }

    public void setMapFocus() {

        if (markerList.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker m : markerList) {
                builder.include(m.getPosition());
            }
            LatLngBounds bounds = builder.build();
            int padding = 20; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
        }
    }

    public void createMarker(double lat, double lng, String title) {

        LatLng latLng = new LatLng(lat, lng);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title));

        markerList.add(marker);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(mMap != null) {
//            mMap.clear();
//        }
    }
}
