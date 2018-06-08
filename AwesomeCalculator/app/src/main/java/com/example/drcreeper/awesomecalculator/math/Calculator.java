package com.example.drcreeper.awesomecalculator.math;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class Calculator implements Serializable {
    private static final int TEXT_FIELD_SIZE = 16;
    private TextView view = null;
    private double firstOperand = 0 ;
    private double secondOperand = 0;
    private double result;
    private char operand;
    private boolean isDotAviable = true;
    private boolean isSolved = false;

    public Calculator() {
        super();
    }

    public Calculator(TextView view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(TextView view) {
        this.view = view;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }

    public double getFirstOperand() {

        return firstOperand;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }
    public void toZero(){
        view.setText("0");
        isDotAviable = true;
    }
    private double parseNum(){
        double result = 0;
        try {
            result = Double.parseDouble(view.getText().toString());
        }catch (NumberFormatException e){
            //Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    private void show(double s){
        String outputField = Double.toString(s);
        if(outputField.endsWith(".0")){
            outputField = outputField.substring(0,outputField.length() - 2);
        }
        if(outputField.length()>TEXT_FIELD_SIZE){
            outputField = outputField.substring(0,TEXT_FIELD_SIZE);
        }
        view.setText(outputField);
    }
    public void numPress(char c){
        if(isSolved){
            toZero();
            isSolved = false;
        }
        if(view.getText().length() < TEXT_FIELD_SIZE) {
            if(c == '.'){
                if(isDotAviable){
                    view.setText(view.getText().toString() + c);
                    isDotAviable = false;
                }
            }else {
                if(view.getText().toString().equals("0")) {
                    view.setText("");
                }
                view.setText(view.getText().toString() + c);
            }
        }
    }
    public void operatorPress(char c){
        operand = c;
        firstOperand = parseNum();
        toZero();
    }
    public void solvePress(){
        if(isSolved){
            firstOperand = parseNum();
        }else{
            isSolved = true;
            secondOperand = parseNum();
        }
        switch (operand){
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                result = firstOperand / secondOperand;
                break;
            default:
                result = firstOperand;
        }
        show(result);
    }
    public void clear(){
        firstOperand = 0;
        secondOperand = 0;
        toZero();
        isSolved = false;
    }
    public void eracePress(){
        String oldText = view.getText().toString();
        if(oldText.endsWith(".")){
            isDotAviable = true;
        }
        if(oldText.length() == 1) {
            toZero();
        }else if (oldText.length() > 0){
            view.setText(oldText.substring(0, oldText.length() - 1));
        }
    }
}
