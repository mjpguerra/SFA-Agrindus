package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCDCL;

public class GUIListaClienteCadastrado extends Activity{  
	   private ListView list = null;
	   private ArrayList<String> lista = null;
	   private List<MBCDCL> l = null;
	   private ArrayAdapter<String> adapter = null;
	   TextView tx_bairro;
	   TextView tx_cidade;
	   TextView tx_cnpj;
	   TextView tx_email;
	   TextView tx_end;
	   TextView tx_nome_cli;
	   TextView tx_nome_fan;
	   TextView tx_tele;
	   TextView tx_cep;
	   TextView tx_fax;
	   TextView tx_inscr_estad;
	   TextView tx_inscr_munic;
	   TextView tx_rg;
	   TextView tx_estado;
	   TextView tx_contato;
	public void onCreate(Bundle savedInstanceState) {
		try {
	        super.onCreate(savedInstanceState);
	        
	        ControllerSFAndroid.getInstancia().setContext(this);
	        
	        this.setTitle("Lista de Clientes cadastrados");
	        
	        lista = new ArrayList<String>();
	 		l = LocatorDAO.getInstancia().getMbcdcl().findAll();
            for (Iterator<MBCDCL> iterator = l.iterator(); iterator.hasNext();) {
            	MBCDCL mbcdcl = (MBCDCL) iterator.next();
				lista.add(mbcdcl.getRaz_social().toString().toUpperCase());
			}
	        loadLista(lista);
	     } catch (Exception var5) {
	        ;
	     }
	}
	
	
	 public void loadLista(List<String> lista){
	     adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
	     
	     android.widget.RelativeLayout ll = new android.widget.RelativeLayout(this);
	     ll.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
	     ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	     
	     list 	= new ListView(this);
	     list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	     list.setAdapter(adapter);
	 	
	     list.setOnItemClickListener(new OnItemClickListener(){
	                 public void onItemClick(AdapterView<?> arg0, View v, int i,long id) {
	                	CoreSFAndroid.CADASTRO = l.get(i);
	 					final Dialog dialog = new Dialog(v.getContext());
						dialog.setTitle("Detalhes do Cliente");
						dialog.setCancelable(true);
						float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
						
						ShapeDrawable shapeback = new ShapeDrawable(new RoundRectShape(outerR, null, null));
						shapeback.getPaint().setColor(Color.argb(20,0, 27, 134));
						
						LinearLayout l = new LinearLayout(v.getContext());
						l.setGravity(Gravity.CENTER_VERTICAL);
						l.setOrientation(LinearLayout.VERTICAL);
						l.setBackgroundDrawable(shapeback);
						LinearLayout.LayoutParams pd = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
						l.setLayoutParams(pd);
						
				        TextView var4 = new TextView(v.getContext());
				        var4.setText(" Razão Social");
				        var4.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var4.setTextColor(-1);
				        l.addView(var4);
				        
				        tx_nome_cli = new TextView(v.getContext());
				        tx_nome_cli.setText("");
				        tx_nome_cli.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_nome_cli.setBackgroundColor(-3355444);
				        tx_nome_cli.setTextColor(-16777216);
				        l.addView(tx_nome_cli);
				       
				        TextView var41 = new TextView(v.getContext());
				        var41.setText(" Nome Fantasia");
				        var41.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var41.setTextColor(-1);
				        l.addView(var41);
				        
				        tx_nome_fan = new TextView(v.getContext());
				        tx_nome_fan.setText("");
				        tx_nome_fan.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_nome_fan.setBackgroundColor(-3355444);
				        tx_nome_fan.setTextColor(-16777216);
				        l.addView(tx_nome_fan);
				        
				        TextView var6 = new TextView(v.getContext());
				        var6.setText(" Endereço");
				        var6.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var6.setTextColor(-1);
				        l.addView(var6);
				       
				        tx_end = new TextView(v.getContext());
				        tx_end.setText("");
				        tx_end.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_end.setBackgroundColor(-3355444);
				        tx_end.setTextColor(-16777216);
				        l.addView(tx_end);
				       
				        TextView var7 = new TextView(v.getContext());
				        var7.setText(" Bairro");
				        var7.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var7.setTextColor(-1);
				        l.addView(var7);
				        
				        tx_bairro = new TextView(v.getContext());
				        tx_bairro.setText("");
				        tx_bairro.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_bairro.setBackgroundColor(-3355444);
				        tx_bairro.setTextColor(-16777216);
				        l.addView(tx_bairro);
				        
				        TextView var811 = new TextView(v.getContext());
				        var811.setText(" Cidade");
				        var811.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var811.setTextColor(-1);
				        l.addView(var811);
				        
				        tx_cidade = new TextView(v.getContext());
				        tx_cidade.setText("");
				        tx_cidade.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_cidade.setBackgroundColor(-3355444);
				        tx_cidade.setTextColor(-16777216);
				        l.addView(tx_cidade);
				        
				        TextView var8 = new TextView(v.getContext());
				        var8.setText(" CEP");
				        var8.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var8.setTextColor(-1);
				        l.addView(var8);
				        
				        tx_cep = new TextView(v.getContext());
				        tx_cep.setText("");
				        tx_cep.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_cep.setBackgroundColor(-3355444);
				        tx_cep.setTextColor(-16777216);
				        l.addView(tx_cep);
				        
				        TextView var81 = new TextView(v.getContext());
				        var81.setText(" Estado");
				        var81.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var81.setTextColor(-1);
				        l.addView(var81);
				        
				        tx_estado = new TextView(v.getContext());
				        tx_estado.setText("");
				        tx_estado.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_estado.setBackgroundColor(-3355444);
				        tx_estado.setTextColor(-16777216);
				        l.addView(tx_estado);
				              
				        TextView var9 = new TextView(v.getContext());
				        var9.setText(" CPF/CNPJ");
				        var9.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var9.setTextColor(-1);
				        l.addView(var9);
				        
				        tx_cnpj = new TextView(v.getContext());
				        tx_cnpj.setText("");
				        tx_cnpj.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_cnpj.setBackgroundColor(-3355444);
				        tx_cnpj.setTextColor(-16777216);
				        l.addView(tx_cnpj);
				        
				        TextView var91 = new TextView(v.getContext());
				        var91.setText(" Inscrição Estadual");
				        var91.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var91.setTextColor(-1);
				        l.addView(var91);
				        
				        tx_inscr_estad = new TextView(v.getContext());
				        tx_inscr_estad.setText("");
				        tx_inscr_estad.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_inscr_estad.setBackgroundColor(-3355444);
				        tx_inscr_estad.setTextColor(-16777216);
				        l.addView(tx_inscr_estad);
				        
				        TextView var911 = new TextView(v.getContext());
				        var911.setText(" Inscrição Municipal");
				        var911.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var911.setTextColor(-1);
				        l.addView(var911);
				        
				        tx_inscr_munic = new TextView(v.getContext());
				        tx_inscr_munic.setText("");
				        tx_inscr_munic.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_inscr_munic.setBackgroundColor(-3355444);
				        tx_inscr_munic.setTextColor(-16777216);
				        l.addView(tx_inscr_munic);
				       
				        
				        TextView var10 = new TextView(v.getContext());
				        var10.setText( " RG");
				        var10.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var10.setTextColor(-1);
				        l.addView(var10);
				        
				        tx_rg = new TextView(v.getContext());
				        tx_rg.setText("");
				        tx_rg.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_rg.setBackgroundColor(-3355444);
				        tx_rg.setTextColor(-16777216);
				        l.addView(tx_rg);
				       
				        TextView var101 = new TextView(v.getContext());
				        var101.setText(" Contato");
				        var101.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var101.setTextColor(-1);
				        l.addView(var101);
				        
				        tx_contato = new TextView(v.getContext());
				        tx_contato.setText("");
				        tx_contato.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_contato.setBackgroundColor(-3355444);
				        tx_contato.setTextColor(-16777216);
				        l.addView(tx_contato);
				        
				        TextView var1011 = new TextView(v.getContext());
				        var1011.setText(" Email");
				        var1011.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var1011.setTextColor(-1);
				        l.addView(var1011);
				        
				        tx_email = new TextView(v.getContext());
				        tx_email.setText("");
				        tx_email.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_email.setBackgroundColor(-3355444);
				        tx_email.setTextColor(-16777216);
				        l.addView(tx_email);
				        
				        TextView var11 = new TextView(v.getContext());
				        var11.setText(" Telefone");
				        var11.setTextSize(CoreSFAndroid.tamanho_fonte);
				        var11.setTextColor(-1);
				        l.addView(var11);
				        
				        tx_tele = new TextView(v.getContext());
				        tx_tele.setText("");
				        tx_tele.setTextSize(CoreSFAndroid.tamanho_fonte);
				        tx_tele.setBackgroundColor(-3355444);
				        tx_tele.setTextColor(-16777216);
				        l.addView(tx_tele);
				        setCliente();
						
				        ScrollView sv = new ScrollView(v.getContext());
				        sv.addView(l);
						dialog.setContentView(sv);
						dialog.show();
	 		}
			});
	     ll.setBackgroundResource(R.drawable.background2);		
	     ll.addView(list);
	     this.setContentView(ll);
	 
	}
	 
		public void setCliente (){ 
	    	try {
	    		if (!(CoreSFAndroid.CADASTRO == null)) {
		            this.tx_nome_cli.setText("  " + CoreSFAndroid.CADASTRO.getRaz_social());
		            this.tx_cnpj.setText("  " + CoreSFAndroid.CADASTRO.getCgc_cpf());
		            this.tx_cidade.setText("  " + CoreSFAndroid.CADASTRO.getCidade());
		            this.tx_bairro.setText("  " + CoreSFAndroid.CADASTRO.getBairro());
		            this.tx_email.setText("  " + CoreSFAndroid.CADASTRO.getEmail());
		            this.tx_end.setText("  " + CoreSFAndroid.CADASTRO.getEndereco());
		            this.tx_tele.setText("  " + CoreSFAndroid.CADASTRO.getTelefone());
				}
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    		e.printStackTrace();
	    	}
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
