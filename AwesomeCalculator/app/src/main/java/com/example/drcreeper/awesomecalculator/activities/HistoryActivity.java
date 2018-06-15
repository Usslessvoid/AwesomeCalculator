package com.example.drcreeper.awesomecalculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryItemAdapter;
import com.example.drcreeper.awesomecalculator.asynktasks.GetOperationsListAsyncTask;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.history_list)
    ListView listView;
    public String[] string = new String[0];
    GetOperationsListAsyncTask reader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        reader = new GetOperationsListAsyncTask();
        reader.setContext(this);
        reader.setTarget(string);
        reader.execute();
        try {
            string = reader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new HistoryItemAdapter(this,string);
        listView.setAdapter(adapter);
    }

}
