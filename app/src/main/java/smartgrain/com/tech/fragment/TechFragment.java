package smartgrain.com.tech.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import smartgrain.com.base.BaseFragment;

/**
 * Created by pc on 2017/3/1.
 */

public class TechFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {

        textView=new TextView(mContext);

        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initDate() {
        textView.setText("TechFragment");
    }
}
