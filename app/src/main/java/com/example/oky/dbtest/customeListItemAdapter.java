package com.example.oky.dbtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oky on 2014/08/22.
 */
public class customeListItemAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<CustomListItem> items;
    private int count;

    public customeListItemAdapter(Context context,ArrayList items) {
        this.context = context;
        this.items = items;
        this.count = items.size();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(CustomListItem item){
        this.items.add(item);
        Log.d("DEBUG","item size"+items.size());
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        count = items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customItemView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customItemView = inflater.inflate(R.layout.cell, parent, false);
            Log.d(this.getClass().getName(), "created");
        } else {
            customItemView = convertView;
            Log.d(this.getClass().getName(), "recycled");
        }

        TextView titleTextView = (TextView)customItemView.findViewById(R.id.textView);
        TextView descriptionTextView = (TextView)customItemView.findViewById(R.id.textView2);

        CustomListItem item = items.get(position);
        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());

        return customItemView;

    }
}