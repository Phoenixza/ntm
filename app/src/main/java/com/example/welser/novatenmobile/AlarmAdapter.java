package com.example.welser.novatenmobile;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by welser on 07.07.2015.
 */
public class AlarmAdapter extends BaseAdapter {

    public String[] mStrings;//the array to display
    public LayoutInflater mInflater;
    public int[] alarmValues;
    public ArrayList<HashMap<String,String>> list;
    ArrayList<HashMap<String, String>> productsList = new ArrayList<HashMap<String, String>>();

    public AlarmAdapter(Context context, ArrayList<HashMap<String, String>>productsList, String[] datas, int[] value) {
        mInflater = LayoutInflater.from(context);
        mStrings = datas;
        alarmValues = value;
        list = productsList;

    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public long getItemIdAtPosition(int position) {
        return position;
    }

    public Object getItemAtPosition(int position) {
        return mStrings[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //the following line is bad in terme of oprimization, you should use a holder, but it is a bit too long to write here, see the link below for example
        convertView = mInflater.inflate(R.layout.list_item, null);

        ViewHolder holder = new ViewHolder();
        holder.text = (TextView) convertView.findViewById(R.id.name);
        holder.text = (TextView) convertView.findViewById(R.id.datum);
        holder.text = (TextView) convertView.findViewById(R.id.price);
        holder.text = (TextView) convertView.findViewById(R.id.description);
        convertView.setTag(holder);


        //set the datas and the colors to the row

        //TextView textView =  (TextView) convertView.findViewById(R.id.tvColor);
        //textView.setText(mStrings[position]);
        //convertView.setBackgroundColor(R.color.your_color);//you can change the color following the position or the string that you put in


        convertView.setBackgroundColor(Color.GREEN);
        return convertView;
    }

    static class ViewHolder {
        TextView text;
        TextView timestamp;
        ImageView icon;
        ProgressBar progress;
        int position;
    }
}