package com.example.drcreeper.awesomecalculator;

import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.Operator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testDivide(){
        Calculator calculator = new Calculator();
        calculator.numPress('1');
        calculator.numPress('2');
        calculator.operatorPress(Operator.DIV);
        calculator.numPress('2');
        calculator.numPress('4');
        calculator.solvePress();
        assertEquals(calculator.getCurrentText(),"0.5");
        calculator.numPress('4');
        assertEquals(calculator.getCurrentText(),"4");
    }

    @Test
    public void testNoZeroInEnd(){
        Calculator calculator = new Calculator();
        calculator.numPress('8');
        calculator.operatorPress(Operator.SUB);
        calculator.numPress('.');
        calculator.numPress('0');
        calculator.numPress('2');
        calculator.solvePress();
        assertEquals(calculator.getCurrentText(),"7.98");
    }

    @Test
    public void autoClear(){
        Calculator calculator = new Calculator();
        calculator.numPress('5');
        calculator.operatorPress(Operator.ADD);
        calculator.numPress('3');
        assertEquals("3",calculator.getCurrentText());
        calculator.solvePress();
        assertEquals("8",calculator.getCurrentText());
        calculator.numPress('9');
        assertEquals("9",calculator.getCurrentText());
    }

    @Test
    public void dotTest(){
        Calculator calculator = new Calculator();
        calculator.numPress('1');
        calculator.numPress('.');
        calculator.numPress('.');
        calculator.numPress('2');
        assertEquals("1.2",calculator.getCurrentText());
        calculator.erasePress();
        calculator.erasePress();
        assertEquals("1",calculator.getCurrentText());
        calculator.numPress('.');
        assertEquals("1.",calculator.getCurrentText());
    }
    @Test
    public void doubleTest(){
        assertEquals(2.99,3.09-0.1,0.00001);
    }
}