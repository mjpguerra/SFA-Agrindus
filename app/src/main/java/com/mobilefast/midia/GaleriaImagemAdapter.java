package com.mobilefast.midia;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.List;

public class GaleriaImagemAdapter extends BaseAdapter {

    private static ImageView imageView;
    private Activity context;
    private List<String> plotsImages;
    private Context mContext;

    public GaleriaImagemAdapter(Activity context, List<String> plotsImages) {

        this.context = context;
        this.plotsImages = plotsImages;
        mContext = context;
    }


    public int getCount() {
        return plotsImages.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = null;


        if (convertView == null) {
            try {
                imageView = new ImageView(mContext);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setBackgroundColor(Color.TRANSPARENT);
                imageView.setLayoutParams(new Gallery.LayoutParams(150, 90));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                imageView = new ImageView(mContext);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setBackgroundColor(Color.TRANSPARENT);
                imageView.setLayoutParams(new Gallery.LayoutParams(150, 90));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (imageView != null) {
            Bitmap bm = BitmapFactory.decodeFile(plotsImages.get(position).toString());
            imageView.setImageBitmap(bm);
        }


        return imageView;
    }


}
