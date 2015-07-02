package com.example.welser.novatenmobile;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by welser on 22.06.2015.
 */
public class RalisierungsFragment extends ListFragment {
    // Store instance variables
    private String title;
    private int page;
    public int value = 111;
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
    public static RalisierungsFragment newInstance(int page, String title) {
        RalisierungsFragment rs = new RalisierungsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        rs.setArguments(args);
        return rs;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        String[] values = new String[] {"A", "B", "C", "D","E","F","G","H","I","J","K","L"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, values);


        // Loading products in Background Thread
        new LoadAllProducts().execute();

        //setListAdapter(adapter);
    }
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity()); // abgendert
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
            Log.d("All Products: ",value + "");

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
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_DATUM, date);
                        map.put(TAG_PRICE, price);
                        map.put(TAG_DESCRIPTION, description);
                        set(products.length());
                        // adding HashList to ArrayList
                        productsList.add(map);
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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */

                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), productsList,
                            R.layout.list_item, new String[]{TAG_PID,
                            TAG_NAME, TAG_PRICE, TAG_DATUM,TAG_DESCRIPTION},
                            new int[]{R.id.pid, R.id.name, R.id.price, R.id.datum, R.id.description});
                    // updating listview
                    int colorPos = 80 % colors.length;

                    setListAdapter(adapter);
                }
            });



        }

        public void set(int i){
            value = i;
        }

    }

}


