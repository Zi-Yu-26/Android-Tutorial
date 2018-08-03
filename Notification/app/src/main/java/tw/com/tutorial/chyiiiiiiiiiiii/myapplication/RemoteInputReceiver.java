package tw.com.tutorial.chyiiiiiiiiiiii.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class RemoteInputReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = android.app.RemoteInput.getResultsFromIntent(intent);
            if (bundle!=null) {
            String message = bundle.getCharSequence("data")==null ? "" : bundle.getCharSequence("data").toString();
            Log.i("onReceive", message);
        }
    }

}
