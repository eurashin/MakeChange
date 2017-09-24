package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Eura  Shin on 8/10/2017.
 */

public class SigninFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_sign_in, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button next = (Button)getActivity().findViewById(R.id.signin_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) getActivity().findViewById(R.id.signin_make_username);
                EditText password = (EditText) getActivity().findViewById(R.id.signin_make_password);

                //check to make sure all fields are filled
                boolean[] filledFields = new boolean[2];
                filledFields[0] = isFilled(username);
                filledFields[1] = isFilled(password);

                boolean allFilled = (filledFields[0] && filledFields[1]);

                if(allFilled) { //start main activity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                }
                else { //all fields are not filled
                    //clear all hint fields first
                    clearErrorHints();
                    //find out which isn't filled
                    for(int i=0; i<2; i++) {
                        if(!filledFields[i]){ //not filled at certain field
                            postErrorHint(i); //post error hint for that field
                        }
                    }
                }
            }
        });

    }


    private void clearErrorHints() {
        ((TextView)getActivity().findViewById(R.id.missing_username_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.missing_password_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.missing_password_confirm_hint)).setText("");
    }
    private void postErrorHint(int errorIndex) {
        TextView textView;
        switch(errorIndex) {
            case 0: //username
                textView = (TextView)getActivity().findViewById(R.id.missing_username_hint);
                textView.setText("Please enter a username");
                break;
            case 1: //password
                textView = (TextView)getActivity().findViewById(R.id.missing_password_hint);
                textView.setText("Please enter a password");
                break;
            case 2: //confirm password
                textView = (TextView)getActivity().findViewById(R.id.missing_password_confirm_hint);
                textView.setText("Please confirm your password");
                break;
        }
    }

    static boolean isFilled(EditText editText) {
        return (!editText.getText().toString().equals(""));
    }
}
