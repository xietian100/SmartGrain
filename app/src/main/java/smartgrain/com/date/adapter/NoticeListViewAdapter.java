package smartgrain.com.date.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.bean.Transaction;
import smartgrain.com.date.service.NoticeService;

/**
 * Created by pc on 2017/3/7.
 */

public class NoticeListViewAdapter extends BaseAdapter {
    private final List<NoticeService.Notice> notices;
    private final Context mContext;

    public NoticeListViewAdapter(Context context, List<NoticeService.Notice> notices) {
        this.notices=notices;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return notices.size();
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
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertview == null){
            convertview=View.inflate(mContext, R.layout.item_listview_notice,null);
            viewHolder=new ViewHolder();
            viewHolder.location= (TextView) convertview.findViewById(R.id.location);
            viewHolder.product= (TextView) convertview.findViewById(R.id.product);
            viewHolder.time1= (TextView) convertview.findViewById(R.id.time1);
            viewHolder.time2= (TextView) convertview.findViewById(R.id.time2);

            convertview.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertview.getTag();
        }
        viewHolder.location.setText(notices.get(i).getLocation());
        viewHolder.product.setText(notices.get(i).getProduct());
        viewHolder.time1.setText(notices.get(i).getTime1());
        viewHolder.time2.setText(notices.get(i).getTime2());
        return convertview;
    }
    static class ViewHolder{
        TextView location;
        TextView product;
        TextView time1;
        TextView time2;
    }
}
