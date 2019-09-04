package com.mobilefast.sfandroid.gui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

@SuppressWarnings("deprecation")
public class GUITabCliente extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControllerSFAndroid.getInstancia().setContext(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setTitle("Clientes");

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost(); // The activity TabHost
        TabHost.TabSpec spec; // Resusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, GUIListaCliente.class);
        spec = tabHost.newTabSpec("").setIndicator("Listagem", res.getDrawable(R.drawable.cliente2)).setContent(intent);
        tabHost.addTab(spec);

        if (CoreSFAndroid.flag_rota == 2) {
            tabHost.setCurrentTab(1);
        } else {
            tabHost.setCurrentTab(0);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return getCurrentActivity().onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "Lista de Pedidos");
        menu.add(0, 2, 0, "Menu");
        menu.add(0, 3, 0, "Sair");
        return true;
    }

}