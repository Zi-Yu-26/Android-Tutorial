package tw.com.sample.chyiiiiiiiiiiii.googlemap;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import tw.com.sample.chyiiiiiiiiiiii.googlemap.adapter.MapInfoWindowAdapter;

public class Test2Activity extends MapActivity implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    @Override
    protected void onMapReady() {
        setMap();
        addMarker();
    }

    private void setMap() {
        getMap().setOnMarkerClickListener(this);
        getMap().setOnInfoWindowClickListener(this);
        //
        getMap().setInfoWindowAdapter(new MapInfoWindowAdapter(this));
        //
        moveCamera();
    }

    public void addMarker() {
        ArrayList<LatLng> list = Data.getPositions();
        for (int i = 0; i < list.size(); i++) {
            LatLng latLng = list.get(i);
            //
            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title("Position - " + i);
            options.snippet("Hello - " + i);
            options.alpha(0.9f);
            options.anchor(0.5f, 0.5f);
            options.draggable(false);
            options.flat(false);
            //            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus));
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            //
            getMap().addMarker(options);
        }
    }

    public void moveCamera() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        //
        ArrayList<LatLng> list = Data.getPositions();
        for (LatLng latlng : list) {
            builder.include(latlng);
        }
        //
        LatLngBounds bounds = builder.build();
        //
        getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50), 3000, null);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
    }

}
