package com.example.eurashin.makechange;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by eurashin on 10/6/17.
 */

public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_user, container, false));


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Donation> topDonations = new ArrayList<Donation>();
        topDonations.add(new Donation("On the Move", 10.45));
        topDonations.add(new Donation("The Nest", 9.57));
        topDonations.add(new Donation("Humane Society", 5.57));

        DonationAdapter adapter = new DonationAdapter(getActivity(), topDonations);
        ListView listView = (ListView) getActivity().findViewById(R.id.nonprofit_donated_list);
        listView.setAdapter(adapter);




    }
}
