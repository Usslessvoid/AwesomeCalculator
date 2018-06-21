package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class HistoryDatabaseOpenHelper extends SQLiteOpenHelper {

    public HistoryDatabaseOpenHelper(Context context){
        super(context, HistoryDatabaseScheme.DB_NAME,null, HistoryDatabaseScheme.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE history (_id INTEGER PRIMARY KEY AUTOINCREMENT, solve STRING);");
        try {


            db.execSQL("CREATE TABLE history(_id INTEGER PRIMARY KEY AUTOINCREMENT , first_operand REAL , second_operand REAL , operator STRING , result REAL);");
        }catch (SQLException e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
