package com.example.lasttry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;

import com.richpath.RichPath;
import com.richpath.RichPathDrawable;
import com.richpath.model.Group;
import com.richpath.model.Vector;
import com.richpath.util.Utils;

public class MainActivity extends AppCompatActivity {

    private ImageView tv;
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
    public void enterInHeaven(View view) throws MalformedURLException {

            String urlString = "https://panoslice-prod.s3.ap-southeast-1.amazonaws.com/stickers/aliens/012.xml";
            //InputStream is = getAssets().open("pathways.xml");
            //String urlString = "https://raw.githubusercontent.com/darkdraiden14/RichpathImplementation/main/pathways.xml";
            URLAsyncTask urlAsyncTask = new URLAsyncTask();
            urlAsyncTask.execute(new URL(urlString));

    }

    @SuppressLint("StaticFieldLeak")
    private class URLAsyncTask extends AsyncTask<URL, String, String> {
        // Use the URL passed in the AysncClass and return an InputStream to be used in onPostExecute
        @Override
        protected String doInBackground(URL... params) {
            try {
                Log.v("APP", "Downloading File");
                StringBuffer output = new StringBuffer("");
                InputStream stream = null;
                URLConnection conn = params[0].openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) conn;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    stream = httpURLConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(stream));
                    String s = "";
                    while ((s = buffer.readLine()) != null)
                        output.append(s);
                }
                return output.toString();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String inputString) {
            try{
                StringBuilder sample = new StringBuilder();
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                myParser = xmlFactoryObject.newPullParser();
                InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

                Vector vector = new Vector();

                myParser.setInput(inputStream,"UTF-8");

                Stack<Group> groupStack = new Stack<>();

                int event = myParser.getEventType();

                while (event != XmlPullParser.END_DOCUMENT)  {
                    String tagName=myParser.getName();
                    switch (event){
                        case XmlPullParser.START_TAG:
                            if(tagName.equals(Vector.TAG_NAME)){
                                vector.inflate();
                            }
                            if(tagName.equals(Group.TAG_NAME)){
                                String name = myParser.getAttributeValue(null,"android:name");
                                float rotation = Float.parseFloat(myParser.getAttributeValue(null,"android:rotation"));
                                float pivotX = Float.parseFloat(myParser.getAttributeValue(null,"android:pivotX"));
                                float pivotY = Float.parseFloat(myParser.getAttributeValue(null,"android:pivotY"));
                                float scaleX = Float.parseFloat(myParser.getAttributeValue(null,"android:scaleX"));
                                float scaleY = Float.parseFloat(myParser.getAttributeValue(null,"android:scaleY"));
                                float translateX = Float.parseFloat(myParser.getAttributeValue(null,"android:translateX"));
                                float translateY = Float.parseFloat(myParser.getAttributeValue(null,"android:translateY"));
                                Group group = new Group(name,rotation,pivotX,pivotY,scaleX,scaleY,translateX,translateY);
                                if (!groupStack.empty()) {
                                    group.scale(groupStack.peek().matrix());
                                }
                                groupStack.push(group);
                            }
                            if(tagName.equals(RichPath.TAG_NAME)){

                                String pathData = myParser.getAttributeValue(null,"android:pathData");
                                RichPath path = new RichPath(pathData);
                                String name = myParser.getAttributeValue(null,"android:name");
                                float fillAlpha = getAttributeFloat(myParser.getAttributeValue(null,"android:fillAlpha"),1.0f);
                                int fillColor = getAttributeColor(myParser.getAttributeValue(null, "android:fillColor"),Color.TRANSPARENT);
                                float strokeAlpha =  getAttributeFloat(myParser.getAttributeValue(null,"android:strokeAlpha"),1.0f);
                                int strokeColor = getAttributeColor(myParser.getAttributeValue(null,"android:strokeColor"),Color.TRANSPARENT);
                                Paint.Cap strokeLineCap =Paint.Cap.BUTT;
                                Paint.Join strokeLineJoin = Paint.Join.MITER;
                                float strokeMiterLimit = getAttributeFloat(myParser.getAttributeValue(null,"android:strokeMeterLimit"),4);
                                float strokeWidth =  getAttributeFloat(myParser.getAttributeValue(null,"android:strokeWidth"),0);
                                float trimPathStart =  getAttributeFloat(myParser.getAttributeValue(null,"android:trimPathStart"),0);
                                float trimPathEnd =  getAttributeFloat(myParser.getAttributeValue(null,"android:trimPathEnd"),1);
                                float trimPathOffset = getAttributeFloat(myParser.getAttributeValue(null,"android:trimPathOffset"),0);
                                Path.FillType fillType = getAttributePathFillType(myParser.getAttributeValue(null,"android:fillType"), Path.FillType.WINDING);
                                path.inflate(pathData,name,fillAlpha,fillColor,strokeAlpha,strokeColor,strokeLineCap,strokeLineJoin,strokeMiterLimit,strokeWidth,trimPathStart,trimPathEnd,trimPathOffset, fillType);
                                //tv.setText(tv.getText() + myParser.getAttributeValue(null,"android:name"));
                                // sample.append(myParser.getAttributeValue(null, "android:name"));
                                if (!groupStack.empty()) {
                                    path.applyGroup(groupStack.peek());
                                }
                                vector.paths.add(path);
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if (Group.TAG_NAME.equals(tagName)) {
                                if (!groupStack.empty()) {
                                    groupStack.pop();
                                }
                            }
                            break;
                    }
                    event = myParser.next();
                }
                final RichPathDrawable alienRichPathDrawable;
                ImageView.ScaleType alienScaleType = ImageView.ScaleType.CENTER;
                alienRichPathDrawable = new RichPathDrawable(vector, alienScaleType);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setImageDrawable(alienRichPathDrawable);
                    }
                });
                inputStream.close();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

        }
        public Path.FillType getAttributePathFillType(String value,Path.FillType defValue) {
            return value != null ? getPathFillType(Integer.parseInt(value), defValue) : defValue;
        }
        private Path.FillType getPathFillType(int id, Path.FillType defValue) {
            switch (id) {
                case 0:
                    return Path.FillType.WINDING;
                case 1:
                    return Path.FillType.EVEN_ODD;
                case 2:
                    return Path.FillType.INVERSE_WINDING;
                case 3:
                    return Path.FillType.INVERSE_EVEN_ODD;
                default:
                    return defValue;
            }
        }
        public float getAttributeFloat(String value, float defValue) { ;
            return value != null ? Float.parseFloat(value) : defValue;
        }
        public  int getAttributeColor(String value, int defValue) {
            return value != null ? Utils.getColorFromString(value) : defValue;
        }

    }
}
