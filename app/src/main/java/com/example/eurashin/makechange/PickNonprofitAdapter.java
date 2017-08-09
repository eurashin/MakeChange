//we need our own because the partner viewing page will not have checkboxes
package com.example.eurashin.makechange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Eura  Shin on 5/3/2017.
 */

public class PickNonprofitAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Partner> mDataSource;
    private int selectedPosition = -1;


    public PickNonprofitAdapter(Context context, ArrayList<Partner> partners) {
        mDataSource = partners;
        mContext = context;
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
        View rowView = mInflater.inflate(R.layout.partner_item, parent, false);
        final Partner source = mDataSource.get(position);
        TextView textView = (TextView) rowView.findViewById(R.id.itemName);
        textView.setText(source.getName());

        CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.checkBox);
        checkbox.setChecked(position == selectedPosition);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    selectedPosition = position;
                }
                else {
                    selectedPosition = -1;
                }
                notifyDataSetChanged();
            }
        });
        return(rowView);
    }
}
