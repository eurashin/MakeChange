package com.example.eurashin.makechange;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/*
* TODO: -make details page for partners -make configuration pages
* */

public class MainActivity extends AppCompatActivity {
    private ListView drawerList;
    private DrawerLayout mDrawerLayout;

    private ArrayList<MenuItem> items;
    private MenuItemAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the drawer
        drawerList = (ListView)findViewById(R.id.navList);
        items = new ArrayList<MenuItem>();
        items.add(new MenuItem("Main"));
        items.add(new MenuItem("Next"));
        adapter = new MenuItemAdapter(this, items);
        drawerList.setAdapter(adapter);

        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }

            /** Swaps fragments in the main content view */
            private void selectItem(int position) {
                Fragment fragment;
                //switch statement to choose appropriate fragment
                switch(position) {
                    case 0: fragment = new SliderFragment();
                        break;
                    default: fragment = new SliderFragment();
                        break;
                }

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

                // Highlight the selected item, update the title, and close the drawer
                //      mDrawerList.setItemChecked(position, true);
                //     setTitle(mPlanetTitles[position]);
                //    mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        /** Swaps fragments in the main content view */
        private void selectItem(int position) {
            Fragment fragment;
            //switch statement to choose appropriate fragment
            switch(position) {
                case 0: fragment = new SliderFragment();
                    break;
                default: fragment = new SliderFragment();
                    break;
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            // Highlight the selected item, update the title, and close the drawer
            //      mDrawerList.setItemChecked(position, true);
            //     setTitle(mPlanetTitles[position]);
            //    mDrawerLayout.closeDrawer(mDrawerList);
        }

    }
}


