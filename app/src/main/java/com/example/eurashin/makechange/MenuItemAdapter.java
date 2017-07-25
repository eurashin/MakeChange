package com.example.eurashin.makechange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 5/12/2017.
 */

public class MenuItemAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MenuItem> mDataSource;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> list) {
        mContext = context;
        mDataSource = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.menu_item, parent, false);
        MenuItem source = mDataSource.get(position);
        TextView name = (TextView) rowView.findViewById(R.id.itemName);
        name.setText(source.getName());
        //icon.setImageResource(source.getImageSrc());
        return(rowView);
    }

}
