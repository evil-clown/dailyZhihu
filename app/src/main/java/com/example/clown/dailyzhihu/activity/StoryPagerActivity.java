package com.example.clown.dailyzhihu.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.clown.dailyzhihu.other.DayNightModeHelper;
import com.example.clown.dailyzhihu.adapter.LoopAdapter;
import com.example.clown.dailyzhihu.fragment.StoryFragment;
import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.bean.News;
import com.example.clown.dailyzhihu.bean.NewsLab;

import java.util.ArrayList;

/**
 *
 * 托管显示日报内容fragment的Activity
 * Created by Clown on 2016/5/12.
 */
public class StoryPagerActivity extends AppCompatActivity{


    public static final String STORY_ID = "id";


    private ArrayList<News> mStories;
    private DayNightModeHelper mDayNightModeHelper;

    private ViewPager mViewPager;
    private Toolbar mToolBar;

    private String id;//点击的新闻的id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDayNightModeHelper = new DayNightModeHelper(this);

        if (mDayNightModeHelper.isDayTime()) {
            setTheme(R.style.AppTheme);
        }else{
            setTheme(R.style.NightTheme);
        }
        setContentView(R.layout.activity_news_pager);


        mToolBar = (Toolbar) findViewById(R.id.news_pager_toolBar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        mStories = NewsLab.get(this).getStories();
        /*for(int i = 0; i < mStories.size(); i ++) {
            System.out.println(mStories.get(i).getId());
        }*/
        FragmentManager fm = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.news_viewPager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                News news = mStories.get(position);
                return StoryFragment.newInstance(news.getId());
            }

            @Override
            public int getCount() {
                return mStories.size();
            }
        });



        //设置当前viewpager的页面
        if(getIntent().getSerializableExtra(LoopAdapter.NEWS_ID) != null){
             id = (String) getIntent().getSerializableExtra(LoopAdapter.NEWS_ID);
        }else{
             id = (String) getIntent().getSerializableExtra(StoryFragment.NEWS_URL);
        }

        for(int i = 0; i < mStories.size(); i ++){
            if(mStories.get(i).getId().equals(id)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            /*case R.id.menu_share:
                Toast.makeText(StoryPagerActivity.this, "You click share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_collect:
                Toast.makeText(StoryPagerActivity.this, "You click collect", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.menu_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(STORY_ID, id);
                startActivity(intent);
                break;
            /*case R.id.menu_like:
                Toast.makeText(StoryPagerActivity.this, "You click like", Toast.LENGTH_SHORT).show();
                break;*/

            case android.R.id.home:
                finish();

            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
