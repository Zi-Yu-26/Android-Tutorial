package tw.com.sample.chyiiiiiiiiiiii.googlemap.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import tw.com.sample.chyiiiiiiiiiiii.googlemap.R;

/**
 * Created by Yi on 2017/2/8.
 */

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public MapInfoWindowAdapter(Context context) {
        this.context = context;
    }

    /**
     * 自訂整個資訊視窗的view也就是視窗的外觀及背景，如果回傳是null就會呼叫 getInfoContents 方法
     */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 自訂顯示內容，如果回傳是null就會使用預設的資訊視窗
     */
    @Override
    public View getInfoContents(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_infowindow_map, null, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        //
        tvTitle.setText(marker.getTitle());
        tvMessage.setText(marker.getSnippet());
        //
        return view;
    }

}
