package tw.com.sample.chyiiiiiiiiiiii.googlemap.activity;

import android.annotation.SuppressLint;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

import tw.com.sample.chyiiiiiiiiiiii.googlemap.Data;

public class Test1Activity extends MapActivity {

    @Override
    protected void onMapReady() {
        setMap();
    }

    /**
     * setMapType - 5種
     *  Normal：典型的地圖
     *  Hybrid：混合衛星照片及道路地圖
     *  Satellite：衛星照片
     *  Terrain：地形圖
     *  None：什麼都沒有
     */
    @SuppressLint("MissingPermission")
    private void setMap() {
        getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
        getMap().setMyLocationEnabled(true);
        //
        moveCamera();
    }

    public void moveCamera() {
        getMap().moveCamera(CameraUpdateFactory.newLatLng(Data.POSITION_TAIPEI101));
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(Data.POSITION_TAIPEI_TRAIN_STATION, 12));
        getMap().animateCamera(CameraUpdateFactory.zoomTo(8));
        //
        CameraPosition cameraPosition = new CameraPosition.Builder().target(Data.POSITION_KENTING).bearing(120).tilt(30).zoom(13).build();
        getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
