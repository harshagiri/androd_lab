package com.example.expno3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText input1, input2;
    Button addition, subtraction, multiplication, division;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input1 = (EditText)findViewById(R.id.etNum1);
        input2 = (EditText)findViewById(R.id.etNum2);

        addition = (Button)findViewById(R.id.btnAdd);
        subtraction = (Button)findViewById(R.id.btnSub);
        multiplication = (Button)findViewById(R.id.btnMult);
        division = (Button)findViewById(R.id.btnDiv);

        tvResult = (TextView)findViewById(R.id.tvResult);

        //set a listner
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float num1 = 0,num2 = 0, result = 0;
        String oper = null;
        if(TextUtils.isEmpty(input1.getText().toString()) || TextUtils.isEmpty(input2.getText().toString())) {
            return ;
        }
        num1 = Float.parseFloat(input1.getText().toString());
        num2 = Float.parseFloat(input2.getText().toString());

        switch(v.getId()){
            case R.id.btnAdd: result = num1 + num2;
                oper = "+";
                break;
            case R.id.btnSub: result = num1 - num2;
                oper = "-";
                break;
            case R.id.btnMult: result = num1 * num2;
                oper = "*";
                break;
            case R.id.btnDiv: result = num1 / num2;
                oper = "/";
                break;
        }

        tvResult.setText(num1 +oper+num2+"="+result);
    }
}