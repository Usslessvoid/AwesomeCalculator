package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<String> {
    public HistoryAdapter(Context context,String[] strings){
        super(context, R.layout.history_item,strings);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.history_item,parent,false);
        String currentText = getItem(position);
        TextView textView = view.findViewById(R.id.item_text);
        textView.setText(currentText);
        return view;
    }
}