package smartgrain.com.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;


import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.date.fragment.DateFragment;
import smartgrain.com.mine.fragment.MineFragment;
import smartgrain.com.news.fragment.NewsFragment;
import smartgrain.com.tech.fragment.TechFragment;

public class MainActivity extends FragmentActivity {

    FrameLayout mFrameLayout;

    RadioGroup mRadioGroup;
    private ArrayList<BaseFragment> fragments;
    private int position;
    /**
     * 缓存的Fragemnt或者上次显示的Fragment
     */
    private Fragment tempFragemnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout= (FrameLayout) findViewById(R.id.fm_contain);
        mRadioGroup= (RadioGroup) findViewById(R.id.rg_main_activity);


        initFragment();
        initListener();
    }

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_news:
                        position = 0;
                        break;
                    case R.id.rb_datas:
                        position = 1;
                        break;
                    case R.id.rb_tech:
                        position = 2;
                        break;
                    case R.id.rb_mine:
                        position = 3;
                        break;

                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragemnt, baseFragment);
            }
        });
        mRadioGroup.check(R.id.rb_news);

    }

    private void initFragment() {
        fragments = new ArrayList();
        fragments.add(new NewsFragment());
        fragments.add(new DateFragment());
        fragments.add(new TechFragment());
        fragments.add(new MineFragment());

    }
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fm_contain, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


}
