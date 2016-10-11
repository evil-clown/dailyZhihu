package com.example.clown.dailyzhihu.netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Clown on 2016/5/14.
 */
public class DataCatch {
    public String getData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        StringBuilder builder = new StringBuilder();
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line);
            }

            reader.close();
        }
        connection.disconnect();


        return builder.toString();
    }

}