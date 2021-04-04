package com.example.expno9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button write, read, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.editText);
        write = (Button)findViewById(R.id.button);
        read = (Button)findViewById(R.id.button2);
        clear = (Button)findViewById(R.id.button3);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = e1.getText().toString();
                try {
                    File f = new File("/sdcard/myfile.txt");
                    f.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(f);
                    fileOutputStream.write(message.getBytes());
                    fileOutputStream.close();;
                    Toast.makeText(getBaseContext(), "Data written in SDCARD", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String message = null;
                    String buf =  "";
                    File f = new File("/sdcard/myfile.txt");
                    f.createNewFile();
                    FileInputStream fileInputStream = new FileInputStream(f);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    while ((message = bufferedReader.readLine()) != null)  {
                        buf += message;
                    }
                    e1.setText(buf);
                    bufferedReader.close();
                    fileInputStream.close();
                    Toast.makeText(getBaseContext(), "Data Received from SDCARD", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
            }
        });
    }
}