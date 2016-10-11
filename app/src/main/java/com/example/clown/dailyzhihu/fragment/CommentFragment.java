package com.example.clown.dailyzhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clown.dailyzhihu.other.CommentDecoration;
import com.example.clown.dailyzhihu.R;
import com.example.clown.dailyzhihu.netWork.SingleNewsTask;
import com.example.clown.dailyzhihu.activity.StoryPagerActivity;
import com.example.clown.dailyzhihu.adapter.CommentAdapter;
import com.example.clown.dailyzhihu.bean.Comment;
import com.example.clown.dailyzhihu.bean.CommentLab;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/8/7.
 */
public class CommentFragment extends Fragment {

    private RecyclerView mRecyclerView;


    private static ArrayList<Comment> longComments = new ArrayList<>();
    private ArrayList<Comment> shortComments = new ArrayList<>();
    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        id = getArguments().getString(StoryPagerActivity.STORY_ID);
        new NewsLongCommentTask().execute("http://news-at.zhihu.com/api/4/story/" + id + "/long-comments");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        CommentDecoration commentDecoration = new CommentDecoration(getActivity(), R.drawable.comment_divider);
        mRecyclerView.addItemDecoration(commentDecoration);
        return view;
    }


    public static CommentFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(StoryPagerActivity.STORY_ID, id);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
    * 获取长评论的Task
    */
    public class NewsLongCommentTask extends SingleNewsTask {
        @Override
        protected void onPostExecute(String s) {
            longComments = new Gson().fromJson(s, CommentLab.class).getComments();
            new NewsShortCommentTask().execute("http://news-at.zhihu.com/api/4/story/" + id + "/short-comments");
        }
    }

    /*
    * 获取短评论的Task
    */
    public class NewsShortCommentTask extends SingleNewsTask {
        @Override
        protected void onPostExecute(String s) {
            shortComments = new Gson().fromJson(s, CommentLab.class).getComments();

            for (int i = 0; i < shortComments.size(); i++) {
                longComments.add(shortComments.get(i));
            }

            mRecyclerView.setAdapter(new CommentAdapter(longComments));
        }
    }
}

