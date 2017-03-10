package smartgrain.com.date.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.adapter.RaceMarketingAdapter;
import smartgrain.com.date.bean.AllRaceMarketing;
import smartgrain.com.date.fragment.RaceMarketingFragment;
import smartgrain.com.date.service.SeeService;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;

/**
 * Created by pc on 2017/3/8.
 */
public class SeeActivity extends FragmentActivity {
    private static final String TAG = "SeeActivity";
    private Toolbar toolbar;
    private TabLayout mTab;
    private ViewPager mViewPager;

    private List<ViewPageBaseFragment> fragments;
    private RaceMarketingAdapter raceMarketingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaciton_see);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("交易观摩");
        toolbar.setTitleTextColor(Color.WHITE);

        Intent intent =new Intent(SeeActivity.this, SeeService.class);
        startService(intent);

        EventBus.getDefault().register(this);
        Log.e(TAG, "SEE START");
        initView();

    }


    private void initView() {
        mTab= (TabLayout) findViewById(R.id.mTab);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        mTab.setupWithViewPager(mViewPager);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public  void AllRaceMarketing(AllRaceMarketing allRaceMarketing ) {
       mTab.addTab(mTab.newTab().setText(allRaceMarketing.getName()));

        if(fragments == null){
            fragments= new ArrayList<>();
            RaceMarketingFragment raceMarketingFragment =new RaceMarketingFragment(allRaceMarketing);
            fragments.add(raceMarketingFragment);
        }else {
            RaceMarketingFragment raceMarketingFragment =new RaceMarketingFragment(allRaceMarketing);
            fragments.add(raceMarketingFragment);
        }

        if(raceMarketingAdapter == null){
            raceMarketingAdapter =new RaceMarketingAdapter(getSupportFragmentManager(),fragments);
            mViewPager.setAdapter(raceMarketingAdapter);
        }else {
            raceMarketingAdapter.notifyDataSetChanged();
        }
    }
    

    


}
