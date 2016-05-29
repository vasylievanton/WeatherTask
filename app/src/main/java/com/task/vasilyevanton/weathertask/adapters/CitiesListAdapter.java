package com.task.vasilyevanton.weathertask.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.task.vasilyevanton.weathertask.R;

import java.util.List;

public class CitiesListAdapter extends ArrayAdapter<String> {

    protected List<String> citiesList;
    private Activity activity;
    private Integer layout;

    public CitiesListAdapter(Activity context, Integer layout, List<String> iList) {
        super(context, layout, iList);
        this.activity = context;
        this.layout = layout;
        this.citiesList = iList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(this.layout, null, true);
            holder = new ViewHolder();
            holder.cityTV = (TextView) convertView.findViewById(R.id.city_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cityTV.setText(citiesList.get(position));
        return convertView;
    }

    static class ViewHolder {
        public TextView cityTV;
    }

}