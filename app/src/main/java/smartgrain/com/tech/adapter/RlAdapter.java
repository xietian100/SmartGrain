package smartgrain.com.tech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.tech.bean.TechListBean;

/**
 * Created by pc on 2017/3/13.
 */

public class RlAdapter extends RecyclerView.Adapter {
    private final LayoutInflater layoutInflater;
    private  List<TechListBean.ListBean> list;
    private  Context mContext;

    public RlAdapter(Context mContext, List<TechListBean.ListBean> list) {
        this.list=list;
        this.mContext=mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_techfragment,null);
        ItemViewHolder itemView=new ItemViewHolder(view);
        return itemView;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String base_url=      "http://tnfs.tngou.net/img";
        ((ItemViewHolder)holder).title.setText(list.get(position).getTitle());
        ((ItemViewHolder)holder).desc.setText(list.get(position).getDescription());
//        ((ItemViewHolder)holder).img.setImageResource(R.drawable.icon);
        Glide.with(mContext)
                .load(base_url+list.get(position).getImg())
                .placeholder(R.drawable.icon)
                .into(((ItemViewHolder)holder).img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView desc;


        public ItemViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.image);
            title= (TextView) itemView.findViewById(R.id.title);
            desc= (TextView) itemView.findViewById(R.id.desc);
        }
    }


}
