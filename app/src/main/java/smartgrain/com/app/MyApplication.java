package smartgrain.com.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by pc on 2017/2/17.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return mContext;
    }


    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /**
         * 初始化OkhttpUtils
         */
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(50000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
