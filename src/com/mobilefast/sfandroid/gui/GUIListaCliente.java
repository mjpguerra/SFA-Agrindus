package com.mobilefast.sfandroid.gui;
 
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCL;
import com.mobilefast.sfandroid.model.MBCLDAO;


 
public class GUIListaCliente extends Activity {

	public static String NOME_CLI = "NOME CLIENTE";
	public static String PRODUTO = "PRODUTO";
	private static int CADASTRO = 1;
	private static int SALVAR = 2;
	private static int CANCELAR = 3;
	@SuppressWarnings("unused")
	private static int FINDBAL = 4;
	ListView list;
	static EditText pesq;	
	int total_prod;
	int total_prodpesq;
	int flag_item =0;
	private DefaultArrayAdapterCliente adapter = null;	
	int aux;
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			ControllerSFAndroid.getInstancia().setContext(this);
	       // requestWindowFeature(Window.FEATURE_NO_TITLE);
	       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setTitle("Clientes");
			
			LinearLayout l = new LinearLayout(this);
		    l.setGravity(Gravity.CENTER_HORIZONTAL);
		    l.setOrientation(LinearLayout.VERTICAL);
		    l.setBackgroundResource(R.drawable.background);
		    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		    l.setLayoutParams(p);

			 LinearLayout ll = new LinearLayout(this);
			 ll.setGravity(Gravity.LEFT);
			 ll.setOrientation(LinearLayout.HORIZONTAL);
			 ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			    
	         final Button btPesq = new Button(this); 
	         btPesq.setText("Pesq.");       
	         btPesq.setTextSize(CoreSFAndroid.tamanho_fonte);
	         btPesq.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            		
				    	CoreSFAndroid.lista_cliente.clear();
				    	CoreSFAndroid.mCliente.clear();
				    	MBCLDAO.getInstancia().offset = 0;
				    	MBCLDAO.nome = pesq.getText().toString();	 
		                CoreSFAndroid.getintance().loadCliente();
		     			try {
							if (!MBCLDAO.nome.equals("")) {
		        	    		List<MBCL> Cliente =  LocatorDAO.getInstancia().getMbcl().findSelectPesq();
		        	    		total_prodpesq = Cliente.size();
							}else{
								flag_item = 0;
							}
		    		    } catch (Exception e) {
		    			   e.printStackTrace();
		    		    }
		                 adapter.notifyDataSetChanged();   	 	              
	             }
	         });
	         ll.addView(btPesq);
	         
		     pesq = new EditText(this);
		     pesq.setText("");
		     pesq.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		     ll.addView(pesq);  
		     l.addView(ll);
		     
			adapter = new DefaultArrayAdapterCliente(this,android.R.layout.simple_list_item_1, CoreSFAndroid.lista_cliente);
			RelativeLayout ll1 = new RelativeLayout(this);		
			ll1.setHorizontalGravity(Gravity.CENTER);
			ll1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
		    list = new ListView(this);
			list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));				
			list.setAdapter(adapter);
			list.setOnScrollListener(onScroll_List);	
		    list.setTextFilterEnabled(true);
		
			l.addView(list);
			setContentView(l);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

    OnItemClickListener onItemClick_List = new OnItemClickListener() {
    	public void onItemClick(AdapterView<?> arg0, View v, int i,long id) {
            //Pegar o item clicado   		
            addItem(i);
            finish();
        }
    };
    
    OnScrollListener onScroll_List = new OnScrollListener() {

		public void onScrollStateChanged(AbsListView view, int scrollState) {
		       if ( scrollState == OnScrollListener.SCROLL_STATE_IDLE )
	            {
	            list.invalidateViews();
	           }
			// TODO Auto-generated method stub
			//System.out.println(1);
		}								
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
		      if (totalItemCount > 0) {
		            int lastInScreen = firstVisibleItem + visibleItemCount;
		            if(lastInScreen == totalItemCount) {
		            	if (flag_item == 0) {
		            		if (total_prod == totalItemCount) {
		            			flag_item++;
							}
		            		if (total_prodpesq == totalItemCount) {
		            			flag_item++;
							}			           
		            		
		    		    	CoreSFAndroid.getintance().loadCliente();
		                    MBCLDAO.getInstancia().somarOffset();            
		                   adapter.notifyDataSetChanged();
						}
		            }
		        }		   

		}
    	
    };
    
    @SuppressWarnings("unused")
	private void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }  

public void addItem(int valor){
  	CoreSFAndroid.dcl = CoreSFAndroid.lista_cliente.get(valor);
  	//CoreSFAndroid.ROTEIRO_CORRENTE = CoreSFAndroid.lRoteiro.get(CoreSFAndroid.dro.getId());
  	CoreSFAndroid.cod_cliente = CoreSFAndroid.dcl.getDescricao();
    CoreSFAndroid.getintance().abrirTelaPrincipalCliente(); 
}

public boolean onCreateOptionsMenu(Menu menu) {
	super.onCreateOptionsMenu(menu);
    menu.add(0,GUIListaCliente.CADASTRO, 0, "Lista de Pedidos");
    menu.add(0,GUIListaCliente.SALVAR, 0, "Menu");
    menu.add(0,GUIListaCliente.CANCELAR, 0, "Sair");
   // menu.add(0,GUIClienteLista.FINDBAL, 0, "Localizar Balanca");
    return true;
}

public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case 1:
    	CoreSFAndroid.getintance().abrirTelalistaped();
    	finish();
        return true;
    case 2:
    	CoreSFAndroid.getintance().abrirTelaPrincipal();
    	finish();
        return true;
    case 3:
    	finish();
        return true;
    case 4:
        return true;
    }
    return false;
}

public boolean onKeyDown(int keyCode, KeyEvent event)  {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        // do something on back.
    	CoreSFAndroid.getintance().abrirTelaPrincipal();
    	finish();
        return true;
    }

    return super.onKeyDown(keyCode, event);
}

} 