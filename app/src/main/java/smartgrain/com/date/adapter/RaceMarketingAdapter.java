package smartgrain.com.date.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.View;

import java.util.List;

import smartgrain.com.news.viewpager.ViewPageBaseFragment;

/**
 * Created by pc on 2017/3/10.
 */
public class RaceMarketingAdapter extends FragmentPagerAdapter {

    private  List<ViewPageBaseFragment> fragments;


    public RaceMarketingAdapter(FragmentManager fm, List<ViewPageBaseFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
