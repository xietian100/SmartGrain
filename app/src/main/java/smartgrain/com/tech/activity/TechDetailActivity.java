package smartgrain.com.tech.activity;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import smartgrain.com.R;
import smartgrain.com.tech.adapter.RlAdapter;

/**
 * Created by pc on 2017/3/16.
 */

public class TechDetailActivity extends Activity {
    private static final String TAG = "TechDetailActivity";
    private TextView title;
    private TextView keywords;
    private TextView message;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.techdetail_activity);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        keywords = (TextView) findViewById(R.id.keywords);
        message = (TextView) findViewById(R.id.message);
        image= (ImageView) findViewById(R.id.image);

        String title1 = getIntent().getStringExtra("title");
        String keywords1 = getIntent().getStringExtra("keywords");
        String message1 = getIntent().getStringExtra("message");
       /* message1=message1.replace("&nbsp;","  ");
        message1=message1.replace("<p>","");
        message1=message1.replace("</p>","");
        message1=message1.replace("<br/>","");
        message1=message1.replace("</br>","");*/

        String image1 = getIntent().getStringExtra("image");

        title.setText(title1);
        keywords.setText(keywords1);
        message.setText(Html.fromHtml(message1));
        String base_url="http://tnfs.tngou.net/img";
        Log.e(TAG,base_url+image1);

        Glide.with(getApplicationContext())
                .load(base_url+image1)
                .placeholder(R.drawable.icon)
                .into(image);

    }
}
