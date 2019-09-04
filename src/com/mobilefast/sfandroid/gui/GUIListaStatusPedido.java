package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.frame.mobilefast.ControllerFrameWAR;
import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.DefaultModelItem;
import com.frame.mobilefast.MyLinearLayout;
import com.frame.mobilefast.MyListener;
import com.frame.mobilefast.MyListenerComRetornoDoNomeDoComponente;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCL;
import com.mobilefast.sfandroid.model.MBPD;
import com.mobilefast.sfandroid.model.MBPM;
import com.mobilefast.sfandroid.model.MBPR;

@SuppressLint("ParserError")
public class GUIListaStatusPedido extends Activity {
	private android.widget.ListView list = null;
	public static DefaultArrayAdapterStatusPedido adapter = null;
	private ArrayList<String> lista = null;
	List<DefaultListModel> lStatus = new ArrayList<DefaultListModel>();
	public static ImageButton var12; 
	public static List<MBPM> lPedidoc = null;
	public static TextView tx;
	public static String Status_Ped = "Novos";
	public static String Status_data = "";
	public static String CONDPAGTO = "CONDPAGTO";
	public static CheckBox chdata;
	public static double comissao = 0.0D;
	private List<MBPD> li = null;
    public static   MBPM MBPM;
    public static  String emissao = "";
    public static Button btvalor;
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ControllerSFAndroid.getInstancia().setContext(this);

		this.setTitle("Lista de Pedido");
		LinearLayout var3 = new LinearLayout(this);
		var3.setGravity(Gravity.TOP);
		var3.setOrientation(LinearLayout.VERTICAL);
		var3.setBackgroundResource(R.drawable.background2);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		var3.setLayoutParams(p);
		

		LayoutParams var4 = new LayoutParams(-1, -1);
		var3.setLayoutParams(var4);
		float[] var5 = new float[] { 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F,12.0F, 12.0F };
		new RectF(6.0F, 6.0F, 6.0F, 6.0F);
		ShapeDrawable var8 = new ShapeDrawable(new RoundRectShape(var5,(RectF) null, (float[]) null));
		var8.getPaint().setColor(Color.argb(90, 211, 211, 211));
		
        this.gerar_status();
        if(Status_Ped.equals("")) {
           var3.addView(this.getTextView(CONDPAGTO, "Click aqui para Selecionar o Status!", "Status do Pedido", this.lStatus, 2));
        } else {
           var3.addView(this.getTextView(CONDPAGTO, Status_Ped, "Status do Pedido", this.lStatus, 2));
        }
        this.addMyListenerComRetornoDoNomeDoComponente(new MyListenerComRetornoDoNomeDoComponente() {
            @SuppressWarnings("unchecked")
			public void onClick(View var1, int var2, String var3) {
               if(var3 == "Status do Pedido") {
                  Status_Ped = "";          
                  //CoreSFAndroid.PEDIDO_CORRENTE.setCond_pagto(GUIMovimentoPedido.this.lCond.get(var2).getNome().substring(0, 4));
                  Status_Ped = lStatus.get(var2).getNome();

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
        
		LinearLayout var33 = new LinearLayout(this);
		var33.setGravity(Gravity.CENTER);
		var33.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		var33.setPadding(10, 10, 10, 10);
		var3.setLayoutParams(p);
		
        chdata = new CheckBox(this);
        chdata.setText("Por Data:  ");
        if(Status_data != "") {
        	chdata.setChecked(true);
         } else {
        	 chdata.setChecked(false);
         }
        
        chdata.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        chdata.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((CheckBox) v).isChecked()) {
					var12.setVisibility(View.VISIBLE);
					tx.setVisibility(View.VISIBLE);
	                  Status_data="";
				}else{
					var12.setVisibility(View.INVISIBLE);
					tx.setVisibility(View.INVISIBLE);
	                Status_data="";
				}
			}
		});      
        var33.addView(chdata);
        
        TextView var9 = new TextView(this);
        var9.setTextColor(Color.rgb(192, 255, 62));
        var9.setText("Data: ");
        //var9.setTextSize(16.0F);
        //var33.addView(var9);
                      
