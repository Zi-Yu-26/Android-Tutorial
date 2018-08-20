package tw.com.sample.chyiiiiiiiiiiii.updatelocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public static final String TAG = "MainActivity";

    private TextView tvLocation;
    private TextView tvUpdateCount;

    private LocationManager locationManager;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tvLocation = findViewById(R.id.tvLocation);
        tvUpdateCount = findViewById(R.id.tvUpdateCount);
        //
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        //
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged - " + location.toString());
        count++;
        tvLocation.setText("經緯度 : " + location.getLongitude() + " , " + location.getLatitude());
        tvUpdateCount.setText("更新 : " + count);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.i(TAG, "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i(TAG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i(TAG, "onProviderDisabled");
    }

}
