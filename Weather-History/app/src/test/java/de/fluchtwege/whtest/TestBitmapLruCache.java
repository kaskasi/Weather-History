package de.fluchtwege.whtest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.provider.BitmapLruCache;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestBitmapLruCache extends BaseTest {

    @Test
    public void Default_LRU_Bitmap_Cache_size_is_466048() {
        int defaultLruCacheSize = BitmapLruCache.getDefaultLruCacheSize();
        Assert.assertEquals(466048, defaultLruCacheSize);
    }
}
