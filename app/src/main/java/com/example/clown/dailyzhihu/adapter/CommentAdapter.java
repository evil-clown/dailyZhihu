package com.example.clown.dailyzhihu.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.bean.Comment;
import com.example.clown.dailyzhihu.viewholder.CommentViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Clown on 2016/8/7.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Comment> mComments;

    public CommentAdapter(ArrayList<Comment> comments) {
        this.mComments = comments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_comment, parent, false);
        CommentViewHolder holder = new CommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentViewHolder) holder).mReceiverNameTextView.setText(mComments.get(position).getAuthor());

        /*Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-DD HH:MM");
        date.setTime(Long.parseLong(mComments.get(position).getTime()));
        String time = dateFormat.format(date);
        ((CommentViewHolder) holder).mCommentTimeTextView.setText(time);*/
        //((CommentViewHolder) holder).mCommentTimeTextView.setText(mComments.get(position).getTime());
        ((CommentViewHolder) holder).mCommentPraiseTextView.setText(mComments.get(position).getLikes());
        ((CommentViewHolder) holder).mCommentTextView.setText(mComments.get(position).getContent());
        ((CommentViewHolder) holder).mReceiverImageView.setImageURI(Uri.parse(mComments.get(position).getAvatar()));

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}
