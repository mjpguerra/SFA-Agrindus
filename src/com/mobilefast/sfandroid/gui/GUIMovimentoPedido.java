

package com.mobilefast.sfandroid.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.frame.mobilefast.ControllerFrameWAR;
import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.DefaultModelItem;
import com.frame.mobilefast.MyLinearLayout;
import com.frame.mobilefast.MyListener;
import com.frame.mobilefast.MyListenerComRetornoDoNomeDoComponente;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCP;
import com.mobilefast.sfandroid.model.MBPD;
import com.mobilefast.sfandroid.model.MBPM;
import com.mobilefast.sfandroid.model.MBPRDAO;
import com.mobilefast.sfandroid.model.MBTM;
import com.mobilefast.sfandroid.model.MBTP;

public class GUIMovimentoPedido extends Activity {

   public static String ACOMP = "ACOMPANHANTE";
   public static String CONDPAGTO = "CONDPAGTO";
   public static String TPGTO = "TPGTO";
   public static String TCLIENTE = "TCLIENTE";
   public static String DATA = "DATA ENTREGA";
   public static int DATE_DIALOG_ID;
   public static String DESCONTO = "DESCONTO item";
   public static int ID_COMPONENTE = 1;
   public static String QTDE = "Quantidade item";
   public static String TOTAL = "TOTAL PEDIDO";

   public static Button btvalor = null;
   private static Context context = null;
   public static String cod_comp;
   public static String cota;
   public static double custo;
   public static List lTotal = new ArrayList();
   public static double mb_itens;
   public static double mk_itens;
   static String preco;
   public static int total_itens;
   public static double total_ped;
   public static int total_peso;
   public static double total_ipi;
   static EditText txpreco;
   static EditText txqtde;
   public static double venda;
   public static int controle_validar = 0;

   private final Map<Object, Object> CACHE = new HashMap<Object, Object>();
   private final Map<String, MyLinearLayout> CACHE_LAYOUT = new HashMap<String, MyLinearLayout>();
   public static DefaultArrayAdapterPedidoItens adapter = null;
   int i;
   List<DefaultListModel> lAcomp = new ArrayList<DefaultListModel>();
   List<DefaultListModel> lCond = new ArrayList<DefaultListModel>();
   List<DefaultListModel> lTMov = new ArrayList<DefaultListModel>();
   private List<?> lPagto = null;
   private List<MBTM> lTMovi = null;
   List<DefaultListModel> lTPagto = new ArrayList<DefaultListModel>();
   private List<?> lcon = null;
   private ListView list = null;
   private MyListener listener;
   private MyListenerComRetornoDoNomeDoComponente listener2;
  
   private OnDateSetListener mDateSetListener = new OnDateSetListener() {
      public void onDateSet(DatePicker var1, int var2, int var3, int var4) {
         GUIMovimentoPedido.this.mYear = var2;
         GUIMovimentoPedido.this.mMonth = var3;
         GUIMovimentoPedido.this.mDay = var4;
         GUIMovimentoPedido.this.updateDisplay();
      }
   };
   private int mDay;
   private int mMonth;
   private int mYear;
   MBPM obPedido = null;
   TextView tx;


   public GUIMovimentoPedido() {
      super();
   }

   private Map getCACHE() {
      return this.CACHE;
   }

   private Map<String, MyLinearLayout> getCACHE_LAYOUT() {
      return this.CACHE_LAYOUT;
   }

   public static Context getContext() {
      return context;
   }


