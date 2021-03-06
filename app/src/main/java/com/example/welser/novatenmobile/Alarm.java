package com.example.welser.novatenmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by welser on 22.06.2015.
 */


/**
 * A placeholder fragment containing a simple view.
 */
public class Alarm extends Fragment {

    public Alarm() {}
    public int value[]=new int[9];
    private View mView;
    private GraphicalView mGraphView;
    // Store instance variables
    private String title;
    private int page;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_DATUM = "created_at";
    static int [] colors = new int[] {0xF0FFFF, 0xD3D3D3 };
    // Hashmap for ListView
    ArrayList<HashMap<String, String>>productsList = new ArrayList<HashMap<String, String>>();
    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    // url to get all products list
    //private static String url_all_products = "http://novaten.cloud.hs-furtwangen.de/phpandroid/get_all_products.php";
    private static String url_all_products = "http://141.28.100.152/phpandroid/get_all_products.php";
    // products JSONArray
    JSONArray products = null;
    // newInstance constructor for creating fragment with arguments
    public static Alarm newInstance(int page, String title) {
        Alarm fragmentSecond = new Alarm();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chart, container, false);
        mView = rootView;

        new LoadAllProducts().execute();
        //initData();


        return rootView;
    }

    private void initData() {
        Log.d("#######INTDATA",value + " ");
        Log.d("#######INTDATA",value[0] + " " + value[4]+ " " + value[2]+ " " + value[1]+ " " + value[3] + " ");
        String[] codename = {
                "Critical", "Warning", "Unknown",
                "Info", "Major"
        };

        int[] x = {0,1,2,3,4};
        double[] values = {value[2], value[0], value[1], value[4], value[3] };
        String[] colors = {"#ff4444", "#99cc00", "#aa66cc", "#33b5e5", "#ffbb33"
        };
        XYSeries charts = new XYSeries("Stats");
        for(int i=0; i<x.length;i++){
            charts.add(i,values[i]);
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(charts);

        XYSeriesRenderer charty = new XYSeriesRenderer();
        charty.setColor(Color.rgb(130, 130, 230));
        charty.setFillPoints(true);
        charty.setLineWidth(10);
        charty.setDisplayChartValues(true);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setZoomRate(0.2f);
        multiRenderer.setChartTitle("Uebersicht Alarmarten");
        multiRenderer.setXTitle("Anzahl der Alarmarten");
        multiRenderer.setYTitle("Anzahl der TRAPS");
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setZoomEnabled(false,false);
        multiRenderer.setChartTitleTextSize(40);
        multiRenderer.setLabelsTextSize(30);
        multiRenderer.setLegendTextSize(30);
        multiRenderer.setPanEnabled(false,false);
        // code
        multiRenderer.addSeriesRenderer(charty);

        mGraphView = ChartFactory.getBarChartView(getActivity(),dataset,multiRenderer,BarChart.Type.DEFAULT);


        RelativeLayout container =
                (RelativeLayout) mView.findViewById(R.id.graph_container);

        container.addView(mGraphView);
    }


    class LoadAllProducts extends AsyncTask<String, String, String> {

        int critical;
        int warning;
        int info;
        int major;
        int unknown;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity()); // abgeaendert
            pDialog.setMessage("Loading data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        /**
         * getting All products from url
         * */
        public String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String price = c.getString(TAG_PRICE);
                        String date = c.getString(TAG_DATUM);
                        String description = c.getString(TAG_DESCRIPTION);
                        Log.d("NAME#####", name +"");
                        if (name.startsWith("cirtical")){
                            critical ++;
                            Log.i("critical count", critical + "");
                        } else if(name.startsWith("warning")){
                            warning ++;
                            Log.i("warning count", warning + "");
                        } else if(name.startsWith("major")){
                            info ++;
                            Log.i("major count", major + "");
                        } else if(name.startsWith("unknown")){
                            major ++;
                            Log.i("major count", unknown + "");
                        } else if(name.startsWith("info")){
                            unknown ++;
                            Log.i("info count", info + "");}
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            super.onPostExecute(file_url);
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */

                }
            });

            set(critical, warning, info, major, unknown);
            initData();
        }
        public void set( int critical,int warning, int info,int major,int unknown){
            value[0] = critical;
            value[1] = warning;
            value[2] = info;
            value[3] = major;
            value[4] = unknown;
        }

    }

}


