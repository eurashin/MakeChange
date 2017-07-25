package com.example.eurashin.makechange;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 7/24/2017.
 */

public class SliderFragment extends Fragment {
    private final int MAX = 5;
    private SeekBar[] seekBars;
    private TextView[] percentList;
    private TextView[] moneyList;
    private TextView[] names;
    private ArrayList<Nonprofit> nonprofits;
    private NonprofitManager nonprofitManager;
    private double totalDonated;
    private LinearLayout[] sliderBoxes;
    private Button[] buttons;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_slider, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize arrays
        nonprofits = new ArrayList<Nonprofit>();
        seekBars = new SeekBar[MAX];
        percentList = new TextView[MAX];
        moneyList = new TextView[MAX];
        names = new TextView[MAX];
        buttons = new Button[MAX];
        sliderBoxes = new LinearLayout[MAX];

        //fill boxes to save place for each nonprofit
        sliderBoxes[0] = (LinearLayout)getActivity().findViewById(R.id.sliderBox1);
        sliderBoxes[1] = (LinearLayout)getActivity().findViewById(R.id.sliderBox2);
        sliderBoxes[2] = (LinearLayout)getActivity().findViewById(R.id.sliderBox3);
        sliderBoxes[3] = (LinearLayout)getActivity().findViewById(R.id.sliderBox4);
        sliderBoxes[4] = (LinearLayout)getActivity().findViewById(R.id.sliderBox5);

        buttons[0] = (Button)getActivity().findViewById(R.id.button1);
        buttons[1] = (Button)getActivity().findViewById(R.id.button2);
        buttons[2] = (Button)getActivity().findViewById(R.id.button3);
        buttons[3] = (Button)getActivity().findViewById(R.id.button4);
        buttons[4] = (Button)getActivity().findViewById(R.id.button5);



        //get the list...make an array of supported nonprofits
        totalDonated = 10;
        nonprofits.add(new Nonprofit("Newton's attic", 60));
        nonprofits.add(new Nonprofit("The Nest", 15));
        nonprofits.add(new Nonprofit("Eura's Closet", 5));
        nonprofits.add(new Nonprofit("Eura's Closet", 10));

        nonprofitManager = new NonprofitManager(nonprofits);

        //fill the seekbar boxes with chosen nonprofits
        for(int i=0; i<nonprofitManager.size; i++) {
            final int index = i;
            //set the layout contents
            //title
            buttons[index].setText(nonprofits.get(index).getName());
            //amount money
            moneyList[index] = new TextView(getActivity());
            moneyList[index].setText(String.format("$%.2f", nonprofits.get(index).getPercentDonated()/100 * totalDonated));
            moneyList[index].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,(float) 0.1));
            sliderBoxes[index].addView(moneyList[index]);
            //seekbar
            seekBars[index] = new SeekBar(getActivity());
            seekBars[index].setProgress((int)Math.round(nonprofits.get(index).getPercentDonated()));
            seekBars[index].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,(float) 0.8));
            sliderBoxes[index].addView(seekBars[index]);
            //percent
            percentList[index] = new TextView(getActivity());
            percentList[index].setText("%" + Integer.toString((int)Math.round(nonprofits.get(index).getPercentDonated())));
            percentList[index].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,(float) 0.1));
            sliderBoxes[index].addView(percentList[index]);

            //set listener for changes
            seekBars[index].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //update percents
                    nonprofitManager.changePercent(index, progress);
                    moneyList[index].setText(String.format("$%.2f", nonprofits.get(index).getPercentDonated()/100 * totalDonated));
                    percentList[index].setText("%" + Integer.toString((int)Math.round(nonprofits.get(index).getPercentDonated())));
                    int [] percentValues = nonprofitManager.getNonprofitPercentages();
                    for(int j = index; j<nonprofitManager.size; j++) {
                        seekBars[j].setProgress(percentValues[j]);
                    }

                    //update text
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }

        //fill the empty boxes with an add option
        for(int i=nonprofitManager.size; i<MAX; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, (float)1);
            buttons[i].setLayoutParams(params);
            buttons[i].setText("+");
            buttons[i].setTextSize(35);
        }
    }

}
