/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mobilefast.FancyCoverFlow;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {
	private Context mContext;
	private List<String> plotsImages;
	private int index = -1;
	public FancyCoverFlowSampleAdapter(Activity context, List<String> plotsImages) {

		this.plotsImages = plotsImages;
		mContext = context;
	}
	
	public String toString() {
		return plotsImages.get(index);
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

    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
    	index = i;
        ImageView imageView = null;

        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
	        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	       // imageView.setBackgroundColor(Color.TRANSPARENT);
	      //  imageView.setPadding(8, 8, 8, 8);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 400));

        }

		if(imageView != null){
	        Bitmap bm = decodeSampledBitmapFromUri(plotsImages.get(i).toString(), 300, 400);	         
	    //    int memClassBytes = GaleriaImagem.am.getMemoryClass() * 300 * 300;
	     //   int cacheSize = memClassBytes / 8;
	    //    LruCache<String, Bitmap> lruCache = new LruCache<String , Bitmap>(cacheSize){
	    //        protected int sizeOf(String key, Bitmap bitmap) {
	     //           return bitmap.getRowBytes() / 300;
	    //        }
	    //    };
	     //   String key = "myKey";
	     //   lruCache.put(key, bm);
	     //   Bitmap newBitmap = (Bitmap) lruCache.get(key);        
	        imageView.setImageBitmap(bm);
		}
        return imageView;
    }
    
	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
		
		Bitmap bm = null;
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
	     
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
	     
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		options.inPurgeable = true ;
		options.inDither = false ;
		options.inScaled = false ;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;

		bm = BitmapFactory.decodeFile(path, options); 
	     
		return bm;  	
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        // Calculate ratios of height and width to requested height and width
        final int heightRatio = Math.round((float) height / (float) reqHeight);
        final int widthRatio = Math.round((float) width / (float) reqWidth);

        // Choose the smallest ratio as inSampleSize value, this will guarantee
        // a final image with both dimensions larger than or equal to the
        // requested height and width.
        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return inSampleSize;
}
}
