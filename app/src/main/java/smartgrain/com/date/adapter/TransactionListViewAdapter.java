package smartgrain.com.date.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import smartgrain.com.R;
import smartgrain.com.date.bean.Transaction;

/**
 * Created by pc on 2017/3/7.
 */

public class TransactionListViewAdapter extends BaseAdapter {
    private final List<Transaction> transactions;
    private final Context mContext;

    public TransactionListViewAdapter(Context context, List<Transaction> transactions) {
        this.transactions=transactions;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return transactions.size();
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
            convertview=View.inflate(mContext, R.layout.item_listview_transactions,null);
            viewHolder=new ViewHolder();
            viewHolder.title= (TextView) convertview.findViewById(R.id.title);
            viewHolder.data= (TextView) convertview.findViewById(R.id.data);

            convertview.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertview.getTag();
        }
        viewHolder.title.setText(transactions.get(i).getTitle().replace("·",""));
        viewHolder.data.setText(transactions.get(i).getData());
        return convertview;
    }
    static class ViewHolder{
        TextView title;
        TextView data;
    }
}
