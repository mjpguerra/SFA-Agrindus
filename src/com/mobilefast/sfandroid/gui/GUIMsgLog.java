package com.mobilefast.sfandroid.gui;

import java.util.Iterator;
import java.util.List;

import sfa.android.CoreSFAndroid;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.Msglog;

public class GUIMsgLog extends Activity {

   private List lMsg = null;


   public GUIMsgLog() {
      super();
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setTitle("Log do Sincronismo");
      LinearLayout var2 = new LinearLayout(this);
      var2.setGravity(3);
      var2.setOrientation(1);
      var2.setLayoutParams(new LayoutParams(-1, -1));

      try {
         this.lMsg = LocatorDAO.getInstancia().getMsgLog().findAll();
         Iterator<?> var4 = this.lMsg.iterator();

         while(var4.hasNext()) {
            Msglog var6 = (Msglog)var4.next();
            String.valueOf(var6.getId());
            TextView var8 = new TextView(this);
            var8.setText(String.valueOf(var6.getData()) + "    -    " + var6.getHora() + "    -    " + var6.getLogentry());
            var8.setTextSize(12);
            var2.addView(var8);
         }

         ScrollView var5 = new ScrollView(this);
         var5.addView(var2);
         this.setContentView(var5);
      } catch (Exception var9) {
         ;
      }

   }

	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
	    	CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}
