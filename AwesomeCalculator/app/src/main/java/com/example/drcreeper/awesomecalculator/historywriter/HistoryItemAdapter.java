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

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemAdapter extends ArrayAdapter<CalculatorHistory> {

    private List<Integer> pos = new ArrayList<>();

    public List<Integer> getPos() {
        return pos;
    }

    public void setPos(List<Integer> pos) {
        this.pos = pos;
    }

    private int tmpId;

    public HistoryItemAdapter(Context context,List<CalculatorHistory> list){
        super(context, R.layout.history_item,list);
        tmpId = R.layout.history_item;
    }

    public HistoryItemAdapter(Context context,int id,List<CalculatorHistory> list){
        super(context, R.layout.history_item,list);
        tmpId = id;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(tmpId,parent,false);
        CalculatorHistory history = getItem(position);
        TextView firstOperand = (TextView)view.findViewById(R.id.first_operand_text);
        firstOperand.setText(Double.toString(history.getFirstOperand()));
        TextView secondOperand = (TextView)view.findViewById(R.id.second_operand_text);
        secondOperand.setText(Double.toString(history.getSecondOperand()));
        TextView operator = (TextView)view.findViewById(R.id.operator_text);
        operator.setText(history.getOperatorSymbol());
        TextView result = (TextView)view.findViewById(R.id.result_text);
        result.setText(Double.toString(history.getResult()));
/*
   IT"S NOT TIME FOR VIEW HOLDER))))))
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(tmpId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.setFirstOperand((TextView)parent.findViewById(R.id.first_operand_text));
            viewHolder.setSecondOperand((TextView)parent.findViewById(R.id.second_operand_text));
            viewHolder.setOperator((TextView)parent.findViewById(R.id.operator_text));
            viewHolder.setOperator((TextView)parent.findViewById(R.id.result_text));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        CalculatorHistory history = getItem(position);
        viewHolder.getFirstOperand().setText(Double.toString(history.getFirstOperand()));
        viewHolder.getSecondOperand().setText(Double.toString(history.getSecondOperand()));
        viewHolder.getOperator().setText(history.getOperatorSymbol());
        viewHolder.getResult().setText(Double.toString(history.getResult()));
        View view =convertView;
        */
        if(tmpId == R.layout.history_item_selectable){ //maybe not best solution
            View finalConvertView = view;
            CheckBox c = (CheckBox)finalConvertView.findViewById(R.id.checkBox);
            Integer i = new Integer(position);
            if (pos.contains(i)){
                c.setChecked(true);
            }
            view.setOnClickListener((d)->{
                if(pos.contains(i)){
                    pos.remove(i);
                    c.setChecked(false);
                }else {
                    pos.add(i);
                    c.setChecked(true);
                }

            });
        }
/*
        */
        //convertView.findViewById(R.id.checkBox).setVisibility(View.VISIBLE);
        return view;
    }
}
