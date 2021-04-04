package com.example.expno7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView tvOutput;
    private static final int t1 = 1;
    private static final int t2 = 2;
    private static final int t3 = 3;
    Thread thread1, thread2, thread3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = (TextView)findViewById(R.id.textView1);
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread1.start();
                thread2.start();
                thread3.start();
            }
        });
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                {
                    if(msg.what == t1){
                        tvOutput.append("\nIn thread 1");
                    }
                    if(msg.what == t2){
                        tvOutput.append("\nIn thread 2");
                    }
                    if(msg.what == t3){
                        tvOutput.append("\nIn thread 3");
                    }
                }
            }
        };

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    handler.sendEmptyMessage(t1);
                }
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    handler.sendEmptyMessage(t2);
                }
            }
        });

        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    handler.sendEmptyMessage(t3);
                }
            }
        });
    }
}