package com.example.clown.dailyzhihu.other;

import android.content.Context;
import android.content.SharedPreferences;

import org.jsoup.select.Evaluator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Clown on 2016/9/6.
 */
public class DayNightModeHelper {

    private static final String FILE_NAME = "setting";
    private static final String MODE = "dayNightMode";

    private SharedPreferences mSharedPreferences;

    public DayNightModeHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存模式
     */
    public boolean setTimeMode(String timeMode) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(MODE, timeMode);
        return editor.commit();
    }

    /**
     * 日间模式
     */
    public boolean isDayTime() {
        String mode = mSharedPreferences.getString(MODE, "DAY");
        return mode.equals("DAY");
    }

    /**
     * 夜间模式
     */
    public boolean isNightTime(){
        String mode = mSharedPreferences.getString(MODE, "DAY");
        return mode.equals("NIGHT");
    }
}
