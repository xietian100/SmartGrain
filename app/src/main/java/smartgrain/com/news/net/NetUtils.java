package smartgrain.com.news.net;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;

import okhttp3.Call;
import smartgrain.com.app.utils.GsonTools;
import smartgrain.com.news.bean.SNBEAN;

/**
 * Created by pc on 2017/3/3.
 */

public class NetUtils {
    private static GetJsonSucced getJsonSucced;

    public static void getDateFromNet(String url, final GetJsonSucced getJsonSucced) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                           getJsonSucced.getResponse(response);
                        }
                    }
                });

    }
    /**
     * 请求成功监听器
     */
    public  interface GetJsonSucced {
        void getResponse(String response);
    }
}
