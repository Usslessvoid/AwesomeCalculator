package com.example.drcreeper.awesomecalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.asynktasks.DeleteHistoryListAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.open_history:
                Intent intent = new Intent(this,HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_history:
                DeleteHistoryListAsyncTask deleter = new DeleteHistoryListAsyncTask(this);
                deleter.execute();
        }
        return true;
    }
}
