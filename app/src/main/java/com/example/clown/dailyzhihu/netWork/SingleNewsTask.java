package com.example.clown.dailyzhihu.netWork;

import android.os.AsyncTask;

import com.example.clown.dailyzhihu.netWork.DataCatch;

import java.io.IOException;

/**
 * Created by Clown on 2016/8/23.
 */
public class SingleNewsTask extends AsyncTask<String, Void, String>{

    public String resultString;

    @Override
    protected String doInBackground(String... params) {
        try {
            resultString = new DataCatch().getData(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
