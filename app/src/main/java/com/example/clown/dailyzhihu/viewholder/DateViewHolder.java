package com.example.clown.dailyzhihu.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.clown.dailyzhihu.R;

/**
 * Created by Clown on 2016/5/29.
 */
public class DateViewHolder extends RecyclerView.ViewHolder {

    public TextView mDateTextView;

    public DateViewHolder(View view) {
        super(view);

        mDateTextView = (TextView) view.findViewById(R.id.date_text);
    }
}
