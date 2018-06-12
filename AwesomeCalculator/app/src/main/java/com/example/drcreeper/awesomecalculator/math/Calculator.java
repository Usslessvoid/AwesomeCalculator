package com.example.drcreeper.awesomecalculator.math;

public class Calculator {

    private static final int TEXT_FIELD_SIZE = 17;

    private String currentText = "0";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private Operator operand = Operator.NONE;
    private boolean isDotAvailable = true;
    private boolean isSolved = false;

    public Calculator() {
        super();
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }

    public Operator getOperand() {
        return operand;
    }

    public void setOperand(Operator operand) {
        this.operand = operand;
    }

    public boolean isDotAvailable() {
        return isDotAvailable;
    }

    public void setDotAvailable(boolean dotAvailable) {
        isDotAvailable = dotAvailable;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    private void toZero(){
        currentText = "0";
        isDotAvailable = true;
    }

    private double parseNum(){
        double result = 0;
        try {
            result = Double.parseDouble(currentText);
        }catch (NumberFormatException e){
            clear();
        }
        return result;
    }

    private void show(double s){
        String outputField = Double.toString(s);
        if(outputField.endsWith(".0")){
            outputField = outputField.substring(0,outputField.length() - 2);
        }
        if(outputField.contains(".")){
            while (outputField.endsWith("0")){
                outputField = outputField.substring(0,outputField.length() - 1);
            }
        }
        if(outputField.length()>TEXT_FIELD_SIZE){
            outputField = outputField.substring(0,TEXT_FIELD_SIZE);
        }
        currentText = outputField;
    }

    public void numPress(char c){
        if(isSolved){
            toZero();
            isSolved = false;
            operand = Operator.NONE;
        }
        if(currentText.length() < TEXT_FIELD_SIZE) {
            if(c == '.'){
                if(isDotAvailable){
                    currentText = currentText + ".";
                    isDotAvailable = false;
                }
            }else {
                if(currentText.equals("0")) {
                    currentText = "";
                }
                currentText = currentText + c;
            }
        }
    }

    public void operatorPress(Operator c){
        operand = c;
        firstOperand = parseNum();
        toZero();
    }

    public void solvePress(){
        double result;
        if(isSolved){
            firstOperand = parseNum();
        }else{
            isSolved = true;
            secondOperand = parseNum();
        }
        switch (operand){
            case ADD:
                result = firstOperand + secondOperand;
                break;
            case SUB:
                result = firstOperand - secondOperand;
                break;
            case MUL:
                result = firstOperand * secondOperand;
                break;
            case DIV:
                result = firstOperand / secondOperand;
                break;
            default:
                result = parseNum();
        }
        show(result);
    }

    public void clear(){
        firstOperand = 0;
        secondOperand = 0;
        toZero();
        isSolved = false;
        operand = Operator.NONE;
    }

    public void erasePress(){
        String oldText = currentText;
        if(oldText.endsWith(".")){
            isDotAvailable = true;
        }
        if(oldText.length() == 1) {
            toZero();
        }else if (oldText.length() > 0){
            currentText = oldText.substring(0, oldText.length() - 1);
        }
    }
}
