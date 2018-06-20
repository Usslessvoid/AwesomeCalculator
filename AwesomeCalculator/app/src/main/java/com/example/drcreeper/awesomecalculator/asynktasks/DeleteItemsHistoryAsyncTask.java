package com.example.drcreeper.awesomecalculator.asynktasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseOpenHelper;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryDatabaseScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteItemsHistoryAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Callback callback;
    private List<Integer> input;

    public DeleteItemsHistoryAsyncTask() {
    }

    public DeleteItemsHistoryAsyncTask(Context context) {
        this.context = context;
    }

    public DeleteItemsHistoryAsyncTask(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }
    public List<Integer> getInput() {
        return input;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        HistoryDatabaseOpenHelper helper = new HistoryDatabaseOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor list = db.rawQuery("SELECT _id FROM history", null);
        List<Integer> ids = new ArrayList<>();
        while (list.moveToNext()){
            ids.add(list.getInt(list.getColumnIndex("_id")));
        }
        list.close();


        db = helper.getWritableDatabase();
        for(int i:input){
            int target = ids.get(i);
            db.beginTransaction();
            db.execSQL("DELETE FROM " + HistoryDatabaseScheme.HISTORY_TABLE + " WHERE " + HistoryDatabaseScheme.HISTORY_ID+
            " = " + Integer.toString(target) + ";");
            db.setTransactionSuccessful();
            db.endTransaction();
        }/**/
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.onComplete();
    }
}
