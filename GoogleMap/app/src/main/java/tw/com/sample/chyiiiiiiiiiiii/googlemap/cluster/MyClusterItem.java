package tw.com.sample.chyiiiiiiiiiiii.googlemap.cluster;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyClusterItem implements ClusterItem {

    private String title;
    private String snippet;
    private LatLng latLng;
    private int type;

    public MyClusterItem(String title, String snippet, LatLng latLng, int type) {
        this.title = title;
        this.snippet = snippet;
        this.latLng = latLng;
        this.type = type;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }

    public int getType() {
        return type;
    }

}
