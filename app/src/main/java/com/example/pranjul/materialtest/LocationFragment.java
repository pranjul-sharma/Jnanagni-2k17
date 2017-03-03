package com.example.pranjul.materialtest;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.drive.Drive;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationFragment extends Fragment implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener,
LocationListener{
    //private GoogleMap googleMap;
    private MapView mapView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static GoogleMap map;
    private LatLng FET;
    private LocationManager locationManager;
    private TextView toGo;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(HomeActivity.currObject)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        toGo=(TextView)rootView.findViewById(R.id.toGo);
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        FET = new LatLng(29.918666, 78.064041);
        googleMap.addMarker(new MarkerOptions().position(FET).title("FET GKV").snippet("Jnanagni 2017, your destination"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(FET).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setMyLocationEnabled(true);
        //Log.e("What the", "is happening");
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        GoogleMapsPath mapsPath;
            float[] results = new float[1];
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation == null) {
                Toast.makeText(HomeActivity.currObject, "Enable location services", Toast.LENGTH_SHORT).show();
            } else {
                Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                        FET.latitude, FET.longitude, results);
                if (results[0] < 1000)
                    toGo.setText(results[0] + " meters to go");
                else
                    toGo.setText(results[0] / 1000 + " KM to go");
                ConnectivityManager cm = (ConnectivityManager) HomeActivity.currObject.getSystemService(Context.CONNECTIVITY_SERVICE);
                locationManager = (LocationManager) HomeActivity.currObject.getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                        || cm.getActiveNetworkInfo() == null)
                    Toast.makeText(HomeActivity.currObject, "Please enable location service", Toast.LENGTH_SHORT).show();
                else
                    mapsPath = new GoogleMapsPath(HomeActivity.currObject, map, new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), FET);
            }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onLocationChanged(Location location) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude()));
        map.moveCamera(center);
        float[] results = new float[1];
        Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                FET.latitude, FET.longitude, results);
        if(results[0]<1000)
            toGo.setText(results[0]+" meters to go");
        else
            toGo.setText(results[0]/1000+" KM to go");
    }
}
