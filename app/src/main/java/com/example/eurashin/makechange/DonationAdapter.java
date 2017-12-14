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

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by Eura  Shin on 10/11/2017.
 */

public class DonationAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Donation> mDataSource;



    public DonationAdapter(Context context, ArrayList<Donation> donations) {
        mDataSource = donations;
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
        final Donation source = mDataSource.get(position);
        View rowView;

        if(source.isDateSet()) { //has date
            rowView = mInflater.inflate(R.layout.donation_item, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.donation_name);
            textView.setText(source.getNonprofit());

            TextView date = (TextView) rowView.findViewById(R.id.donation_date);
            date.setText(source.getDay() + "/" + source.getMonth() + "/" + source.getYear());

            TextView amount = (TextView) rowView.findViewById(R.id.donation_amount);
            amount.setText("$" + source.getAmount());
        }
        else { //does not have date
            rowView = mInflater.inflate(R.layout.history_item, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.history_name);
            textView.setText(source.getNonprofit());

            TextView amount = (TextView) rowView.findViewById(R.id.history_donated);
            amount.setText("$" + source.getAmount());
        }

        return(rowView);
    }
}
