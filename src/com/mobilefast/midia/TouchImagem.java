package com.mobilefast.midia;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import sfa.android.ControllerSFAndroid;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.FloatMath;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class TouchImagem extends Activity implements Runnable {
	
	private static Context context                  = null;
	private Handler handler = new Handler();
	public Bitmap downloadedBitmap;
	public ImageView myImageView; 
  
    
	public AbsoluteLayout aLayout;
    int status=0;
    int touchState;
    final int IDLE = 0;
    final int TOUCH = 1;
    final int PINCH = 2;
    float dist0, distCurrent;
    int bmpWidth, bmpHeight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	      ControllerSFAndroid.getInstancia().setContext(this);
	      
	      this.setTitle("Imagem do Produto");

		LinearLayout localLinearLayout = new LinearLayout(this);
		localLinearLayout.setGravity(Gravity.CENTER);
		localLinearLayout.setOrientation(1);
		localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		
		aLayout = new AbsoluteLayout(this);
		aLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		aLayout.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (status == 1){
					LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, (int) event.getX()- myImageView.getWidth() / 2, (int) event.getY() - myImageView.getHeight() / 2);
					myImageView.setLayoutParams(lp);
				}
				  switch(event.getAction()){
				   case MotionEvent.ACTION_UP:
						status = 0;
						myImageView.setBackgroundColor(Color.TRANSPARENT);
				    break;
				   }
				return true;
			}
		});
		
 		myImageView = new ImageView(this);
		myImageView.setAdjustViewBounds(true);
		//myImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		myImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		myImageView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				   status = 1;
                   float distx, disty;
				   
				   switch(event.getAction() & MotionEvent.ACTION_MASK){
				   case MotionEvent.ACTION_DOWN:
				    //A pressed gesture has started, the motion contains the initial starting location.
				   // myTouchEvent.setText("ACTION_DOWN"); 
				    touchState = TOUCH;
				    break;
				   case MotionEvent.ACTION_POINTER_DOWN:
				    //A non-primary pointer has gone down.
				   // myTouchEvent.setText("ACTION_POINTER_DOWN"); 
				    touchState = PINCH;
				    
				    //Get the distance when the second pointer touch
				    distx = event.getX(0) - event.getX(1);
				    disty = event.getY(0) - event.getY(1);
				    dist0 = FloatMath.sqrt(distx * distx + disty * disty);

				    break;
				   case MotionEvent.ACTION_MOVE:

				    if(touchState == PINCH){      
				     //Get the current distance
				     distx = event.getX(0) - event.getX(1);
				     disty = event.getY(0) - event.getY(1);
				     distCurrent = FloatMath.sqrt(distx * distx + disty * disty);

				     drawMatrix();
				    }
				    
				    break;
				   case MotionEvent.ACTION_UP:
				    touchState = IDLE;
				    break;
				   case MotionEvent.ACTION_POINTER_UP:
				    //A non-primary pointer has gone up.
				   // myTouchEvent.setText("ACTION_POINTER_UP"); 
				    touchState = TOUCH;
				    break;
				   }
				   return true;
				  
			}
		});
	     distCurrent = 1; //Dummy default distance
	     dist0 = 1;   //Dummy default distance
	     drawMatrix();
	     touchState = IDLE;
	     
         aLayout.addView(myImageView);
    	 localLinearLayout.addView(aLayout);
		 setContentView(localLinearLayout);
	}
	
	
	@SuppressWarnings("deprecation")
	private void drawMatrix(){
	     float curScale = distCurrent/dist0;
	     if (curScale < 0.1){
	      curScale = 0.1f; 
	     }	     
	     Bitmap resizedBitmap;    
	     Bitmap bm = BitmapFactory.decodeFile(GridImagens.tFileList.get(GridImagens.posi).toString()); 
	     int newHeight = (int) (bm.getHeight() * curScale);
	     int newWidth = (int) (bm.getWidth() * curScale);
	     
	     resizedBitmap = Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
	     myImageView.setImageBitmap(resizedBitmap); 
	    }
	
	
	private Bitmap DownloadBMP(String url) throws IOException {
		URL location = new URL(url);
		InputStream is = location.openStream();
		Bitmap returnedBMP = BitmapFactory.decodeStream(is);
		is.close();
		return returnedBMP;
	}

	public void run() {
		handler.post(new Runnable() {
			public void run() {
				try {
					downloadedBitmap = DownloadBMP("http://imageshack.us/a/img217/4377/marioe.png");
				} catch (IOException e) {
					//downloadedBitmap = BitmapFactory.decodeResource(
					//		getResources(), R.drawable.imagenotfound);
				}
			}
		});
	}


    
    public void setContext(Context bulletedMenuLista) {
    	context = bulletedMenuLista;
    }
    
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
        	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}