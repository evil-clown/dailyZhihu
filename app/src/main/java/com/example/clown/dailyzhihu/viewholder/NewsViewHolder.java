package com.example.clown.dailyzhihu.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clown.dailyzhihu.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Clown on 2016/5/12.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;
    public SimpleDraweeView mSimpleDraweeView;
    public TextView mCommentText;
    public TextView mLikeText;

    public NewsViewHolder(View view) {
        super(view);

        mTextView = (TextView) view.findViewById(R.id.news_title);
        mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.news_image);
        mCommentText = (TextView) view.findViewById(R.id.story_comments);
        mLikeText = (TextView) view.findViewById(R.id.story_like);
    }
}
