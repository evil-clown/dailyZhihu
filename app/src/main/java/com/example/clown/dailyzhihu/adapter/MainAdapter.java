package com.example.clown.dailyzhihu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clown.dailyzhihu.bean.BeanLab;
import com.example.clown.dailyzhihu.bean.DataWrapper;
import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.bean.News;
import com.example.clown.dailyzhihu.bean.NewsExtra;
import com.example.clown.dailyzhihu.bean.NewsLab;
import com.example.clown.dailyzhihu.bean.Bean;
import com.example.clown.dailyzhihu.netWork.SingleNewsTask;
import com.example.clown.dailyzhihu.viewholder.DateViewHolder;
import com.example.clown.dailyzhihu.viewholder.HeaderViewHolder;
import com.example.clown.dailyzhihu.viewholder.NewsViewHolder;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/12.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private DataWrapper mWrapper;
    private Context mContext;
    private ArrayList<Bean> mBeans = new ArrayList<>();
    private ArrayList<News> mTopStories = new ArrayList<>();
    private MyListener mListener = null;

    private String string;
    private NewsExtra mNewsExtra = null;

    public MainAdapter(DataWrapper wrapper, Context context){
        this.mWrapper = wrapper;
        this.mContext = context;

        mBeans = BeanLab.get(context).getBeans();
        if(mWrapper.getTop_stories() == null){
            mTopStories = NewsLab.get(mContext).getTopStories();
        }else{
            mTopStories = mWrapper.getTop_stories();
        }
    }

    private final int HEADER = 0;
    private final int DATE = 1;
    private final int STORY = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        } else return mBeans.get(position - 1).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_header, parent, false);
            view.setOnClickListener(this);
            return new HeaderViewHolder(view);
        }else if(viewType == DATE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_date, parent, false);
            return new DateViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_story, parent, false);
            view.setOnClickListener(this);

            /*TypedValue typedValue = new TypedValue();
            mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
            view.setBackgroundResource(typedValue.resourceId);*/
            return new NewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == HEADER){
            LoopAdapter adapter = new LoopAdapter(((HeaderViewHolder)holder).mRollPagerView, mContext);
            adapter.initData(mTopStories);
            ((HeaderViewHolder)holder).mRollPagerView.setAdapter(adapter);
        }
        else if(holder.getItemViewType() == DATE){
            ((DateViewHolder)holder).mDateTextView.setText(mBeans.get(position - 1).getDate());
        }
        else{
            ((NewsViewHolder)holder).mTextView.setText(mBeans.get(position - 1).getStory().getTitle());
            ((NewsViewHolder)holder).mSimpleDraweeView.setImageURI(Uri.parse(mBeans.get(position - 1).getStory().getImages().get(0)));
            ((NewsViewHolder)holder).itemView.setTag(mBeans.get(position - 1).getStory().getId());
            new NewsExtraTask(holder).execute("http://news-at.zhihu.com/api/4/story-extra/" + mBeans.get(position - 1).getStory().getId());

            /*if (mStoryExtra == null) {
                ((NewsViewHolder)holder).mCommentText.setText("...");
                ((NewsViewHolder)holder).mLikeText.setText("...");
            }else{
                ((NewsViewHolder)holder).mCommentText.setText(mStoryExtra.getComments());
                ((NewsViewHolder)holder).mLikeText.setText(mStoryExtra.getPopularity());
            }*/
        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v, (String) v.getTag());
        }
    }


    public void setOnItemClickListener(MyListener mListener){
        this.mListener = mListener;
    }

    public interface MyListener{
        public void onItemClick(View view, String data);
    }


    class NewsExtraTask extends SingleNewsTask {

        private RecyclerView.ViewHolder mHolder;

        public NewsExtraTask(RecyclerView.ViewHolder holder) {
            this.mHolder = holder;
        }

        /*@Override
        protected String doInBackground(String... params) {
            for(String param:params){
                try {
                    string =  new DataCatch().getData(param);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return string;
        }*/

        @Override
        protected void onPostExecute(String s) {
            mNewsExtra = new Gson().fromJson(s, NewsExtra.class);


            if (mNewsExtra == null) {
                ((NewsViewHolder)mHolder).mCommentText.setText("...");
                ((NewsViewHolder)mHolder).mLikeText.setText("...");
            }else{
                ((NewsViewHolder)mHolder).mCommentText.setText(mNewsExtra.getComments());
                ((NewsViewHolder)mHolder).mLikeText.setText(mNewsExtra.getPopularity());
            }
        }
    }
}

