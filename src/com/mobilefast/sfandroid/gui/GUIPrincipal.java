package com.mobilefast.sfandroid.gui;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.frame.mobilefast.Thema;
import com.mobilefast.sfandroid.model.MBCLDAO;
import com.mobilefast.sfandroid.model.MBPRDAO;

public class GUIPrincipal extends Activity {	
	private ProgressDialog progressDialog; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);
       // ControllerFrameRAY.getInstancia().setContext(this);
        this.setTitle("Principal");  
        
        LinearLayout ll = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
		ll.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		ll.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
				Thema.VAL_B);
		ll.setBackgroundResource(R.drawable.background);
        
        GridView gridview = new GridView(ControllerSFAndroid.getInstancia().getContext()); 
		gridview.setNumColumns(2);  
		gridview.setGravity(Gravity.CENTER);  
		gridview.setAdapter(new AppsAdapterPrincipal());      
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == 0) {
					CoreSFAndroid.flag_rota = 1;
					MBCLDAO.getInstancia().offset = 0;
					MBCLDAO.getInstancia().nome = "";
			    	CoreSFAndroid.lista_cliente.clear();
			    	CoreSFAndroid.mCliente.clear();
	                CoreSFAndroid.getintance().loadCliente();
					CoreSFAndroid.getintance().abrirTelaListaCliente();
					finish();
				} else if (position == 1) {
			    	CoreSFAndroid.lista_prod.clear();
			    	CoreSFAndroid.mProdutos.clear();
			    	MBPRDAO.getInstancia().offset = 0;
					CoreSFAndroid.nm_lista = 5;
	                CoreSFAndroid.getintance().addProdList();
					CoreSFAndroid.getintance().abrirTelaProdutoPrincipal();
					finish();
				} else if (position == 2) {
					CoreSFAndroid.getintance().abrirTelaListaCadastroCliente();
				    finish();
				} else if (position == 3) {
					CoreSFAndroid.getintance().abrirTelaCadastroCliente();
					finish();
				} else if (position == 4) {
					CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
					finish();
				}
			}
		});
		
        ll.addView(gridview);
        
       // ScrollView J = new ScrollView(this);
        //J.addView(ll);
        setContentView(ll);
    }
    
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        menu.add(0,1, 0, "Cadastrar Cliente novo...");
        menu.add(0,2, 0, "FastSync");
        menu.add(0,3, 0, "Sair");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
			CoreSFAndroid.getintance().abrirTelaCadastroCliente();
			finish();
            return true;
		case 2:
			CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
			finish();
			return true;
        case 3:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);     
		    builder.setMessage("Deseja realmente sair do Sales Force Automation?")            
		    .setCancelable(false)        
		    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
		    .setTitle("Atenção:") //título do caixa de diálogo
		            
		    //Evento disparado se clicar no botão Sim
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
		        public void onClick(DialogInterface dialog, int id) {	        			
                       finish();
		        }            
		    })            

		    //Event disparado se clicar no botão Não
		    .setNegativeButton("Não", new DialogInterface.OnClickListener() {                
		        public void onClick(DialogInterface dialog, int id) {                     
		            dialog.cancel(); //Cancela a caixa de diálogo e volta a tela anterior
		        }            
		    });     
		    AlertDialog alert = builder.create();     
		    alert.show(); //Chama a caixa de diálogo
            return true;        
        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.     	
        	CoreSFAndroid.getintance().abrirTelaLogin();
        	finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}