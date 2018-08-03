package tw.com.tutorial.chyiiiiiiiiiiii.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    // 群組(ID必須唯一)
    public static final String GROUP_ID_STORE = "100";
    public static final String GROUP_NAME_STORE = "商店通知";
    // 頻道(ID必須唯一)
    public static final String CHANNEL_ID_FOOD = "101";
    public static final String CHANNEL_NAME_FOOD = "美食快報";
    public static final String CHANNEL_DESCRIPTION_FOOD = "附近店家的新品、優惠";

    private NotificationManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 啟動服務
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 建立群組
        createNotificationGroup(GROUP_ID_STORE, GROUP_NAME_STORE);
        // 建立頻道
        createNotificationChannel(CHANNEL_ID_FOOD, CHANNEL_NAME_FOOD, CHANNEL_DESCRIPTION_FOOD);
        // 發出通知
        sendNotification("Hello", "I am Yii", "How are you?", CHANNEL_ID_FOOD, 1);
        //
//        sendButtonNotification();
//        sendBigTextNotification();
//        sendBigPictureNotification();
//        sendBoxNotification();
//        sendMessagesNotification();
        sendInputNotification();
    }


    /**
     * 建立群組
     *
     * @param groupId
     * @param groupName
     */
    public void createNotificationGroup(String groupId, String groupName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelGroup group = new NotificationChannelGroup(groupId, groupName);
            manager.createNotificationChannelGroup(group);
        }
    }

    /**
     * 建立頻道
     *
     * @param channelId
     * @param channelName
     * @param channelDescription
     */
    public void createNotificationChannel(String channelId, String channelName, String channelDescription) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            // 描述
            channel.setDescription(channelDescription);
            // 開啟指示燈
            channel.enableLights(true);
            // 設置指示燈顏色
            channel.setLightColor(ContextCompat.getColor(this, R.color.colorPrimary));
            // 是否在久按桌面圖示時顯示此管道的通知
            channel.setShowBadge(true);
            // 設置是否應在鎖定螢幕上顯示此頻道的通知
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
            // 設置繞過免打擾模式
            channel.setBypassDnd(true);
            // 設定群組
            channel.setGroup(GROUP_ID_STORE);
            //
            manager.createNotificationChannel(channel);
        }
    }



    //-----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 發出一般通知
     *
     * @param ticker
     * @param title
     * @param content
     * @param channelId
     * @return
     */
    public void sendNotification(String ticker, String title, String content, String channelId, int notifyId) {
        /// 建立通知
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        //
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setColor(ContextCompat.getColor(this, R.color.colorAccent));
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher_foreground));
        builder.setWhen(System.currentTimeMillis());
        // 超時銷毀
//        builder.setTimeoutAfter(10 * 1000);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setOngoing(false);
        // 角標類型
        builder.setBadgeIconType(NotificationCompat.BADGE_ICON_NONE);
        // 訊息數量
//        builder.setNumber(10);
        // 進度條，(3)indeterminate - true模糊、false進度
//        builder.setProgress(100, 50, false);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifyId, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        /// 送出通知
        manager.notify(notifyId, builder.build());
    }

    public NotificationCompat.Builder getSimpleBuilder(String channelId, String title, String content) {
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        return builder;
    }

    /**
     * 有按鈕的通知
     */
    public void sendButtonNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_good, "Yes", null);
        builder.addAction(action);
        //
        manager.notify(1, builder.build());
    }

    /**
     * 多文字的通知
     */
    public void sendBigTextNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("aaa");
        style.bigText("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        style.setSummaryText("ccc");
        builder.setStyle(style);
        //
        manager.notify(2, builder.build());
    }


    /**
     * 大圖片的通知
     */
    public void sendBigPictureNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.android));
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.android));
        builder.setStyle(style);
        //
        manager.notify(3, builder.build());
    }

    /**
     * 列表型的通知
     */
    public void sendBoxNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.addLine("你好");
        style.addLine("我是Yii");
        builder.setStyle(style);
        //
        manager.notify(4, builder.build());
    }

    /**
     * 進階列表型的通知
     */
    public void sendMessagesNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("AAA").setConversationTitle("This is Messaging");
        style.addMessage(new NotificationCompat.MessagingStyle.Message("你好", System.currentTimeMillis(), "Yii"));
        style.addMessage(new NotificationCompat.MessagingStyle.Message("我是阿逸", System.currentTimeMillis(), "Yii"));
        style.addMessage(new NotificationCompat.MessagingStyle.Message("很高興認識你", System.currentTimeMillis(), "Yii"));
        builder.setStyle(style);
        //
        manager.notify(5, builder.build());
    }

    /**
     * 輸入框的通知
     */
    public void sendInputNotification() {
        NotificationCompat.Builder builder = getSimpleBuilder(CHANNEL_ID_FOOD, "20180803", "I am Yii");
        //
        RemoteInput remoteInput = new RemoteInput.Builder("data").setLabel("請輸入").build();
        //
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 6, new Intent(this, RemoteInputReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_music, "Send", pendingIntent).addRemoteInput(remoteInput).build();
        //
        builder.addAction(action);
        //
        manager.notify(6, builder.build());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 檢查通知頻道是否有開啟，沒有就引導使用者打開
     */
    public void checkChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "請手動將通知打開", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 刪除群組
     *
     * @param groupId
     */
    public void deleteGroup(String groupId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.deleteNotificationChannelGroup(groupId);
        }
    }

    /**
     * 刪除頻道
     *
     * @param channelId
     */
    public void deleteChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.deleteNotificationChannel(channelId);
        }
    }

}
