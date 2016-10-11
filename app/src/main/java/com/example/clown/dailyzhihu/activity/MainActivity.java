package com.example.clown.dailyzhihu.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clown.dailyzhihu.other.DayNightModeHelper;
import com.example.clown.dailyzhihu.fragment.MainFragment;
import com.example.clown.dailyzhihu.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private DayNightModeHelper mDayNightModeHelper;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Toolbar mToolBar;
    /*private DrawerLayout mDrawerLayout;*/
    /*private ActionBarDrawerToggle mToggle;*/

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Fragment fragment;

    /*private NavigationView mNavigationView;*/
    /*private NavSubMenu mCollectSubMenu;
    private NavSubMenu mDownloadSubMenu;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDayNightModeHelper = new DayNightModeHelper(getApplicationContext());


        //在setContentView之前setTheme，否则setTheme要recreateActivity才生效
        if (mDayNightModeHelper.isDayTime()) {
            setTheme(R.style.AppTheme);
        }else{
            setTheme(R.style.NightTheme);
        }

        //Fresco.initialize(context)要在setContentView之前调用
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_refresh);
        mToolBar = (Toolbar) findViewById(R.id.main_toolBar);
        setSupportActionBar(mToolBar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ToolBar上的字体样式
        SpannableString spannableString = new SpannableString("首页");
        //spannableString.setSpan(new RelativeSizeSpan(2.0f), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        /*mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mToggle);*/


        /*mCollectSubMenu = (NavSubMenu) findViewById(R.id.nav_collect_subMenu);
        mDownloadSubMenu = (NavSubMenu) findViewById(R.id.nav_download_subMenu);*/


        //fragment
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);
        transaction= fm.beginTransaction();
        if(fragment == null){
            fragment = new MainFragment();

            transaction.add(R.id.fragment_container, fragment)
                    .commit();
        }


        //下拉刷新,重绘当前视图
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                transaction = fm.beginTransaction();
                if (fragment != null) {
                    transaction.remove(fragment);
                }
                fragment = new MainFragment();
                transaction.add(R.id.fragment_container, fragment)
                        .commit();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        /*mNavigationView = (NavigationView) findViewById(R.id.main_nav);
        mNavigationView.setItemIconTintList(null);*/
    }

    /*@Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_change_mode:
                timeModeChangeAnimation();
                /*if (mDayNightModeHelper.isDayTime()) {
                    mDayNightModeHelper.setTimeMode("NIGHT");
                    System.out.println("日间模式转为夜间模式");
                    setTheme(R.style.NightTheme);
                }else{
                    mDayNightModeHelper.setTimeMode("DAY");
                    System.out.println("夜间模式转为日间模式");
                    setTheme(R.style.AppTheme);
                }*/

                if (mDayNightModeHelper.isNightTime()) {
                    mDayNightModeHelper.setTimeMode("DAY");
                    setTheme(R.style.AppTheme);
                } else {
                    mDayNightModeHelper.setTimeMode("NIGHT");
                    setTheme(R.style.NightTheme);
                }
                //refreshUI();
                initFragment();
                break;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }


    /**
     * 模式切换时的动画
     */
    public void timeModeChangeAnimation(){
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, params);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(1000);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);

                }
            });
            objectAnimator.start();
        }
    }
    /**
     * 获取View的缓存视图
     * @param view
     * @return
     */
    public Bitmap getCacheBitmapFromView(View view) {
        boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
        }else{
            bitmap = null;
        }

        return bitmap;
    }

    public void initFragment() {
        transaction = fm.beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
        }
        fragment = new MainFragment();
        transaction.add(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     *
     * 刷新UI界面
     */
    /*private void refreshUI() {
        transaction = fm.beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
        }
        fragment = new MainFragment();
        transaction.add(R.id.fragment_container, fragment)
                .commit();

        //System.out.println("RefreshUI");
        TypedValue backgroundColor = new TypedValue();
        TypedValue textColor = new TypedValue();
        //TypedValue toolBarColor = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.colorBackground, backgroundColor, true);
        theme.resolveAttribute(R.attr.colorText, textColor, true);
        //theme.resolveAttribute(R.attr.colorToolBar, toolBarColor, true);

        //mDrawerLayout.setBackgroundResource(backgroundColor.resourceId);

        //mToolBar.setBackgroundResource(toolBarColor.resourceId);
        //RecyclerView fragmentRecyclerView = (RecyclerView) getSupportFragmentManager().findFragmentById(R.id.fragment_container).getView().findViewById(R.id.fragment_recycler);
        RecyclerView fragmentRecyclerView = (RecyclerView) fragment.getView().findViewById(R.id.fragment_recycler);
        fragmentRecyclerView.setBackgroundResource(backgroundColor.resourceId);
        int childCount = fragmentRecyclerView.getChildCount();

        Resources resources = getResources();
        for(int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) fragmentRecyclerView.getChildAt(childIndex);
            childView.setBackgroundResource(backgroundColor.resourceId);
            TextView mDateTextView = (TextView) childView.findViewById(R.id.date_text);
            CardView mStoryCardView = (CardView) childView.findViewById(R.id.story_cell_cardView);
            TextView mStoryTitleTextView = (TextView) childView.findViewById(R.id.news_title);
            View mStoryExtraLayout = childView.findViewById(R.id.story_cell_extra);
            *//*View mStoryLikeLayout = childView.findViewById(R.id.like_layout);
            View mStoryCommentLayout = childView.findViewById(R.id.comment_layout);*//*
            if (mDateTextView != null) {
                mDateTextView.setBackgroundResource(backgroundColor.resourceId);
                //mDateTextView.setTextColor(resources.getColor(textColor.resourceId));
            }else if (mStoryCardView != null) {
                mStoryCardView.setBackgroundResource(backgroundColor.resourceId);
                mStoryTitleTextView.setBackgroundResource(backgroundColor.resourceId);
                mStoryExtraLayout.setBackgroundResource(backgroundColor.resourceId);
                *//*mStoryLikeLayout.setBackgroundResource(backgroundColor.resourceId);
                mStoryCommentLayout.setBackgroundResource(backgroundColor.resourceId);*//*
            }


            //mSwipeRefreshLayout.setRefreshing(false);
        }


        //清除RecyclerView 缓存在 pool中的item
        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).
                    getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField.get(fragmentRecyclerView), new Object[0]);
            RecyclerView.RecycledViewPool recyclerViewPool = fragmentRecyclerView.getRecycledViewPool();
            recyclerViewPool.clear();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/
}
