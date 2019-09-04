package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.frame.mobilefast.DefaultListModel;

public class GUIOcorrenciaCliente extends Activity{
	
	 private android.widget.ListView list = null;
	 private android.widget.ArrayAdapter<String> adapter = null;
	 private ArrayList<String> lista = null;
	 //private List<Ocorrencia> l = null; 
	 
	public static String OCORRENCIA = "OCORRENCIA DE NAO VENDA"; 
	public static String NOME_CLI = "NOME CLIENTE";
	public static String PRODUTO = "PRODUTO";
    String string [] =
    	{ "COMERCIO FECHADO", "COMPRADOR AUSENTE", "ESTOQUE ALTO", "PREÇO ALTO", "OUTRO FORNECEDOR" };
    List<DefaultListModel> lOcorr = new ArrayList<DefaultListModel>();
   // Ocorrencia obOcorrencia =null;
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try {
            super.onCreate(savedInstanceState);     
            
            ControllerSFAndroid.getInstancia().setContext(this);
            
            this.setTitle("Occorencia Não Venda"); 
            
            LinearLayout l = new LinearLayout(this);
            l.setGravity(Gravity.CENTER_HORIZONTAL);
            l.setOrientation(LinearLayout.VERTICAL);
            
            l.setBackgroundResource(R.drawable.background);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
            l.setLayoutParams(p);

           // l.addView(FactoryMyComponent.getInstance().getTextView(GUIOcorrenciaCliente.NOME_CLI,"Cliente",""));
    		gerar_ocorrencia();
    		
    	   // l.addView(FactoryMyComponent.getInstance().getTextView(GUIOcorrenciaCliente.OCORRENCIA,"","Ocorrecia Não Venda",lOcorr,2));
           
    	    lista = new ArrayList<String>();
        	lista.add("teste");	
        	listaOcorrencia();
        	//listaOcorrencia();
        	
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
            android.widget.RelativeLayout ll = new android.widget.RelativeLayout(this);
            ll.setHorizontalGravity(Gravity.CENTER);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
            
            list 	= new ListView(this);
            list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
            list.setAdapter(adapter);
        	
            list.setOnItemClickListener(new OnItemClickListener(){
                        public void onItemClick(AdapterView<?> arg0, View v, int i,long id) {
                        //FactoryMyComponent.getInstance().getMyTextView(PRODUTO).setText("NOTEBOOK CCE-CL30");
        		}
       		});
            ll.setBackgroundResource(R.drawable.background);       
            ll.addView(list);
            
            l.addView(ll);

        	
        	
        	setContentView(l);
        	this.setCliente();
		} catch (Exception e) {
			// TODO: handle exception
		}

    }
      
    public void addItem(int valor){
      	//String nome = lista.get(valor).toString();
        //CoreSFAndroid.getintance().abrirTelaPrincipalCliente();  
        //finish();
    }
    
    @SuppressWarnings("unchecked")
	public void listaOcorrencia(){
    	try {
    		lista.clear();
        //	l = LocatorDAO.getInstancia().getOcorrencia().findAll();
        	//for (Iterator<Ocorrencia> iterator = l.iterator(); iterator.hasNext();) {
    		//	Ocorrencia oco = (Ocorrencia) iterator.next();
    		//	lista.add(oco.getDescricao());	  			
    		//}	
        	
		} catch (Exception e) {
			// TODO: handle exception
		}

    	
    }
    
	public void gerar_ocorrencia() {
		String string[] = { "COMERCIO FECHADO", "COMPRADOR AUSENTE",
				"ESTOQUE ALTO", "PREÇO ALTO", "OUTRO FORNECEDOR" };

		for (int i = 0; i < string.length; i++) {
			DefaultListModel d = new DefaultListModel();
			d.setNome(string[i]);
			lOcorr.add(d);
		}
	}

	//public void salvar(Ocorrencia obOcorrencia){
	//	try {
	//		if (obOcorrencia == null){
		//		obOcorrencia = new Ocorrencia();
		//		obOcorrencia.setIdEmpresa(Controller.idEmpresa);
		//	}
			//obOcorrencia.setDescricao(FactoryMyComponent.getInstance()
			//		.getMyTextView(OCORRENCIA).getText().toString());
			//obOcorrencia.setCliente_ir(Integer.valueOf(CoreSFAndroid.cod_cliente));
			//obOcorrencia = LocatorDAO.getInstancia().getOcorrencia().salvar(obOcorrencia);
			

		//} catch (Exception e) {
			// TODO: handle exception
	//	}
//	}
	public void setCliente() {
		try {
		//	FactoryMyComponent.getInstance().getMyTextView(NOME_CLI)
		//			.setText(CoreSFAndroid.CLIENTE_CORRENTE.getA1_NOME());		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0,1, 0, "GRAVAR OCORRÊNCIA");
        menu.add(0,3, 0, "VOLTAR");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        //	salvar(obOcorrencia);
        	listaOcorrencia();
        	refresh(); 	
        case 2:  	
        	CoreSFAndroid.getintance().abrirTelaListaCliente();
        	finish();
            return true;  
        }
        return false;
    }
    
    
    public void  refresh(){
    	 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
    	 list.setAdapter(adapter);
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
