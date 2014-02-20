package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fluchtwege.weatherhistory.R;

/**
 * Created by jkettner on 20.02.14.
 */
public class CurrentWeatherFragment extends BaseFragment {


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.currentweather, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        hideProgress();
        return root;
    }
}
