package de.fluchtwege.weatherhistory.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import de.fluchtwege.weatherhistory.R;

public abstract class BaseFragment extends Fragment {

    private RelativeLayout progressContainer = null;

    protected Loader<Cursor> cursorLoader = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout contentContainer = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        progressContainer = (RelativeLayout) inflater.inflate(R.layout.progress, container, false);
        progressContainer.setLayoutParams(params);
        View root = createView(inflater, container, savedInstanceState);
        root.setLayoutParams(params);
        contentContainer.addView(root);

        contentContainer.addView(progressContainer);
        return contentContainer;
    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void showProgress() {
        progressContainer.setVisibility(RelativeLayout.VISIBLE);
    }

    public void hideProgress() {
        progressContainer.post(new Runnable() {
            @Override
            public void run() {
                progressContainer.setVisibility(View.GONE);
            }
        });
    }

    public Loader<Cursor> getCursorLoader() {
        return cursorLoader;
    }

    public boolean isShowingProgress() {
        return progressContainer.getVisibility() == RelativeLayout.VISIBLE;
    }


}
