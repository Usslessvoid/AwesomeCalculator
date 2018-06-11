package com.example.drcreeper.awesomecalculator;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.fragments.CalkuulatorFragment;
import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    public static final String PREFERENCES_KEYS[] = {
            "calculatorCurentText",
            "calculatorFirstOperand",
            "calculatorSecondOperand",
            "calculatorOperator",
            "calculatorIsDotAviable",
            "calculatorIsSolved"
    };
    Fragment calculatorFragment;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorFragment = new CalkuulatorFragment();
        ButterKnife.bind(this);
        calculator = new Calculator();
        restoreState();
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
                calculator.eracePress();
                refresh();
            }
        });
    }



    private void saveState(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFERENCES_KEYS[0],calculator.getCurrentText());
        editor.putFloat(PREFERENCES_KEYS[1],(float)calculator.getFirstOperand());
        editor.putFloat(PREFERENCES_KEYS[2],(float)calculator.getSecondOperand());
        editor.putString(PREFERENCES_KEYS[3],calculator.getOperand().name());
        editor.putBoolean(PREFERENCES_KEYS[4],calculator.isDotAviable());
        editor.putBoolean(PREFERENCES_KEYS[5],calculator.isSolved());
        editor.apply();
    }

    private void restoreState(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        calculator.setCurrentText(preferences.getString(PREFERENCES_KEYS[0],"0"));
        calculator.setFirstOperand(preferences.getFloat(PREFERENCES_KEYS[1],0));
        calculator.setSecondOperand(preferences.getFloat(PREFERENCES_KEYS[2],0));
        calculator.setOperand(Operator.valueOf(preferences.getString(PREFERENCES_KEYS[3],"NONE")));
        calculator.setDotAviable(preferences.getBoolean(PREFERENCES_KEYS[4],true));
        calculator.setSolved(preferences.getBoolean(PREFERENCES_KEYS[5],false));
        refresh();
    }

    public void refresh(){
        mainTextView.setText(calculator.getCurrentText());
        saveState();
    }

}
