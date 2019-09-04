package com.mobilefast.sfandroid.gui;
 
import java.util.ArrayList;
import java.util.Iterator;
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
import android.widget.RelativeLayout;

import com.frame.mobilefast.DefaultListModel;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCP;
import com.mobilefast.sfandroid.model.MBLP;
import com.mobilefast.sfandroid.model.MBPD;
import com.mobilefast.sfandroid.model.MBPM;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;
import com.mobilefast.sfandroid.model.MBTM;
import com.mobilefast.sfandroid.model.MBTP;


 
public class GUIListaPedido  extends Activity{  
 

	   private ArrayAdapter<String> adapter = null;
	   private List<MBPM> l = null;
	   private List<MBCP> lcp = null;
	   private List<MBTP> ltp = null;
	   private List<MBPD> li = null;
	   private ListView list = null;
	   private ArrayList<String> lista = null;
	   MBPM pedi = null;
     

public void onCreate(Bundle savedInstanceState) {
	try {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);
        
        this.setTitle("Lista Pedidos");
        
        lista = new ArrayList<String>();
 		l = LocatorDAO.getInstancia().getMbPm().findAllIDCliente(CoreSFAndroid.cod_cliente);
        for (Iterator<MBPM> iterator = l.iterator(); iterator.hasNext();) {
        	pedi = iterator.next();
        	String ts = pedi.getD_I_R_T_Y_();
 			String status = null;
			if (ts.equals("T") || ts == null) {
				status = "Novo";
			}else if (ts.equals("")){	
				status = "Cotação";					
			}else if (ts.equals("F")){
				status = "Transmitido";				
			}

			String data = pedi.getDt_ped();
			String ano = data.substring(0, 4);
			String mes = data.substring(4, 6);
			String dia = data.substring(6, 8);			
			String emissao = dia + "/" + mes + "/" + ano;
			
			lista.add("Cod: " + String.valueOf(pedi.getNumero_sfa()) + "   Emissão: " + emissao + "   Status: " + status);
		} 
        this.loadLista(this.lista);
     } catch (Exception var5) {
        ;
     }
}
 public void loadLista(List<String> lista){
	 
     adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista); 
     RelativeLayout ll = new RelativeLayout(this);
     ll.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
     ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
     
     list 	= new ListView(this);
     list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
     list.setAdapter(adapter);	
     list.setOnItemClickListener(new OnItemClickListener(){
           public void onItemClick(AdapterView<?> arg0, View v, int i,long id) {
                     addItem(i);
                     finish();
 	       }
	 });
     ll.setBackgroundResource(R.drawable.background2);		
     ll.addView(list);
     setContentView(ll);
}

 
public void addItem(int valor){
	
	    CoreSFAndroid.PEDIDO_CORRENTE = l.get(valor);
		//Pedido Novo = 1 / Pedido Update = 2
		CoreSFAndroid.DataEntrega = "";
		CoreSFAndroid.controler_pedido = 2;
		CoreSFAndroid.flag_grupo = 2;
		CoreSFAndroid.codigo_pedidoc = CoreSFAndroid.PEDIDO_CORRENTE.getId();
			
				
		// CONDIÇÃO DE PAGAMENTO
		try {
			String cod_cond = CoreSFAndroid.PEDIDO_CORRENTE.getCond_pagto();
			List<MBCP> lcond = ((List<MBCP>) LocatorDAO.getInstancia().getMbcp()
					.findBycodigo(cod_cond));
			for (Iterator<MBCP> iterator = lcond.iterator(); iterator.hasNext();) {
				MBCP cond = iterator.next();
				CoreSFAndroid.CONDPAGTO = cond.getCond_pgto() + " - " + cond.getDescricao();
			}
		} catch (SFAndroidException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		try {
			String cod_mov = ""+CoreSFAndroid.PEDIDO_CORRENTE.getTipo_cli();
			MBTM tipo = LocatorDAO.getInstancia().getMbtm().findTipoCliente(cod_mov);
			if (!(tipo == null)) {
				CoreSFAndroid.TCLIENTE = tipo.getTIPO_MOVTO() + " - "+ tipo.getDESCRICAO();
			}
		} catch (SFAndroidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ltp = LocatorDAO.getInstancia().getMbTp().findAll();
			for (Iterator<MBTP> iterator = ltp.iterator(); iterator.hasNext();) {
				MBTP tpgto = iterator.next();
				if (CoreSFAndroid.PEDIDO_CORRENTE.getEmissao().equals(tpgto.getEmissao())) {
					CoreSFAndroid.TPGTO = tpgto.getEmissao() + " - " + tpgto.getDescricao();
				}			
			}
		} catch (SFAndroidException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        try {
			CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
			if (!CoreSFAndroid.VENDEDOR_CORRENTE.getMens2().trim().equals("")) {
				CoreSFAndroid.nm_listaVend = Integer.valueOf(CoreSFAndroid.VENDEDOR_CORRENTE.getMens2());
			}
		} catch (SFAndroidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    	CoreSFAndroid.lista_prod.clear();
    	CoreSFAndroid.mProdutos.clear();
    	MBPRDAO.getInstancia().offset = 0;
        CoreSFAndroid.getintance().addProdList(); 
        
	String idPedido = CoreSFAndroid.PEDIDO_CORRENTE.getNumero_sfa();
    CoreSFAndroid.lista_itensPedido.clear();
	CoreSFAndroid.mCarrinho.clear();

	try {
		int i =1;
		li = LocatorDAO.getInstancia().getMbPd().findByIDSuperior(idPedido);
		for (Iterator<MBPD> iterator = li.iterator(); iterator.hasNext();) {
			 MBPD item  = iterator.next();
			 String idO = item.getC_prod_palm();
				if (CoreSFAndroid.DataEntrega.equals("") || CoreSFAndroid.DataEntrega.equals(null)) {
					//CoreSFAndroid.DataEntrega = item.getDt_entrega();	
					String data = item.getDt_entrega();
					String ano = data.substring(0, 4);
					String mes = data.substring(4, 6);
					String dia = data.substring(6, 8);			
					CoreSFAndroid.DataEntrega =  dia + "/" + mes + "/" + ano;
				}			
				MBPR pt = LocatorDAO.getInstancia().getMbpr().findCodProduto(idO);
				CoreSFAndroid.mCarrinho.put(String.valueOf(pt.getId()),pt);	
				
				CoreSFAndroid.di = new DefaultListModel();	
				CoreSFAndroid.di.setSequencia_item(Integer.valueOf(item.getSequencia()));
				CoreSFAndroid.di.setMetaDataTag(item.getC_prod_palm());
				CoreSFAndroid.di.setQuantidade((int) item.getQtde());
				CoreSFAndroid.di.setValor(CoreSFAndroid.formataMoeda(item.getVlr_unit()));
				CoreSFAndroid.di.setPeso_item(pt.getPeso_bruto());
				CoreSFAndroid.di.setFrete(pt.getPosicao_estq());
				CoreSFAndroid.di.setNome(String.valueOf(pt.getId()));
				CoreSFAndroid.di.setDescricao(pt.getDescricao());
				CoreSFAndroid.di.setComissao(item.getReservado5());
				CoreSFAndroid.di.setTotalvalor(item.getVlr_unit()* item.getQtde());
				double preco = 0.00;
				double precoOrig = 0.00;
				MBLP pr = null;
				try {
					pr = LocatorDAO.getInstancia().getMblp().findSelect(CoreSFAndroid.nm_lista,pt.getC_prod_palm());
					if (!(pr == null)) {
						preco = pr.getPRECO();
					} else {
						preco = 0.00;
					}
					pr = LocatorDAO.getInstancia().getMblp().findSelect(CoreSFAndroid.nm_listaVend,pt.getC_prod_palm());
					if (!(pr == null)) {
						precoOrig = pr.getPRECO();
					} else {
						precoOrig = 0.00;
					}
				} catch (SFAndroidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CoreSFAndroid.di.setvalorCliente(CoreSFAndroid.formataMoeda(preco));
				CoreSFAndroid.di.setValorOriginal(CoreSFAndroid.formataMoeda(precoOrig));
				CoreSFAndroid.lista_itensPedido.add(CoreSFAndroid.di);

		}
	} catch (SFAndroidException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    CoreSFAndroid.getintance().abrirTelaPrincipalPedido(); 
    
//	}
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

public void setItenspedido(){

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