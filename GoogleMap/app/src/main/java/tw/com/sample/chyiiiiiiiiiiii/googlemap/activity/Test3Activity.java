package tw.com.sample.chyiiiiiiiiiiii.googlemap.activity;

import com.google.android.gms.maps.model.MapStyleOptions;

import tw.com.sample.chyiiiiiiiiiiii.googlemap.R;

public class Test3Activity extends MapActivity {

    @Override
    protected void onMapReady() {
        getMap().setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style3));
    }

}
