package com.example.expno2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created*/
    EditText txtData1, txtData2;
    float num1, num2, result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button)findViewById(R.id.button1);
        Button sub = (Button)findViewById(R.id.button2);
        Button clear = (Button)findViewById(R.id.button3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    txtData1 = (EditText) findViewById(R.id.editText1);
                    txtData2 = (EditText) findViewById(R.id.editText2);

                    num1 = Float.parseFloat(txtData1.getText().toString());
                    num2 = Float.parseFloat(txtData2.getText().toString());

                    result1 = num1 + num2;

                    Toast.makeText(getBaseContext(), "ANSWER:"+ result1, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    txtData1 = (EditText) findViewById(R.id.editText1);
                    txtData2 = (EditText) findViewById(R.id.editText2);

                    num1 = Float.parseFloat(txtData1.getText().toString());
                    num2 = Float.parseFloat(txtData2.getText().toString());

                    result1 = num1 - num2;

                    Toast.makeText(getBaseContext(), "ANSWER:"+ result1, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    txtData1.setText("");
                    txtData2.setText("");

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}