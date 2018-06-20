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
import com.example.drcreeper.awesomecalculator.math.CalculatorHistory;

import java.util.List;

public class CalculatorHistoryAdapter extends ArrayAdapter<CalculatorHistory> {
    public CalculatorHistoryAdapter(Context context, List<CalculatorHistory> list){
        super(context, R.layout.history_item,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_item, parent, false);
            viewHolder = new ViewHolder((TextView) convertView.findViewById(R.id.item_text));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.view.setText(getItem(position).toString());
        return convertView;
    }
}
