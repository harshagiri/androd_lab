package com.example.expno4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    private class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setTextSize(40);

            paint.setColor(Color.GREEN);
            canvas.drawText("CIRCLE", 55, 30, paint);
            paint.setColor(Color.RED);
            canvas.drawCircle(100, 150, 100, paint);
            
            canvas.drawText("RECTANGLE", 255, 30, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(250, 50, 400, 350, paint);
            paint.setColor(Color.GREEN);

            canvas.drawText("SQUARE", 55, 430, paint);
            paint.setColor(Color.BLUE);
            canvas.drawRect(50, 450, 150, 550, paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("LINE", 255, 430, paint);
            paint.setColor(Color.CYAN);

            canvas.drawLine(250, 500, 350, 500, paint);
        }
    }
}