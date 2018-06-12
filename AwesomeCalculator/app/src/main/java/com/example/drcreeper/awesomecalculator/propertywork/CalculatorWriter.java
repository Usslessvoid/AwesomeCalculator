package com.example.drcreeper.awesomecalculator.propertywork;

import android.content.SharedPreferences;

import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;

public class CalculatorWriter {

    public static void saveState(Calculator calculator, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferencesConstants.KEY_CURRENT_TEXT,calculator.getCurrentText());
        editor.putFloat(PreferencesConstants.KEY_FIRST_OPERAND,(float)calculator.getFirstOperand());
        editor.putFloat(PreferencesConstants.KEY_SECOND_OPERAND,(float)calculator.getSecondOperand());
        editor.putString(PreferencesConstants.KEY_OPERATOR,calculator.getOperand().name());
        editor.putBoolean(PreferencesConstants.KEY_DOT_AVAILABLE,calculator.isDotAvailable());
        editor.putBoolean(PreferencesConstants.KEY_SOLVED,calculator.isSolved());
        editor.apply();
    }

    public static void restoreState(Calculator calculator, SharedPreferences sharedPreferences){
        calculator.setCurrentText(sharedPreferences.getString(PreferencesConstants.KEY_CURRENT_TEXT,"0"));
        calculator.setFirstOperand(sharedPreferences.getFloat(PreferencesConstants.KEY_FIRST_OPERAND,0));
        calculator.setSecondOperand(sharedPreferences.getFloat(PreferencesConstants.KEY_SECOND_OPERAND,0));
        calculator.setOperand(Operator.valueOf(sharedPreferences.getString(PreferencesConstants.KEY_OPERATOR,"NONE")));
        calculator.setDotAvailable(sharedPreferences.getBoolean(PreferencesConstants.KEY_DOT_AVAILABLE,true));
        calculator.setSolved(sharedPreferences.getBoolean(PreferencesConstants.KEY_SOLVED,false));
    }

}
