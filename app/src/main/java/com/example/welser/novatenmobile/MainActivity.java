package com.example.welser.novatenmobile;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    SparseArray<View> views = new SparseArray<View>();
    static MainActivity app;
    public FragmentStatePagerAdapter adapterViewPager;
    public static ViewPager vpPager;
    //private SmartFragmentStatePagerAdapter adapterViewPager;
    public RadioButton five;
    public RadioButton fiveteen;
    static public String selection[] = new String[9];
    public static Bundle bundles = null;
    public static int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //selection = null;
        selection[0] = "false";
        selection[1] = "false";
        //setupTabs();
        five = (RadioButton) findViewById(R.id.five);
        fiveteen = (RadioButton) findViewById(R.id.fiveteen);
        Intent intent = getIntent();
        bundles = intent.getExtras();
        if(bundles != null){
            selection = bundles.getStringArray("Auswahl");
            //MyPagerAdapter.NUM_ITEMS= 2;
            MyPagerAdapter.setN(2);
            Toast.makeText(getApplicationContext(),"5 Minuten Intervall "+selection[0] + " und 15 Minuten Intervall " + selection[1],Toast.LENGTH_LONG).show();
        }


        vpPager = (ViewPager) findViewById(R.id.vpPager);
        /*if (bundles == null){
            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterViewPager);
        } else {
            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterViewPager);
        }*/
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);




        // Attach the page change listener inside the activity
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here

            }
        });



        Toast.makeText(getApplicationContext(), "Array 0 = " + selection[0] + " Array 1 = " + selection[1] +" Array 2 = " + selection[2]
               +" Array 3 = " + selection[3] +" Array 4 = " + selection[4],Toast.LENGTH_LONG).show();
        }



//##############################################################################################################################################


    public static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private static int NUM_ITEMS = 4;
        public boolean mState = true;
        public static int NUM_VIEWS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {

            if (NUM_VIEWS != 4){
                notifyDataSetChanged();
            }
            /*
            if (bundles != null){
                NUM_ITEMS = 3;
                return NUM_ITEMS;
            }

                int i = 0;
                int selecLength = selection.length;

                //while(selecLength - 1 > i){
                while(9 > i){
                String a = selection[i];
                    if (selection[i] != null){
                        //if(a.contains("t")){
                            if(a.equals("true")){
                            //counter ++;
                        }
                    }
                    i++;
                    counter = 2;
                }

                //NUM_ITEMS = counter;
                // adapterViewPager.notifyDataSetChanged();
                //vpPager.destroyDrawingCache();
                notifyDataSetChanged();
                */
                return NUM_VIEWS;
            }
            //return NUM_ITEMS;

        public static void setN(int N){
           NUM_VIEWS = N;

        }


        // Returns the fragment to display for that page
        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            if(bundles != null){
                return RalisierungsFragment.newInstance(0, "Page # 1");
            }

                switch (position) {
                    case 0: // Fragment # 0 - This will show FirstFragment
                        if(selection[3] == "false"){
                            return FirstFragment.newInstance(0, "Page # true");
                        } else if(selection[4] == "true"){
                            return SecondFragment.newInstance(0, "Page # false");
                        } else if(selection[5] == "true"){
                            return SecondFragment.newInstance(0, "Page # false");
                        }
                            //Toast.makeText(app,""+selection[3],Toast.LENGTH_LONG).show();
                        return RalisierungsFragment.newInstance(0, "Page # 1");
                    case 1: // Fragment # 0 - This will show FirstFragment different title
                            return SecondFragment.newInstance(1, "Page # false");
                    case 2: // Fragment # 1 - This will show SecondFragment
                        return SecondFragment.newInstance(2, "Page # 3");
                    case 3:
                        return Alarm.newInstance(3, "Page # 4");
                    default:
                        return null;
                }


        }
/*
        @Override
        public void notifyDataSetChanged(){
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            notifyDataSetChanged();
            return POSITION_NONE;
        }
*/

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

                if(bundles == null){
                    return "Alarmliste (Page " + position + ")";
                } else {
                    switch (position) {
                    case 0:
                        return "Alarmliste (Page " + position + ")";
                    case 1:
                        return "Alarmstatistik";
                    case 2:
                        return "Devices by CPU Utilization";
                    case 3:
                        return "Devices with Alarams";
                    default:
                        break;
                }
            }
            return null;
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
    public void onClick(View v) {

        //new MyAsyncTask().execute("Test");
        Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
        startActivity(i);
    }

     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_refresh:
                onClick(menuItem.getItemId());
            case R.id.action_settings:
                onClick(menuItem.getItemId());
            default:
                return (super.onOptionsItemSelected(menuItem));

        }

    }
   /*
    private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
    } */

    private void onClick(int i) {

        switch (i) {
            case 1:

            case 2:

            default:

        }

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        //finish();
    }
}






