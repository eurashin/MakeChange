package com.example.eurashin.makechange;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eura  Shin on 8/27/2017.
 */

public class PartnerExpandFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.nonprofit_expand_fragment, container, false));
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
