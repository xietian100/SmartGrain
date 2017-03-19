package smartgrain.com.weather.activity;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import smartgrain.com.R;
import smartgrain.com.date.net.NetUtils;
import smartgrain.com.weather.adapter.DisaterListAdapter;
import smartgrain.com.weather.bean.DisaterNews;

/**
 * 灾害预警
 * Created by Administrator on 2017/3/18.
 */

public class DisasterWarningActivity extends Activity {
    private static final String TAG = "DisasterWarningActivity";
    private List<Object> disaterNewses;

    private ListView disaterList;
    private DisaterListAdapter adapter;


    //为2种布局定义一个标识
    private final int TEXT = 0;
    private final int NEWS = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster_warning);
        disaterList= (ListView) findViewById(R.id.disaterList);
        String url = getIntent().getStringExtra("url");
        Log.e(TAG, url);
        initDate();
    }



    public void initDate() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://wap.nmc.gov.cn/";
                getDisaterNewsList(url);
            }
        }).start();
    }

    private void getDisaterNewsList(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Document content = Jsoup.parse(doc.toString());
            Element warning_left = content.getElementById("warning_left");
            Elements lis = warning_left.select("ul").select("li");
            disaterNewses=new ArrayList<>();
            for (Element li : lis) {
                Elements div = li.select("div");
                /*
                得到灾害图片地址
                * */
                String pic = div.get(0).attr("class");
                /*
                * 得到灾害新闻地址
                * */
                String htmlurl = div.get(1).select("a").attr("href");
                /*
                * 得到灾害新闻标题
                * */
                String title = div.get(1).select("a").text();
                /*
                *得到灾害新闻时间
                * */
                String time = div.get(1).text().replace(title,"").trim();

                /*
                * 生成灾害新闻集合
                * */
                DisaterNews disaterNews=new DisaterNews();
                disaterNews.setTitle(title);
                disaterNews.setTime(time);
                disaterNews.setPic("http://image.nmc.cn/static2/site/nmc/themes/basic/alarm/"+pic.replace("warning_img ","")+".png");
                disaterNews.setUrl("http://wap.nmc.gov.cn/"+htmlurl);
                disaterNewses.add(disaterNews);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter=new DisaterListAdapter(getApplicationContext(),disaterNewses);
                    disaterList.setAdapter(adapter);
                    disaterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int viewTypeCount = adapter.getItemViewType(position);
                            switch (viewTypeCount){
                                case TEXT:
                                    Log.e(TAG,TEXT+"");
                                    break;
                                case NEWS:
                                    Log.e(TAG,NEWS+"");
                                    int nextTypeCount = adapter.getItemViewType(position+1);
                                    switch (nextTypeCount){
                                        case TEXT:
                                            removeInList(position+1);
                                            break;
                                        case NEWS:
                                            DisaterNews disaterNews = (DisaterNews) disaterNewses.get(position);
                                            Log.e(TAG, disaterNews.getUrl());
                                            getDisaterNewsDetail(disaterNews.getUrl(),position);
                                            break;
                                    }
                                    break;
                            }

                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDisaterNewsDetail(final String newsurl, final int i) {
        smartgrain.com.news.net.NetUtils.getDateFromNet(newsurl, new smartgrain.com.news.net.NetUtils.GetJsonSucced() {
            @Override
            public void getResponse(String response) {
                Document content = Jsoup.parse(response);
                Element alarmtext = content.getElementById("alarmtext");
                String newsdetail = alarmtext.text();
                addInList(newsdetail,i);
            }
        });
    }

    private void addInList(String newsdetail,int i) {
        disaterNewses.add(i+1,newsdetail);
        adapter=new DisaterListAdapter(getApplicationContext(),disaterNewses);
        disaterList.setAdapter(adapter);
    }
    private void removeInList(int i) {
        disaterNewses.remove(i);
        adapter=new DisaterListAdapter(getApplicationContext(),disaterNewses);
        disaterList.setAdapter(adapter);
    }
}
