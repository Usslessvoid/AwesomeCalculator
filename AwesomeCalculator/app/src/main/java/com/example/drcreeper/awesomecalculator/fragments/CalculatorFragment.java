package com.example.drcreeper.awesomecalculator.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.historywriter.HistoryWriter;
import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;
import com.example.drcreeper.awesomecalculator.propertywork.CalculatorPreferencesContract;
import com.example.drcreeper.awesomecalculator.propertywork.CalculatorWriter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    Button[] numbersButtons;
    @BindView(R.id.num_dot_button)
    Button dotButton;
    @BindView(R.id.add_button)
    Button addButton;
    @BindView(R.id.sub_button)
    Button subButton;
    @BindView(R.id.mul_button)
    Button mulButton;
    @BindView(R.id.div_button)
    Button divButton;
    @BindView(R.id.clear_button)
    Button clearButton;
    @BindView(R.id.erace_button)
    Button eraseButton;
    private Calculator calculator;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator_fragment, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        calculator = new Calculator();
        CalculatorWriter.restoreState(calculator,getActivity().getSharedPreferences(CalculatorPreferencesContract.FILE_NAME, Context.MODE_PRIVATE));
        refresh();
        for (final Button button : numbersButtons) {
            button.setOnClickListener((v) -> {
                    calculator.numPress(button.getText().charAt(0));
                    refresh();
            });
        }
        dotButton.setOnClickListener((v)-> {
                calculator.numPress('.');
                refresh();
        });
        addButton.setOnClickListener((v) -> {
                calculator.operatorPress(Operator.ADD);
                refresh();
        });
        subButton.setOnClickListener((v) -> {
                calculator.operatorPress(Operator.SUB);
                refresh();
        });
        mulButton.setOnClickListener((v) -> {
                calculator.operatorPress(Operator.MUL);
                refresh();
        });
        divButton.setOnClickListener((v) -> {
                calculator.operatorPress(Operator.DIV);
                refresh();
        });

        clearButton.setOnClickListener(this::onClearClick);
        eraseButton.setOnClickListener(this::onEraseClick);
        return  view;
    }

    @OnClick(R.id.slove_button) //the best
    public void onSolveClick() {
        calculator.solvePress();
        refresh();
        HistoryWriter writer = new HistoryWriter();
        writer.setContext(getContext());
        writer.execute(calculator.getHistoryLog());
    }

    public void onClearClick(View v) {
        calculator.clear();
        refresh();
    }

    public void onEraseClick(View v) {
        calculator.erasePress();
        refresh();
    }

    public void refresh() {
        mainTextView.setText(calculator.getCurrentText());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(CalculatorPreferencesContract.FILE_NAME,0);
        CalculatorWriter.saveState(calculator,sharedPreferences);
    }

}