package com.lundin_pr.sqlitetesting.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eirikur Lundin on 3/17/2017.
 *
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String SCRIPT_CREATE_DB = "CREATE TABLE IF NOT EXISTS test_table ("
            + "counter integer not null, "
            + "content text not null, "
            + "time text not null)";

    SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCRIPT_CREATE_DB);

        onCreate(db);
    }
}
