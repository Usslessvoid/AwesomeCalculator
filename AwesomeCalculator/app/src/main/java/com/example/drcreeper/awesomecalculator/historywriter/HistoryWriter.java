package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.HistoryActivity;

public class HistoryWriter extends AsyncTask<String, Void, Void> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HistoryWriterHelper helper = new HistoryWriterHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query = "INSERT INTO " + HistoryDatabaseContract.HISTORY_TABLE +
                "(solve) VALUES ('" +
                strings[0] +
                "');";
        database.execSQL(query);
        return null;
    }
}
