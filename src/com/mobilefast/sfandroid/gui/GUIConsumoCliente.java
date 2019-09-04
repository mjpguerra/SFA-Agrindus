package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class GUIConsumoCliente extends Activity{
	
	 private android.widget.ListView list = null;
	 private android.widget.ArrayAdapter<String> adapter = null;
	 private ArrayList<String> lista = null;
	 //private  List<Consumo> lc = null;
	 //p/rivate  List<Produto> lp = null;
	 
	public static String NOME_CLI = "NOME CLIENTE";  
	public static String PRODUTO = "PRODUTO";
	public static String DETALHE = "DETALHE CONSUMO";
	
	public static TextView labellogon = null;
    
	  
    /** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	try {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);
       // ControllerFrameRAY.getInstancia().setContext(this);
        
        this.setTitle("Consumo");      
            lista = new ArrayList<String>();
          //  lc = LocatorDAO.getInstancia().getConsumo().findByIDSuperior(""+CoreSFAndroid.cod_cliente);
          //  for (Iterator<Consumo> iterator = lc.iterator(); iterator.hasNext();) {
          //  	Consumo consu = (Consumo) iterator.next();			
			//    lista.add("Produto: " + consu.getCSN_PRODUT() + " / Qdte: "+ consu.getCSN_QDEATE() + " / Preço: "+ consu.getCSN_PRECO());
		//	}
            //lista = s.getStringArrayList("lista");
            this.loadLista(lista);
        
    	} catch (Exception e) {
    		e.printStackTrace();
    	}  
    }
    
    public void loadLista(List<String> lista){
    	
        LinearLayout l = new LinearLayout(this);
        l.setGravity(Gravity.CENTER_HORIZONTAL);
        l.setOrientation(LinearLayout.VERTICAL);
        //l.setBackgroundResource(sfa.android.R.drawable.back);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
        l.setLayoutParams(p);

        //l.addView(FactoryMyComponent.getInstance().getTextView(GUIConsumoCliente.NOME_CLI,"",CoreSFAndroid.CLIENTE_CORRENTE.getA1_NOME()));
        //l.addView(FactoryMyComponent.getInstance().getTextView(GUIConsumoCliente.PRODUTO,"Produto",""));
        
        //l.addView(FactoryMyComponent.getInstance().getTextViewTitulo("",false,false,0)
        
        labellogon = new TextView(this); 
        labellogon.setText(""); 
        labellogon.setTextColor(Color.rgb(0,229,238));
        labellogon.setTextSize(16); 
        labellogon.setId(393);
        //labellogon.setVisibility(TextView.VISIBLE); 
        l.addView(labellogon);
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.browser_link_context_header,lista);
        android.widget.RelativeLayout ll = new android.widget.RelativeLayout(this);
        ll.setHorizontalGravity(Gravity.CENTER);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        
        list 	= new ListView(this);
        list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        list.setAdapter(adapter);
    	
        list.setOnItemClickListener(new OnItemClickListener(){
                    public void onItemClick(AdapterView<?> arg0, View v, int i,long id) {
                    addItem(i);
    		}
   		});
        ll.setBackgroundColor(Color.BLACK);	          
        ll.addView(list);
        l.addView(ll);
        

       // l.addView(FactoryMyComponent.getInstance().getTextView(GUIConsumoCliente.PRODUTO,"Produto:",""));
        
        
        //ScrollView j = new ScrollView(this);
        //j.addView(ll);
        //setContentView(j);
        this.setContentView(l);
   }
    
    @SuppressWarnings("unchecked")
	public void addItem(int valor){
    	try {
        //	CoreSFAndroid.CONSUMO_CORRENTE = lc.get(valor);
        //	int id_prod = Integer.valueOf(CoreSFAndroid.CONSUMO_CORRENTE.getCSN_PRODUT());
        //	lp = (List<Produto>) LocatorDAO.getInstancia().getProduto().findAll();
        //	for (Iterator<Produto> iterator = lp.iterator(); iterator.hasNext();) {
		//		Produto pr = (Produto) iterator.next();
		//		if (pr.get_id() == id_prod) {
		//			FactoryMyComponent.getInstance().getMyTextView(PRODUTO).setText(pr.getB1_DESC());
		//		}
				
		//	}
       // 	labellogon.setText(" Pedido: " + CoreSFAndroid.CONSUMO_CORRENTE.getCSN_PEDIDO() + " Quantidade: " + CoreSFAndroid.CONSUMO_CORRENTE.getCSN_QDEATE() + " Data: " + CoreSFAndroid.CONSUMO_CORRENTE.getCSN_DATA()); 
        	
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0,1, 0, "INCLUIR PEDIDO");
        menu.add(0,2, 0, "VOLTAR");
        return true;
    }

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			CoreSFAndroid.getintance().inserirPedido();
			finish();
			return true;

		case 2:
			CoreSFAndroid.getintance().abrirTelaListaCliente();
			finish();
			return true;
		}
		return false;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // do something on back.
	    	CoreSFAndroid.getintance().abrirTelaListaCliente();
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}

}