		LinearLayout var10 = new LinearLayout(this);
		var10.setGravity(Gravity.CENTER_HORIZONTAL);
		var10.setOrientation(LinearLayout.HORIZONTAL);
		var10.setLayoutParams(pp);
		var10.setPadding(10, 10, 10, 10);
		var10.setBackgroundDrawable(var8);
        
        tx = new TextView(this);
        tx.setTextColor(-1);
        //tx.setTextSize(18.0F);
        if(Status_data != "") {
           tx.setText(Status_data);
        } else {
           tx.setText("");
        }

        var10.addView(tx);
        
        var12 = new ImageButton(this);
        var12.setAdjustViewBounds(true);
        var12.setScaleType(ImageView.ScaleType.CENTER_CROP);
        var12.setBackgroundResource(R.drawable.calendar48);
        if(Status_data != "") {
        	var12.setVisibility(View.VISIBLE);
         } else {
        	 var12.setVisibility(View.INVISIBLE);
         }
        
       // var12.setWidth(54);
       // var12.setHeight(54);
        var12.setPadding(10, 5, 10, 5);
        var12.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
               showDialog(0);
            }
         });
        var10.addView(var12);     
        var33.addView(var10);
        
		LinearLayout var100 = new LinearLayout(this);
		var100.setGravity(Gravity.CENTER_HORIZONTAL);
		var100.setOrientation(LinearLayout.HORIZONTAL);
		var100.setLayoutParams(pp);
		var100.setPadding(10, 10, 10, 10);
		//var100.setBackgroundDrawable(var8);
		
        ImageButton var112 = new ImageButton(this);
        var112.setAdjustViewBounds(true);
        var112.setScaleType(ImageView.ScaleType.CENTER_CROP);
        var112.setBackgroundResource(R.drawable.earch);
        var112.setOnClickListener(new OnClickListener() {
			public void onClick(View var1) {
				if (chdata.isChecked()) {
					listar_pedidos_data();
					
				} else {
					listar_pedidos_status();
				}
			}
		});
        var100.addView(var112);
        var33.addView(var100);
        var3.addView(var33);
        
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        btvalor = new Button(this);
        btvalor.setText("Comissão Total: 0.00");
        btvalor.setTypeface(Typeface.DEFAULT_BOLD);
        btvalor.setTextSize(CoreSFAndroid.tamanho_fonte);
        var3.addView(btvalor);         
        
		adapter = new DefaultArrayAdapterStatusPedido(this,android.R.layout.simple_list_item_1, CoreSFAndroid.lista_pedidos);
		
		list = new ListView(this);
		list.setAdapter(adapter);
		
		var3.addView(list);
        setContentView(var3);
        
        listar_pedidos_status();
	}

	public void listar_pedidos_status(){
        try {    	
        	comissao = 0.0D;
        	    CoreSFAndroid.lista_pedidos.clear();  
				lPedidoc = LocatorDAO.getInstancia().getMbPm().findStatus(Status_Ped);
                for (Iterator<MBPM> iterator = lPedidoc.iterator(); iterator.hasNext();) {
					    MBPM = iterator.next();
					    
					    String data = MBPM.getDt_ped();
					    String ano = data.substring(0, 4);
					    String mes = data.substring(4, 6);
					    String dia = data.substring(6, 8);			
					    emissao = dia + "/" + mes + "/" + ano;
					    
					    getPedido();			    
                }
                btvalor.setText("Comissão Total: " + CoreSFAndroid.formataMoeda(comissao)); 
                adapter.notifyDataSetChanged();
            	//CoreSFAndroid.getintance().abrirTelalistaped();
          	    //finish();
			} catch (SFAndroidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	

	public void listar_pedidos_data(){
        try {  
        	comissao = 0.0D;    	
        	  CoreSFAndroid.lista_pedidos.clear();  
				lPedidoc = com.mobilefast.sfandroid.model.LocatorDAO.getInstancia().getMbPm().findAll();
                for (Iterator<MBPM> iterator = lPedidoc.iterator(); iterator.hasNext();) {
					    MBPM = iterator.next();
					    String ts = MBPM.getD_I_R_T_Y_();
					    String data = MBPM.getDt_ped();
					    String ano = data.substring(0, 4);
					    String mes = data.substring(4, 6);
					    String dia = data.substring(6, 8);			
					    emissao = dia + "/" + mes + "/" + ano;
					    if (Status_Ped.equals("Todos")) {
					    	if (emissao.equals(Status_data)) {
					    		getPedido();
					    	}
						} else if (Status_Ped.equals("Novos")){	  			        	
	  						if (ts.equals("T") || ts == null) {
	  							if (emissao.equals(Status_data)) {
	  								getPedido();	
	  							}
	  						}	
	  					} else if (Status_Ped.equals("Cotação")){    	
	  						 if (ts.equals("C")){	
	  							if (emissao.equals(Status_data)) {
	  								getPedido();
	  							}
	  						 }
	  					} else if (Status_Ped.equals("Transmitidos")){    
	  						if (ts.equals("F")){	
	  							if (emissao.equals(Status_data)) {
	  								getPedido();	
	  							}							
	  						} 						
						}
					   
                } 
                btvalor.setText("Comissão Total: " + CoreSFAndroid.formataMoeda(comissao)); 
                adapter.notifyDataSetChanged();
            	//CoreSFAndroid.getintance().abrirTelalistaped();
          	    //finish();
			} catch (SFAndroidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getPedido(){
    	CoreSFAndroid.ps = new DefaultListModel();
    	CoreSFAndroid.ps.setNome(MBPM.getNumero_sfa());
    	CoreSFAndroid.ps.setDescricao(emissao);
    	Double total = 0.0D;
    	double total_peso = 0;
    	double total_comissao = 0.0D;
		try {
			li = LocatorDAO.getInstancia().getMbPd().findByIDSuperior(MBPM.getNumero_sfa());
			for (Iterator<MBPD> iterator1 = li.iterator(); iterator1.hasNext();) {
				 MBPD item  = (MBPD) iterator1.next();							 
				 total = total + item.getVlr_total(); 
				 String idO = item.getC_prod_palm();	
				 MBPR pt = LocatorDAO.getInstancia().getMbpr().findCodProduto(idO);
				 if(pt != null){
				    total_peso = total_peso + pt.getPeso_bruto(); 
				 }
				 total_comissao += item.getReservado5() * item.getQtde();

			}
		} catch (SFAndroidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        comissao += total_comissao;
		CoreSFAndroid.ps.setComissao(total_comissao);
		CoreSFAndroid.ps.setPeso_item(total_peso);
		CoreSFAndroid.ps.setValor(CoreSFAndroid.formataMoeda(total));
		try {
			CoreSFAndroid.lCli = LocatorDAO.getInstancia().getMbcl().findCodCliente(MBPM.getCliente());
			for (Iterator<MBCL> iterator1 = CoreSFAndroid.lCli.iterator(); iterator1.hasNext();) {
				MBCL var3 = (MBCL)iterator1.next();
				CoreSFAndroid.ps.setFrete(var3.getNom_fantasia());
			}
	    	CoreSFAndroid.lista_pedidos.add(CoreSFAndroid.ps);
		} catch (SFAndroidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void gerar_status() {
		String[] var1 = new String[] { "Todos", "Novos", "Cotação", "Transmitidos" };
		lStatus.clear();
		for (int var2 = 0; var2 < 4; ++var2) {
			DefaultListModel var3 = new DefaultListModel();
			var3.setNome(var1[var2]);
			this.lStatus.add(var3);
		}

	}

	private void updateDisplay() {
		String mes1 = String.valueOf(mMonth + 1);
		String dia1 = String.valueOf(mDay);
		if (dia1.length() == 2) {
			if (mes1.length() == 2 ) {
				Status_data = mDay + "/" + (1 + mMonth) + "/" + this.mYear;
			} else {
				Status_data = mDay + "/0" + (1 + mMonth) + "/" + this.mYear;
			}
		} else {
			if (mes1.length() == 2 ) {
				Status_data = "0"+mDay + "/" + (1 + mMonth) + "/" + this.mYear;
			} else {
				Status_data = "0"+mDay + "/0" + (1 + mMonth) + "/" + this.mYear;
			}
		}
		tx.setText(Status_data);
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
	   
	private OnDateSetListener mDateSetListener = new OnDateSetListener() {
		public void onDateSet(DatePicker var1, int var2, int var3, int var4) {
			mYear = var2;
			mMonth = var3;
			mDay = var4;
			updateDisplay();
		}
	};
	
	private int mDay;
	private int mMonth;
	private int mYear;
	private final Map CACHE = new HashMap();
	private final Map<String, MyLinearLayout> CACHE_LAYOUT = new HashMap<String, MyLinearLayout>();
	private MyListener listener;
	private MyListenerComRetornoDoNomeDoComponente listener2;

	private Map getCACHE() {
		return this.CACHE;
	}

	private Map<String, MyLinearLayout> getCACHE_LAYOUT() {
		return this.CACHE_LAYOUT;
	}

	public void addMyListenerComRetornoDoNomeDoComponente(
			MyListenerComRetornoDoNomeDoComponente var1) {
		this.listener2 = var1;
	}

	public MyLinearLayout getTextView(String var1, String var2,
			final String var3, final List<DefaultListModel> var4, int var5) {
		final CharSequence[] var6 = new CharSequence[var4.size()];
		int var7 = 0;
		int var10000;
		if (var5 == 0) {
			var10000 = ControllerFrameWAR.getInstancia().getW() / 2;
		} else if (var5 == 1) {
			ControllerFrameWAR.getInstancia().getW();
		} else if (var5 == 2) {
			var10000 = -80 + ControllerFrameWAR.getInstancia().getW();
		} else if (var5 == 3) {
			var10000 = ControllerFrameWAR.getInstancia().getW() / 3;
		}

		int var19;
		for (Iterator<DefaultListModel> var9 = var4.iterator(); var9.hasNext(); var7 = var19) {
			DefaultListModel var18 = var9.next();
			var19 = var7 + 1;
			var6[var7] = var18.getNome();
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
		var12.setPadding(5, 8, 0, 5);
		var12.setOrientation(1);
		var12.setLayoutParams(new LayoutParams(-1, -2));

		TextView var13 = new TextView(this);
		//var13.setTextSize((float) Thema.FONTE_TITULO);
		var13.setText(var3);
		var13.setTextColor(Color.WHITE);
		var13.setPadding(0, 0, 0, 0);
		var12.addView(var13);
		float[] var14 = new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F,
				5.0F };
		ShapeDrawable var15 = new ShapeDrawable(new RoundRectShape(var14,
				(RectF) null, (float[]) null));
		var15.getPaint().setColor(Color.argb(90, 211, 211, 211));
		final Button var16 = new Button(this);
		var16.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView var1, int var2,
					KeyEvent var3x) {
				if (listener2 != null) {
					listener2.onEditorAction(var1, var2, var3x, var3);
				}

				return false;
			}
		});
		var16.setText(var2);
		//var16.setTextSize((float) Thema.FONTE_CONTEXT);
		//var16.setHeight(Thema.SIZE_CAMPO_H);
		var16.setTextColor(-16777216);
		var16.setBackgroundDrawable(var15);
		var16.setPadding(0, 5, 0, 5);
		var16.setOnClickListener(new OnClickListener() {
			public void onClick(View var1) {
				// (new MyButton(var1.getContext())).clickDesclik();
				Builder var2 = new Builder(var1.getContext());
				var2.setTitle("Selecione o Status");
				var2.setItems(var6,
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface var1, int var2) {
								DefaultListModel var3x = var4.get(var2);
								var16.setText(var3x.getNome());
								if (listener != null) {
									listener.onClick(var16, var2);
								}

								if (listener2 != null) {
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
         //   MBCLDAO.getInstancia().offset = 0;
         //  MBCLDAO.getInstancia().nome = "";
          //  CoreSFAndroid.lista_cliente.clear();
          //  CoreSFAndroid.getintance().loadCliente();
            CoreSFAndroid.getintance().abrirTelaListaCliente();
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}
