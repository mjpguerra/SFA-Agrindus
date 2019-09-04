package com.mobilefast.sfandroid.gui;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

import sfa.android.ControllerSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class GUITabInfoCliente extends TabActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControllerSFAndroid.getInstancia().setContext(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setTitle("Clientes");

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, GUIDetalheCliente.class);
        spec = tabHost.newTabSpec("").setIndicator("Detalhes", res.getDrawable(R.drawable.detalhe)).setContent(intent);
        tabHost.addTab(spec);
        //tabHost.setBackgroundResource(R.drawable.background2);

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, GUIListaPedido.class);
        spec = tabHost.newTabSpec("").setIndicator("L.Pedidos", res.getDrawable(R.drawable.listpedido)).setContent(intent);
        tabHost.addTab(spec);
        //tabHost.setBackgroundResource(R.drawable.background);

        // Create an Intent to launch an Activity for the tab (to be reused)
        //intent = new Intent().setClass(this,GUIOcorrenciaCliente.class);
        // spec = tabHost.newTabSpec("").setIndicator("Ocorrência",res.getDrawable(R.drawable.ocorr)).setContent(intent);
        //tabHost.addTab(spec);
        //tabHost.setBackgroundResource(R.drawable.background);

        // Create an Intent to launch an Activity for the tab (to be reused)
        //intent = new Intent().setClass(this,GUIConsumoCliente.class);
        //spec = tabHost.newTabSpec("").setIndicator("Consumo",res.getDrawable(R.drawable.consumo)).setContent(intent);
        //tabHost.addTab(spec);
        //tabHost.setBackgroundResource(R.drawable.background);

        tabHost.getTabWidget().setCurrentTab(0);

    }
}