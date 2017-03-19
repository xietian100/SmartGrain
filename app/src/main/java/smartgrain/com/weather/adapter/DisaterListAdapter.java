package smartgrain.com.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.news.net.NetUtils;
import smartgrain.com.tech.adapter.RlAdapter;
import smartgrain.com.weather.bean.DisaterNews;

/**
 * Created by Administrator on 2017/3/18.
 */

public class DisaterListAdapter extends BaseAdapter {
    private final List<Object> list;
    private final Context context;
    //为2种布局定义一个标识
    private final int TEXT = 0;
    private final int NEWS = 1;

    public DisaterListAdapter(Context context, List<Object> disaterNewses) {
        this.list = disaterNewses;
        this.context = context;
    }


    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法必须重写，它返回了有几种不同的布局
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    // 每个convertView都会调用此方法，获得当前应该加载的布局样式
    @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局

        if (list.get(position) instanceof String) {
            return TEXT;
        } else {
            return NEWS;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextViewHolder holder1 = null;
        NewsViewHolder holder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TEXT:
                    convertView=View.inflate(context,R.layout.item_listview_disaterlist_text,null);
                    holder1=new TextViewHolder();
                    holder1.content= (TextView) convertView.findViewById(R.id.content);
                    convertView.setTag(holder1);
                    break;
                case NEWS:
                    convertView = View.inflate(context, R.layout.item_listview_disaterlist, null);
                    holder2 = new NewsViewHolder();
                    holder2.pic = (ImageView) convertView.findViewById(R.id.pic);
                    holder2.time = (TextView) convertView.findViewById(R.id.time);
                    holder2.title = (TextView) convertView.findViewById(R.id.title);
                    convertView.setTag(holder2);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TEXT:
                    holder1 = (TextViewHolder) convertView.getTag();
                    break;
                case NEWS:
                    holder2 = (NewsViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TEXT:
                String text = (String) list.get(position);
                holder1.content.setText(text);
                break;
            case NEWS:
                DisaterNews disaterNews = (DisaterNews) list.get(position);
                holder2.time.setText(disaterNews.getTime());
                holder2.title.setText(disaterNews.getTitle());
                Glide.with(context)
                        .load(disaterNews.getPic())
                        .placeholder(R.drawable.icon)
                        .into(holder2.pic);
                break;
        }
        return convertView;
    }

}


class TextViewHolder {
    TextView content;
}

class NewsViewHolder {
    ImageView pic;
    TextView title;
    TextView time;
}
