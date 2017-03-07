package smartgrain.com.date.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.adapter.TransactionListViewAdapter;
import smartgrain.com.date.bean.Transaction;


public class TransactionList extends Activity {

    private Toolbar toolbar;
    private List<Transaction> transactionList;
    private ListView transactionListView;
    private TransactionListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("交易公告");
        toolbar.setTitleTextColor(Color.WHITE);
        transactionListView= (ListView) findViewById(R.id.transactionListView);
        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //在ui线程执行
    public void onUserEvent(List<Transaction> transactions) {
        ininListView(transactions);
    }

    private void ininListView(final List<Transaction> transactions) {
        adapter=new TransactionListViewAdapter(getApplication().getApplicationContext(),transactions);
        transactionListView.setAdapter(adapter);
        transactionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = transactions.get(i).getUrl();
                String title = transactions.get(i).getTitle();
                Intent intent =new Intent(TransactionList.this,TransactionDeatilActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }



}
