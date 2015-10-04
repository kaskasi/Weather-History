package de.fluchtwege.whtest.mock;

import android.database.Cursor;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;

import org.robolectric.Robolectric;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import de.fluchtwege.weatherhistory.ui.BaseFragment;

public class TestLoaderHandler {

    private CountDownLatch latch = new CountDownLatch(1);


    public boolean waitForLoadFinished(BaseFragment fragment) {
        boolean loaderFinished = false;
        int counter = 0;
        while (latch.getCount() > 0 && counter < 2500) {
            try {
                counter++;
                if (!fragment.isShowingProgress()) {
                    latch.countDown();
                }
                loaderFinished = latch.await(10, TimeUnit.MILLISECONDS);
                Robolectric.getBackgroundThreadScheduler().advanceBy(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return loaderFinished;
    }
}
