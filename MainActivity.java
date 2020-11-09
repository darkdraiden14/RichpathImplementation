package com.example.stickereditor;

import androidx.appcompat.app.AppCompatActivity;

import com.richpath.RichPath;
import com.richpath.RichPathView;
import com.richpathanimator.RichPathAnimator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RichPathView alienRichPathView;
    private boolean reverse = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alienRichPathView = findViewById(R.id.ic_alien);

        Button blue,red,yellow,gray,green,orange;
        blue = findViewById(R.id.bluebtn);
        yellow = findViewById(R.id.yellowbtn);
        red = findViewById(R.id.redbtn);
        green = findViewById(R.id.greenbtn);
        gray = findViewById(R.id.graybtn);
        orange = findViewById(R.id.orangebtn);

        final RichPath part1 = alienRichPathView.findRichPathByName("path_1");
        final RichPath part2 = alienRichPathView.findRichPathByName("path_2");
        final RichPath part3 = alienRichPathView.findRichPathByName("path_3");
        final RichPath part4 = alienRichPathView.findRichPathByName("path_4");
        final RichPath part5 = alienRichPathView.findRichPathByName("path_5");
        final RichPath part6 = alienRichPathView.findRichPathByName("path_6");
        final RichPath part7 = alienRichPathView.findRichPathByName("path_7");

        yellow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                part1.setFillColor(R.color.yellow);
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                part1.setFillColor(R.color.red);
                // Code here executes on main thread after user presses button
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                part1.setFillColor(R.color.blue);
                // Code here executes on main thread after user presses button
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                part1.setFillColor(R.color.green);
                // Code here executes on main thread after user presses button
            }
        });
        gray.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                part1.setFillColor(R.color.gray);
                // Code here executes on main thread after user presses button
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                part1.setFillColor(R.color.orange);
                // Code here executes on main thread after user presses button
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
