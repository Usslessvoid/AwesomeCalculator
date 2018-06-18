package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drcreeper.awesomecalculator.R;

import java.util.List;

public class HistoryItemAdapter extends ArrayAdapter<String> {
    public HistoryItemAdapter(Context context, List<String> list){
        super(context, R.layout.history_item,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.history_item,parent,false);
        String currentText = getItem(position);
        TextView textView = view.findViewById(R.id.item_text);
        textView.setText(currentText);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),currentText,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
