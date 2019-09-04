package com.mobilefast.midia;

import java.util.List;

import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;


public class FullImageActivity extends Activity {
	
	private List<String> tFileList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.full_image);
		
		Bitmap bm = BitmapFactory.decodeFile(GridImagens.tFileList.get(GridImagens.posi).toString()); 
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageBitmap(bm);
		//imageView.setAdjustViewBounds(true);
		//imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	}
	
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    
		        	//CoreSFAndroid.getintance().abrirTelaGrid();
                    finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
