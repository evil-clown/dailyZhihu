package com.example.clown.dailyzhihu.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.clown.dailyzhihu.netWork.SingleNewsTask;
import com.example.clown.dailyzhihu.bean.NewsHtml;
import com.example.clown.dailyzhihu.R;
import com.google.gson.Gson;

/**
 * Created by Clown on 2016/5/12.
 */
public class StoryFragment extends Fragment{

    //private Toolbar mToolBar;
    public WebView mWebView;
    private String resultString;
    /*private String htmlString;
    private String cssString;*/

    private NewsHtml mNewsHtml;
    private String urlString;

    public static final String NEWS_URL = "urlString";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        urlString = (String) getArguments().getSerializable(NEWS_URL);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        //mToolBar = (Toolbar) view.findViewById(R.id.news_toolBar);
        mWebView = (WebView) view.findViewById(R.id.news_web);
        new NewsTask().execute(urlString);

        return view;
    }

    public static Fragment newInstance(String id){
        Bundle args = new Bundle();
        //System.out.println(id);
        args.putSerializable(NEWS_URL, "http://news-at.zhihu.com/api/4/news/" + id);
        StoryFragment fragment = new StoryFragment();
        fragment.setArguments(args);

        return fragment;

    }

    public class NewsTask extends SingleNewsTask {
        @Override
        protected void onPostExecute(String s) {
            mNewsHtml = new Gson().fromJson(s, NewsHtml.class);
            mWebView.loadUrl(mNewsHtml.getShare_url());
           /* mWebView.getSettings().setLoadsImagesAutomatically(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadDataWithBaseURL(mNewsHtml.getShare_url(), mNewsHtml.createHtmlData(mStoryHtml), NewsHtml.MIME_TYPE, NewsHtml.ENCODING, null);*/
            //mWebView.loadData(mNewsHtml.createHtmlData(mStoryHtml), NewsHtml.MIME_TYPE, NewsHtml.ENCODING);
            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //return super.shouldOverrideUrlLoading(view, url);
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }



    /*public class NewsTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            for(String param: params){
                try {
                    resultString = new DataCatch().getData(param);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return resultString;
        }

        *//*
        * 将获取的JSON内容解释为HTMl，显示在WebView上*//*
        @Override
        protected void onPostExecute(String s) {
            resultString = s;
            mStoryHtml = new Gson().fromJson(resultString, NewsHtml.class);
            mWebView.loadUrl(mStoryHtml.getShare_url());
            *//*System.out.println(mStoryHtml.toString());
            String storyData = "<html><body><head><title>" + mStoryHtml.getTitle() + "</title>"
                    +"<link  rel = 'stylesheet' href = " + mStoryHtml.getCss().get(0).replace("http:/", "") + "></link>"
                    //+"<script src= " + mStoryHtml.getJs() + "></script>"
                    + mStoryHtml.getBody() + "</body></html>";
            String storyData = "<html><body>" + mStoryHtml.getBody() + "</body></html>";
            mWebView.loadData(storyData, "text/html", "UTF-8" );
            mWebView.loadDataWithBaseURL("", storyData, "text/html", "UTF-8", "");*//*
        }
    }*/


    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.pager_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Toast.makeText(getActivity(), "You click share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_collect:

                break;
            case R.id.menu_like:
                Toast.makeText(getActivity(), "You click like", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                getActivity().finish();

            default:
                super.onOptionsItemSelected(item);
        }

        return true;
        }*/
    }
