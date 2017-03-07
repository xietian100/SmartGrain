package smartgrain.com.date.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.adapter.NoticeListViewAdapter;
import smartgrain.com.date.service.NewsDetailService;
import smartgrain.com.date.service.NoticeService;


public class NoticeActivity extends Activity {
    private static final String TAG = "NoticeActivity";
    private Toolbar toolbar;
    private ListView mListView;

    private List<NoticeService.Notice> notices;

    private NoticeListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
       toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("交易通知");
        toolbar.setTitleTextColor(Color.WHITE);

        EventBus.getDefault().register(this);

        mListView= (ListView) findViewById(R.id.mListView);
        Intent intent= new Intent(NoticeActivity.this, NoticeService.class);
        startService(intent);
    }



    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleNotice(NoticeService.Notice notice) {

        if(adapter !=null){
            adapter.notifyDataSetChanged();
            notices.add(notice);
        }else {
            notices=new ArrayList<>();
            notices.add(notice);
            adapter=new NoticeListViewAdapter(NoticeActivity.this,notices);
            mListView.setAdapter(adapter);
        }
    }
}
