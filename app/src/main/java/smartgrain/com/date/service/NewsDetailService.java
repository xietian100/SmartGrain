package smartgrain.com.date.service;

import android.app.IntentService;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import smartgrain.com.date.bean.Transaction;


public class NewsDetailService extends IntentService {

    private String tail="友情链接 网站首页 新闻中心 交易动态 销售报价 品种信息 供求信息 关于我们 关于我们 集团概况 企业文化 下属企业 虚位以待 企业荣誉 企业形象 电子地图 新闻中心 热点新闻 集团动态 行业动态 交易动态 交易通知 历史成交 销售报价 成品粮油价格 原粮价格 品种信息 稻米 玉米 油脂 大豆 大米 期货 供求信息 公司地址：赣州市粮食城文峰路B2栋（105国道旁） 邮编：341000 联系电话：0797-2130600 传真：0797-2130600 免责申明：本站部分信息资源来自于互联网，如有侵权情况，请与我们联系，本站立即予以处理 Copyright © 2015 Gngrain.com All Rights Reserved 赣ICP备15007427号　版权所有·赣南粮食交易网 技术支持：华企立方";
    private String head="-赣南粮食交易网 登录注册 设为首页 | 加入收藏 | 联系我们 追溯码 新闻 网站首页 新闻中心 交易动态 市场报价 品种信息 供求信息 关于我们 溯源产品 粮易购 周边交易市场公告 交易公告 采购信息 销售信息  服务热线： 0797-2130600  您当前的位置：首页 -> 周边交易市场公告 -> 交易公告 ";

    private static final String TAG = "NewsDetailService";
    private String url;



    public NewsDetailService() {
        super("NewsDetailService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        url = intent.getStringExtra("url");
        grabNewsDetail();
        Log.e(TAG, url);
    }

    private void grabNewsDetail() {
        Log.e(TAG, "NewsDetailService BEAIN");
        try {
            Document doc = Jsoup.connect(url).timeout(5000).post();

             /*
            * 获取交易清单下载地址
            * */
            Document content = Jsoup.parse(doc.toString());
            String qingdan_url = content.select("a").select("[href*=xls]").attr("href");
            Log.e(TAG,"qingdan_url"+qingdan_url);


            Elements span = content.select("span").select("[style*= FONT-SIZE: 18px]");
            String text = span.html();


//            Log.e(TAG, "span"+text);
            Log.e(TAG, "span"+text);

            NewsDetail newsDetail=new NewsDetail();
            newsDetail.setNewsDetail(text);
            newsDetail.setQingdan_url(qingdan_url);

            EventBus.getDefault().post(newsDetail);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public class NewsDetail{
        public String getNewsDetail() {
            return content;
        }
        public void setNewsDetail(String newsDetail) {
            this.content = newsDetail;
        }

        private String content;

        public String getQingdan_url() {
            return qingdan_url;
        }

        public void setQingdan_url(String qingdan_url) {
            this.qingdan_url = qingdan_url;
        }
        private String qingdan_url;
    }

}
