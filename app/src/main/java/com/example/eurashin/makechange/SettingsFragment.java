package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 8/30/2017.
 */

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_settings, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set list of settings options
        ArrayList<String> options = new ArrayList<String>();
        options.add("Card");
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, options);
        ListView listView = (ListView) getActivity().findViewById(R.id.settings_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //switch statement to choose appropriate fragment
                Fragment fragment;
                FragmentManager fragmentManager = getFragmentManager();

                switch(position) {
                    case 0:
                        fragment = new CardManageFragment();
                        break;
                    default:
                        fragment = new CardManageFragment();
                        break;
                }

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack("card")
                        .commit();
            }
        });
    }
}
