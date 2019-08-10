package com.pioneer.viewpagernest.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pioneer.viewpagernest.R;
import com.pioneer.viewpagernest.viewpagerdemo.indicator.SimpleIndicator;

public class SencondFragment extends Fragment {

    private static final int[] PICTURES = {R.mipmap.first1, R.mipmap.first2, R.mipmap.first3};
    private ViewPager viewPager;
    private SimpleIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sencond, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indicator = view.findViewById(R.id.indicator_simple);
        viewPager = view.findViewById(R.id.sen_viewpager);
        MyViewpagerAdapter adapter = new MyViewpagerAdapter(this.getContext());
        viewPager.setAdapter(adapter);
        indicator.setUpWidthViewPager(viewPager);
    }

    class MyViewpagerAdapter extends PagerAdapter {

        private Context context;

        public MyViewpagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return PICTURES.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = View.inflate(context, R.layout.item_viewpager, null);
            ImageView imageView = itemView.findViewById(R.id.item_image);
            imageView.setBackgroundResource(PICTURES[position]);
            container.addView(itemView);
            return itemView;
        }
    }
}
