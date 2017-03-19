package smartgrain.com.weather.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;
import smartgrain.com.tech.listener.OnRecylerViewItemClickListener;
import smartgrain.com.weather.activity.DisasterWarningActivity;
import smartgrain.com.weather.activity.PrecipitationActivity;
import smartgrain.com.weather.activity.WeatherBullerActivity;
import smartgrain.com.weather.adapter.RecyclerViewAdapter;
import smartgrain.com.weather.bean.WeatherForecast;

/**
 * Created by pc on 2017/3/1.
 */

public class WeatherFragment extends BaseFragment {

    private static final String TAG = "WeatherFragment";

    private List<ViewPageBaseFragment> fragments;
    private RecyclerView recyclerView;

    private List<WeatherForecast> itemlist;

    private String url = "http://wap.nmc.gov.cn/publish/weather-bulletin/index.htm";

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_weather, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addOnItemTouchListener(new OnRecylerViewItemClickListener(getActivity(), recyclerView, new OnRecylerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(mContext, WeatherBullerActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(mContext, PrecipitationActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(mContext, DisasterWarningActivity.class);
                        intent2.putExtra("url", itemlist.get(position).getUrl());
                        startActivity(intent2);
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

        }));
        return view;
    }

    @Override
    public void initDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getItemList(url);
            }
        }).start();
    }

    private void getItemList(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Document content = Jsoup.parse(doc.toString());
            Elements items = content.getElementsByClass("dropdown-menu").select("a");
            if (items != null) {
                itemlist = new ArrayList<>();
                for (Element item : items) {
                    Log.e(TAG, item.text());
                    Log.e(TAG, item.attr("href"));
                    WeatherForecast forecast = new WeatherForecast();
                    forecast.setName(item.text());
                    forecast.setUrl("http://wap.nmc.gov.cn/" + item.attr("href"));
                    itemlist.add(forecast);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initRecyclerViewAdapter(itemlist);
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerViewAdapter(List<WeatherForecast> itemlist) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext, itemlist);
        recyclerView.setAdapter(adapter);
    }
}
