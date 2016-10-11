package com.example.clown.dailyzhihu.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clown.dailyzhihu.R;

/**
 * Created by Clown on 2016/5/28.
 */
public class NavSubMenu extends RelativeLayout{

    private LayoutParams mImageViewLayoutParams;
    private LayoutParams mTextViewLayoutParams;
    private Drawable mMenuImage;
    private String mMenuText;
    private int mMenuTextColor;
    private float mMenuTextSize;

    private ImageView mImageView;
    private TextView mTextView;
    private onNavSubMenuClickListener mListener;

    public NavSubMenu(Context context) {
        super(context);
    }

    public NavSubMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.nav_sub_menu);
        mMenuImage = ta.getDrawable(R.styleable.nav_sub_menu_menu_image);
        mMenuText = ta.getString(R.styleable.nav_sub_menu_menu_text);
        mMenuTextColor = ta.getColor(R.styleable.nav_sub_menu_menu_textColor, 0);
        mMenuTextSize = ta.getDimension(R.styleable.nav_sub_menu_menu_textSize, 10);

        ta.recycle();

        mImageView = new ImageView(context);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onclick();
            }
        });
        mTextView = new TextView(context);
        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onclick();
            }
        });

        mImageView.setImageDrawable(mMenuImage);
        mTextView.setText(mMenuText);
        mTextView.setTextColor(mMenuTextColor);
        mTextView.setTextSize(mMenuTextSize);

        mImageViewLayoutParams = new LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mImageViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mImageView, mImageViewLayoutParams);

        mTextViewLayoutParams = new LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mTextViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mTextView, mTextViewLayoutParams);
    }

    public NavSubMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface onNavSubMenuClickListener{
        void onclick();
    }
}
