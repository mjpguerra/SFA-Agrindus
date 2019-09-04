package com.mobilefast.sfandroid.gui;

import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.mobilefast.DefaultListModel;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCL;
import com.mobilefast.sfandroid.model.MBCP;
import com.mobilefast.sfandroid.model.MBLP;
import com.mobilefast.sfandroid.model.MBPD;
import com.mobilefast.sfandroid.model.MBPM;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;
import com.mobilefast.sfandroid.model.MBTM;
import com.mobilefast.sfandroid.model.MBTP;

public class GUIDetalheCliente extends Activity{
	
	public static String COD_CLI = "CODIGO CLIENTE";  
	public static String NOME_CLI = "NOME CLIENTE";
	public static String CNPJ_CLI = "CNJP CLIENTE";
	public static String END_CLI = "ENDEREÇO CLIENTE";
	public static String BAIRRO_CLI = "BAIRRO CLIENTE";
	public static String CIDADE_CLI = "CIDADE CLIENTE";
	public static String TEL_CLI = "TELEFONE CLIENTE";
	public static String EMAIL_CLI = "EMAIL CLIENTE";
	public static String STATUS_CLI = "STATUS CLIENTE";
	public static String ROTA_CLI = "ROTA CLIENTE"; 
	
	   MBCL obCliente = null;
	   TextView tx_bairro;
	   TextView tx_cidade;
	   TextView tx_cmpj;
	   TextView tx_cod;
	   TextView tx_email;
	   TextView tx_end;
	   TextView tx_nome_cli;
	   TextView tx_rota;
	   TextView tx_status;
	   TextView tx_tele;
	   private List<MBPD> li = null;
	   private List<MBTP> ltp = null;
    /** Called when the activity is first created. */
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);
        //ControllerFrameRAY.getInstancia().setContext(this);
        
        this.setTitle("Detalhe Cliente"); 
        
        LinearLayout var2 = new LinearLayout(this);
        var2.setGravity(1);
        var2.setOrientation(1);
        var2.setLayoutParams(new LayoutParams(-1, -1));
        
        TextView var3 = new TextView(this);
        var3.setText("Codigo");
        var3.setTextSize(CoreSFAndroid.tamanho_fonte);
        var3.setTextColor(-1);
        var2.addView(var3);
       
        this.tx_cod = new TextView(this);
        this.tx_cod.setText("");
        this.tx_cod.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cod.setBackgroundColor(-3355444);
        this.tx_cod.setTextColor(-16777216);
        var2.addView(this.tx_cod);
       
        TextView var4 = new TextView(this);
        var4.setText("Razão Social");
        var4.setTextSize(CoreSFAndroid.tamanho_fonte);
        var4.setTextColor(-1);
        var2.addView(var4);
        
        this.tx_nome_cli = new TextView(this);
        this.tx_nome_cli.setText("");
        this.tx_nome_cli.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_nome_cli.setBackgroundColor(-3355444);
        this.tx_nome_cli.setTextColor(-16777216);
        var2.addView(this.tx_nome_cli);
       
        TextView var5 = new TextView(this);
        var5.setText("Rota");
        var5.setTextSize(CoreSFAndroid.tamanho_fonte);
        var5.setTextColor(-1);
        var2.addView(var5);
      
        this.tx_rota = new TextView(this);
        this.tx_rota.setText("");
        this.tx_rota.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_rota.setBackgroundColor(-3355444);
        this.tx_rota.setTextColor(-16777216);
        var2.addView(this.tx_rota);
        
        TextView var6 = new TextView(this);
        var6.setText("Endereço");
        var6.setTextSize(CoreSFAndroid.tamanho_fonte);
        var6.setTextColor(-1);
        var2.addView(var6);
       
        this.tx_end = new TextView(this);
        this.tx_end.setText("");
        this.tx_end.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_end.setBackgroundColor(-3355444);
        this.tx_end.setTextColor(-16777216);
        var2.addView(this.tx_end);
       
        TextView var7 = new TextView(this);
        var7.setText("Bairro");
        var7.setTextSize(CoreSFAndroid.tamanho_fonte);
        var7.setTextColor(-1);
        var2.addView(var7);
        
        this.tx_bairro = new TextView(this);
        this.tx_bairro.setText("");
        this.tx_bairro.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_bairro.setBackgroundColor(-3355444);
        this.tx_bairro.setTextColor(-16777216);
        var2.addView(this.tx_bairro);
        
        TextView var8 = new TextView(this);
        var8.setText("Cidade");
        var8.setTextSize(CoreSFAndroid.tamanho_fonte);
        var8.setTextColor(-1);
        var2.addView(var8);
        
        this.tx_cidade = new TextView(this);
        this.tx_cidade.setText("");
        this.tx_cidade.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cidade.setBackgroundColor(-3355444);
        this.tx_cidade.setTextColor(-16777216);
        var2.addView(this.tx_cidade);
       
        TextView var9 = new TextView(this);
        var9.setText("CNPJ");
        var9.setTextSize(CoreSFAndroid.tamanho_fonte);
        var9.setTextColor(-1);
        var2.addView(var9);
        
        this.tx_cmpj = new TextView(this);
        this.tx_cmpj.setText("");
        this.tx_cmpj.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cmpj.setBackgroundColor(-3355444);
        this.tx_cmpj.setTextColor(-16777216);
        var2.addView(this.tx_cmpj);
       
        TextView var10 = new TextView(this);
        var10.setText("Email");
        var10.setTextSize(CoreSFAndroid.tamanho_fonte);
        var10.setTextColor(-1);
        var2.addView(var10);
        
        this.tx_email = new TextView(this);
        this.tx_email.setText("");
        this.tx_email.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_email.setBackgroundColor(-3355444);
        this.tx_email.setTextColor(-16777216);
        var2.addView(this.tx_email);
        
        TextView var11 = new TextView(this);
        var11.setText("Telefone");
        var11.setTextSize(CoreSFAndroid.tamanho_fonte);
        var11.setTextColor(-1);
        var2.addView(var11);
        
        this.tx_tele = new TextView(this);
        this.tx_tele.setText("");
        this.tx_tele.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_tele.setBackgroundColor(-3355444);
        this.tx_tele.setTextColor(-16777216);
        var2.addView(this.tx_tele);
        
        
		LinearLayout fundoPED = new LinearLayout(this);
		fundoPED.setGravity(Gravity.CENTER_HORIZONTAL);
		fundoPED.setOrientation(LinearLayout.HORIZONTAL);
		fundoPED.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		fundoPED.setPadding(0, 20, 0, 0);
		
		float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
		ShapeDrawable shape3 = null;
		shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape3.getPaint().setColor(Color.argb(100, 89, 148, 103));
		Rect mRect = new Rect(0, 0, 120, 120);
		
		
		
		GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 0, 155, 52), Color.rgb( 0, 105, 35)});
		grad.setCornerRadii(outerR);
		grad.setBounds(mRect);
		//grad.setGradientType(1);
	      
	    LinearLayout lpedido = new LinearLayout(this); 
	    lpedido.setGravity(Gravity.LEFT);
	    lpedido.setOrientation(1);
	    lpedido.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	      
		Button btpedido = new Button(this);	
		btpedido.setText("Novo Pedido");
		btpedido.setTextColor(Color.WHITE);
		btpedido.setTextSize(CoreSFAndroid.tamanho_fonte);
		btpedido.setBackgroundDrawable(grad);
		btpedido.setPadding(10,10,10,10);
		btpedido.setGravity(Gravity.CENTER);
		btpedido.setTypeface(Typeface.DEFAULT_BOLD);
		btpedido.setTag(CoreSFAndroid.dcl);
		btpedido.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btpedido.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				if (!CoreSFAndroid.dcl.getFrete().equals("1")) {
					try {
						CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
						if (!CoreSFAndroid.VENDEDOR_CORRENTE.getMens2().trim().equals("")) {
							CoreSFAndroid.nm_listaVend = Integer.valueOf(CoreSFAndroid.VENDEDOR_CORRENTE.getMens2());
						}

					} catch (SFAndroidException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CoreSFAndroid.getintance().inserirPedido();
					finish();

				} else {
					Toast.makeText(
							v.getContext(),
							"BLOQUEADO!  Cliente Indisponivel para Emissão de Pedidos!",
							Toast.LENGTH_LONG).show();
				}
			}

		});	
		lpedido.addView(btpedido);	
		fundoPED.addView(lpedido);	
		
		
		GradientDrawable grad6 = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 223, 171, 67),Color.rgb( 230, 154, 0)});
		//grad.setShape(GradientDrawable.);
		grad6.setCornerRadii(outerR);
		grad6.setBounds(mRect);
		//grad.setGradientType(1);
	      
	    LinearLayout lultimo = new LinearLayout(this);  
	    lultimo.setGravity(Gravity.LEFT);
	    lultimo.setOrientation(1);
	    lultimo.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	    lultimo.setPadding(20, 0, 0, 0);
	    
		Button btUltimo = new Button(this);	 
		btUltimo.setText("C. Ultimo Pedido");
		btUltimo.setTextSize(CoreSFAndroid.tamanho_fonte);
		btUltimo.setTextColor(Color.WHITE);
		btUltimo.setBackgroundDrawable(grad6);
		btUltimo.setPadding(10,10,10,10);
		btUltimo.setGravity(Gravity.CENTER);
		btUltimo.setTypeface(Typeface.DEFAULT_BOLD);
		///btassinatura.setTag(CoreSFAndroid.dcl);
		btUltimo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btUltimo.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {								            
				if (!CoreSFAndroid.dcl.getFrete().equals("1")) {	
				try {
					MBPM pedido = LocatorDAO.getInstancia().getMbPm().findUltimoCliente(CoreSFAndroid.cod_cliente);
					if (pedido != null) {												
						CoreSFAndroid.PEDIDO_CORRENTE = null;
						CoreSFAndroid.getintance();
						CoreSFAndroid.criarPedido();
						CoreSFAndroid.PEDIDO_CORRENTE.setTipo_ped("N");						
						CoreSFAndroid.controler_pedido = 1;
						if (CoreSFAndroid.lista_itensPedido != null) {
							CoreSFAndroid.lista_itensPedido.clear();
						}
						if (CoreSFAndroid.mCarrinho != null) {
							CoreSFAndroid.mCarrinho.clear();
						}
						CoreSFAndroid.DataEntrega = "";
						CoreSFAndroid.flag_grupo = 2;
										
						try {
							MBCP cond = LocatorDAO.getInstancia().getMbcp().findCodPagto(pedido.getCond_pagto());
							if (!(cond == null)) {
								CoreSFAndroid.PEDIDO_CORRENTE.setCond_pagto(CoreSFAndroid.Cond_Pagto);
								CoreSFAndroid.CONDPAGTO = cond.getCond_pgto() + " - "+ cond.getDescricao();
							}
						} catch (SFAndroidException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							MBTM tipo = LocatorDAO.getInstancia().getMbtm().findTipoCliente(CoreSFAndroid.tipo_cliente.trim());
							if (!(tipo == null)) {
								CoreSFAndroid.PEDIDO_CORRENTE.setTipo_cli(CoreSFAndroid.tipo_cliente);
								CoreSFAndroid.PEDIDO_CORRENTE.setReservado2(Integer.parseInt(tipo.getTIPO_MOVTO()));
								CoreSFAndroid.TCLIENTE = tipo.getTIPO_MOVTO() + " - "+ tipo.getDESCRICAO();
							}
						} catch (SFAndroidException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							ltp = LocatorDAO.getInstancia().getMbTp().findAll();
							for (Iterator<MBTP> iterator = ltp.iterator(); iterator
									.hasNext();) {
								MBTP tpgto = iterator.next();
								if ("V".equals(tpgto.getEmissao())) {
									CoreSFAndroid.TPGTO = tpgto.getEmissao() + " - "
											+ tpgto.getDescricao();
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
						MBPRDAO.getInstancia().nome = "";
						CoreSFAndroid.getintance().addProdList();
					
					CoreSFAndroid.codigo_pedidoc = pedido.getId();
					String idPedido = pedido.getNumero_sfa();
					
				    CoreSFAndroid.lista_itensPedido.clear();
					CoreSFAndroid.mCarrinho.clear();

					try {
						int i =1;
						li = LocatorDAO.getInstancia().getMbPd().findByIDSuperior(idPedido);
						for (Iterator<MBPD> iterator = li.iterator(); iterator.hasNext();) {
							 MBPD item  = iterator.next();
							 String idO = item.getC_prod_palm();
								if (CoreSFAndroid.DataEntrega.equals("")) {
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
								CoreSFAndroid.di.setTotalvalor(item.getVlr_unit() * item.getQtde());
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
				    finish();
					}else{
			            Toast.makeText(v.getContext(),"Não existe pedido para esse Cliente!!", Toast.LENGTH_LONG).show();           
					}
				} catch (SFAndroidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} else {
					Toast.makeText(
							v.getContext(),
							"BLOQUEADO!  Cliente Indisponivel para Emissão de Pedidos!",
							Toast.LENGTH_LONG).show();
				}
			}

		});	
		lultimo.addView(btUltimo);			
		fundoPED.addView(lultimo);
		var2.addView(fundoPED);
		
		LinearLayout fundoPED1 = new LinearLayout(this);
		fundoPED1.setGravity(Gravity.CENTER_HORIZONTAL);
		fundoPED1.setOrientation(LinearLayout.HORIZONTAL);
		fundoPED1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		fundoPED1.setPadding(0, 20, 0, 0);
		
		GradientDrawable grad1 = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 103, 117, 243),Color.rgb( 4, 4, 144)});
		//grad.setShape(GradientDrawable.);
		grad1.setCornerRadii(outerR);
		grad1.setBounds(mRect);
		//grad.setGradientType(1);
	      
	    LinearLayout lcamera = new LinearLayout(this);  
	    lcamera.setGravity(Gravity.LEFT);
	    lcamera.setOrientation(1);
	    lcamera.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	    lcamera.setPadding(20, 0, 0, 0);
	    
		Button btCamera = new Button(this);	 
		btCamera.setText("Câmera");
		btCamera.setTextSize(CoreSFAndroid.tamanho_fonte);
		btCamera.setTextColor(Color.WHITE);
		btCamera.setBackgroundDrawable(grad1);
		btCamera.setPadding(10,10,10,10);
		btCamera.setGravity(Gravity.CENTER);
		btCamera.setTypeface(Typeface.DEFAULT_BOLD);
		///btassinatura.setTag(CoreSFAndroid.dcl);
		btCamera.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btCamera.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
 	            CoreSFAndroid.getintance().abrirTelaCamera();
 	            //finish();
			}

		});	
		lcamera.addView(btCamera);			
		fundoPED1.addView(lcamera);
		
		
		GradientDrawable grad11 = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 243, 255, 67),Color.rgb( 174, 186, 0)});
		//grad.setShape(GradientDrawable.);
		grad11.setCornerRadii(outerR);
		grad11.setBounds(mRect);
		//grad.setGradientType(1);
	      
	    LinearLayout lgaleria = new LinearLayout(this);  
	    lgaleria.setGravity(Gravity.LEFT);
	    lgaleria.setOrientation(1);
	    lgaleria.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	    lgaleria.setPadding(20, 0, 0, 0);
	    
		Button btGaleria = new Button(this);	 
		btGaleria.setText("Galeria");
		btGaleria.setTextSize(CoreSFAndroid.tamanho_fonte);
		btGaleria.setTextColor(Color.WHITE);
		btGaleria.setBackgroundDrawable(grad11);
		btGaleria.setPadding(10,10,10,10);
		btGaleria.setGravity(Gravity.CENTER);
		btGaleria.setTypeface(Typeface.DEFAULT_BOLD);
		//btassinatura1.setTag(CoreSFAndroid.dcl);
		btGaleria.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btGaleria.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
 	            CoreSFAndroid.getintance().abrirTelaGaleria();
 	           // finish();
			}

		});	
		lgaleria.addView(btGaleria);			
		fundoPED1.addView(lgaleria); 	   
 	    var2.addView(fundoPED1);
       
        ScrollView var12 = new ScrollView(this);
        var12.setBackgroundResource(R.drawable.background2);
        var12.addView(var2);
        setContentView(var12);
        setCliente();
    }
    
	public void setCliente (){ 
    	try {
    		CoreSFAndroid.lCli = LocatorDAO.getInstancia().getMbcl().findCodCliente(CoreSFAndroid.cod_cliente);
    		for (Iterator<MBCL> iterator = CoreSFAndroid.lCli.iterator(); iterator.hasNext();) {
				//Cliente cli = (Cliente) iterator.next();
				MBCL var3 = (MBCL)iterator.next();
	            this.tx_cod.setText("  " + var3.getCliente_alfa());
	            this.tx_nome_cli.setText("  " + var3.getRaz_social());
	            this.tx_cmpj.setText("  " + var3.getCgc_cpf());
	            this.tx_cidade.setText("  " + var3.getCidade());
	            this.tx_bairro.setText("  " + var3.getBairro());
	            this.tx_email.setText("  " + var3.getEmail());
	            this.tx_end.setText("  " + var3.getEndereco());
	            this.tx_rota.setText("  " + var3.getEstado());
	            this.tx_tele.setText("  " + var3.getTelefone());
			}
 	

        //FactoryMyComponent.getInstance().getMyTextView(NOME_CLI).setText(obCliente.getA1_NOME());
    	} catch (Exception e) {
    		// TODO: handle exception
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
            //MBCLDAO.getInstancia().offset = 0;
           // MBCLDAO.getInstancia().nome = "";
           // CoreSFAndroid.lista_cliente.clear();
           // CoreSFAndroid.getintance().loadCliente();
            CoreSFAndroid.getintance().abrirTelaListaCliente();
        	finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    

}
