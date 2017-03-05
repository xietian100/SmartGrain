package smartgrain.com.date.fragment;

import android.content.Intent;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import smartgrain.com.base.BaseFragment;
import smartgrain.com.date.service.DateService;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by pc on 2017/3/1.
 */

public class DateFragment extends BaseFragment {
    public static final String TAG = "DateFragment";
    private TextView textView;
    @Override
    public View initView() {

        textView=new TextView(mContext);

        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initDate() {
//        getActivity().startService(new Intent(mContext,))
        new Thread(new Runnable() {
            @Override
            public void run() {
                grabDate();
            }
        }).start();

    }

    private void grabDate() {
        Document doc;
        String url="http://www.gngrain.com/NewsList.Asp?SortID=7";
        try {
            doc = Jsoup.connect(url).timeout(5000).post();
            Document content = Jsoup.parse(doc.toString());
            Elements table = content.getElementsByClass("NewsListPage");
            Elements tbody = table.select("tbody");
            Log.e(TAG,tbody.text());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
