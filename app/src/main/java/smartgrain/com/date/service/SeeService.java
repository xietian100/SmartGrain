package smartgrain.com.date.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import smartgrain.com.date.bean.AllRaceMarketing;
import smartgrain.com.news.net.NetUtils;

/**
 * Created by pc on 2017/3/10.
 */

public class SeeService extends Service {
    private static final String TAG = "SeeService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAllRaceMarket();

    }

    private void getAllRaceMarket() {
        String url = "http://220.248.203.59:8686/rtp/data/race/getAllRaceMarketing.jsp";
        NetUtils.getDateFromNet(url, new NetUtils.GetJsonSucced() {
            @Override
            public void getResponse(String response) {
                Log.e(TAG,response);
                if(response != null){
                    List<AllRaceMarketing> allRaceMarketings = parseResponse(response);
                    for (int i = 0; i<allRaceMarketings.size();i++){
                        EventBus.getDefault().post(allRaceMarketings.get(i));
                    }
                }
            }

        });



    }

    private List<AllRaceMarketing> parseResponse(String response){
        List<AllRaceMarketing> allRaceMarketings=new ArrayList<AllRaceMarketing>();
        AllRaceMarketing allRaceMarketing=new AllRaceMarketing();

        response = response.replace("[", "");
        response = response.replace("]", "");
        response = response.replace("", "");
        response = response.replace("]", "");
        response = response.replace("\"", "");

        Log.e(TAG,response);
        String[] sa=response.split(",");

        for (int i = 0 ; i<sa.length ;i ++){
            String s = sa[i];
            if(i == 0){
                allRaceMarketing.setNumber(s);
            }else if((i%2) == 1){
                allRaceMarketing.setName(s);
                allRaceMarketings.add(allRaceMarketing);
            }else {
                allRaceMarketing.setNumber(s);
            }
            Log.e(TAG,allRaceMarketing.toString());
            Log.e(TAG,allRaceMarketings.size()+"");

        }
        return allRaceMarketings;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
