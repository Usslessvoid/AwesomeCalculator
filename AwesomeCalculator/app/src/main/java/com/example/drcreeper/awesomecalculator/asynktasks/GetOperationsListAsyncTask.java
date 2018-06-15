package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetOperationsListAsyncTask extends AsyncTask<Void,Void,String[]> {

    Context context;
    String[] target = new String[0];
    List<String> list = new ArrayList<>();

    public String[] getTarget() {
        return target;
    }

    public void setTarget(String[] target) {
        this.target = target;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        SQLiteOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String query = "SELECT solve FROM history";

        Cursor cursor = database.rawQuery(query,null);
        while (cursor.moveToNext()){
            int id = cursor.getColumnIndex("solve");
            list.add(cursor.getString(id));
        }
        cursor.close();
        return  list.toArray(new String[0]);
    }



}
