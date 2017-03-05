package smartgrain.com.news.viewpager;


import android.nfc.Tag;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import smartgrain.com.R;
import smartgrain.com.app.utils.GsonTools;
import smartgrain.com.news.adapter.HotFragmentListViewAdapter;
import smartgrain.com.news.bean.SNBEAN;
import smartgrain.com.news.constants.Constans;
import smartgrain.com.news.net.NetUtils;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;


/**
 * Created by pc on 2017/3/1.
 */

public class HotFragment extends ViewPageBaseFragment {

    private static int start = 0;
    private static int end = 0;


    public Banner banner;
    private PullToRefreshListView listview;


    private static HotFragmentListViewAdapter adapter;


    private static List<SNBEAN.News> news;

    public static String SN_URL = "http://219.232.243.58:83/meilisannong/server/index.php/category_interface/getNewsAndPic?lid=544&tid=null&start=" + start + "&end=" + start + 10;


    private  int refreshState ;
    private int REFRESHING = 0;
    private int UNREFRESHING = 1;



    public HotFragment(String title) {
        super(title);
    }


    @Override
    View initView() {
        View view = View.inflate(mContext, R.layout.fragment_news_hot, null);
        banner = (Banner) view.findViewById(R.id.banner);
        listview = (PullToRefreshListView) view.findViewById(R.id.mListView);
        return view;
    }

    @Override
    protected void initDate() {
        NetUtils.getDateFromNet(Constans.SN_URL,
                new NetUtils.GetJsonSucced() {
                    @Override
                    public void getResponse(String response) {
                        ProcessDate(response);
                    }
                });
        start = 11;
        end = 20;



    }


    private void ProcessDate(String response) {
        if (!TextUtils.isEmpty(response)) {
            SNBEAN snbean = GsonTools.changeGsonToBean(response, SNBEAN.class);
            news = snbean.getNews();
            if (snbean != null) {
                setBannerData(snbean.getFocusimg());
                setListData(snbean.getNews());
            }
        }
    }

    private void setListData(final List<SNBEAN.News> news) {
        adapter = new HotFragmentListViewAdapter(mContext, news);
        refreshState = UNREFRESHING;
        listview.setAdapter(adapter);
        listview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                if(refreshState == UNREFRESHING){
                    refreshState=REFRESHING;
                    listviewRefresh(refreshView);
                }
            }

        });
    }

    private void listviewRefresh(PullToRefreshBase<ListView> refreshView) {
            start = start + 10;
            end = end + 10;
            String url = "http://219.232.243.58:83/meilisannong/server/index.php/category_interface/getNewsAndPic?lid=544&tid=null&start=" + start + "&end=" + (end + 10);
            NetUtils.getDateFromNet(url, new NetUtils.GetJsonSucced() {
                @Override
                public void getResponse(String response) {
                    if (!TextUtils.isEmpty(response)) {
                        SNBEAN newsnbean = GsonTools.changeGsonToBean(response, SNBEAN.class);
                        if (newsnbean != null) {
                            for (int i = 0; i < newsnbean.getNews().size(); i++) {
                                news.add(newsnbean.getNews().get(i));
                            }
                        }
                    }
                }
            });
            refreshView.onRefreshComplete();
            adapter.notifyDataSetChanged();
            refreshState = UNREFRESHING;
            Log.e(TAG, "" + start);
    }

    private void setBannerData(final List<SNBEAN.Focusimg> data) {
        List<String> imageUrls = new ArrayList<>();
        List<String> list = new ArrayList<String>();
        for (int j = 0; j < data.size(); j++) {
            String imageUrl = data.get(j).getImgsrc();
            imageUrls.add(imageUrl);
            list.add(data.get(j).getTitle());
        }
        String[] arr = list.toArray(new String[list.size()]);

        //设置循环指示点
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitle(arr);
        //设置类似手风琴动画
        banner.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置加载图片
        banner.setImages(imageUrls, new OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                //联网请求图片-Glide
                Glide.with(mContext)
                        .load(url)
                        .into(view);
            }
        });
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "BANNER" + data.get(position).getVid(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
