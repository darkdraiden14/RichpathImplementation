package com.example.stickereditor;

import android.annotation.SuppressLint;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.richpath.RichPath;
import com.richpath.RichPathDrawable;
import com.richpath.model.Vector;
import com.richpath.util.XmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    ImageView alienImg;
    Vector alienVector;
    RichPath stickerPart;
    XmlResourceParser xrp;
    RichPathDrawable alienRichPathDrawable;
    ImageView.ScaleType alienScaleType = ImageView.ScaleType.CENTER;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        alienImg = findViewById(R.id.ic_alien);
        alienVector = new Vector();
        xrp = getResources().getXml(R.drawable.ic_aliens_12);
        final TextView txtUse = findViewById(R.id.txtUse);
        alienRichPathDrawable = new RichPathDrawable(alienVector, alienScaleType);
        
        final Button blue,red,yellow,gray,green,orange;
        blue = findViewById(R.id.bluebtn);
        yellow = findViewById(R.id.yellowbtn);
        red = findViewById(R.id.redbtn);
        green = findViewById(R.id.greenbtn);
        gray = findViewById(R.id.graybtn);
        orange = findViewById(R.id.orangebtn);
        
        try {
            XmlParser.parseVector(alienVector,xrp,Main2Activity.this);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        alienImg.setImageDrawable(alienRichPathDrawable);
        stickerPart = alienRichPathDrawable.findRichPathByName("path_5");

        txtUse.setText(String.valueOf(stickerPart.getFillColor()));


        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stickerPart.setFillColor(Color.BLUE);
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);
                txtUse.setText(String.valueOf(stickerPart.getFillColor()));
                // Code here executes on main thread after user presses button
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stickerPart.setFillColor(Color.rgb(214,83,44));
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);
                txtUse.setText(String.valueOf(stickerPart.getFillColor()));
                // Code here executes on main thread after user presses button
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stickerPart.setFillColor(Color.YELLOW);
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);
                txtUse.setText(String.valueOf(stickerPart.getFillColor()));

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                stickerPart.setFillColor(Color.RED);
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);
                txtUse.setText(String.valueOf(stickerPart.getFillColor()));

                // Code here executes on main thread after user presses button
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stickerPart.setFillColor(Color.GREEN);
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);

                txtUse.setText(String.valueOf(stickerPart.getFillColor()));
                // Code here executes on main thread after user presses button
            }
        });
        gray.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stickerPart.setFillColor(Color.GRAY);
                alienImg.invalidate();
                alienImg.setImageDrawable(alienRichPathDrawable);

                txtUse.setText(String.valueOf(stickerPart.getFillColor()));
                // Code here executes on main thread after user presses button
            }
        });

    }

}
