package smartgrain.com.date.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class NoticeService extends IntentService {


    private static final String TAG ="NoticeService" ;

    public NoticeService() {
        super("NoticeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initDate();
    }

    private void initDate() {
        try {
            String url = "http://www.gngrain.com/Price.asp?SortID=1";
            Document doc = Jsoup.connect(url).timeout(5000).post();
            Document content = Jsoup.parse(doc.toString());
//            String span = content.select("td").select("[height*= 29]").;
            Elements tables = content.select("div.right_content");
            Elements tab = tables.select("table").select("[bgcolor*=#eaeaea]");
            Elements trs = tab.select("tr");

            for (Element tr:trs){
                Elements tds = tr.select("td");
                String location = tds.get(0).text();
                String product = tds.get(1).text();
                String time1 = tds.get(2).text();
                String time2 = tds.get(3).text();

                Notice notice=new Notice();
                notice.setLocation(location);
                notice.setProduct(product);
                notice.setTime1(time1);
                notice.setTime2(time2);

                EventBus.getDefault().post(notice);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Notice{
            String location;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getTime1() {
            return time1;
        }

        public void setTime1(String time1) {
            this.time1 = time1;
        }

        public String getTime2() {
            return time2;
        }

        public void setTime2(String time2) {
            this.time2 = time2;
        }

        String product;
            String time1;
            String time2;
    }


}
