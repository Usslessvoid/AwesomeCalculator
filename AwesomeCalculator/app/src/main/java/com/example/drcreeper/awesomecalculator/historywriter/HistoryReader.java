package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class HistoryReader extends AsyncTask<Void,Void,String[]> {

    Context context;
    String[] result;

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        SQLiteOpenHelper helper = new HistoryWriterHelper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String query = "SELECT solve FROM " +
                HistoryDatabaseContract.HISTORY_TABLE + ";";
        Cursor cursor = database.rawQuery(query,null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getColumnIndex("solve");
            list.add(cursor.getString(id));
        }
        result = (String[]) list.toArray();
        return result;
    }
}
