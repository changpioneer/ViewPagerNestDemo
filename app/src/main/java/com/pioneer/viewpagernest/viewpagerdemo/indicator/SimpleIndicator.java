package com.pioneer.viewpagernest.viewpagerdemo.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.pioneer.viewpagernest.utils.Tool;

public class SimpleIndicator extends LinearLayout {

    private static final int DOT_GAP = 20;//点与点的间隙
    public static final int DOT_WIDTH = 30;//圆点的直径
    private int position;

    public SimpleIndicator(Context context) {
        this(context, null);
    }

    public SimpleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    /**
     * 和viewpager联动,根据viewpager页面动态生成相应的小圆点
     */
    public void setUpWidthViewPager(ViewPager viewPager) {
        removeAllViews();
        if (viewPager == null || viewPager.getAdapter() == null || viewPager.getAdapter().getCount() < 2) {
            return;
        }

        position = 0;
        for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
            SimpleItemView imageView = new SimpleItemView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DOT_WIDTH, DOT_WIDTH);
            //设置小圆点之间的距离
            if (i > 0) {
                layoutParams.setMargins(Tool.dp2Px(getContext(), DOT_GAP), 0, 0, 0);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
            //默认第一个指示器选中
            setSelected(0);
        }
        //根据viewpager页面切换事件，设置指示器大小变化
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (SimpleIndicator.this.position != position) {
                    toggleSelected(SimpleIndicator.this.position, position);
                    SimpleIndicator.this.position = position;
                }
            }
        });
    }


    private void setSelected(int position) {
        SimpleItemView child = (SimpleItemView) getChildAt(position);
        child.setSelected(true);
    }

    private void toggleSelected(int prePosition, int currPosition) {
        SimpleItemView preChild = (SimpleItemView) getChildAt(prePosition);
        preChild.setSelected(false);
        setSelected(currPosition);
    }

}
