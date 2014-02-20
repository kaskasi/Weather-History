package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import de.fluchtwege.weatherhistory.R;

/**
 * Created by jkettner on 20.02.14.
 */
public abstract class BaseFragment extends Fragment {

    private View mProgressRL = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout containerRL = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mProgressRL =  (View) inflater.inflate(R.layout.progress, container, false);
        mProgressRL.setLayoutParams(params);
        View root = createView(inflater, container, savedInstanceState);
        root.setLayoutParams(params);
        containerRL.addView(root);

        containerRL.addView(mProgressRL);
        return containerRL;
    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void showProgress(){
        mProgressRL.setVisibility(RelativeLayout.VISIBLE);
    }

    public void hideProgress(){
        mProgressRL.setVisibility(RelativeLayout.INVISIBLE);
    }



}
