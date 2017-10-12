package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Eura  Shin on 8/11/2017.
 */

public class NonprofitStarterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_nonprofit_starter, container, false));


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button[] buttons = new Button[5];
        buttons[0] = (Button)getActivity().findViewById(R.id.startnonprofit1);
        buttons[1] = (Button)getActivity().findViewById(R.id.startnonprofit2);
        buttons[2] = (Button)getActivity().findViewById(R.id.startnonprofit3);
        buttons[3] = (Button)getActivity().findViewById(R.id.startnonprofit4);
        buttons[4] = (Button)getActivity().findViewById(R.id.startnonprofit5);

        //on each button click
        for(Button button:buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("Activity Type", 0);
                        Fragment fragment = new PickNonprofitFragment();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.setup_frame, fragment)
                                .commit();
                }
            });
        }

        //next button, start main activity
        Button nextButton = (Button) getActivity().findViewById(R.id.start_nonprofit_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
