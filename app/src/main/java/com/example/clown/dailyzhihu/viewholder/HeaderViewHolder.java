package com.example.clown.dailyzhihu.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.clown.dailyzhihu.R;
import com.jude.rollviewpager.RollPagerView;

/**
 * Created by Clown on 2016/5/12.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public RollPagerView mRollPagerView;

    public HeaderViewHolder(View view) {
        super(view);

        mRollPagerView = (RollPagerView) view.findViewById(R.id.header_rollpager);
    }

    /*void initData(){
        mRollPagerView.setAdapter(new LoopAdapter(mRollPagerView));
    }*/
}
