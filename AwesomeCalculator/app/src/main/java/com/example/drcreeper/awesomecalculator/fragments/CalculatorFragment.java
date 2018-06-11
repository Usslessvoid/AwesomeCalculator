package com.example.drcreeper.awesomecalculator.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class CalculatorFragment extends Fragment {

    @BindView(R.id.main_textView)
    TextView mainTextView;
    @BindViews({
            R.id.num_0_button,
            R.id.num_1_button,
            R.id.num_2_button,
            R.id.num_3_button,
            R.id.num_4_button,
            R.id.num_5_button,
            R.id.num_6_button,
            R.id.num_7_button,
            R.id.num_8_button,
            R.id.num_9_button
    })
    Button[] numbers;
    @BindView(R.id.num_dot_button)
    Button dot;
    @BindView(R.id.add_button)
    Button add;
    @BindView(R.id.sub_button)
    Button sub;
    @BindView(R.id.mul_button)
    Button mul;
    @BindView(R.id.div_button)
    Button div;
    @BindView(R.id.slove_button)
    Button slove;
    @BindView(R.id.clear_button)
    Button clear;
    @BindView(R.id.erace_button)
    Button erace;
    Calculator calculator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator_fragment, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        calculator = new Calculator();
        calculator.setCurrentText("0000");
       // restoreState();
        for (final Button button : numbers) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.numPress(button.getText().charAt(0));
                    refresh();
                }
            });
        }
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.numPress('.');
                refresh();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress(Operator.ADD);
                refresh();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress(Operator.SUB);
                refresh();
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress(Operator.MUL);
                refresh();
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress(Operator.DIV);
                refresh();
            }
        });
        slove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.solvePress();
                refresh();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.clear();
                refresh();
            }
        });
        erace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.erasePress();
                refresh();
            }
        });
        return  view;
    }


    public void refresh() {
        mainTextView.setText(calculator.getCurrentText());
        //saveState();
    }
}