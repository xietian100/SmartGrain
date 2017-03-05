package smartgrain.com.news.fragment;


import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;

import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.news.adapter.mViewPagerAdapter;
import smartgrain.com.news.viewpager.BroadCastFragment;
import smartgrain.com.news.viewpager.HotFragment;
import smartgrain.com.news.viewpager.PolicyFragment;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;

/**
 * Created by pc on 2017/3/1.
 */

public class NewsFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private mViewPagerAdapter adapter;
    private ArrayList<ViewPageBaseFragment> fragments;
    private Toolbar toolbar;



    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_news, null);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("新闻");
        toolbar.setTitleTextColor(Color.WHITE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    @Override
    public void initDate() {
        initFragment();
        adapter=new mViewPagerAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);

    }

    private void initFragment() {
        fragments=new ArrayList();
        fragments.add(new HotFragment("最新"));
        fragments.add(new BroadCastFragment("最热"));
        fragments.add(new PolicyFragment("政策"));
    }



    }
