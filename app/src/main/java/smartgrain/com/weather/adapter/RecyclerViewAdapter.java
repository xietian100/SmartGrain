package smartgrain.com.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartgrain.com.R;


/**
 * Created by pc on 2017/3/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {


    private final LayoutInflater layoutInflater;
    private ArrayList<String> list;

    public RecyclerViewAdapter(Context mContext) {
        list=new ArrayList<>();
        list.add("天气公报");
        list.add("1-7天降水预报");
        list.add("1-7天气温预报");
        list.add("天气公报");
        layoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_fragment_weather,null);
        itemViewHolder viewHolder=new itemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((itemViewHolder)holder).title.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder{

            TextView title;
        public itemViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
        }
    }


}
