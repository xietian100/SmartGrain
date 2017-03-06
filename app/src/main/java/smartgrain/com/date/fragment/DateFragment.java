package smartgrain.com.date.fragment;

import android.content.Intent;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;


import smartgrain.com.R;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.date.activity.TransactionList;
import smartgrain.com.date.service.DateService;

/**
 * Created by pc on 2017/3/1.
 */

public class DateFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "DateFragment";
    private Toolbar toolbar;
    private LinearLayout item_transaction;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_date, null);
        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("数据");
        toolbar.setTitleTextColor(Color.WHITE);
        item_transaction= (LinearLayout) view.findViewById(R.id.item_transaction);
        item_transaction.setOnClickListener(this);
        return view;
    }

    @Override
    public void initDate() {
        /*
        * 开启后台服务，获取交易列表
        * */
        Intent intent =new Intent(mContext,DateService.class);
        getActivity().startService(intent);
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
        }
    }
}
