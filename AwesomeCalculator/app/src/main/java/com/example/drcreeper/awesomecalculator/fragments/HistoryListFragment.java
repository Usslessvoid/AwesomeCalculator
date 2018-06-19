package com.example.drcreeper.awesomecalculator.fragments;

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
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.asynktasks.AfterExecute;
import com.example.drcreeper.awesomecalculator.asynktasks.DeleteHistoryListAsyncTask;
import com.example.drcreeper.awesomecalculator.asynktasks.GetOperationsListAsyncTask;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class HistoryListFragment extends Fragment {

    @BindView(R.id.history_list)
    ListView listView;
    @BindView(R.id.empty_text)
    TextView emptyText;
    List<String> list = new ArrayList<>();
    GetOperationsListAsyncTask reader = null;
    ListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,view);
        listView.setEmptyView(emptyText);
        reader = new GetOperationsListAsyncTask();
        reader.setContext(view.getContext());
        reader.setCallback((list)->{
            this.list = list;
            adapter = new HistoryItemAdapter(view.getContext(),list);
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
            case R.id.choose_history:
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.setAdapter(adapter);
                break;
            case R.id.clear_all:
                DeleteHistoryListAsyncTask deleter = new DeleteHistoryListAsyncTask(getActivity());
                deleter.setOnCompleteListener(()->{
                    list.clear();
                    adapter = new HistoryItemAdapter(getContext(),list);
                    listView.setAdapter(adapter);
                });
                deleter.execute();
                break;
        }
        return true;
    }
}
