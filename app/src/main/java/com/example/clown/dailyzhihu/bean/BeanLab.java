package com.example.clown.dailyzhihu.bean;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/31.
 */
public class BeanLab {

    private static BeanLab sBeanLab;
    private Context mContext;
    private ArrayList<Bean> mBeans;
    private Bean mBean;

    private BeanLab(Context context){
        this.mContext = context;
        this.mBeans = mBeans;
    }

    public static BeanLab get(Context context){
        if(sBeanLab == null){
            sBeanLab = new BeanLab(context.getApplicationContext());
        }

        return sBeanLab;
    }

    public ArrayList<Bean> getBeans() {
        return mBeans;
    }

    public void setBeans(ArrayList<Bean> beans) {
        this.mBeans = beans;
    }

    public Bean getBean() {
        return mBean;
    }

    public void setBean(Bean bean) {
        this.mBean = bean;
    }
}
