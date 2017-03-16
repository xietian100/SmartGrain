package smartgrain.com.tech.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.app.utils.GsonTools;
import smartgrain.com.base.BaseFragment;
import smartgrain.com.news.net.NetUtils;
import smartgrain.com.tech.activity.TechDetailActivity;
import smartgrain.com.tech.adapter.RlAdapter;
import smartgrain.com.tech.bean.TechListBean;
import smartgrain.com.tech.listener.OnRecylerViewItemClickListener;

/**
 * Created by pc on 2017/3/1.
 */

public class TechFragment extends BaseFragment {
    private static final String TAG = "TechFragment";
    private RecyclerView recyclerView;
    private RlAdapter rlAdapter;
    private GridLayoutManager mLayoutManager;
    private List<TechListBean.ListBean> list;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tech, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.rl);
        return view;
    }

    @Override
    public void initDate() {
        String url = "http://www.tngou.net/api/tech/news";
        NetUtils.getDateFromNet(url, new NetUtils.GetJsonSucced() {
            @Override
                public void getResponse(String response) {
                    Log.e(TAG, response);
                    if (response != null) {
                        TechListBean techListBean = GsonTools.changeGsonToBean(response, TechListBean.class);
                        list = techListBean.getList();
                        rlAdapter = new RlAdapter(mContext, list);
                        recyclerView.setAdapter(rlAdapter);
                        int spanCount = 3;
                   /* mLayoutManager = new StaggeredGridLayoutManager(
                            spanCount,
                            StaggeredGridLayoutManager.VERTICAL);*/
                    mLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
                    recyclerView.setLayoutManager(mLayoutManager);

                    recyclerView.addOnItemTouchListener(new OnRecylerViewItemClickListener(getActivity(), recyclerView, new OnRecylerViewItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent=new Intent(mContext,TechDetailActivity.class);
                            intent.putExtra("title",list.get(position).getTitle());
                            String text=list.get(position).getTitle();
                            Log.e(TAG,text);
                            intent.putExtra("keywords",list.get(position).getKeywords());
                            intent.putExtra("message",list.get(position).getMessage());
                            intent.putExtra("image",list.get(position).getImg());
                            startActivity(intent);
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
                            // ...
                        }
                    }));
                }
            }
        });
    }
}
