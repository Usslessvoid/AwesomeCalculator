package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;

public class SaveOperationToHistoryAsyncTask extends AsyncTask<String, Void, Void> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HistoryDatabaseOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query = "INSERT INTO " + HistoryDatabaseScheme.HISTORY_TABLE +
                "(solve) VALUES ('" +
                strings[0] +
                "');";
        database.execSQL(query);
        return null;
    }
}
