package com.example.clown.dailyzhihu.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clown.dailyzhihu.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Clown on 2016/8/7.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder{

    public SimpleDraweeView mReceiverImageView;
    public TextView mReceiverNameTextView;
    public TextView mCommentTextView;
    //public TextView mCommentTimeTextView;
    public TextView mCommentPraiseTextView;

    public CommentViewHolder(View itemView) {
        super(itemView);

        mReceiverImageView = (SimpleDraweeView) itemView.findViewById(R.id.receiver_imageView);
        mReceiverNameTextView = (TextView) itemView.findViewById(R.id.receiver_name_textView);
        mCommentTextView = (TextView) itemView.findViewById(R.id.comment_textView);
        //mCommentTimeTextView = (TextView) itemView.findViewById(R.id.comment_time_textView);
        mCommentPraiseTextView = (TextView) itemView.findViewById(R.id.comment_praise_textView);

    }
}
