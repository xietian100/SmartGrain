package smartgrain.com.date.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DateService extends Service {
    private static final String TAG = "DateService";
    Document doc;
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
        grabDate();
    }

    private void grabDate() {
        String url="http://www.gngrain.com/NewsList.Asp?SortID=7";
        try {
            doc = Jsoup.connect(url).timeout(5000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document content = Jsoup.parse(doc.toString());
        Elements table = content.select("class.NewsListPage");

        Log.e(TAG,table.toString());


    }
}
