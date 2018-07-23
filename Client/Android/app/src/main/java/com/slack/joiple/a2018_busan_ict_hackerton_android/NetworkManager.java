package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class NetworkManager extends AsyncTask<String, Void, Void> {
    String url;
    public String response;
    public Map<String,String> outputMap;
    public NetworkManager(String url,String res){
        this.url=url;
        response=res;
    }
    public String getResponseValue(String key){
        return (String)outputMap.get(key);
    }
    @Override
    public Void doInBackground(String... params) {
        try {

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

            byte[] outputInBytes = params[0].getBytes("UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write( outputInBytes );
            os.close();
            int retCode = conn.getResponseCode();
            Log.d("responseCode",conn.getResponseMessage());
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();

            this.response= response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
