package smartgrain.com.date.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import smartgrain.com.R;
import smartgrain.com.date.service.NewsDetailService;
import smartgrain.com.date.view.XRTextView;


public class TransactionDeatilActivity extends Activity {
    private static final String TAG ="TransactionDeatil" ;
    private Toolbar toolbar;
    private XRTextView content;
    private Button download;

    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_deatil);
        String url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        Log.e(TAG,url+title);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title.replace("·",""));
        toolbar.setTitleTextColor(Color.WHITE);

        content= (XRTextView) findViewById(R.id.content);
        download= (Button) findViewById(R.id.download);

        EventBus.getDefault().register(this);

        Intent intent= new Intent(TransactionDeatilActivity.this, NewsDetailService.class);
        intent.putExtra("url",url);
        startService(intent);
    }



    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(final NewsDetailService.NewsDetail event) {
        content.setText(event.getNewsDetail());
        if(event.getQingdan_url()!=null){
            download.setVisibility(View.VISIBLE);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    downloadxls(event,title);
                }
            });
        }else {
            download.setVisibility(View.VISIBLE);
        }
    }

    private void downloadxls(NewsDetailService.NewsDetail event,String title) {
        String qingdan_url = event.getQingdan_url();

        OkHttpUtils.get().url(qingdan_url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),title) {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(TransactionDeatilActivity.this,"文件下载失败",Toast.LENGTH_SHORT).show();
                Log.e(TAG,e.toString());

            }
            @Override
            public void onResponse(File response, int id) {
                Toast.makeText(TransactionDeatilActivity.this,"文件下载成功",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
