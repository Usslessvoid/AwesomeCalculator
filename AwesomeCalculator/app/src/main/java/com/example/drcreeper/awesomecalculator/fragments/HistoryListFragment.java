package com.example.drcreeper.awesomecalculator.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.drcreeper.awesomecalculator.R;
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
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,view);

        reader = new GetOperationsListAsyncTask();
        reader.setContext(view.getContext());
        reader.execute();


        try {
            list = reader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new HistoryItemAdapter(view.getContext(),list);
        listView.setAdapter(adapter);
        return view;
    }

}
