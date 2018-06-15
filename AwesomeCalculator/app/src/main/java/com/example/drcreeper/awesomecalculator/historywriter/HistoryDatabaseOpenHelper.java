package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabaseOpenHelper extends SQLiteOpenHelper {

    public HistoryDatabaseOpenHelper(Context context){
        super(context, HistoryDatabaseScheme.DB_NAME,null, HistoryDatabaseScheme.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE history (_id INTEGER PRIMARY KEY AUTOINCREMENT, solve STRING);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
