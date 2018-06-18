package com.example.drcreeper.awesomecalculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.activities.HistoryActivity;
import com.example.drcreeper.awesomecalculator.asynktasks.DeleteHistoryListAsyncTask;
import com.example.drcreeper.awesomecalculator.asynktasks.GetOperationsListAsyncTask;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;



public class HistoryListFragment extends Fragment {

    @BindView(R.id.history_list)
    ListView listView;
    List<String> list = new ArrayList<>();
    GetOperationsListAsyncTask reader = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,view);

        reader = new GetOperationsListAsyncTask();
        reader.setContext(view.getContext());
        reader.setCallback((list)->{
            this.list = list;
            ListAdapter adapter = new HistoryItemAdapter(view.getContext(),list);
            listView.setAdapter(adapter);
        });
        reader.execute();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.history_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear_all:
                DeleteHistoryListAsyncTask deleter = new DeleteHistoryListAsyncTask(getActivity());
                deleter.execute();
                break;
        }
        return true;
    }
}
