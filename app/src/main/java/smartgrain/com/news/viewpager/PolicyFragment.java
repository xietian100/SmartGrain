package smartgrain.com.news.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pc on 2017/3/1.
 */

public class PolicyFragment extends ViewPageBaseFragment {

    private TextView textView;

    public PolicyFragment(String title) {
        super(title);
    }

    @Override
    View initView() {

        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initDate() {
        textView.setText("政策");

    }
}