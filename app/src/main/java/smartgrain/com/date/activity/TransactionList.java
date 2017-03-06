package smartgrain.com.date.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.bean.Transaction;


public class TransactionList extends Activity {

    private Toolbar toolbar;
    private TextView textView;
    private List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        textView= (TextView) findViewById(R.id.text);
        EventBus.getDefault().register(this);
        toolbar.setTitle("交易公告");
        transactions=new ArrayList<>();
//        toolbar.setTitleTextColor(Color.WHITE);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //在ui线程执行
    public void onUserEvent(List<Transaction> transactions) {
        textView.setText(transactions.toString());
        this.transactions=transactions;
    }

}