         @SuppressWarnings("deprecation")
		private void updateDisplay() {
			String mes1 = String.valueOf(mMonth + 1);
			String dia1 = String.valueOf(mDay);
			if (dia1.length() == 2) {
				if (mes1.length() == 2 ) {
					CoreSFAndroid.DataEntrega = mDay + "/" + (1 + mMonth) + "/" + this.mYear;
				} else {
					CoreSFAndroid.DataEntrega = mDay + "/0" + (1 + mMonth) + "/" + this.mYear;
				}
			} else {
				if (mes1.length() == 2 ) {
					CoreSFAndroid.DataEntrega = "0"+mDay + "/" + (1 + mMonth) + "/" + this.mYear;
				} else {
					CoreSFAndroid.DataEntrega = "0"+mDay + "/0" + (1 + mMonth) + "/" + this.mYear;
				}
			}
			String tipocli = "";
			try {
				MBTM tipo = LocatorDAO.getInstancia().getMbtm().findTipoCliente(CoreSFAndroid.PEDIDO_CORRENTE.getTipo_cli());
				if (!(tipo == null)) {
					tipocli =  tipo.getTIPO_MOVTO();
				}
			} catch (SFAndroidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	      SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy"); 
	      DateFormat formataAtual = DateFormat.getDateInstance();
	      Date hoje = new Date(); 
	      String dataHoje = formataAtual.format(hoje);	      
	      int dias_a_avancar = 60; // se quiser diminuir, basta por -2  
	      int dias_a_diminuir = -60; 
	      
	      Date datHoje; 
	      Date datEntrega;
	      try {	    
	    	  try {
				CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
				 String diasS = CoreSFAndroid.VENDEDOR_CORRENTE.getMens1();
				 if (diasS.trim().equals("")) {
					 diasS = "1234567";
				 }
			  datHoje = formatarData.parse(dataHoje);	      
		      datEntrega = formatarData.parse(CoreSFAndroid.DataEntrega);
		      if (tipocli.equals("310") || tipocli.equals("480")) {
		    	  Date dataAvancar = new Date(hoje.getTime());  
		    	  dataAvancar.setDate(dataAvancar.getDate() + dias_a_avancar);
			      String avancar = formataAtual.format(dataAvancar);		      
			      dataAvancar = formatarData.parse(avancar);	
			      if(datEntrega.after(datHoje)) {
			    	  if (datEntrega.before(dataAvancar) || datEntrega.equals(dataAvancar)){
			    		  
			    		 
			    		  int domingo = Integer.valueOf(diasS.substring(0, 1));
			    		  int segunda = Integer.valueOf(diasS.substring(1, 2));
			    		  int terca = Integer.valueOf(diasS.substring(2, 3));
			    		  int quarta = Integer.valueOf(diasS.substring(3, 4));
			    		  int quinta = Integer.valueOf(diasS.substring(4, 5));
			    		  int sexta = Integer.valueOf(diasS.substring(5, 6));
			    		  int sabado = Integer.valueOf(diasS.substring(6, 7));
			    		  
			    		  ArrayList<String> ldias = new ArrayList<String>();
			    		  
			    		  if (domingo != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(domingo));							
						  }
			    		  if (segunda != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(segunda));							
						  }
			    		  if (terca != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(terca));							
						  }
			    		  if (quarta != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(quarta));							
						  }
			    		  if (quinta != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(quinta));							
						  }
			    		  if (sexta != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(sexta));							
						  }
			    		  if (sabado != 9) {
			    			  ldias.add(CoreSFAndroid.getintance().pesquisarDiaSemana(sabado));							
						  }
			    		  int dia = Integer.valueOf(CoreSFAndroid.DataEntrega.substring(0, 2));
			    		  int mes = Integer.valueOf(CoreSFAndroid.DataEntrega.substring(3, 5));
			    		  int ano =  Integer.valueOf(CoreSFAndroid.DataEntrega.substring(6, 10));
			    		  
			    		  String diasemanaEntrega = CoreSFAndroid.getintance().retornarDiaSemana(ano, mes, dia);
			    		  String diaSemana = "";
			    		  for (int i = 0; i < ldias.size(); i++) {
			    			  String j = ldias.get(i);
			    			  if (j.equals(diasemanaEntrega)) {
			    				  diaSemana = diasemanaEntrega;
							  }														
						  }
			    		  if (!diaSemana.equals("")) {
			    			  tx.setText(CoreSFAndroid.DataEntrega);	
						  }else{
							  String dias= "";
							  for (int i = 0; i < ldias.size(); i++) {
								  if (i == 0) {
									  dias = ldias.get(i);	
								  }else{
									  dias = dias + ", " + ldias.get(i);
								  }							  											
							  }
			                    Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Data de Entrega fora dos dias permitdos: " + dias +" Feira.", Toast.LENGTH_LONG).show();
						  }			    		 		    		  
					  }else{
						CoreSFAndroid.DataEntrega = "";
						tx.setText(CoreSFAndroid.DataEntrega);	
	                    Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Data de Entrega fora do permitdo: "+avancar, 1).show();
					  }
			        }else{
			        	CoreSFAndroid.DataEntrega = "";
			        	tx.setText(CoreSFAndroid.DataEntrega);	
	                    Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Data de Entrega invalida...", 1).show();
		            }
			} else  if (tipocli.equals("315")) {
		    	  Date dataAvancar = new Date(hoje.getTime());  
		    	  dataAvancar.setDate(dataAvancar.getDate() - dias_a_avancar);
			      String avancar = formataAtual.format(dataAvancar);		      
			      dataAvancar = formatarData.parse(avancar);			     
			      if (datEntrega.before(datHoje) || datEntrega.equals(datHoje)){
			    	  if(datEntrega.after(dataAvancar)) {
			    		  tx.setText(CoreSFAndroid.DataEntrega);			    		  
					  }else{
						  CoreSFAndroid.DataEntrega = "";
						  tx.setText(CoreSFAndroid.DataEntrega);	
						  Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Data de Entrega fora do permitdo: "+avancar, 1).show();
					  }
			        }else{
			        	 CoreSFAndroid.DataEntrega = "";
			        	 tx.setText(CoreSFAndroid.DataEntrega);	
	                     Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Data de Entrega invalida...", 1).show();
		            }
			}
				} catch (SFAndroidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	   }
   

   public void addMyListenerComRetornoDoNomeDoComponente(MyListenerComRetornoDoNomeDoComponente var1) {
      this.listener2 = var1;
   }

   public boolean deletar_pedido() {
		boolean aux = true;		
		try {

			LocatorDAO.getInstancia().getMbPm().deletar(CoreSFAndroid.codigo_pedidoc);
			MBPRDAO.getInstancia().offset = 0;
			MBPRDAO.getInstancia().nome = "";
			for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
				LocatorDAO.getInstancia().getMbPd().deletar(CoreSFAndroid.ITENS_CORRENTE.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return aux;
	}

   public void gerar_acomp() {
      String var1 = "VENDA";
      this.lAcomp.clear();
      DefaultListModel var3 = new DefaultListModel();
      var3.setNome(var1);
      this.lAcomp.add(var3);
   
   }

   public void gerar_condpagto() {
      try {
         this.lcon = LocatorDAO.getInstancia().getMbcp().findAll();
         this.lCond.clear();
         Iterator<?> var2 = this.lcon.iterator();

         while(var2.hasNext()) {
            MBCP var3 = (MBCP)var2.next();
            DefaultListModel var4 = new DefaultListModel();
            var4.setNome(var3.getCond_pgto());
            var4.setDescricao(var3.getDescricao());
            lCond.add(var4);
         }
      } catch (SFAndroidException var5) {
         var5.printStackTrace();
      }

   }
   
   public void gerar_tipoMovimento() {
	      try {
	         lTMovi = LocatorDAO.getInstancia().getMbtm().findAll();
	         lTMov.clear();
	         Iterator<MBTM> var2 = (Iterator<MBTM>) lTMovi.iterator();

	         while(var2.hasNext()) {
	            MBTM var3 = (MBTM)var2.next();
	            DefaultListModel var4 = new DefaultListModel();
	            var4.setNome(var3.getTIPO_MOVTO());
	            var4.setDescricao(var3.getDESCRICAO());
	            var4.setFrete(var3.getTIPO_CLI());
	            lTMov.add(var4);
	         }
	      } catch (SFAndroidException var5) {
	         var5.printStackTrace();
	      }

	   }

   public void gerar_tipoPagamento() {
      try {
         this.lPagto = LocatorDAO.getInstancia().getMbTp().findAll();
         this.lTPagto.clear();
         Iterator<?> var2 = this.lPagto.iterator();

         while(var2.hasNext()) {
            MBTP var3 = (MBTP)var2.next();
            DefaultListModel var4 = new DefaultListModel();
            var4.setNome(var3.getEmissao() + " - " + var3.getDescricao());
            this.lTPagto.add(var4);
         }
      } catch (SFAndroidException var5) {
         var5.printStackTrace();
      }

   }

   public int getGerarIDComponente() {
      int var1 = ID_COMPONENTE;
      ID_COMPONENTE = var1 + 1;
      return var1;
   }

   public MyLinearLayout getTextView(String var1, String var2, final String var3, final List<DefaultListModel> var4, int var5) {
      final CharSequence[] var6 = new CharSequence[var4.size()];
      int var7 = 0;
      int var10000;
      if(var5 == 0) {
         var10000 = ControllerFrameWAR.getInstancia().getW() / 2;
      } else if(var5 == 1) {
         ControllerFrameWAR.getInstancia().getW();
      } else if(var5 == 2) {
         var10000 = -80 + ControllerFrameWAR.getInstancia().getW();
      } else if(var5 == 3) {
         var10000 = ControllerFrameWAR.getInstancia().getW() / 3;
      }

      int var19;
      for(Iterator<DefaultListModel> var9 = var4.iterator(); var9.hasNext(); var7 = var19) {
         DefaultListModel var18 = var9.next();
         var19 = var7 + 1;
         if (var18.getDescricao() == null) {
        	 var6[var7] = var18.getNome();
		} else {
			var6[var7] = var18.getNome() + " - " +  var18.getDescricao();
		}
         
      }

      MyLinearLayout var10 = new MyLinearLayout(this, var1);
      var10.setGravity(3);
      var10.setOrientation(0);
      var10.setLayoutParams(new LayoutParams(-1, -2));
      var10.setPadding(2, 2, 2, 5);
      
      MyLinearLayout var11 = new MyLinearLayout(this, var1);
      var11.setPadding(0, 0, 0, 0);
      var11.setGravity(3);
      var11.setOrientation(0);
      var11.setLayoutParams(new LayoutParams(-1, -1));
      
      MyLinearLayout var12 = new MyLinearLayout(this, var1);
      var12.setPadding(5, 0, 0, 5);
      var12.setOrientation(1);
      var12.setLayoutParams(new LayoutParams(-1, -2));
      
      TextView var13 = new TextView(this);
      var13.setTextSize(CoreSFAndroid.tamanho_fonte);
      var13.setText(var3);
      var13.setTextColor(-1);
      var13.setTypeface(Typeface.DEFAULT_BOLD);
      var13.setPadding(0, 0, 0, 0);
      var12.addView(var13);
      
      float[] var14 = new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
      ShapeDrawable var15 = new ShapeDrawable(new RoundRectShape(var14, (RectF)null, (float[])null));
      var15.getPaint().setColor(Color.rgb(200, 200, 200));
     
      final Button var16 = new Button(this);
      var16.setOnEditorActionListener(new OnEditorActionListener() {
         public boolean onEditorAction(TextView var1, int var2, KeyEvent var3x) {
            if(GUIMovimentoPedido.this.listener2 != null) {
               GUIMovimentoPedido.this.listener2.onEditorAction(var1, var2, var3x, var3);
            }

            return false;
         }
      });
      var16.setText(var2);
      var16.setTextSize(CoreSFAndroid.tamanho_fonte);
      var16.setTextColor(-16777216);
      var16.setTypeface(Typeface.DEFAULT_BOLD);
      var16.setBackgroundDrawable(var15);
      var16.setPadding(0, 5, 0, 5);
      var16.setOnClickListener(new OnClickListener() {
         public void onClick(View var1) {
            Builder var2 = new Builder(var1.getContext());
            var2.setTitle("Selecionar Item");
            var2.setItems(var6, new android.content.DialogInterface.OnClickListener() {
               public void onClick(DialogInterface var1, int var2) {
                   DefaultListModel var3x = var4.get(var2);
                   if (var3x.getDescricao() == null) {
                       var16.setText(var3x.getNome());
          		} else {
                     var16.setText(var3x.getNome() + " - " +  var3x.getDescricao());
          		}
                   cod_comp = var3x.getNome();
                   if(listener != null) {
                      listener.onClick(var16, var2);
                   }

                   if(listener2 != null) {
                      listener2.onClick(var16, var2, var3);
                      listener2.refresh(var3);
                      listener2.onClick(var16, var3);
                   }

                   var1.cancel();
                   var1.dismiss();
                }
             });
            var2.create().show();
         }
      });
      var12.addView(var16);
      var11.addView(var12);
      var10.addView(var11);
      this.CACHE_LAYOUT.put(var1, var10);
      return var10;
   }
   
   public boolean onKeyDown(int keyCode, KeyEvent event)  {
       if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
           // do something on back.     
			AlertDialog.Builder builder = new AlertDialog.Builder(this);     
		    builder.setMessage("Deseja realmente sair??")            
		    .setCancelable(false)        
		    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
		    .setTitle("Atenção:") //título do caixa de diálogo
		            
		    //Evento disparado se clicar no botão Sim
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
		        public void onClick(DialogInterface dialog, int id) {
		        	
                        CoreSFAndroid.controler_consumo = 1;
                        MBPRDAO.getInstancia().offset = 0;
                        MBPRDAO.getInstancia().nome = "";
                        CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
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

        return super.onKeyDown(keyCode, event);
    }

   public void onCreate(Bundle var1) {
      try {
         super.onCreate(var1);
         ControllerSFAndroid.getInstancia().setContext(this);
         this.setTitle("Pedido");
 		
 		float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
         ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
 		shape.getPaint().setColor(Color.argb(20,0, 27, 134));
 		
 		
         LinearLayout var3 = new LinearLayout(this);
         var3.setGravity(48);
         var3.setOrientation(1);
         var3.setBackgroundResource(R.drawable.background2);
        //var3.setBackgroundDrawable(shape);         
         var3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
         
         
			LinearLayout lFundo = new LinearLayout(this);
			lFundo.setGravity(Gravity.LEFT);
			lFundo.setOrientation(LinearLayout.VERTICAL);
			lFundo.setBackgroundDrawable(shape);
			lFundo.setPadding(15,15,15,15);
			lFundo.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
			
	         btvalor = new Button(this);
	         ///btvalor.setTextColor(Color.BLACK);
	         //btvalor.setTypeface(Typeface.DEFAULT_BOLD);
	         btvalor.setTextSize(CoreSFAndroid.tamanho_fonte);
	         //btvalor.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	         CoreSFAndroid.gerar_total_itens();
	         var3.addView(btvalor); 
	         
			
        // gerar_condpagto();
         if(CoreSFAndroid.PEDIDO_CORRENTE.getCond_pagto() == null) {
        	 lFundo.addView(this.getTextView(CONDPAGTO, "Click aqui para Selecionar Condição de Pagamento!", "Condição de Pagamento", this.lCond, 2));
         } else {
        	 lFundo.addView(this.getTextView(CONDPAGTO, CoreSFAndroid.CONDPAGTO, "Condição de Pagamento", this.lCond, 2));
         }

         //this.gerar_tipoPagamento();
         if(CoreSFAndroid.PEDIDO_CORRENTE.getEmissao() == null) {
        	 lFundo.addView(this.getTextView(TPGTO, CoreSFAndroid.TPGTO, "Tipo de Pagamento", this.lTPagto, 2));
            CoreSFAndroid.PEDIDO_CORRENTE.setEmissao("V");
         } else {
        	 lFundo.addView(this.getTextView(TPGTO, CoreSFAndroid.TPGTO, "Tipo de Pagamento", this.lTPagto, 2));
         }
         
         gerar_tipoMovimento();
         if(CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 0) {
        	 lFundo.addView(this.getTextView(TCLIENTE, "Click aqui para Selecionar Tipo de Movimento!", "Tipo de Movimento", this.lTMov, 2));
         } else {
        	 lFundo.addView(this.getTextView(TCLIENTE, CoreSFAndroid.TCLIENTE, "Tipo de Movimento", this.lTMov, 2));
         }
         
         gerar_acomp();
         if(CoreSFAndroid.PEDIDO_CORRENTE.getTipo_ped() == null) {
        	 lFundo.addView(this.getTextView(ACOMP, "VENDA", "Tipo Pedido", this.lAcomp, 2));
         } else if(CoreSFAndroid.PEDIDO_CORRENTE.getTipo_ped().equals("C")) {
        	 lFundo.addView(this.getTextView(ACOMP, "COTAÇÃO", "Tipo Pedido", this.lAcomp, 2));
         } else {
        	 lFundo.addView(this.getTextView(ACOMP, "VENDA", "Tipo Pedido", this.lAcomp, 2));
         }
                 
         addMyListenerComRetornoDoNomeDoComponente(new MyListenerComRetornoDoNomeDoComponente() {
             public void onClick(View var1, int var2, String var3) {
                if(var3 == "Condição de Pagamento") {
                   CoreSFAndroid.CONDPAGTO = "";
                   CoreSFAndroid.PEDIDO_CORRENTE.setCond_pagto(cod_comp);
                   CoreSFAndroid.CONDPAGTO = lCond.get(var2).getNome();
                } else if(var3 == "Tipo de Pagamento") {
                   CoreSFAndroid.TPGTO = "";
                   CoreSFAndroid.PEDIDO_CORRENTE.setEmissao(lTPagto.get(var2).getNome().substring(0, 1));
                   CoreSFAndroid.TPGTO = lTPagto.get(var2).getNome();
                } else if(var3 == "Tipo Pedido") {
                   CoreSFAndroid.Acomp = lAcomp.get(var2).getNome();
                   if(CoreSFAndroid.Acomp.equals("VENDA")) {
                      CoreSFAndroid.PEDIDO_CORRENTE.setTipo_ped("N");
                   } else {
                      CoreSFAndroid.PEDIDO_CORRENTE.setTipo_ped("C");
                   }
                } else if(var3 == "Tipo de Movimento") {
                	CoreSFAndroid.tipo_cliente = lTMov.get(var2).getFrete();
					CoreSFAndroid.PEDIDO_CORRENTE.setTipo_cli(CoreSFAndroid.tipo_cliente.trim());
					CoreSFAndroid.PEDIDO_CORRENTE.setReservado2(Integer.valueOf(cod_comp));
					CoreSFAndroid.TCLIENTE = lTMov.get(var2).getNome() + " - "+ lTMov.get(var2).getDescricao();
                }

             }
             public void onClick(View var1, String var2) {}
             public boolean onEditorAction(TextView var1, int var2, KeyEvent var3, String var4) {
                return false;
             }
             public void refresh(String var1) {}
             public void result(DefaultListModel var1, String var2) {}
             public void result(DefaultModelItem var1, String var2) {}
             public void resultItem(List var1, String var2) {}
             public void resultModel(List var1, String var2) {}
          });

		ShapeDrawable shape3 = null;
		shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape3.getPaint().setColor(Color.argb(100, 89, 148, 103));
		Rect mRect = new Rect(0, 0, 120, 120);
			
		GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 102, 102, 102),Color.rgb( 239, 239, 239)});
		grad.setCornerRadii(outerR);
		grad.setBounds(mRect);
	      
	    LinearLayout lpedido = new LinearLayout(this); 
	    lpedido.setGravity(1);
	    lpedido.setOrientation(0);
	    lpedido.setPadding(5, 5, 5, 5);
	    lpedido.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
	      	        
        tx = new TextView(this);
        tx.setTextColor(-1);
        tx.setTypeface(Typeface.DEFAULT_BOLD);
        if(CoreSFAndroid.DataEntrega != "") {
           tx.setText(CoreSFAndroid.DataEntrega);
        } else {
           tx.setText("");
        }
        lpedido.addView(tx);
        
        Calendar var13 = Calendar.getInstance();
        mYear = var13.get(1);
        mMonth = var13.get(2);
        mDay = var13.get(5);  
        
		Button btEntrega = new Button(this);	
		btEntrega.setText("Data de Entrega");
		btEntrega.setTextColor(Color.rgb(0, 27, 134));
		btEntrega.setBackgroundDrawable(grad);
		btEntrega.setTextSize(CoreSFAndroid.tamanho_fonte);
		btEntrega.setGravity(Gravity.CENTER);
		btEntrega.setTypeface(Typeface.DEFAULT_BOLD);
		btEntrega.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btEntrega.setOnClickListener(new OnClickListener() {
           public void onClick(View var1) {
              showDialog(0);
           }
        });
		lpedido.addView(btEntrega);		
		lFundo.addView(lpedido);
	    
        
		LinearLayout fundoPED = new LinearLayout(this);
		fundoPED.setGravity(Gravity.LEFT);
		fundoPED.setOrientation(LinearLayout.HORIZONTAL);
		fundoPED.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		fundoPED.setPadding(0, 0, 0, 0);
		
		
 		GradientDrawable grad1 = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 102, 102, 102),Color.rgb( 239, 239, 239)});
 		//grad.setShape(GradientDrawable.);
 		grad1.setCornerRadii(outerR);
 		grad1.setBounds(mRect);
 		//grad.setGradientType(1);
 	      
 	    LinearLayout lpedido1 = new LinearLayout(this); 
 	    lpedido1.setGravity(1);
 	    lpedido1.setOrientation(0);
 	    lpedido1.setPadding(5, 5, 5, 5);
 	    lpedido1.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
 	    
 		Button btObs = new Button(this);	
 		btObs.setTextColor(Color.rgb(0, 27, 134));
 		btObs.setBackgroundDrawable(grad1);
 		btObs.setTextSize(CoreSFAndroid.tamanho_fonte);
 		btObs.setGravity(Gravity.CENTER);
 		btObs.setTypeface(Typeface.DEFAULT_BOLD);
 		btObs.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
 		btObs.setText("Observação");
        btObs.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
               final Dialog var2 = new Dialog(var1.getContext());
               var2.setTitle("Observação");
               var2.setCancelable(true);
               
               float[] var3 = new float[]{12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F};
               new RectF(6.0F, 6.0F, 6.0F, 6.0F);
               float[] var5 = new float[]{12.0F, 12.0F, 0.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F};
               ShapeDrawable var6 = new ShapeDrawable(new RoundRectShape(var3, (RectF)null, (float[])null));
               var6.getPaint().setColor(Color.argb(50, 211, 211, 211));
               ShapeDrawable var7 = new ShapeDrawable(new RoundRectShape((float[])null, (RectF)null, (float[])null));
               var7.getPaint().setColor(Color.argb(100, 10, 10, 10));
              
               LinearLayout var8 = new LinearLayout(var1.getContext());
               var8.setGravity(3);
               var8.setOrientation(1);
               var8.setBackgroundDrawable(var6);
               var8.setLayoutParams(new LayoutParams(-1, -1));
             
               LinearLayout var9 = new LinearLayout(var1.getContext());
               var9.setGravity(1);
               var9.setOrientation(1);
               var9.setPadding(15, 10, 10, 10);
               var9.setBackgroundDrawable(var7);
             
               final EditText var10 = new EditText(var1.getContext());
               var10.setWidth(400);
               var10.setHeight(200);
               
               //var10.setTextSize(20.0F);
               if(CoreSFAndroid.PEDIDO_CORRENTE.getTexto_esp() == null) {
                  var10.setText("");
               } else {
                  var10.setText(CoreSFAndroid.PEDIDO_CORRENTE.getTexto_esp());
               }

               var9.addView(var10);
               var8.addView(var9);
               
               Button var11 = new Button(var1.getContext());
               var11.setHeight(50);
               //var11.setTextSize(18.0F);
               var11.setText("Ok");
               var11.setOnClickListener(new OnClickListener() {
                  public void onClick(View var1) {
                     if(var10.length() > 60) {
                        Toast.makeText(GUIMovimentoPedido.this.getBaseContext(), "Limite de Caracteres exedido!! OBS menos que 60 caracteres para validação.", 1).show();
                     } else {
                        CoreSFAndroid.PEDIDO_CORRENTE.setTexto_esp(var10.getText().toString());
                        var2.dismiss();
                     }

                  }
               });
               var8.addView(var11);
               var2.setContentView(var8);
               var2.show();
            }
         });
        lpedido1.addView(btObs);
        fundoPED.addView(lpedido1);
       
 		GradientDrawable grad11 = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.rgb( 102, 102, 102),Color.rgb( 239, 239, 239)});
 		//grad.setShape(GradientDrawable.);
 		grad11.setCornerRadii(outerR);
 		grad11.setBounds(mRect);
 		//grad.setGradientType(1);
 	      
 	    LinearLayout lpedido11 = new LinearLayout(this); 
 	    lpedido11.setGravity(1);
 	    lpedido11.setOrientation(0);
 	    lpedido11.setPadding(5, 5, 5, 5);
 	    lpedido11.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
 	    
 		Button btassinatura = new Button(this);	
 		btassinatura.setTextColor(Color.rgb(0, 27, 134));
 		btassinatura.setBackgroundDrawable(grad11);
 		btassinatura.setTextSize(CoreSFAndroid.tamanho_fonte);
 		btassinatura.setGravity(Gravity.CENTER);
 		btassinatura.setTypeface(Typeface.DEFAULT_BOLD);
 		btassinatura.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
 	    btassinatura.setText("Assinatura");
 	    btassinatura.setOnClickListener(new View.OnClickListener() {
 	        public void onClick(View v) {          
 	            CoreSFAndroid.getintance().abrirTelaGUIAssinatura();
 	           // finish();
 	        }
 	    });
 	   //lpedido11.addView(btassinatura);
       //fundoPED.addView(lpedido11);
       
       lFundo.addView(fundoPED);
       var3.addView(lFundo);

			GradientDrawable grad111 = new GradientDrawable(
					Orientation.TOP_BOTTOM, new int[] { Color.rgb(0, 155, 52),
							Color.rgb(0, 105, 35 ) });
			grad111.setCornerRadii(outerR);

			LinearLayout lgravarped = new LinearLayout(this);
			lgravarped.setGravity(Gravity.CENTER_HORIZONTAL);
			lgravarped.setOrientation(LinearLayout.VERTICAL);
			lgravarped.setPadding(20, 20, 20, 20);
			lgravarped.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));

			Button var121 = new Button(this);
			var121.setTextSize(14);
			var121.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			var121.setTextColor(Color.WHITE);
			var121.setBackgroundDrawable(grad111);
			var121.setGravity(Gravity.CENTER);
			var121.setTypeface(Typeface.DEFAULT_BOLD);
			var121.setText("Finalizar Pedido");
			var121.setOnClickListener(new OnClickListener() {
				public void onClick(View var1) {
					validarPedido(var1);
				}
			});
			lgravarped.addView(var121);
			var3.addView(lgravarped);
			
			LinearLayout ldeletarped = new LinearLayout(this);
			ldeletarped.setGravity(Gravity.CENTER_HORIZONTAL);
			ldeletarped.setOrientation(LinearLayout.VERTICAL);
			//ldeletarped.setPadding(20, 20, 20, 20);
			ldeletarped.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
			
			GradientDrawable grad2 = new GradientDrawable(
					Orientation.TOP_BOTTOM, new int[] { Color.rgb(204, 0, 0),
							Color.rgb(153, 0, 0) });
			grad2.setCornerRadii(outerR);
			
			Button var1211 = new Button(this);
			var1211.setTextSize(14);
			var1211.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			var1211.setTextColor(Color.WHITE);
			var1211.setBackgroundDrawable(grad2);
			var1211.setGravity(Gravity.CENTER);
			var1211.setTypeface(Typeface.DEFAULT_BOLD);
			var1211.setText("Deletar Pedido");
			var1211.setOnClickListener(new OnClickListener() {
				public void onClick(View var1) {
					if (CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_() == null
							|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_()
									.equals("")
							|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_()
									.equals("T")) {
						AlertDialog.Builder builder = new AlertDialog.Builder(var1.getContext());
						builder.setMessage("Deseja realmente deletar o Pedido?")
								.setCancelable(false)
								.setIcon(android.R.drawable.ic_dialog_alert) // ícone de
																				// alerta
								.setTitle("Atenção:") // título do caixa de diálogo

								// Evento disparado se clicar no botão Sim
								.setPositiveButton("Sim",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,
													int id) {
												deletar_pedido();
												CoreSFAndroid.getintance()
														.abrirTelaPrincipalCliente();
												finish();
											}
										})
								// Event disparado se clicar no botão Não
								.setNegativeButton("Não",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,
													int id) {
												dialog.cancel(); // Cancela a caixa de
																	// diálogo e volta a
																	// tela anterior
											}
										});
						AlertDialog alert = builder.create();
						alert.show(); // Chama a caixa de diálogo

					} else {
						Builder var7 = new Builder(var1.getContext());
						var7.setMessage("Pedido Trasmitido!! È inválido para Exclusão.")
								.setCancelable(false)
								.setIcon(17301543)
								.setTitle("Atenção:")
								.setPositiveButton(
										"Ok",
										new android.content.DialogInterface.OnClickListener() {
											public void onClick(DialogInterface var1,
													int var2) {
												var1.cancel();
											}
										});
						var7.create().show();
					}
				}
			});
			ldeletarped.addView(var1211);
			var3.addView(ldeletarped);

			setContentView(var3);
		} catch (Exception var20) {

      }

   }

   protected Dialog onCreateDialog(int var1) {
      DatePickerDialog var2;
      switch(var1) {
      case 0:
         var2 = new DatePickerDialog(this, this.mDateSetListener, this.mYear, this.mMonth, this.mDay);
         break;
      default:
         var2 = null;
      }

      return var2;
   }

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "GRAVAR PEDIDO");
		menu.add(0, 2, 0, "DELETAR PEDIDO");
		menu.add(0, 3, 0, "VOLTAR");
		return true;
	}

	public void validarPedido(View var1){
		if (CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_() == null|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_().equals("")|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_().equals("T")) {
			if (CoreSFAndroid.PEDIDO_CORRENTE.getCond_pagto() == null) {
				Builder var5 = new Builder(this);
				var5.setMessage("Prenchar a Condição de Pagamento!")
						.setCancelable(false)
						.setIcon(17301543)
						.setTitle("Atenção:")
						.setPositiveButton(
								"Ok",
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface var1, int var2) {
										controle_validar = 1;
										var1.cancel();
									}
								});
				var5.create().show();
			} else if (CoreSFAndroid.PEDIDO_CORRENTE.getEmissao() == null) {
				Builder var7 = new Builder(this);
				var7.setMessage("Prenchar a Tipo de Pagamento!")
						.setCancelable(false)
						.setIcon(17301543)
						.setTitle("Atenção:")
						.setPositiveButton(
								"Ok",
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface var1, int var2) {
										controle_validar = 1;
										var1.cancel();
									}
								});
				var7.create().show();
			} else if (CoreSFAndroid.lista_itensPedido.size() == 0) {
				Builder var9 = new Builder(this);
				var9.setMessage("Favor incluir Itens no Pedido!")
						.setCancelable(false)
						.setIcon(17301543)
						.setTitle("Atenção:")
						.setPositiveButton(
								"Ok",
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface var1, int var2) {
										controle_validar = 1;
										var1.cancel();
									}
								});
				var9.create().show();
			}else if (CoreSFAndroid.DataEntrega.equals("")){
				Builder var9 = new Builder(this);
				var9.setMessage("Favor informar a Data de Entrega!").setCancelable(false).setIcon(17301543).setTitle("Atenção:").setPositiveButton("Ok",
					 new android.content.DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface var1, int var2) {
							 controle_validar = 1;
								var1.cancel();
						 }
					});
				var9.create().show();
			} else {
				CoreSFAndroid.controler_consumo = 2;
				salvar_pedido();
				if (CoreSFAndroid.controler_pedido == 1) {
	   			AlertDialog.Builder builder = new AlertDialog.Builder(var1.getContext());     
			    builder.setMessage("Deseja que o cliente assine o pedido?")            
			    .setCancelable(false)        
			    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
			    .setTitle("Atenção:") //título do caixa de diálogo
			            
			    //Evento disparado se clicar no botão Sim
			    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
			        public void onClick(DialogInterface dialog, int id) {

							CoreSFAndroid.getintance().abrirTelaGUIAssinatura();
						
			        	 	        	 
			        }            
			    })            

			    //Event disparado se clicar no botão Não
			    .setNegativeButton("Não", new DialogInterface.OnClickListener() {                
			        public void onClick(DialogInterface dialog, int id) {                     

							 CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
							 finish();
						

			        }            
			    });     
			    AlertDialog alert = builder.create();     
			    alert.show(); //Chama a caixa de diálogo
				}else{
					 CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
					 finish();
				}
			}
		} else {
			Builder var7 = new Builder(this);
			var7.setMessage("Pedido Trasmitido!! È inválido para Gravação.")
					.setCancelable(false)
					.setIcon(17301543)
					.setTitle("Atenção:")
					.setPositiveButton(
							"Ok",
							new android.content.DialogInterface.OnClickListener() {
								public void onClick(DialogInterface var1,
										int var2) {
									controle_validar = 1;
									var1.cancel();
								}
							});
			var7.create().show();
		}


	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			validarPedido(item.getActionView());
			return true;	
		case 2:
			if (CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_() == null
					|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_()
							.equals("")
					|| CoreSFAndroid.PEDIDO_CORRENTE.getD_I_R_T_Y_()
							.equals("T")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Deseja realmente deletar o Pedido?")
						.setCancelable(false)
						.setIcon(android.R.drawable.ic_dialog_alert) // ícone de
																		// alerta
						.setTitle("Atenção:") // título do caixa de diálogo

						// Evento disparado se clicar no botão Sim
						.setPositiveButton("Sim",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										deletar_pedido();
										CoreSFAndroid.getintance()
												.abrirTelaPrincipalCliente();
										finish();
									}
								})
						// Event disparado se clicar no botão Não
						.setNegativeButton("Não",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel(); // Cancela a caixa de
															// diálogo e volta a
															// tela anterior
									}
								});
				AlertDialog alert = builder.create();
				alert.show(); // Chama a caixa de diálogo

			} else {
				Builder var7 = new Builder(this);
				var7.setMessage("Pedido Trasmitido!! È inválido para Exclusão.")
						.setCancelable(false)
						.setIcon(17301543)
						.setTitle("Atenção:")
						.setPositiveButton(
								"Ok",
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(DialogInterface var1,
											int var2) {
										var1.cancel();
									}
								});
				var7.create().show();
			}
			return true;
		case 3:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Deseja realmente sair?")
					.setCancelable(false)
					.setIcon(android.R.drawable.ic_dialog_alert) // ícone de
																	// alerta
					.setTitle("Atenção:") // título do caixa de diálogo

					// Evento disparado se clicar no botão Sim
					.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									MBPRDAO.getInstancia().offset = 0;
									MBPRDAO.getInstancia().nome = "";
									CoreSFAndroid.getintance()
											.abrirTelaPrincipalCliente();
									finish();
								}
							})

					// Event disparado se clicar no botão Não
					.setNegativeButton("Não",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel(); // Cancela a caixa de
														// diálogo e volta a
														// tela anterior
								}
							});
			AlertDialog alert = builder.create();
			alert.show(); // Chama a caixa de diálogo
			return true;
		}
		return false;
	}

   @SuppressWarnings("unchecked")
