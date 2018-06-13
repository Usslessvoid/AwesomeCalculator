package com.example.drcreeper.awesomecalculator.math;

public class Calculator {

    private static final int TEXT_FIELD_SIZE = 17;

    private String currentText = "0";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private Operator operand = Operator.NONE;
    private State state = State.INITIAL;
    private boolean isDotAvailable = true;



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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    private double currentSolve(){
        double result;
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
        return result;
    }
    public void numPress(char c){
        if(state == State.SOLVED){
            clear();
        }
        if(state == State.OPERATOR_CHECKED){
            toZero();
            state = State.SECOND_NUM_ENTERED;
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
        switch(state){
            case INITIAL:
                firstOperand = parseNum();
                toZero();
                state = State.OPERATOR_CHECKED;
                operand = c;
                break;
            case OPERATOR_CHECKED:
                operand = c;
                secondOperand = parseNum();
                break;
            case SECOND_NUM_ENTERED:
                secondOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                state = State.OPERATOR_CHECKED;
                operand = c;
                break;
            case SOLVED:
                firstOperand = parseNum();
                toZero();
                state = State.OPERATOR_CHECKED;
                operand = c;
        }
    }

    public void solvePress(){
        switch(state){
            case INITIAL:
            case OPERATOR_CHECKED:
                show(firstOperand);
            case SECOND_NUM_ENTERED:
                secondOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                state = State.SOLVED;
                break;
            case SOLVED:
                firstOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                break;
            case SOLVED_OPERATOR_CHECKED:
                secondOperand = parseNum();
                show(currentSolve());
                firstOperand = parseNum();
                state = State.SOLVED;
        }
    }

    public void clear(){
        toZero();
        firstOperand = 0;
        secondOperand = 0;
        operand = Operator.NONE;
        state = State.INITIAL;
    }

    public void erasePress(){
        String oldText = currentText;
        if(oldText.endsWith(".")){
            isDotAvailable = true;
        }
        if(oldText.length() == 1) {
            toZero();
            if(state == State.SECOND_NUM_ENTERED){
                state = State.OPERATOR_CHECKED;
            }
        }else if (oldText.length() > 0){
            currentText = oldText.substring(0, oldText.length() - 1);
        }
    }
}
