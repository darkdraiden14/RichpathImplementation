package com.example.lasttry;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myParser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.hell);

    }

    @SuppressLint("SetTextI18n")
    public void enterInHeaven(View view) {
        try {
            InputStream is = getAssets().open("pathways.xml");
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            myParser = xmlFactoryObject.newPullParser();

            myParser.setInput(is, null);

            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)  {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("path")){
                            tv.setText(tv.getText() + myParser.getAttributeValue(null,"name"));
                            if(myParser.getAttributeValue(null,"pathData")!=null) {
                                tv.setText(tv.getText() + "\n " + myParser.getAttributeValue(null, "pathData"));
                            }if(myParser.getAttributeValue(null,"fillColor")!=null) {
                                tv.setText(tv.getText() + " \n " + myParser.getAttributeValue(null, "fillColor"));
                            }if(myParser.getAttributeValue(null,"strokeWidth")!=null) {
                                tv.setText(tv.getText() + " \n " + myParser.getAttributeValue(null, "strokeWidth"));
                            }if(myParser.getAttributeValue(null,"strokeColor")!=null) {
                                tv.setText(tv.getText() + " \n" + myParser.getAttributeValue(null, "strokeColor"));
                            }if(myParser.getAttributeValue(null,"strokeLineJoin")!=null) {
                                tv.setText(tv.getText() + " \n" + myParser.getAttributeValue(null, "strokeLineJoin"));
                            }if(myParser.getAttributeValue(null,"strokeLineCap")!=null) {
                                tv.setText(tv.getText() + " \n" + myParser.getAttributeValue(null, "strokeLineCap"));
                            }if(myParser.getAttributeValue(null,"fillAlpha")!=null) {
                                tv.setText(tv.getText() + " \n" + myParser.getAttributeValue(null, "fillAlpha"));
                            }if(myParser.getAttributeValue(null,"strokeAlpha")!=null) {
                                tv.setText(tv.getText() + " \n" + myParser.getAttributeValue(null, "strokeAlpha"));
                            }
                            tv.setText(tv.getText() + "\n" + "-------------------------------");
                        }
                        break;
                }
                event = myParser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}