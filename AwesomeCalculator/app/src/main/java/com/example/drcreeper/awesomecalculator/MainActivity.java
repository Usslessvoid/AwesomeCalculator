package com.example.drcreeper.awesomecalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/*
    public static final String PREFERENCES_KEYS[] = {
            "calculatorCurentText",
            "calculatorFirstOperand",
            "calculatorSecondOperand",
            "calculatorOperator",
            "calculatorIsDotAviable",
            "calculatorIsSolved"
    };
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
        calculator.setDotAvaiable(preferences.getBoolean(PREFERENCES_KEYS[4],true));
        calculator.setSolved(preferences.getBoolean(PREFERENCES_KEYS[5],false));
        refresh();
    }

*/
}
