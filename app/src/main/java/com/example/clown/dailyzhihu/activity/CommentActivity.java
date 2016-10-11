package com.example.clown.dailyzhihu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.clown.dailyzhihu.other.DayNightModeHelper;
import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.fragment.CommentFragment;

/**
 * Created by Clown on 2016/8/7.
 */
public class CommentActivity extends AppCompatActivity{

    private Toolbar mToolBar;
    private DayNightModeHelper mDayNightModeHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDayNightModeHelper = new DayNightModeHelper(this);

        if (mDayNightModeHelper.isDayTime()) {
            setTheme(R.style.AppTheme);
        }else{
            setTheme(R.style.NightTheme);
        }
        setContentView(R.layout.activity_comment);

        mToolBar = (Toolbar) findViewById(R.id.comment_toolBar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.comment_fragment_container);


        String id = getIntent().getStringExtra(StoryPagerActivity.STORY_ID);
        if (fragment == null) {
            fragment = CommentFragment.newInstance(id);
            fm.beginTransaction()
                    .add(R.id.comment_fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*return super.onOptionsItemSelected(item);*/

        switch (item.getItemId()) {
            case R.id.menu_write_comment:
                Toast.makeText(CommentActivity.this, "写评论", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
