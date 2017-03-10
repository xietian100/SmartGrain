package smartgrain.com.date.net;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import smartgrain.com.date.bean.AllRaceMarketing;

/**
 * Created by pc on 2017/3/10.
 */

public class NetUtils {
    public static void getRaceMarketing(int i) {
        String url = "http://220.248.203.59:8686/rtp/data/race/getRaceMeeting.jsp?id="+i;
        smartgrain.com.news.net.NetUtils.getDateFromNet(url, new smartgrain.com.news.net.NetUtils.GetJsonSucced() {
            @Override
            public void getResponse(String response) {
                if (response != null) {

                }
            }

        });

    }
}
