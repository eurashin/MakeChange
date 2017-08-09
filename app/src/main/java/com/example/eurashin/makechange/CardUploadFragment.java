package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 8/10/2017.
 */

public class CardUploadFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_connect_card, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button next = (Button)getActivity().findViewById(R.id.card_next);

        //add patterns for card types
        final ArrayList<String> listOfPattern=new ArrayList<String>();
        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        String ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);

        EditText cardNumber = (EditText)getActivity().findViewById(R.id.card_number);
        //TODO: validate all card entries
        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ((ImageView)getActivity().findViewById(R.id.visa_image)).setBackgroundResource(0);
                ((ImageView)getActivity().findViewById(R.id.discover_image)).setBackgroundResource(0);
                ((ImageView)getActivity().findViewById(R.id.amex_image)).setBackgroundResource(0);
                ((ImageView)getActivity().findViewById(R.id.mastercard_image)).setBackgroundResource(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String enteredCardNumber = s.toString();
                ImageView cardIcon = null;
                for(String pattern:listOfPattern) {
                    if(enteredCardNumber.matches(pattern)) {
                        String cardType = cardPatternToName(pattern);
                        if(cardType.equals("Visa")) {
                            //highlight the Visa card icon
                            cardIcon = (ImageView)getActivity().findViewById(R.id.visa_image);
                        }
                        else if(cardType.equals("Discover")) {
                            cardIcon = (ImageView)getActivity().findViewById(R.id.discover_image);
                        }
                        else if(cardType.equals("American Express")) {
                            cardIcon = (ImageView)getActivity().findViewById(R.id.amex_image);
                        }
                        else { //mastercard
                            cardIcon = (ImageView)getActivity().findViewById(R.id.mastercard_image);
                        }
                        cardIcon.setBackgroundResource(R.drawable.halo);
                    }
                }
            }
        });


        //for next button pressed
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] entries = new EditText[5];
                entries[0] = (EditText) getActivity().findViewById(R.id.card_firstname);
                entries[1] = (EditText) getActivity().findViewById(R.id.card_lastname);
                entries[2] = (EditText) getActivity().findViewById(R.id.card_number);
                entries[3] = (EditText) getActivity().findViewById(R.id.card_exp_date);
                entries[4] = (EditText) getActivity().findViewById(R.id.card_cvv);

                //check to make sure all fields are filled
                boolean[] filledFields = new boolean[5];
                for(int i=0; i<5; i++) {
                    filledFields[i] = RegisterFragment.isFilled(entries[i]);
                }


                boolean allFilled = (filledFields[0] && filledFields[1] && filledFields[2] && filledFields[3] && filledFields[4]);
                //confirm card number is correct
                boolean canNext = allFilled;
                if(canNext) {
                    Fragment fragment = new NonprofitStarterFragment();

                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.setup_frame, fragment)
                            .commit();
                }
                else { //all fields are not filled
                    //clear all hint fields first
                    clearErrorHints();
                    //find out which isn't filled
                    for(int i=0; i<5; i++) {
                        if(!filledFields[i]){ //not filled at certain field
                            postErrorHint(i); //post error hint for that field
                        }
                    }
                }
            }
        });
    }


    private void clearErrorHints() {
        ((TextView)getActivity().findViewById(R.id.card_firstname_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.card_lastname_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.card_exp_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.card_number_hint)).setText("");
        ((TextView)getActivity().findViewById(R.id.card_cvv_hint)).setText("");
    }

    private void postErrorHint(int hintIndex) {
        TextView textView;
        switch(hintIndex) {
            case 0:
                textView = (TextView)getActivity().findViewById(R.id.card_firstname_hint);
                textView.setText("Please enter your first name");
                break;
            case 1:
                textView = (TextView)getActivity().findViewById(R.id.card_lastname_hint);
                textView.setText("Please enter your last name");
                break;
            case 2:
                textView = (TextView)getActivity().findViewById(R.id.card_number_hint);
                textView.setText("Please enter the card number");
                break;
            case 3:
                textView = (TextView)getActivity().findViewById(R.id.card_exp_hint);
                textView.setText("Please enter your first name");
                break;
            case 4:
                textView = (TextView)getActivity().findViewById(R.id.card_cvv_hint);
                textView.setText("Please enter the security digits");
                break;
        }
    }

    private String cardPatternToName(String pattern) {
        if(pattern.equals("^4[0-9]{6,}$")) {
            return "Visa";
        }
        else if(pattern.equals("^5[1-5][0-9]{5,}$")) {
            return "MasterCard";
        }
        else if(pattern.equals("^6(?:011|5[0-9]{2})[0-9]{3,}$")) {
            return "Discover";
        }
        else {
            return "American Express";
        }
    }

}
