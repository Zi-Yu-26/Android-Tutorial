package tw.com.sample.chyiiiiiiiiiiii.googlemap;

import com.google.android.gms.maps.model.MapStyleOptions;

public class Test3Activity extends MapActivity {

    @Override
    protected void onMapReady() {
        getMap().setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style2));
    }

}
