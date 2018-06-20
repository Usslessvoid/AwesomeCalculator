package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drcreeper.awesomecalculator.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemAdapter extends ArrayAdapter<String> {

    private List<Integer> pos = new ArrayList<>();

    public List<Integer> getPos() {
        return pos;
    }

    public void setPos(List<Integer> pos) {
        this.pos = pos;
    }

    private int tmpId;
    public HistoryItemAdapter(Context context,List<String> list){
        super(context, R.layout.history_item,list);
        tmpId = R.layout.history_item;
    }

    public HistoryItemAdapter(Context context,int id,List<String> list){
        super(context, R.layout.history_item,list);
        tmpId = id;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(tmpId, parent, false);
            viewHolder = new ViewHolder((TextView) convertView.findViewById(R.id.item_text));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.view.setText(getItem(position));
        if(tmpId == R.layout.history_item_selectable){ //maybe not best solution
            View finalConvertView = convertView;
            convertView.setOnClickListener((d)->{
                CheckBox c = (CheckBox)finalConvertView.findViewById(R.id.checkBox);
                c.setChecked(!c.isChecked());
                Integer i = new Integer(position);
                if(pos.contains(i)){
                    pos.remove(i);
                }else {
                    pos.add(i);
                }

            });
        }
/*
        */
        //convertView.findViewById(R.id.checkBox).setVisibility(View.VISIBLE);
        return convertView;
    }
}
