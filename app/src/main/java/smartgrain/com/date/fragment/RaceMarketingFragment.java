package smartgrain.com.date.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import smartgrain.com.R;
import smartgrain.com.date.bean.AllRaceMarketing;
import smartgrain.com.news.net.NetUtils;
import smartgrain.com.news.viewpager.ViewPageBaseFragment;

/**
 * Created by pc on 2017/3/10.
 */
public class RaceMarketingFragment extends ViewPageBaseFragment {


    private static final String TAG = "RaceMarketingFragment";
    AllRaceMarketing allRaceMarketing;
    private TextView name;
    private TextView matket;
    private TextView jie;
    private TextView state;
    private String s;
    private String jiedian;


    public RaceMarketingFragment(AllRaceMarketing allRaceMarketing) {
        super(allRaceMarketing.getName());
        this.allRaceMarketing = allRaceMarketing;
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity().getApplicationContext(), R.layout.fragment_racemarketing, null);
        name= (TextView) view.findViewById(R.id.name);
        matket= (TextView) view.findViewById(R.id.matket);
        jie= (TextView) view.findViewById(R.id.jie);
        state= (TextView) view.findViewById(R.id.state);
        return view;
    }

    @Override
    public void initDate() {
        getRaceMarketing();

    }

    public void getRaceMarketing() {
        String url = "http://220.248.203.59:8686/rtp/data/race/getRaceMeeting.jsp?id=" + allRaceMarketing.getNumber();
        NetUtils.getDateFromNet(url, new NetUtils.GetJsonSucced() {
            @Override
            public void getResponse(String response) {
                response = response.replace("[", "");
                response = response.replace("]", "");
                response = response.replace("", "");
                response = response.replace("]", "");
                response = response.replace("\"", "");
                String[] sa = response.split(",");
                s = sa[0];
                jiedian = sa[3];
                name.setText(sa[1]);
                matket.setText(allRaceMarketing.getName());
                jie.setText(sa[3]);
                state.setText(sa[2]);
                getRaceTacheDetail(s,jiedian);
            }
        });
    }

    public void getRaceTacheDetail(String s,String jiedian) {
        String url="http://220.248.203.59:8686/rtp/data/race/getRaceTacheDetail.jsp?id="+s+"-"+jiedian;
        NetUtils.getDateFromNet(url, new NetUtils.GetJsonSucced() {
            @Override
            public void getResponse(String response) {
                if(response!=null){
//                    TODO
                }
            }
        });
    }
}
