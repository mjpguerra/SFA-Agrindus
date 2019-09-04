package com.mobilefast.sfandroid.gui;


import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

public class GUITabPedido extends TabActivity {	
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);

        
        this.setTitle("Movimentação do Pedido");  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Resources res 	= getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        
        
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this,GUIProdutosPedido.class);
        spec = tabHost.newTabSpec("").setIndicator("PRODUTOS", res.getDrawable(R.drawable.movimentopedido)).setContent(intent);
        tabHost.addTab(spec);
       // tabHost.setBackgroundResource(R.drawable.back);
                
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this,GUIPedidoCarrinho.class);
        spec = tabHost.newTabSpec("").setIndicator("CARRINHO",res.getDrawable(R.drawable.carrinhoproduto)).setContent(intent);
        tabHost.addTab(spec);
        
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this,GUIMovimentoPedido.class);
        spec = tabHost.newTabSpec("").setIndicator("FINALIZAR",res.getDrawable(R.drawable.movimentopedido)).setContent(intent);
        tabHost.addTab(spec);

       
	    if (CoreSFAndroid.flag_grupo == 2) {
	    	tabHost.setCurrentTab(0);
	    }else if (CoreSFAndroid.flag_grupo == 3) {
	    	tabHost.setCurrentTab(0);
		}else{
		    tabHost.setCurrentTab(2);	
		}
    }
  
}