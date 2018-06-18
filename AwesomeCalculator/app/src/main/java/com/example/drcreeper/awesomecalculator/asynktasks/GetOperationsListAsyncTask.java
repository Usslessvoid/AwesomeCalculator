package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GetOperationsListAsyncTask extends AsyncTask<Void,Void,List<String>> {

    private Context context;
    AfterExecute callback;

    public AfterExecute getCallback() {
        return callback;
    }

    public void setCallback(AfterExecute callback) {
        this.callback = callback;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        List<String> list = new ArrayList<>();
        SQLiteOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String query = "SELECT solve FROM history";
        Cursor cursor = database.rawQuery(query,null);
        while (cursor.moveToNext()){
            int id = cursor.getColumnIndex("solve");
            list.add(cursor.getString(id));
        }
        cursor.close();
        return  list;
    }

    @Override
    protected void onPostExecute(List<String> list) {
        callback.setAfterQuery(list);
    }
}
