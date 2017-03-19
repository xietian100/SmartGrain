package smartgrain.com.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import smartgrain.com.R;

/**
 * Created by Administrator on 2017/3/18.
 */

public class PrecipitationActivity extends Activity{
    private static final String TAG = "WeatherBullerActivity";
    private WebView webview;
    /*
    * 每日气象提示
    * */
    private String url="http://wap.nmc.gov.cn/publish/weatherperday/index.htm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_bulletin);
        webview= (WebView) findViewById(R.id.webview);
        initDate();
    }

    private void initDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getArticl(url);
            }
        }).start();
    }

    private void getArticl(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(5000).get();
            Document content = Jsoup.parse(doc.toString());
            Log.e(TAG,content.text());

            Elements writing = content.getElementsByClass("writing");
            final String html = writing.html();
            Log.e(TAG,html);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initWebView(html);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initWebView(String html) {
        webview.loadDataWithBaseURL(null,html, "text/html", "utf-8", null);
        webview.getSettings().setDisplayZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
    }
}
