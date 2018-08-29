package tw.com.sample.chyiiiiiiiiiiii.fusedlocationprovider;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TextView tvLon, tvLat, tvCount;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private Location lastLocation;

    private int updateCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tvLon = findViewById(R.id.tvLon);
        tvLat = findViewById(R.id.tvLat);
        tvCount = findViewById(R.id.tvCount);
        //
        initFusedLocationClient();
        initLocationRequest();
        setLocationCallback();
    }

    @SuppressLint("MissingPermission")
    public void initFusedLocationClient() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lastLocation = location;
                }
            }
        });
    }

    private void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(5 * 1000);
        locationRequest.setFastestInterval(2 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void setLocationCallback() {
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                //
                updateCount++;
                for (Location location : locationResult.getLocations()) {
                    Log.i(TAG, location.toString());
                    lastLocation = location;
                    //
                    tvLon.setText("經度 : " + location.getLongitude());
                    tvLat.setText("緯度 : " + location.getLatitude());
                    tvCount.setText("更新次數 : " + updateCount);
                }
            }
        };
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------

    public Location getLastLocation() {
        return lastLocation;
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }


    public void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onStart() {
        super.onStart();
        //
        startLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //
        stopLocationUpdates();
    }

}
