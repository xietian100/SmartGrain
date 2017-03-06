package smartgrain.com.date.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import smartgrain.com.date.bean.Transaction;

public class DateService extends Service {
    private static final String TAG = "DateService";
    private List<Transaction> transactions;

    /*
    * 网址集合
    * */
    private List<String> herfList;

    /*
    * 标题集合
    * */
    private List<String> titleList;
    /*
    * 日期集合
    * */
    private List<String> dataList;

    public DateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        * 开启子线程抓取新闻标题页面
        * 得到title
        * 得到url
        * 得到data
        * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                grabNewsList();
            }
        }).start();
    }

    private void grabNewsList() {
        Document doc;

        herfList=new ArrayList<>();
        titleList=new ArrayList<>();
        dataList=new ArrayList<>();
        String url="http://www.gngrain.com/NewsList.Asp?SortID=7";
        try {
            doc = Jsoup.connect(url).timeout(5000).post();
            Document content = Jsoup.parse(doc.toString());
            Elements table = content.getElementsByClass("NewsListPage");
            Elements tbody = table.select("tbody");
            Elements herfs = tbody.select("a");
            for (Element a:herfs){
                String href = "http://www.gngrain.com/"+a.attr("href");
                herfList.add(href);
                Log.e(TAG,href);
            }

            Elements tds = tbody.select("td");
            int i= 1;
            for (Element td:tds){
                i++;
                String text = td.text();
                if (((i%2)==1)){
                    titleList.add(text);
                }else {
                    dataList.add(text);
                }
            }
            /*
            * 拼接新闻对象
            * */
            transactions = new ArrayList<>();

            for (int j = 0; j<titleList.size();j++){
                Transaction transaction=new Transaction();
                transaction.setTitle(titleList.get(j));
                transaction.setData(dataList.get(j));
                transaction.setUrl(herfList.get(j));
                transactions.add(transaction);
            }
            EventBus.getDefault().postSticky(transactions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
