package com.lundin_pr.fcmtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getIntent().getStringExtra("title");
        String msg = getIntent().getStringExtra("msg");
        String url = getIntent().getStringExtra("url");

        textView = (TextView)findViewById(R.id.textView1);
        textView.setText(title + "\n" + msg + "\n" + "More info at: \n" + url);
    }
}
