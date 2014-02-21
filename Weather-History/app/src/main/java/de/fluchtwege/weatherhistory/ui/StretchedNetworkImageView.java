package de.fluchtwege.weatherhistory.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by jkettner on 21.02.14.
 */
public class StretchedNetworkImageView extends NetworkImageView{

    public StretchedNetworkImageView(Context context) {
        super(context);
    }

    public StretchedNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StretchedNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Drawable d = getDrawable();
        if (d != null && getVisibility() == View.VISIBLE) {
            int imgHeight = d.getIntrinsicHeight();
            int imgWidth = d.getIntrinsicWidth();
            if (imgHeight > 0 && imgWidth > 0) {
                // if (ImageManager.noFile != null
                // && ImageManager.noFile.getIntrinsicHeight() == imgHeight
                // && ImageManager.noFile.getIntrinsicWidth() == imgWidth)
                // if (d != ImageManager.noFile) { // don't stretch place holder
                // // images
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = (int) ((double) imgHeight * (double) width /
                (double) imgWidth);
                setMeasuredDimension(width, height);
                // }
            }
        }
    }
}
