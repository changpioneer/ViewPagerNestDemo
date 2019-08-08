package com.pioneer.viewpagernest.indicator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.pioneer.viewpagernest.R;
import com.pioneer.viewpagernest.utils.Tool;

/**
 * ViewPager指示器
 */
public class BannerIndicator extends LinearLayout {

    private int dashGap;
    private int position;
    private static final String TAG = BannerIndicator.class.getSimpleName();
    private int slider_width;
    private int slider_height;
    private int sliderAlign;

    public BannerIndicator(Context context) {
        this(context, null);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicator);
        dashGap = (int) array.getDimension(R.styleable.BannerIndicator_gap, Tool.dp2Px(context, 3));
        slider_width = (int) array.getDimension(R.styleable.BannerIndicator_slider_width,
                Tool.dp2Px(context, 12));
        slider_height = (int) array.getDimension(R.styleable.BannerIndicator_slider_height, Tool.dp2Px(context, 4));
        sliderAlign = array.getInt(R.styleable.BannerIndicator_sleider_align, 1);
        array.recycle();
    }

    //和viewpager联动,根据viewpager页面动态生成相应的小圆点
    public void setUpWidthViewPager(ViewPager viewPager) {
        removeAllViews();
        if (viewPager == null || viewPager.getAdapter() == null || viewPager.getAdapter().getCount() < 2) {
            return;
        }
        position = 0;
        for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
            BannerItemView imageView = new BannerItemView(getContext(), sliderAlign);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(slider_width,
                    slider_height);
            //设置小圆点之间的距离
            if (i > 0) {
                layoutParams.setMargins(dashGap, 0, 0, 0);
                imageView.setAlpha(0.4f);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
                imageView.setAlpha(1);
            }
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
            //默认第一个指示器增大
            setLarge(0);
        }
        //根据viewpager页面切换事件，设置指示器大小变化
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (BannerIndicator.this.position != position) {
                    resetSize(BannerIndicator.this.position, position);
                    BannerIndicator.this.position = position;
                }
            }
        });
    }

    //重置指示器样式
    private void resetSize(int last, int current) {
        setLarge(current);
        setSmall(last);
    }

    //指示器增大同时设置透明度变化
    private void setLarge(int position) {
        if (getChildAt(position) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator animator = getEnlarge((BannerItemView) getChildAt(position));
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 0.4f, 1f);
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }


    //放大动画
    private ValueAnimator getEnlarge(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                0, getOffset(roundRectView));
    }

    //根据大小变化方向获取指示器大小偏移量
    private int getOffset(BannerItemView bannerItemView) {
        int offsest = 0;
        switch (bannerItemView.getLocation()) {
            case BannerItemView.CENTER:
                offsest = (slider_width - slider_height) / 2;
                break;
            case BannerItemView.LEFT:
                offsest = slider_width - slider_height;
                break;
            case BannerItemView.RIGHT:
                offsest = slider_width - slider_height;
                break;
        }
        return offsest;
    }

    //缩小动画
    private ValueAnimator getShrink(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                getOffset(roundRectView), 0);
    }

    //缩小动画同事伴随透明度变化
    public void setSmall(int small) {
        if (getChildAt(small) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 1, 0.4f);
            ValueAnimator animator = getShrink((BannerItemView) getChildAt(small));
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }
}
