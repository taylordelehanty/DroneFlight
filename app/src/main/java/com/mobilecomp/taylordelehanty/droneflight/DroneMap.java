package com.mobilecomp.taylordelehanty.droneflight;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class DroneMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Context context = this;
    private LatLng currentLocation;
    private LatLng lastLocationLatLng;
    private ArrayList<Marker> markers = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_map);

        //Get location
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            public void onLocationChanged(final Location location) {
                //Toast.makeText(getApplicationContext(), "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                LatLng changedLoc = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(changedLoc, 19.0f));
            }

        };
        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            lastLocationLatLng = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
        } catch (SecurityException e) {
            Toast.makeText(this, "SECURITY ERROR", Toast.LENGTH_SHORT).show();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        markers.clear();
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng location) {

//                mMap.addMarker(new MarkerOptions().position(location));
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(location));
                markers.add(marker);
            }
        });

        LatLng cc = new LatLng(38.8484505, -104.8231503);
        //mMap.addMarker(new MarkerOptions().position(cc).title("Marker at School"));
        try {
            //mMap.addMarker(new MarkerOptions().position(currentLocation).title("Your Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((currentLocation), 19.0f));
        } catch (Exception e) {
            //mMap.addMarker(new MarkerOptions().position(lastLocationLatLng).title("Your Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((lastLocationLatLng), 19.0f));
        }
    }
}
