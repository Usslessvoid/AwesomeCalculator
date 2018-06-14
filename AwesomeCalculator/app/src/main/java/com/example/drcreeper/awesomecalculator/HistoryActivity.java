package com.example.drcreeper.awesomecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.drcreeper.awesomecalculator.historywriter.HistoryAdapter;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryReader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.history_list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        String[] string = new String[0];
        HistoryReader reader = new HistoryReader();
        reader.setContext(this);
        reader.execute();
        try {
            string = reader.get(2,TimeUnit.SECONDS);
        }catch (InterruptedException | ExecutionException e){
            
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new HistoryAdapter(this,string);
        listView.setAdapter(adapter);
    }
}
