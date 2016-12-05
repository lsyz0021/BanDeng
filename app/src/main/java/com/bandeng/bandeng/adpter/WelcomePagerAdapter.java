package com.bandeng.bandeng.adpter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Lilu on 2016/12/4.
 */
public class WelcomePagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> lists;

    public WelcomePagerAdapter(ArrayList<ImageView> lists) {
        this.lists = lists;
    }

    //返回要滑动的View的个数
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "标题"+position;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = lists.get(position);
        //1、将当前视图添加到container中
        container.addView(imageView);
        //2、返回当前View
        return imageView;
    }

    //从当前container中删除指定位置（position）的View
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}

