package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryWriterHelper extends SQLiteOpenHelper {

    public HistoryWriterHelper(Context context){
        super(context,HistoryDatabaseContract.DB_NAME,null,HistoryDatabaseContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + HistoryDatabaseContract.HISTORY_TABLE
                + "(id integer primary key autoincrement, solve string);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
