package net.kwmt27.supportdesignsample;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BottomNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView mSmallLabel;
    private TextView mLargeLabel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
//                    setChecked(true);

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    setChecked(false);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_2:
                    mTextMessage.setText("4");
                    return true;
                case R.id.navigation_3:
                    mTextMessage.setText("5");
                    return true;
            }
            return false;
        }

    };
    private int mDefaultMargin = 24;
    private int mShiftAmount = -8;
    private float mScaleUpFactor = 1.6f;
    private float mScaleDownFactor = 0.625f;
    private ImageView mIcon;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        layout = (RelativeLayout)findViewById(R.id.content);
        mTextMessage = (TextView) findViewById(R.id.message);
//        mSmallLabel = (TextView) findViewById(R.id.smallLabel);
//        mLargeLabel = (TextView) findViewById(R.id.largeLabel);
//        mIcon = (ImageView) findViewById(R.id.icon);

        ((Button)findViewById(R.id.snackButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(layout, "テスト", Snackbar.LENGTH_LONG).show();
                sendNotification("ハロ", "http://kwmt27.net/", "push");
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void sendNotification(String message, String url, String type) {
        Intent intent = new Intent(this, BottomNavigationActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        String appName = getResources().getString(R.string.app_name);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


//        String imageUrl = "https://lh3.googleusercontent.com/ib1nysLhxuxrpiHokdVbB-SGp6pR-DFuDrVn8tNTrxwUtHLX3-8kicJp4nBICAi6oDBD0d6zaSP332lgU9c__Inn7RYU45xIQPlF3jdSELnyin-Q-F7lYgt3zizQzpbfLTrh12kp1QpbwF3smwzKprFL15-DjoRZYaFLUnCgbpTw4wgS8bTp3eZ9ioigOEyQOBEY7ygSB4dUmsf84zlNIDzZuEANH2e_rVt1lKF_Hd85RzRcqbnf08YB2Lz8GgmRUnfWQkGewCjpn6tNJlNFr4v9Ksd3hCFUCLeOFFRMXRcP2JtJIraRdRuKle9AvB658mcTj6-f_u_PpOEkBA_WFRUsgkF7lI540voJkvcCkIqF8lmq9QLRTzyONN8ebUAbBoGwHx6TV4LIIBsPoIyKLOX1xzE8TxP56xQqV4_6FMOQ4IBIv2JMWmZlr53vCLrSnpttdBuuYGDiq_HDK_RTbdwJYn9SV_a48MlQqbdCWBkUlbYhjgZWiFJ1s4TauNG3MD3Dk1o2a-WKMOLCqDZqbTqSqu9-zk6slRFIFkH013a3Eu7pB5_tuIG2QekCv9KYQESb5Nu4_TbVysJGp8sCbqJTsvIDisEORHPDZjogqlykXNM=w2414-h1810-no";
//        Glide.with(this).asBitmap().load(imageUrl).

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(appName)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setTicker(appName + ":" + message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getBitmapFromURL()))

//                .setSound(defaultSoundUri)

                .setContentIntent(pi)
                .setAutoCancel(true); // タップするとキャンセル(消える);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle(appName);
        bigTextStyle.bigText(message);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, bigTextStyle.build());
    }

    private void setChecked(boolean checked) {
//        mLargeLabel.setPivotX(mLargeLabel.getWidth() / 2);
//        mLargeLabel.setPivotY(mLargeLabel.getBaseline());
//        mSmallLabel.setPivotX(mSmallLabel.getWidth() / 2);
//        mSmallLabel.setPivotY(mSmallLabel.getBaseline());
//
//        if (checked) {
//            FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) mIcon.getLayoutParams();
//            iconParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
//            iconParams.topMargin = mDefaultMargin + mShiftAmount;
//            mIcon.setLayoutParams(iconParams);
//            mLargeLabel.setVisibility(View.VISIBLE);
//            mSmallLabel.setVisibility(View.INVISIBLE);
//
//            mLargeLabel.setScaleX(1f);
//            mLargeLabel.setScaleY(1f);
//            mSmallLabel.setScaleX(mScaleUpFactor);
//            mSmallLabel.setScaleY(mScaleUpFactor);
//        } else {
//            FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) mIcon.getLayoutParams();
//            iconParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
//            iconParams.topMargin = mDefaultMargin;
//            mIcon.setLayoutParams(iconParams);
//            mLargeLabel.setVisibility(View.INVISIBLE);
//            mSmallLabel.setVisibility(View.VISIBLE);
//
//            mLargeLabel.setScaleX(mScaleDownFactor);
//            mLargeLabel.setScaleY(mScaleDownFactor);
//            mSmallLabel.setScaleX(1f);
//            mSmallLabel.setScaleY(1f);
//        }
//        TransitionManager.beginDelayedTransition(content);

    }

}
