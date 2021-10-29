package com.example.lasttry;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class CreateInput extends AsyncTask<String, Integer, InputStream> {
    InputStream text;

    protected InputStream doInBackground(String...params){
        URL url;
        try {
            url = new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            text = new BufferedInputStream(con.getInputStream());
            return text;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(InputStream result){
        //use "text"
    }
}
