package smartgrain.com.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import smartgrain.com.base.BaseFragment;
import smartgrain.com.news.viewpager.HotFragment;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;

/**
 * Created by pc on 2017/3/1.
 */

public class mViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<ViewPageBaseFragment> fragments;
    public mViewPagerAdapter(FragmentManager fm, ArrayList<ViewPageBaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
