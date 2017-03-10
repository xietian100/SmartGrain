package smartgrain.com.date.fragment;

import android.content.Intent;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.date.activity.NoticeActivity;
import smartgrain.com.date.activity.SeeActivity;
import smartgrain.com.date.activity.TransactionList;
import smartgrain.com.date.bean.HeadlineBean;
import smartgrain.com.date.bean.Transaction;
import smartgrain.com.date.service.NewsListService;
import smartgrain.com.date.service.NoticeService;
import smartgrain.com.date.view.TaobaoHeadline;



/**
 * Created by pc on 2017/3/1.
 */

public class DateFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "DateFragment";
    private Toolbar toolbar;
    private TaobaoHeadline item_transaction;
    private TaobaoHeadline itenm_notice;
    private TaobaoHeadline itenm_see;





    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_date, null);
        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("数据");
        toolbar.setTitleTextColor(Color.WHITE);

        item_transaction= (TaobaoHeadline) view.findViewById(R.id.item_transaction);
        itenm_notice= (TaobaoHeadline) view.findViewById(R.id.itenm_notice);
        itenm_see = (TaobaoHeadline) view.findViewById(R.id.itenm_see1);


        item_transaction.setTitle("交易公告");
        itenm_notice.setTitle("交易通知");
        itenm_see.setTitle("交易观摩");

        itenm_notice.setOnClickListener(this);
        item_transaction.setOnClickListener(this);
        itenm_see.setOnClickListener(this);

        return view;
    }

    @Override
    public void initDate() {
        EventBus.getDefault().register(this);

        /*
        * 开启后台服务，获取交易列表
        * */
        Intent intent1 =new Intent(mContext,NewsListService.class);
        getActivity().startService(intent1);

        /*
        * 开启后台服务，获取交易列表
        * */
        Intent intent2 =new Intent(mContext,NoticeService.class);
        getActivity().startService(intent2);


    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_transaction:
                Intent intent =new Intent(getActivity(),TransactionList.class);
                getActivity().startActivity(intent);
                break;
            case R.id.itenm_notice:
                Intent intent1 =new Intent(getActivity(),NoticeActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.itenm_see1:
              Intent intent2 =new Intent(getActivity(),SeeActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getnewsList(List<Transaction> transactions) {

        Log.e(TAG,"liststart");
        List<HeadlineBean> data1 = new ArrayList<>();
        for (int i = 0; i<3;i++){
            data1.add(new HeadlineBean("", transactions.get(i).getTitle()));
        }

        item_transaction.setData(data1);
        itenm_see.setData(data1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getnewsNotice(NoticeService.Notice notice) {

        List<HeadlineBean> data2 = new ArrayList<>();
        data2.add(new HeadlineBean("", notice.toString()));
        itenm_notice.setData(data2);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
