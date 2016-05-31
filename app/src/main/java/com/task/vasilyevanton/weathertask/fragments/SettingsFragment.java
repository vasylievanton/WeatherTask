package com.task.vasilyevanton.weathertask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.task.vasilyevanton.weathertask.R;
import com.task.vasilyevanton.weathertask.utils.SharedPreferencesManager;
import com.task.vasilyevanton.weathertask.adapters.CitiesListAdapter;

import java.util.ArrayList;

public class SettingsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<String> citiesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_settings, container, false);
        fragmentInit(view);
        setRetainInstance(true);
        return view;
    }

    private void fragmentInit(View view) {
        ListView citiesListView = (ListView) view.findViewById(R.id.cities);
        String[] cities = getResources().getStringArray(R.array.cities);
        citiesList = new ArrayList<>();
        for (String city : cities) {
            if (!city.equals(SharedPreferencesManager.getInstance().getCity())){
                citiesList.add(city);
            }
        }
        citiesListView.setAdapter(new CitiesListAdapter(getActivity(),R.layout.weather_list_item,citiesList));
        citiesListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferencesManager.getInstance().setCity(citiesList.get(position));
        getFragmentManager().popBackStack();
    }
}
