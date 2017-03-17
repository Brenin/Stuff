package com.lundin_pr.sqlitetesting.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lundin_pr.sqlitetesting.sql.entity.TestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eirikur Lundin on 3/17/2017.
 *
 */

public class SQLiteAdapter {

    public String TABLE     = "test_table";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SQLiteAdapter (Context context) {
        this.context = context;
    }

    public SQLiteAdapter open () {
        SQLiteHelper databaseHelper = new SQLiteHelper(context, TABLE, null, 1);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    //=============================================================================================
    //--Testing------------------------------------------------------------------------------------

    public void createEntity(TestEntity entity){
        ContentValues values = new ContentValues();
        values.put("counter",   entity.getCounter());
        values.put("content",   entity.getContent());
        values.put("time",      entity.getTime());

        sqLiteDatabase.insert(TABLE, null, values);
    }

    public List<TestEntity> getAll(){
        List<TestEntity> entities = new ArrayList<>();

        try(Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE, null)){
            if(cursor.moveToFirst()){
                do {
                    TestEntity entity = new TestEntity();
                    entity.setCounter(cursor.getInt(0));
                    entity.setContent(cursor.getString(1));
                    entity.setTime(cursor.getString(2));

                    entities.add(entity);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return entities;
    }

    public void deleteEntity(TestEntity entity){
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE + " WHERE counter = '" + entity.getCounter() + "'");
    }

    //=============================================================================================
    //--Util---------------------------------------------------------------------------------------

    public void close() {
        sqLiteDatabase.close();
    }
}

