package com.lundin_pr.sqlitetesting.sql.entity;

import android.content.Context;
import android.content.Entity;

import com.lundin_pr.sqlitetesting.sql.SQLiteAdapter;

import java.util.List;

/**
 * Created by Eirikur Lundin on 3/17/2017.
 *
 */

public class Util {
    public static List<TestEntity> getAll(Context context){
        List<TestEntity> list;

        SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(context);
        sqLiteAdapter.open();
        list = sqLiteAdapter.getAll();
        sqLiteAdapter.close();

        return list;
    }

    public static void createEntity(TestEntity entity, Context context){
        SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(context);
        sqLiteAdapter.open();
        sqLiteAdapter.createEntity(entity);
        sqLiteAdapter.close();
    }

    public static void deleteEntity(TestEntity entity, Context context){
        SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(context);
        sqLiteAdapter.open();
        sqLiteAdapter.deleteEntity(entity);
        sqLiteAdapter.close();
    }
}
