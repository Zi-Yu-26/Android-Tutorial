package tw.com.sample.chyiiiiiiiiiiii.googlemap.activity;


import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import tw.com.sample.chyiiiiiiiiiiii.googlemap.R;
import tw.com.sample.chyiiiiiiiiiiii.googlemap.cluster.CustomClusterRenderer;
import tw.com.sample.chyiiiiiiiiiiii.googlemap.cluster.CustomInfoViewAdapter;
import tw.com.sample.chyiiiiiiiiiiii.googlemap.cluster.MyClusterItem;

public class Test4Activity extends MapActivity {

    private ClusterManager<MyClusterItem> mClusterManager;

    private LatLng baseLocation = new LatLng(-34, 151);

    @Override
    protected void onMapReady() {
        getMap().setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style2));
        //
        initClusterManager();
        addClusterItem();
        //
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(baseLocation, 1));
    }

    /**
     * 1.新增ClusterManager以根據縮放層級來將群集項目(標記)分組
     */
    public void initClusterManager() {
        mClusterManager = new ClusterManager<>(this, getMap());
        ////// 設定Item的外觀
        CustomClusterRenderer renderer = new CustomClusterRenderer(this, getMap(), mClusterManager);
        mClusterManager.setRenderer(renderer);
        ////// 設定傾聽事件
        // 點擊群集
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyClusterItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyClusterItem> cluster) {
                Toast.makeText(Test4Activity.this, "Cluster click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // 點擊群集裡的項目
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyClusterItem>() {
            @Override
            public boolean onClusterItemClick(MyClusterItem clusterItem) {
                Toast.makeText(Test4Activity.this, "Cluster item click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // 點擊項目的訊息框
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new CustomInfoViewAdapter(LayoutInflater.from(this)));
        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyClusterItem>() {
            @Override
            public void onClusterItemInfoWindowClick(MyClusterItem clusterItem) {
                Toast.makeText(Test4Activity.this, "Clicked info window: " + clusterItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        ////// 地圖的事件放入ClusterManager
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setInfoWindowAdapter(mClusterManager.getMarkerManager());
        getMap().setOnInfoWindowClickListener(mClusterManager);
    }

    /**
     * 向ClusterManager提供ClusterItem標記
     */
    public void addClusterItem() {
        for (int i = 0; i < 10; i++) {
            LatLng latLng = new LatLng(baseLocation.latitude + i, baseLocation.longitude + i);
            mClusterManager.addItem(new MyClusterItem("Marker #" + (i + 1), (i + 1) + "", latLng, 1));
        }
        mClusterManager.cluster();
    }

}
