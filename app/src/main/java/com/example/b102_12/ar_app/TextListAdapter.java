package com.example.b102_12.ar_app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Znfod on 2015-05-11.
 */
public class TextListAdapter extends BaseAdapter {
    private Context mContext;
    private List<IconTextItem> mItems = new ArrayList<IconTextItem>();

    public TextListAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getCount() {
        return mItems.size();
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
    public View getView(int position, View converView, ViewGroup viewGroup) {
        IconTextView itemView;
        if(converView == null) {
            itemView = new IconTextView(mContext);
        } else {
            itemView = (IconTextView) converView;
        }

        itemView.setText(0, mItems.get(position).getData(0));
        itemView.setText(1, mItems.get(position).getData(1));
        return itemView;
    }
    public void addItem(IconTextItem ici) {
        Log.i("Adapter","ICI"+ici.getData(0) +"/"+ici.getData(1) );
        mItems.add(ici);
    }

}
