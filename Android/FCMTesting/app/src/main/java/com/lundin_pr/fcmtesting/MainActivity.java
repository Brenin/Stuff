package com.lundin_pr.fcmtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

/*        String body = getIntent().getStringExtra("note");

        textView = (TextView)findViewById(R.id.textView1);
        textView.setText(body);*/
    }
}
