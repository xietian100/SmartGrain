package smartgrain.com.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.news.bean.SNBEAN;

/**
 * Created by pc on 2017/3/2.
 */

public class HotFragmentListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private  List<SNBEAN.News> news;

    public HotFragmentListViewAdapter(Context mContext,List<SNBEAN.News> news) {
        this.news=news;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView=View.inflate(mContext, R.layout.item_listview_hotfragment,null);

            viewHolder=new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.digest= (TextView) convertView.findViewById(R.id.digest);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
    }
        viewHolder.title.setText(news.get(i).getTitle());
        viewHolder.digest.setText(news.get(i).getDigest());
        Glide.with(mContext)
                .load(news.get(i).getImgsrc())
                .placeholder(R.drawable.icon)
                .into(viewHolder.img);


        return convertView;
}
    static class ViewHolder  {
        ImageView img;
        TextView title;
        TextView digest;
    }
}
