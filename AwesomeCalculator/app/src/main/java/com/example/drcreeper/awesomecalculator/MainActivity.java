package com.example.drcreeper.awesomecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.math.Calculator;

public class MainActivity extends AppCompatActivity {
private TextView mainTextView;
private Button numbers[];
private Button dot;
private Button add;
private Button sub;
private Button mul;
private Button div;
private Button slove;
private Button clear;
private Button erace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = (TextView)findViewById(R.id.main_textView);
        numbers = new Button[]{
                (Button) findViewById(R.id.num_0_button),
                (Button) findViewById(R.id.num_1_button),
                (Button) findViewById(R.id.num_2_button),
                (Button) findViewById(R.id.num_3_button),
                (Button) findViewById(R.id.num_4_button),
                (Button) findViewById(R.id.num_5_button),
                (Button) findViewById(R.id.num_6_button),
                (Button) findViewById(R.id.num_7_button),
                (Button) findViewById(R.id.num_8_button),
                (Button) findViewById(R.id.num_9_button)
        };
        dot = (Button)findViewById(R.id.num_dot_button);
        add = (Button)findViewById(R.id.add_button);
        sub = (Button)findViewById(R.id.sub_button);
        mul = (Button)findViewById(R.id.mul_button);
        div = (Button)findViewById(R.id.div_button);
        slove = (Button)findViewById(R.id.slove_button);
        clear = (Button)findViewById(R.id.clear_button);
        erace = (Button)findViewById(R.id.erace_button);
        final Calculator calculator = new Calculator(mainTextView);
        for(final Button button : numbers){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.numPress(button.getText().charAt(0));
                }
            });
        }
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.numPress('.');
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress('+');
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress('-');
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress('*');
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operatorPress('/');
            }
        });
        slove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.solvePress();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.clear();
            }
        });
        erace.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               calculator.eracePress();
           }
        });

    }
}
