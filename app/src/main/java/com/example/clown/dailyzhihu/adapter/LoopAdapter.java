package com.example.clown.dailyzhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clown.dailyzhihu.bean.News;
import com.example.clown.dailyzhihu.activity.StoryPagerActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/12.
 */
public class LoopAdapter extends LoopPagerAdapter{

    ArrayList<News> mTopStories;
    private Context mContext;

    public static final String NEWS_ID = "id";

    void initData(ArrayList<News> mTopStories){
        this.mTopStories = mTopStories;
    }

    public LoopAdapter(RollPagerView viewPager, Context context) {
        super(viewPager);
        mContext = context;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        RelativeLayout mRelativeLayout = new RelativeLayout(mContext);
        mRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        SimpleDraweeView mSimpleDraweeView = new SimpleDraweeView(mContext);
        mSimpleDraweeView.setImageURI(Uri.parse(mTopStories.get(position).getImage()));
        mSimpleDraweeView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoryPagerActivity.class);
                intent.putExtra(NEWS_ID, mTopStories.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        TextView mTextView = new TextView(mContext);
        mTextView.setText(mTopStories.get(position).getTitle());
        mTextView.setTextSize(32);
        mTextView.setTextColor(0xffffffff);
        RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mRelativeLayout.addView(mSimpleDraweeView);
        mRelativeLayout.addView(mTextView, textViewLayoutParams);
        return mRelativeLayout;
    }

    @Override
    protected int getRealCount() {
        return mTopStories.size();
    }
}
