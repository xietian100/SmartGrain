package smartgrain.com.weather.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;
import smartgrain.com.weather.adapter.RecyclerViewAdapter;

/**
 * Created by pc on 2017/3/1.
 */

public class WeatherFragment extends BaseFragment {

    private static final String TAG = "WeatherFragment";

    private List<ViewPageBaseFragment> fragments;
    private RecyclerView recyclerView;

    @Override
    public View initView() {

       View view =View.inflate(mContext, R.layout.fragment_weather,null);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

        @Override
        public void initDate() {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext );
            RecyclerViewAdapter adapter=new RecyclerViewAdapter(mContext);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            layoutManager.setOrientation(OrientationHelper. VERTICAL);

        }


}
