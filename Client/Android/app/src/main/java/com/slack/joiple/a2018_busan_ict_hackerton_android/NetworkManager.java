package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager extends AsyncTask<String, Void, Void> {
    String url;
    public PacketConverter in,out;
    public NetworkManager(String url){
        this.url=url;
        in=new PacketConverter();
        out=new PacketConverter();
    }
    public void execute(){
        this.execute(in.pack());
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
                response.append('\n');
            }
            br.close();
            out.unPack(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
