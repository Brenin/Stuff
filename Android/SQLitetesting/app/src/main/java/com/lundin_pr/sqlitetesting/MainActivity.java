package com.lundin_pr.sqlitetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lundin_pr.sqlitetesting.sql.entity.TestEntity;
import com.lundin_pr.sqlitetesting.sql.entity.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener();
        createData();
    }

    private void setListener() {
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData();
            }
        });
    }

    private void createData(){
        TestEntity entity;
        for(int i = 0; i < 2; i++){
            entity = new TestEntity();
            entity.setCounter(i);
            entity.setContent("Testing");
            entity.setTime("2017-03-16");
            Util.createEntity(entity, this);
        }

        for(int i = 2; i < 4; i++){
            entity = new TestEntity();
            entity.setCounter(i);
            entity.setContent("Testing");
            entity.setTime("2017-03-17");
            Util.createEntity(entity, this);
        }

        for(int i = 4; i < 6; i++){
            entity = new TestEntity();
            entity.setCounter(i);
            entity.setContent("Testing");
            entity.setTime("2017-03-18");
            Util.createEntity(entity, this);
        }
         //formatDateToString(new Date())
    }

    private void displayData() {
        List<TestEntity> entities = Util.getAll(this);
        TextView textview = (TextView) findViewById(R.id.textview);

        String string = "";
        int items = 0;

        for(TestEntity entity : entities){
            if(compareDates(entity.getTime()) > 0){
                string += "Entity deleted\n";
                Util.deleteEntity(entity, this);
                items++;
            } else {
                string += entity.getContent() + " " + entity.getTime() + " " + entity.getCounter() + "\n";
            }
        }

        String length = "\nNumber of entries: " + entities.size();
        String deleted = "\nEntries deleted: " + items;

        textview.setText(string + length + deleted);
    }

    private String formatDateToString(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private int compareDates(String date1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        Date date2 = null;
        try {
            today = sdf.parse(formatDateToString(new Date()));
            date2 = sdf.parse(date1);
            Log.d("DATE", "Current date" + today);
            Log.d("DATE", "Control date" + date2);

            return today.compareTo(date2);
/*            if(today.compareTo(date2) > 0){
                Log.d("Result", "Today is after date2");
                return -1;
            } else if(today.compareTo(date2) < 0){
                Log.d("Result", "Today is before date2");
                return 1;
            } else if(today.compareTo(date2) == 0){
                Log.d("Result", "Today is equal to date2");
                return 0;
            } else {
                Log.d("Result", "Somethings wrong");
            }*/
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -5;
    }
}
