package com.example.clown.dailyzhihu.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.netWork.SingleNewsTask;
import com.example.clown.dailyzhihu.activity.StoryPagerActivity;
import com.example.clown.dailyzhihu.adapter.MainAdapter;
import com.example.clown.dailyzhihu.bean.Bean;
import com.example.clown.dailyzhihu.bean.BeanLab;
import com.example.clown.dailyzhihu.bean.DataWrapper;
import com.example.clown.dailyzhihu.bean.News;
import com.example.clown.dailyzhihu.bean.NewsLab;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/12.
 */
public class MainFragment extends Fragment {

    public final static String STORIES = "stories";

   // private DayNightModeHelper mDayNightModeHelper;
    //private LruCache mStoryCache;

    private String resultString;
    private DataWrapper wrapper;
    private ArrayList<News> mStories;
    private ArrayList<News> mTopStories;
    private ArrayList<Bean> mBeans = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private int lastVisiblePosition;

    private LinearLayoutManager manager;

    private ConnectivityManager conMgr;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //检查网络的连接性
        //getActiveNetworkInfo() == null 表示网络没连接
        conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isConnected()){


            /*if(conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable()){
                Toast.makeText(getActivity(), "正在使用手机流量，请注意", Toast.LENGTH_SHORT).show();
            }*/
            new NewsListTask().execute("http://news-at.zhihu.com/api/4/news/latest");
            mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler);
            manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                boolean isScrollToLast = false;
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        lastVisiblePosition = manager.findLastVisibleItemPosition();
                        int totalItemCount = manager.getItemCount();

                        if(lastVisiblePosition == (totalItemCount - 1) && isScrollToLast){
                            //Log.e("Recyclerview", "已经滑到底部");
                            new NewsListTask().execute("http://news.at.zhihu.com/api/4/news/before/"
                                    + wrapper.getDate());
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if(dy > 0){
                        isScrollToLast = true;
                    }else{
                        isScrollToLast = false;
                    }
                }
            });

        }else{
            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    public class NewsListTask extends SingleNewsTask {
        @Override
        protected void onPostExecute(String resultString) {
            wrapper = new Gson().fromJson(resultString, DataWrapper.class);


            mBeans.add(new Bean(wrapper.getDate(), 1));
            for(int i = 0; i < wrapper.getStories().size(); i ++){
                mBeans.add(new Bean(wrapper.getStories().get(i), 2));
            }

            mStories = wrapper.getStories();
            if(wrapper.getTop_stories() != null){
                mTopStories = wrapper.getTop_stories();
            }

            mRecyclerView.scrollToPosition(lastVisiblePosition);


            NewsLab.get(getActivity()).setTopStories(mTopStories);
            NewsLab.get(getActivity()).setStories(mStories);
            BeanLab.get(getActivity()).setBeans(mBeans);

            if(wrapper.getTop_stories() == null){
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }else{
                setAdapter();
            }
        }
    }



    void setAdapter(){
        MainAdapter adapter = new MainAdapter(wrapper, getActivity());
        if(mRecyclerView == null){
            return;
        }
        if(wrapper != null ){
            mRecyclerView.setAdapter(adapter);
        }else{
            mRecyclerView.setAdapter(null);
        }

        adapter.setOnItemClickListener(new MainAdapter.MyListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity(), StoryPagerActivity.class);
                System.out.println(data);
                intent.putExtra(StoryFragment.NEWS_URL, data);
                startActivity(intent);
            }
        });
    }
/*

    public void getRecyclerViewState(){
        int position = manager.findFirstVisibleItemPosition();
        View view = mRecyclerView.getChildAt(position);
        if (view != null) {
            int top = view.getTop();
        }

        initFragment(position);
    }


    public void initFragment(int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            transaction.remove(fragment);
        }
        fragment = new MainFragment();
        mRecyclerView.scrollToPosition(position);
        transaction.add(R.id.fragment_container, fragment)
                .commit();
    }*/
}
