package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 7/25/2017.
 */
//TODO: add a search option
public class PickNonprofitFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.nonprofit_picker_fragment, container, false));
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Partner> partners = new ArrayList<Partner>();
        partners.add(new Partner("Nonprofit 1", "image", "This is a nonprofit yayaya", "address", new ArrayList<String>()));
        partners.add(new Partner("Nonprofit 2", "image", "This is a nonprofit yayaya", "address", new ArrayList<String>()));
        PickNonprofitAdapter adapter = new PickNonprofitAdapter(getActivity(), partners);

        ListView listView = (ListView) getActivity().findViewById(R.id.nonprofitlist);
        listView.setAdapter(adapter);

        Button supportButton = (Button) getActivity().findViewById(R.id.support);
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = getArguments();
                if (args != null) {
                    if (args.containsKey("Activity Type")) {

                        int type = args.getInt("Activity Type");
                        Fragment fragment;
                        FragmentManager fragmentManager = getFragmentManager();
                        if (type == 0) { //comes from upload card
                            fragment = new NonprofitStarterFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.setup_frame, fragment)
                                    .commit();
                        } else { //comes from slider page
                            fragment = new SliderFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                        }
                    }
                }
            }
        });
    }
}