public void salvar_pedido() {
	   try {
		Date dd = new Date();
		SimpleDateFormat data = new SimpleDateFormat("yyyyMMdd");
		//SimpleDateFormat codigoped = new SimpleDateFormat("yyMMddHHmm");

        CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
        CoreSFAndroid.codigo_vendedor = CoreSFAndroid.VENDEDOR_CORRENTE.getVendedor();
		//String num = String.valueOf(codigoped.format(dd) +  CoreSFAndroid.codigo_vendedor);
		//int n = Integer.parseInt(num);
		// ########### controler_pedido = 2(ALTERAÇÃO) / controler_pedido =
		// 1(NOVO) ##############################
		if (CoreSFAndroid.controler_pedido == 1) {
			CoreSFAndroid.num_ped = Integer.valueOf(CoreSFAndroid.VENDEDOR_CORRENTE.getProximo_ped()) + 1;
			CoreSFAndroid.PEDIDO_CORRENTE.setNumero_sfa(String.format("%08d",CoreSFAndroid.num_ped));
			CoreSFAndroid.PEDIDO_CORRENTE.setCliente(String.valueOf(CoreSFAndroid.cod_cliente));
			CoreSFAndroid.PEDIDO_CORRENTE.setVendedor(CoreSFAndroid.codigo_vendedor);
			CoreSFAndroid.PEDIDO_CORRENTE.setDt_ped(String.valueOf(data.format(dd)));
			CoreSFAndroid.PEDIDO_CORRENTE.setLoja_cli("01");
			CoreSFAndroid.PEDIDO_CORRENTE.setNro_lista("1");
			CoreSFAndroid.PEDIDO_CORRENTE.setStatus("N");
			CoreSFAndroid.PEDIDO_CORRENTE.setEmpresa(3493);
			CoreSFAndroid.PEDIDO_CORRENTE.setFilial(2);			
			CoreSFAndroid.PEDIDO_CORRENTE.setReservado1(0);		
			CoreSFAndroid.VENDEDOR_CORRENTE.setProximo_ped(""+CoreSFAndroid.num_ped);
			CoreSFAndroid.VENDEDOR_CORRENTE.setD_I_R_T_Y_("T");
			CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().salvar(CoreSFAndroid.VENDEDOR_CORRENTE);	
			
		}
		
		CoreSFAndroid.PEDIDO_CORRENTE.setReservado14(String.valueOf(total_itens));
		if(CoreSFAndroid.PEDIDO_CORRENTE.getTipo_ped().equals("N")){
		   CoreSFAndroid.PEDIDO_CORRENTE.setD_I_R_T_Y_("T");
		}else{	
		   CoreSFAndroid.PEDIDO_CORRENTE.setD_I_R_T_Y_("");
		}		
		CoreSFAndroid.codigo_cotacao = CoreSFAndroid.PEDIDO_CORRENTE.getNumero_sfa().toString();
		String movimento = String.valueOf(CoreSFAndroid.PEDIDO_CORRENTE.getReservado2());
		CoreSFAndroid.PEDIDO_CORRENTE = LocatorDAO.getInstancia().getMbPm().salvar(CoreSFAndroid.PEDIDO_CORRENTE);
		
		
		// ########### controler_pedido = 2(ALTERAÇÃO) / controler_pedido =
		// 1(NOVO) ##############################
		if (CoreSFAndroid.controler_pedido == 2) {
			List<MBPD> li = new ArrayList<MBPD>();
			li = LocatorDAO.getInstancia().getMbPd().findByIDSuperior(CoreSFAndroid.codigo_cotacao);
			for (Iterator<MBPD> iterator = li.iterator(); iterator.hasNext();) {
				CoreSFAndroid.ITENS_CORRENTE = iterator.next();
				LocatorDAO.getInstancia().getMbPd().deletar(CoreSFAndroid.ITENS_CORRENTE.getId());
			}
		}
		for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
			CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(i);
			// ######### GRAVANDO ITENS DO PEDIDO
			// #############################################################
			CoreSFAndroid.ITENS_CORRENTE = null;
			CoreSFAndroid.getintance();
			CoreSFAndroid.criaritensPedido();
			CoreSFAndroid.ITENS_CORRENTE.setNumero_sfa(CoreSFAndroid.codigo_cotacao);
			CoreSFAndroid.ITENS_CORRENTE.setC_prod_palm(CoreSFAndroid.di.getMetaDataTag());
			CoreSFAndroid.ITENS_CORRENTE.setQtde(CoreSFAndroid.di.getQuantidade());
			CoreSFAndroid.ITENS_CORRENTE.setVlr_unit(Double.valueOf(CoreSFAndroid.di.getValor()));
			CoreSFAndroid.ITENS_CORRENTE.setReservado16(CoreSFAndroid.codigo_vendedor);
			CoreSFAndroid.ITENS_CORRENTE.setSequencia(String.valueOf(i + 1));
			CoreSFAndroid.ITENS_CORRENTE.setTes(movimento);
			if (!CoreSFAndroid.DataEntrega.equals("") || CoreSFAndroid.DataEntrega.equals(null)) {
				String entrega = CoreSFAndroid.DataEntrega.substring(6, 10)+CoreSFAndroid.DataEntrega.substring(3, 5)+CoreSFAndroid.DataEntrega.substring(0, 2);
				CoreSFAndroid.ITENS_CORRENTE.setDt_entrega(entrega);
			}
			CoreSFAndroid.ITENS_CORRENTE.setGrupo(""+CoreSFAndroid.di.getNm_lista());		
			if(CoreSFAndroid.PEDIDO_CORRENTE.getTipo_ped().equals("N")){
				CoreSFAndroid.ITENS_CORRENTE.setD_I_R_T_Y_("T");
			}else{	
				CoreSFAndroid.ITENS_CORRENTE.setD_I_R_T_Y_("C");
			}
			CoreSFAndroid.ITENS_CORRENTE.setVlr_total(CoreSFAndroid.di.getQuantidade() * Double.valueOf(CoreSFAndroid.di.getValor()));
			CoreSFAndroid.ITENS_CORRENTE.setReservado5(CoreSFAndroid.di.getComissao());
			CoreSFAndroid.ITENS_CORRENTE.setEmpresa(3493);
			CoreSFAndroid.ITENS_CORRENTE.setFilial(2);		
			CoreSFAndroid.ITENS_CORRENTE = LocatorDAO.getInstancia().getMbPd().salvar(CoreSFAndroid.ITENS_CORRENTE);
		}

		CoreSFAndroid.mCarrinho.clear();
		CoreSFAndroid.lista_itensPedido.clear();
	} catch (SFAndroidException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   }

	public void valor_total() {
		final Dialog var1 = new Dialog(this);
		var1.setTitle("Valores");
		var1.setCancelable(true);
		float[] var2 = new float[] { 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F,
				12.0F, 12.0F };
		new RectF(6.0F, 6.0F, 6.0F, 6.0F);
		float[] var4 = new float[] { 12.0F, 12.0F, 0.0F, 0.0F, 12.0F, 12.0F,
				0.0F, 0.0F };
		RoundRectShape var5 = new RoundRectShape(var2, (RectF) null,
				(float[]) null);
		ShapeDrawable var6 = new ShapeDrawable(var5);
		var6.getPaint().setColor(Color.argb(50, 211, 211, 211));
		ShapeDrawable var7 = new ShapeDrawable(new RoundRectShape(
				(float[]) null, (RectF) null, (float[]) null));
		var7.getPaint().setColor(Color.argb(100, 10, 10, 10));
		LinearLayout var8 = new LinearLayout(this);
		var8.setGravity(3);
		var8.setOrientation(1);
		var8.setBackgroundDrawable(var6);
		var8.setLayoutParams(new LayoutParams(-1, -1));
		LinearLayout var9 = new LinearLayout(this);
		var9.setGravity(1);
		var9.setOrientation(1);
		var9.setPadding(15, 10, 10, 10);
		var9.setBackgroundDrawable(var7);

		TextView var10 = new TextView(this);
		var10.setText("                Total ST: "
				+ CoreSFAndroid.formataMoeda(total_ipi) + "               ");
		//var10.setTextSize(20.0F);
		var10.setTextColor(-1);
		var8.addView(var10);

		TextView var11 = new TextView(this);
		var11.setText("Total : " + CoreSFAndroid.formataMoeda(total_ped));
		//var11.setTextSize(20.0F);
		var11.setTextColor(-1);
		var9.addView(var11);

		TextView var111 = new TextView(this);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Peso Total : ");
		stringBuilder.append(total_peso);
		var111.setText(stringBuilder.toString());
		//var111.setTextSize(20.0F);
		var111.setTextColor(-1);
		var9.addView(var111);

		TextView var13 = new TextView(this);
		var13.setText("Itens: " + String.valueOf(total_itens));
		//var13.setTextSize(20.0F);
		var13.setTextColor(-1);
		var9.addView(var13);
		var8.addView(var9);

		Button var14 = new Button(this);
		var14.setHeight(50);
		//var14.setTextSize(18.0F);
		var14.setText("Ok");
		OnClickListener var15 = new OnClickListener() {
			public void onClick(View var1x) {
				var1.dismiss();
			}
		};
		var14.setOnClickListener(var15);
		var8.addView(var14);
		var1.setContentView(var8);
		var1.show();
	}
}
