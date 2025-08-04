package it.fohnckham.mycalculator;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;

    String currentInput = "";
    String operator = "";
    double firstNumber = 0;
    boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setNumberButtonListener(R.id.btn0, "0");
        setNumberButtonListener(R.id.btn1, "1");
        setNumberButtonListener(R.id.btn2, "2");
        setNumberButtonListener(R.id.btn3, "3");
        setNumberButtonListener(R.id.btn4, "4");
        setNumberButtonListener(R.id.btn5, "5");
        setNumberButtonListener(R.id.btn6, "6");
        setNumberButtonListener(R.id.btn7, "7");
        setNumberButtonListener(R.id.btn8, "8");
        setNumberButtonListener(R.id.btn9, "9");
        setNumberButtonListener(R.id.btnDot, ".");

        findViewById(R.id.btnPlus).setOnClickListener(v -> operatorPressed("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> operatorPressed("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> operatorPressed("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> operatorPressed("/"));

        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
    }

    private void setNumberButtonListener(int id, String value) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            if (isNewOp) {
                currentInput = "";
                isNewOp = false;
            }
            currentInput += value;
            display.setText(currentInput);
        });
    }

    private void operatorPressed(String op) {
        try {
            firstNumber = Double.parseDouble(currentInput);
        } catch (Exception e) {
            firstNumber = 0;
        }
        operator = op;
        isNewOp = true;
    }

    private void calculateResult() {
        double secondNumber;
        try {
            secondNumber = Double.parseDouble(currentInput);
        } catch (Exception e) {
            secondNumber = 0;
        }

        double result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
        isNewOp = true;
    }
}