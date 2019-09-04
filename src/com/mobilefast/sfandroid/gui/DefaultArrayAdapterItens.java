package com.mobilefast.sfandroid.gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.Thema;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBDE;
import com.mobilefast.sfandroid.model.MBLP;
import com.mobilefast.sfandroid.model.MBPR;

@SuppressLint("ParserError")
public class DefaultArrayAdapterItens extends ArrayAdapter<DefaultListModel>
		implements OnCheckedChangeListener {

	public static String QTDE_ITEM = "QUANTIDADE";
	public static String PRECO_ITEM = "PRECO";
	public static int qtdeItem = 0;
	public static Double precoVendido = 0.00;
	private ArrayList<String> lpreco = null;
	public static View vv;
	
	public static CheckBox chk;
	public static DecimalFormat decimal = new DecimalFormat("##,##00.00");
    public static int controle_produto = 0;
	public static MBPR pt = null;
	private List<DefaultListModel> items;
	private int index = -1;

	//private Display display = null;
	//private int w = 0;
	//private int h = 0;


	   public static EditText txpreco;
	   public static EditText txqtde;


	public DefaultArrayAdapterItens(Context context, int textViewResourceId,List<DefaultListModel> lista) {
		super(context, textViewResourceId, lista);
		this.items = lista;

		//display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		//w = display.getWidth();
		//h = display.getHeight();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout v1 = null;// (ViewItens) convertView;
		index = position;
		CoreSFAndroid.dd = items.get(position);

		float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
		//RectF inset = new RectF(6, 6, 6, 6);
		//float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
		ShapeDrawable shape = null;

		shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape.getPaint().setColor(Color.argb(90, 211, 211, 211));

		ShapeDrawable shape2 = null;
		shape2 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape2.getPaint().setColor(Color.argb(70, 211, 211, 211));

		if (convertView == null) {

			v1 = new LinearLayout(getContext());
			v1.setGravity(Gravity.LEFT);
			v1.setOrientation(LinearLayout.VERTICAL);
			v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
			
			ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR,null, null));
			shape1.getPaint().setColor(Color.argb(20,0, 27, 134));

			LinearLayout fundo = new LinearLayout(getContext());
			fundo.setGravity(Gravity.LEFT);
			fundo.setOrientation(LinearLayout.VERTICAL);			
			fundo.setPadding(5,5,5,5);
			fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			fundo.setBackgroundDrawable(shape1);
			
			LinearLayout g = new LinearLayout(getContext());
			g.setGravity(Gravity.LEFT);
			g.setOrientation(LinearLayout.VERTICAL);
			g.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			g.setBackgroundDrawable(shape2);
			//g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
			//		Thema.VAL_B);

			// //imagem
			// --------------------------------------------------------------------
			LinearLayout fundoIMG = new LinearLayout(getContext());
			fundoIMG.setGravity(Gravity.LEFT);
			fundoIMG.setOrientation(LinearLayout.VERTICAL);
			fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			fundoIMG.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
			//
			ImageView img = new ImageView(getContext());
			img.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			img.setLayoutParams(new GridView.LayoutParams(100, 100));
			img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
			img.setImageResource(R.drawable.tablet);

			// fundoIMG.addView(img);
			//fundo.addView(img);

			LinearLayout fundoCTX = new LinearLayout(getContext());
			//fundoCTX.setGravity(Gravity.LEFT);
			fundoCTX.setOrientation(LinearLayout.VERTICAL);
			fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);

			LinearLayout fundoCTX3 = new LinearLayout(getContext());
			fundoCTX3.setGravity(Gravity.LEFT);
			fundoCTX3.setOrientation(0);
			fundoCTX3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
			fundoCTX3.setPadding(0, 5, 0, 0);
			
			TextView nomeValor = new TextView(getContext());
			nomeValor.setTextColor(Color.WHITE);
			nomeValor.setText(" " + CoreSFAndroid.dd.getMetaDataTag() + " - " + CoreSFAndroid.dd.getDescricao());
			nomeValor.setTextSize(CoreSFAndroid.tamanho_fonte);
			fundoCTX3.addView(nomeValor);
						
			LinearLayout fundoCTX4 = new LinearLayout(getContext());
			fundoCTX4.setGravity(Gravity.LEFT);
			fundoCTX4.setOrientation(0);
			fundoCTX4.setPadding(20, 0, 0, 0);
			fundoCTX4.setLayoutParams(new LayoutParams(-1, -2));
			
			chk = new CheckBox(getContext());
			chk.setOnCheckedChangeListener(this);
			pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
			chk.setTag(CoreSFAndroid.dd);
			if(CoreSFAndroid.getintance().isCarrinho(pt.getId())){
				chk.setChecked(true);
			}else{
				chk.setChecked(false);					
			}
			
			
			fundoCTX4.addView(chk);
			//fundoCTX3.addView(fundoCTX4);
			
			fundoCTX.addView(fundoCTX3);
			
			LinearLayout fundoCTX2 = new LinearLayout(getContext());
			fundoCTX2.setGravity(Gravity.LEFT);
			fundoCTX2.setOrientation(LinearLayout.HORIZONTAL);
			fundoCTX2.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			//fundoCTX2.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
			//		Thema.VAL_B);
			
			//CoreSFAndroid.id_imagem = CoreSFAndroid.dd.getMetaDataTag();
//			ImagemProdutoView view = new ImagemProdutoView(getContext());
//			
			Button btImagemProd = new Button(getContext());
			//btImagemProd.setAdjustViewBounds(true);
			//btImagemProd.setScaleType(ImageView.ScaleType.CENTER_CROP);
			//btImagemProd.setLayoutParams(new LinearLayout.LayoutParams(80,80));
			///btImagemProd.setBackgroundResource(R.drawable.foto);
			btImagemProd.setText("Imagem Produto");
		//	pt =  (Produto) CoreSFAndroid.dd.getObjeto();
			btImagemProd.setTag(CoreSFAndroid.dd);
			btImagemProd.setOnClickListener(new OnClickListener() {			
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CoreSFAndroid.dd = (DefaultListModel) v.getTag();
					//pt = (Produto) CoreSFAndroid.dd.getObjeto();
					//CoreSFAndroid.id_imagem = CoreSFAndroid.dd.getid_imagem();
					//CoreSFAndroid.getintance().abrirTelaImagemProduto();
					((Activity) getContext()).finish();
				}
			});
			//fundoCTX2.addView(btImagemProd);
			
			TextView subdescri = new TextView(getContext());
			subdescri.setTextColor(Color.rgb(192, 255, 62));
			subdescri.setText("Preço R$: " + CoreSFAndroid.dd.getValor());// + "  Condição de Venda: " + CoreSFAndroid.dd.getScor());
			subdescri.setTextSize(CoreSFAndroid.tamanho_fonte);
			//subdescri.setLayoutParams(new LinearLayout.LayoutParams(
			//		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			subdescri.setPadding(5,0,0,0);
			fundoCTX2.addView(subdescri);
			 
			
			//TextView txestoque = new TextView(getContext());
//			 String est = pt.getHb1_est().toString();
//			 Double valorDouble = Double.parseDouble(est.replaceAll("\\.","").replace(",",".")); 
//			 BigDecimal valor = new BigDecimal(est.replaceAll("\\.","").replace(",","."));  
	       //  int esto = (int) pt.getB1_est();
			// if(esto > 0){
			//	 txestoque.setTextColor(Color.rgb(0, 139, 0));
		    //     txestoque.setText("Estoque: " + CoreSFAndroid.dd.getFrete() );//+ String.valueOf(esto));
			//  }else if (esto <= 0){
			//	  txestoque.setTextColor(Color.rgb(139, 0, 0));
			//      txestoque.setText("Estoque: " + CoreSFAndroid.dd.getFrete());// + String.valueOf(esto));
			//  }

		//	txestoque.setTextSize(CoreSFAndroid.tamanho_fonte);
			//txestoque.setLayoutParams(new LinearLayout.LayoutParams(
			//LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			//txestoque.setPadding(5,0,0,0);
			//fundoCTX2.addView(txestoque);
			
			fundoCTX.addView(fundoCTX2);
			fundo.addView(fundoCTX);
			
			// ===========================================================
			LinearLayout lCHK = new LinearLayout(getContext());
			lCHK.setGravity(Gravity.RIGHT);
			lCHK.setOrientation(LinearLayout.VERTICAL);
			lCHK.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			lCHK.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
					Thema.VAL_B);
			//lCHK.setBackgroundDrawable(shape1);

			
			LinearLayout linfo = new LinearLayout(getContext());
			linfo.setGravity(Gravity.LEFT);
			linfo.setOrientation(LinearLayout.HORIZONTAL);
			linfo.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			//linfo.setPadding(5,5,5,5);
			//linfo.setBackgroundDrawable(shape1);
			
			TextView lbdescr = new TextView(getContext());
			lbdescr.setText("Detalhe do Produto: ");
			lbdescr.setTextColor(Color.WHITE);
			//lbdescr.setTextSize(18);
			//lbdescr.setLayoutParams(new LinearLayout.LayoutParams(
			//		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			//linfo.addView(lbdescr);
			
			Button btInfo = new Button(getContext());
			//btInfo.setBackgroundResource(R.drawable.info);
			btInfo.setText("Detalhe do Produto");
			btInfo.setTextSize(CoreSFAndroid.tamanho_fonte);
			pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
			btInfo.setTag(CoreSFAndroid.dd);
			btInfo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			btInfo.setOnClickListener(new OnClickListener() {
	
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(getContext());
					dialog.setTitle("Detalhes Produto");
					dialog.setCancelable(true);
					float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
					
					ShapeDrawable shapeback = new ShapeDrawable(new RoundRectShape(outerR, null, null));
					shapeback.getPaint().setColor(Color.argb(20,0, 27, 134));
					
					LinearLayout ld = new LinearLayout(getContext());
					ld.setGravity(Gravity.CENTER_VERTICAL);
					ld.setOrientation(LinearLayout.VERTICAL);
					ld.setBackgroundDrawable(shapeback);
					//ld.setBackgroundResource(R.drawable.background2);
					LinearLayout.LayoutParams pd = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					ld.setLayoutParams(pd);

					
					ShapeDrawable shape = null;
					shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
					shape.getPaint().setColor(Color.argb(90, 1, 0, 0));

					LinearLayout lld = new LinearLayout(getContext());
					lld.setGravity(Gravity.CENTER_HORIZONTAL);
					lld.setOrientation(LinearLayout.VERTICAL);
					lld.setPadding(10, 10, 10, 10);
					lld.setLayoutParams(pd);
					
					
					Button okButton = new Button(getContext());
					okButton.setHeight(50);
					okButton.setText("Ok");
					ld.addView(okButton);
					okButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							
							
							
		                    String var2x = DefaultArrayAdapterItens.txqtde.getText().toString();
		                     String var3 = DefaultArrayAdapterItens.txpreco.getText().toString();

		                     if(!var2x.equals("") && !var2x.equals("0")) {
			                        qtdeItem = Integer.valueOf(var2x).intValue();
			                        if(vv.getTag() == null) {
			                           ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
			                        } else {
			                           CoreSFAndroid.dd = (DefaultListModel)vv.getTag();
			                           MBPR var4 = (MBPR)CoreSFAndroid.dd.getObjeto();
			                           if(var3.equals("")) {
			                              var3 = "0.00";
			                           }
				                    	 String idO = String.valueOf(var4.getId());
					         				if (CoreSFAndroid.mCarrinho.containsKey(idO)) {
					        					CoreSFAndroid.mCarrinho.remove(idO);
					        					for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
					        						CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(i);
					        						if (CoreSFAndroid.di.getNome().equals(idO)) {
					        							CoreSFAndroid.lista_itensPedido.remove(i);
					        						}
					        					}
					        				}
					         				precoVendido = Double.valueOf(var3);
					        				if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
						                           controle_produto = 1;
						                           chk.setChecked(true);
							                       dialog.dismiss();
							                       GUIProdutosPedido.adapter.notifyDataSetChanged();
					        				}else{
												double precoCli = Double.valueOf(CoreSFAndroid.dd.getvalorCliente());									
												double precoVend = Double.valueOf(CoreSFAndroid.dd.getValorOriginal());																			
												double validar = 0.25 * precoCli;
												double resultMax = validar + precoCli;								
												if (!(precoVendido < precoVend)) {
													if (!(precoVendido > resultMax)) {																																
								                           controle_produto = 1;
								                           chk.setChecked(true);
									                       dialog.dismiss();
									                       GUIProdutosPedido.adapter.notifyDataSetChanged();
													}else{
											            Toast.makeText(v.getContext(),"Preço maximo fora do permitido: " 
													+ CoreSFAndroid.formataMoeda(resultMax), Toast.LENGTH_LONG).show();           
													}
												}else{
										            Toast.makeText(v.getContext(),"Preço minimo fora do permitido: " 
												+ CoreSFAndroid.formataMoeda(precoVend), Toast.LENGTH_LONG).show();           
												}
					        				}	
			                        }
			                     } else {
							            Toast.makeText(v.getContext(),"Informe a quantidade!! ", Toast.LENGTH_LONG).show();           
			                       // DefaultArrayAdapterItens.qtdeItem = 1;
			                     }			                   
			                  }
						});

					
					//MBPR pt = null;
					if (v.getTag() == null) {
						ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
					} else {
						CoreSFAndroid.dd = (DefaultListModel) v.getTag();
						pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
						vv = v;
						 TextView var12 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var12.setText(pt.getC_prod_palm() + " - " + pt.getDescricao());
		                  var12.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var12.setTextColor(Color.rgb(192, 255, 62));
		                  ld.addView(var12);
		                  
		                  TextView var13 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var13.setText("Preço Produto: " + CoreSFAndroid.dd.getValor());
		                  var13.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var13.setTextColor(-1);
		                  ld.addView(var13);
		                 
		                  TextView var14 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var14.setText("Peso Bruto: " + pt.getPeso_bruto());
		                  var14.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var14.setTextColor(-1);
		                  ld.addView(var14);
		                
		                  TextView var15 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var15.setText("Unidade: " + pt.getUnidade());
		                  var15.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var15.setTextColor(-1);
		                  ld.addView(var15);
		                		                 
		                  TextView var17 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var17.setText("Prazo de Entrega: " + pt.getPrazo_entrega());
		                  var17.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var17.setTextColor(-1);
		                  ld.addView(var17);
		                 
		                  TextView var18 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var18.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var18.setTextColor(-1);
		                 
		                  TextView var19 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var19.setText("Quantidade Embalagem: " + pt.getQtde_p_embal());
		                  var19.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var19.setTextColor(-1);
		                  ld.addView(var19);
		                
		                  TextView var20 = new TextView(DefaultArrayAdapterItens.this.getContext());
		               //   var20.setText("Grupo: " + pt.getGrupo());
		                  var20.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var20.setTextColor(-1);
		                 // ld.addView(var20);
		               
		                  TextView var21 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var21.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var21.setTextColor(-1);
		                 
		                  TextView var22 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var22.setText("% IPI: " + CoreSFAndroid.formataMoeda(pt.getPorc_ipi()));
		                 var22.setTextSize(CoreSFAndroid.tamanho_fonte);
		                  var22.setTextColor(-1);
		                  ld.addView(var22);
		                 
						TextView var23 = new TextView(getContext());
						var23.setText("Qtde Item: ");
						var23.setTextSize(CoreSFAndroid.tamanho_fonte);
						var23.setTextColor(-1);
						ld.addView(var23);

						txqtde = new EditText(getContext());
						txqtde.findFocus();
						txqtde.setTextSize(CoreSFAndroid.tamanho_fonte);
						txqtde.setInputType(2);
						txqtde.setText("");
						ld.addView(txqtde);

						TextView var25 = new TextView(getContext());
						var25.setText("Preço Vendido: ");
						var25.setTextSize(CoreSFAndroid.tamanho_fonte);
						var25.setTextColor(-1);
						ld.addView(var25);

						txpreco = new EditText(getContext());
						DecimalKeyListener myDecimalKeyListener = new DecimalKeyListener();		
						txpreco.setKeyListener(myDecimalKeyListener);
						txpreco.setText(CoreSFAndroid.dd.getValor());
						txpreco.setTextSize(CoreSFAndroid.tamanho_fonte);
        				if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
        					txpreco.setEnabled(false);
        				}else{
        					txpreco.setEnabled(true);
        				}
						ld.addView(txpreco);
			        
	                  TextView var255 = new TextView(DefaultArrayAdapterItens.this.getContext());
	                  var255.setText("-");
	                  //var255.setTextSize(20.0F);
	                  var255.setTextColor(-1);
	                  ld.addView(var255);
					}


						//ld.addView(lld);
			            ScrollView var12 = new ScrollView(getContext());
			            var12.addView(ld);
						dialog.setContentView(var12);
						dialog.show();
					}
					
				});
			
			linfo.addView(btInfo);
			linfo.addView(fundoCTX4);
			lCHK.addView(linfo);
			fundo.addView(lCHK);
			
			v1.addView(fundo);
		} else {
			v1 = (LinearLayout) convertView;
			v1.removeAllViews();

			try {
				ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR,
						null, null));
				shape1.getPaint().setColor(Color.argb(20,0, 27, 134));
				
				LinearLayout fundo = new LinearLayout(getContext());
				fundo.setGravity(Gravity.LEFT);
				fundo.setOrientation(LinearLayout.VERTICAL);			
				fundo.setPadding(5,5,5,5);
				fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				fundo.setBackgroundDrawable(shape1);
				
				LinearLayout g = new LinearLayout(getContext());
				g.setGravity(Gravity.LEFT);
				g.setOrientation(LinearLayout.VERTICAL);
				g.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				g.setBackgroundDrawable(shape2);
				//g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
				//		Thema.VAL_B);

				// //imagem
				// --------------------------------------------------------------------
				LinearLayout fundoIMG = new LinearLayout(getContext());
				fundoIMG.setGravity(Gravity.LEFT);
				fundoIMG.setOrientation(LinearLayout.VERTICAL);
				fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				fundoIMG.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
				//
				ImageView img = new ImageView(getContext());
				img.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				img.setLayoutParams(new GridView.LayoutParams(100, 100));
				img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
				img.setImageResource(R.drawable.tablet);

				// fundoIMG.addView(img);
				//fundo.addView(img);

				LinearLayout fundoCTX = new LinearLayout(getContext());
				//fundoCTX.setGravity(Gravity.LEFT);
				fundoCTX.setOrientation(LinearLayout.VERTICAL);
				fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);

				LinearLayout fundoCTX3 = new LinearLayout(getContext());
				fundoCTX3.setGravity(Gravity.LEFT);
				fundoCTX3.setOrientation(0);
				fundoCTX3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
				fundoCTX3.setPadding(0, 5, 0, 0);
				
				TextView nomeValor = new TextView(getContext());
				nomeValor.setTextColor(Color.WHITE);
				nomeValor.setText(" " + CoreSFAndroid.dd.getMetaDataTag() + " - " + CoreSFAndroid.dd.getDescricao());
				nomeValor.setTextSize(CoreSFAndroid.tamanho_fonte);
				fundoCTX3.addView(nomeValor);
							
				LinearLayout fundoCTX4 = new LinearLayout(getContext());
				fundoCTX4.setGravity(Gravity.LEFT);
				fundoCTX4.setOrientation(0);
				fundoCTX4.setPadding(20, 0, 0, 0);
				fundoCTX4.setLayoutParams(new LayoutParams(-1, -2));
				
				chk = new CheckBox(getContext());
				chk.setOnCheckedChangeListener(this);
				pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
				chk.setTag(CoreSFAndroid.dd);
				if(CoreSFAndroid.getintance().isCarrinho(pt.getId())){
					chk.setChecked(true);
				}else{
					chk.setChecked(false);					
				}
				
				
				fundoCTX4.addView(chk);
				//fundoCTX3.addView(fundoCTX4);
				
				fundoCTX.addView(fundoCTX3);
				
				LinearLayout fundoCTX2 = new LinearLayout(getContext());
				fundoCTX2.setGravity(Gravity.LEFT);
				fundoCTX2.setOrientation(LinearLayout.HORIZONTAL);
				fundoCTX2.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//fundoCTX2.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
				//		Thema.VAL_B);
				
				//CoreSFAndroid.id_imagem = CoreSFAndroid.dd.getMetaDataTag();
//				ImagemProdutoView view = new ImagemProdutoView(getContext());
//				
				Button btImagemProd = new Button(getContext());
				//btImagemProd.setAdjustViewBounds(true);
				//btImagemProd.setScaleType(ImageView.ScaleType.CENTER_CROP);
				//btImagemProd.setLayoutParams(new LinearLayout.LayoutParams(80,80));
				///btImagemProd.setBackgroundResource(R.drawable.foto);
				btImagemProd.setText("Imagem Produto");
			//	pt =  (Produto) CoreSFAndroid.dd.getObjeto();
				btImagemProd.setTag(CoreSFAndroid.dd);
				btImagemProd.setOnClickListener(new OnClickListener() {			
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CoreSFAndroid.dd = (DefaultListModel) v.getTag();
						//pt = (Produto) CoreSFAndroid.dd.getObjeto();
						//CoreSFAndroid.id_imagem = CoreSFAndroid.dd.getid_imagem();
						//CoreSFAndroid.getintance().abrirTelaImagemProduto();
						((Activity) getContext()).finish();
					}
				});
				//fundoCTX2.addView(btImagemProd);
				
				TextView subdescri = new TextView(getContext());
				subdescri.setTextColor(Color.rgb(192, 255, 62));
				subdescri.setText("Preço R$: " + CoreSFAndroid.dd.getValor());// + "  Condição de Venda: " + CoreSFAndroid.dd.getScor());
				subdescri.setTextSize(CoreSFAndroid.tamanho_fonte);
				//subdescri.setLayoutParams(new LinearLayout.LayoutParams(
				//		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				subdescri.setPadding(5,0,0,0);
				fundoCTX2.addView(subdescri);
				 
				
				//TextView txestoque = new TextView(getContext());
//				 String est = pt.getHb1_est().toString();
//				 Double valorDouble = Double.parseDouble(est.replaceAll("\\.","").replace(",",".")); 
//				 BigDecimal valor = new BigDecimal(est.replaceAll("\\.","").replace(",","."));  
		       //  int esto = (int) pt.getB1_est();
				// if(esto > 0){
				//	 txestoque.setTextColor(Color.rgb(0, 139, 0));
			    //     txestoque.setText("Estoque: " + CoreSFAndroid.dd.getFrete() );//+ String.valueOf(esto));
				//  }else if (esto <= 0){
				//	  txestoque.setTextColor(Color.rgb(139, 0, 0));
				//      txestoque.setText("Estoque: " + CoreSFAndroid.dd.getFrete());// + String.valueOf(esto));
				//  }

			//	txestoque.setTextSize(CoreSFAndroid.tamanho_fonte);
				//txestoque.setLayoutParams(new LinearLayout.LayoutParams(
				//LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				//txestoque.setPadding(5,0,0,0);
				//fundoCTX2.addView(txestoque);
				
				fundoCTX.addView(fundoCTX2);
				fundo.addView(fundoCTX);
				
				// ===========================================================
				LinearLayout lCHK = new LinearLayout(getContext());
				lCHK.setGravity(Gravity.RIGHT);
				lCHK.setOrientation(LinearLayout.VERTICAL);
				lCHK.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				lCHK.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
						Thema.VAL_B);
				//lCHK.setBackgroundDrawable(shape1);

				
				LinearLayout linfo = new LinearLayout(getContext());
				linfo.setGravity(Gravity.LEFT);
				linfo.setOrientation(LinearLayout.HORIZONTAL);
				linfo.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				//linfo.setPadding(5,5,5,5);
				//linfo.setBackgroundDrawable(shape1);
				
				TextView lbdescr = new TextView(getContext());
				lbdescr.setText("Detalhe do Produto: ");
				lbdescr.setTextColor(Color.WHITE);
				//lbdescr.setTextSize(18);
				//lbdescr.setLayoutParams(new LinearLayout.LayoutParams(
				//		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//linfo.addView(lbdescr);
				
				Button btInfo = new Button(getContext());
				//btInfo.setBackgroundResource(R.drawable.info);
				btInfo.setText("Detalhe do Produto");
				btInfo.setTextSize(CoreSFAndroid.tamanho_fonte);
				pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
				btInfo.setTag(CoreSFAndroid.dd);
				btInfo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				btInfo.setOnClickListener(new OnClickListener() {
		
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final Dialog dialog = new Dialog(getContext());
						dialog.setTitle("Detalhes Produto");
						dialog.setCancelable(true);

						float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
						
						ShapeDrawable shapeback = new ShapeDrawable(new RoundRectShape(outerR, null, null));
						shapeback.getPaint().setColor(Color.argb(20,0, 27, 134));
						
						LinearLayout ld = new LinearLayout(getContext());
						ld.setGravity(Gravity.CENTER_VERTICAL);
						ld.setOrientation(LinearLayout.VERTICAL);
						ld.setBackgroundDrawable(shapeback);
						//ld.setBackgroundResource(R.drawable.background2);
						LinearLayout.LayoutParams pd = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
						ld.setLayoutParams(pd);


						ShapeDrawable shape = null;
						shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
						shape.getPaint().setColor(Color.argb(90, 1, 0, 0));

						LinearLayout lld = new LinearLayout(getContext());
						lld.setGravity(Gravity.CENTER_HORIZONTAL);
						lld.setOrientation(LinearLayout.VERTICAL);
						lld.setPadding(10, 10, 10, 10);
						lld.setLayoutParams(pd);
						
						
						Button okButton = new Button(getContext());
						okButton.setHeight(50);
						okButton.setText("Ok");
						ld.addView(okButton);
						okButton.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								
								
								
			                    String var2x = DefaultArrayAdapterItens.txqtde.getText().toString();
			                     String var3 = DefaultArrayAdapterItens.txpreco.getText().toString();

			                     if(!var2x.equals("") && !var2x.equals("0")) {
				                        qtdeItem = Integer.valueOf(var2x).intValue();
				                        if(vv.getTag() == null) {
				                           ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
				                        } else {
				                           CoreSFAndroid.dd = (DefaultListModel)vv.getTag();
				                           MBPR var4 = (MBPR)CoreSFAndroid.dd.getObjeto();
				                           if(var3.equals("")) {
				                              var3 = "0.00";
				                           }
					                    	 String idO = String.valueOf(var4.getId());
						         				if (CoreSFAndroid.mCarrinho.containsKey(idO)) {
						        					CoreSFAndroid.mCarrinho.remove(idO);
						        					for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
						        						CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(i);
						        						if (CoreSFAndroid.di.getNome().equals(idO)) {
						        							CoreSFAndroid.lista_itensPedido.remove(i);
						        						}
						        					}
						        				}
						         				precoVendido = Double.valueOf(var3);
						        				if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
							                           controle_produto = 1;
							                           chk.setChecked(true);
								                       dialog.dismiss();
								                       GUIProdutosPedido.adapter.notifyDataSetChanged();
						        				}else{
													double precoCli = Double.valueOf(CoreSFAndroid.dd.getvalorCliente());									
													double precoVend = Double.valueOf(CoreSFAndroid.dd.getValorOriginal());																			
													double validar = 0.25 * precoCli;
													double resultMax = validar + precoCli;								
													if (!(precoVendido < precoVend)) {
														if (!(precoVendido > resultMax)) {																																
									                           controle_produto = 1;
									                           chk.setChecked(true);
										                       dialog.dismiss();
										                       GUIProdutosPedido.adapter.notifyDataSetChanged();
														}else{
												            Toast.makeText(v.getContext(),"Preço maximo fora do permitido: " 
														+ CoreSFAndroid.formataMoeda(resultMax), Toast.LENGTH_LONG).show();           
														}
													}else{
											            Toast.makeText(v.getContext(),"Preço minimo fora do permitido: " 
													+ CoreSFAndroid.formataMoeda(precoVend), Toast.LENGTH_LONG).show();           
													}
						        				}	
						         														
	
				                        }
				                     }else{
								          Toast.makeText(v.getContext(),"Informe a quantidade!! ", Toast.LENGTH_LONG).show();           
				                     }			                   
				                  }
							});

						
						//MBPR pt = null;
						if (v.getTag() == null) {
							ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
						} else {
							CoreSFAndroid.dd = (DefaultListModel) v.getTag();
							pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
							vv = v;
							 TextView var12 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var12.setText(pt.getC_prod_palm() + " - " + pt.getDescricao());
			                  var12.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var12.setTextColor(Color.rgb(192, 255, 62));
			                  ld.addView(var12);
			                  
			                  TextView var13 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var13.setText("Preço Produto: " + CoreSFAndroid.dd.getValor());
			                  var13.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var13.setTextColor(-1);
			                  ld.addView(var13);
			                 
			                  TextView var14 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var14.setText("Peso Bruto: " + pt.getPeso_bruto());
			                  var14.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var14.setTextColor(-1);
			                  ld.addView(var14);
			                
			                  TextView var15 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var15.setText("Unidade: " + pt.getUnidade());
			                  var15.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var15.setTextColor(-1);
			                  ld.addView(var15);
			                		                 
			                  TextView var17 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var17.setText("Prazo de Entrega: " + pt.getPrazo_entrega());
			                  var17.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var17.setTextColor(-1);
			                  ld.addView(var17);
			                 
			                  TextView var18 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var18.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var18.setTextColor(-1);
			                 
			                  TextView var19 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var19.setText("Quantidade Embalagem: " + pt.getQtde_p_embal());
			                  var19.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var19.setTextColor(-1);
			                  ld.addView(var19);
			                
			                  TextView var20 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                //  var20.setText("Grupo: " + pt.getGrupo());
			                  var20.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var20.setTextColor(-1);
			                //  ld.addView(var20);
			               
			                  TextView var21 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var21.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var21.setTextColor(-1);
			                 
			                  TextView var22 = new TextView(DefaultArrayAdapterItens.this.getContext());
			                  var22.setText("% IPI: " + CoreSFAndroid.formataMoeda(pt.getPorc_ipi()));
			                 var22.setTextSize(CoreSFAndroid.tamanho_fonte);
			                  var22.setTextColor(-1);
			                  ld.addView(var22);
			                 
							TextView var23 = new TextView(getContext());
							var23.setText("Qtde Item: ");
							var23.setTextSize(CoreSFAndroid.tamanho_fonte);
							var23.setTextColor(-1);
							ld.addView(var23);

							txqtde = new EditText(getContext());
							txqtde.findFocus();
							txqtde.setTextSize(CoreSFAndroid.tamanho_fonte);
							txqtde.setInputType(2);
							txqtde.setText("");
							ld.addView(txqtde);

							TextView var25 = new TextView(getContext());
							var25.setText("Preço Vendido: ");
							var25.setTextSize(CoreSFAndroid.tamanho_fonte);
							var25.setTextColor(-1);
							ld.addView(var25);

							txpreco = new EditText(getContext());
							DecimalKeyListener myDecimalKeyListener = new DecimalKeyListener();		
							txpreco.setKeyListener(myDecimalKeyListener);
							txpreco.setText(CoreSFAndroid.dd.getValor());
							txpreco.setTextSize(CoreSFAndroid.tamanho_fonte);
	        				if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
	        					txpreco.setEnabled(false);
	        				}else{
	        					txpreco.setEnabled(true);
	        				}
							ld.addView(txpreco);
				        
		                  TextView var255 = new TextView(DefaultArrayAdapterItens.this.getContext());
		                  var255.setText("-");
		                  //var255.setTextSize(20.0F);
		                  var255.setTextColor(-1);
		                  ld.addView(var255);
						}

	
			            ScrollView var12 = new ScrollView(getContext());
			            var12.addView(ld);
						dialog.setContentView(var12);
						dialog.show();
						}
						
					});
				
				linfo.addView(btInfo);
				linfo.addView(fundoCTX4);
				lCHK.addView(linfo);
				fundo.addView(lCHK);
				
				v1.addView(fundo);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return v1;
	}

	@Override
	public String toString() {
		return items.get(index).getNome();
	}

	public void onCheckedChanged(CompoundButton b, boolean isChecked) {
		if (controle_produto != 1) {
			CoreSFAndroid.dd = (DefaultListModel) b.getTag();
		} else {
			CoreSFAndroid.dd = (DefaultListModel) vv.getTag();
		}
		pt = (MBPR) CoreSFAndroid.dd.getObjeto();
		String idO = String.valueOf(pt.getId());

		if (isChecked) {
			if (!CoreSFAndroid.mCarrinho.containsKey(idO)) {

				CoreSFAndroid.di = new DefaultListModel();
				CoreSFAndroid.di.setId(CoreSFAndroid.pegarUltimaSeq());
				CoreSFAndroid.di.setSequencia_item(CoreSFAndroid.pegarUltimaSeq());
				CoreSFAndroid.di.setMetaDataTag(pt.getC_prod_palm());
				CoreSFAndroid.di.setNome(String.valueOf(pt.getId()));
				CoreSFAndroid.di.setFrete(pt.getPosicao_estq());
				CoreSFAndroid.di.setDescricao(pt.getDescricao());
				if (qtdeItem > 0) {
					CoreSFAndroid.di.setQuantidade(qtdeItem);
					CoreSFAndroid.di.setPeso_item(pt.getPeso_bruto());
				} else {
					CoreSFAndroid.di.setQuantidade(1);
					CoreSFAndroid.di.setPeso_item(pt.getPeso_bruto());
				}
				double preco = 0.00;
				double precoOrig = 0.00;
				MBLP pr;
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

				if (precoVendido.doubleValue() == 0.0D) {
					preco = preco- (preco * (CoreSFAndroid.desc_contratual / 1000));
					CoreSFAndroid.di.setcontratual(preco* (CoreSFAndroid.desc_contratual / 1000));
					DescontoComissao(preco, precoOrig);
				} else if (precoVendido != preco) {
					precoVendido = precoVendido- (precoVendido * (CoreSFAndroid.desc_contratual / 1000));
					CoreSFAndroid.di.setcontratual(precoVendido* (CoreSFAndroid.desc_contratual / 1000));
					DescontoComissao(preco, precoOrig);
				} else {
					preco = preco- (preco * (CoreSFAndroid.desc_contratual / 1000));
					CoreSFAndroid.di.setcontratual(preco* (CoreSFAndroid.desc_contratual / 1000));
					DescontoComissao(preco, precoOrig);
				}

				if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
					CoreSFAndroid.mCarrinho.put(idO, pt);
					CoreSFAndroid.lista_itensPedido.add(CoreSFAndroid.di);				
					qtdeItem = 0;
					precoVendido = Double.valueOf(0.0D);
					controle_produto = 0;
					CoreSFAndroid.gerar_total_itens();
					CoreSFAndroid.preco_lista = "";
				}else{
					CalculoPermissao(idO, b);
				}								
			}
		} else {
			if (CoreSFAndroid.mCarrinho.containsKey(idO)) {
				CoreSFAndroid.mCarrinho.remove(idO);
				for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
					CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(i);
					if (CoreSFAndroid.di.getNome().equals(idO)) {
						CoreSFAndroid.lista_itensPedido.remove(i);
					}
				}
				CoreSFAndroid.gerar_total_itens();
			}
		}
	}
	
	public void CalculoPermissao(String idO, CompoundButton b){
		double precoVendido = Double.valueOf(CoreSFAndroid.di.getValor());
		double precoCli = Double.valueOf(CoreSFAndroid.di.getvalorCliente());
		double precoVend = Double.valueOf(CoreSFAndroid.di.getValorOriginal());
		double validar = 0.25 * precoCli;
		double resultMax = validar + precoCli;
		if (!(precoVendido < precoVend)) {
			if (!(precoVendido > resultMax)) {
				CoreSFAndroid.mCarrinho.put(idO, pt);
				CoreSFAndroid.lista_itensPedido.add(CoreSFAndroid.di);				
				qtdeItem = 0;
				precoVendido = Double.valueOf(0.0D);
				controle_produto = 0;
				CoreSFAndroid.gerar_total_itens();
				CoreSFAndroid.preco_lista = "";
			} else {
				Toast.makeText(b.getContext(),"Preço maximo fora do permitido: "+ CoreSFAndroid.formataMoeda(resultMax),Toast.LENGTH_LONG).show();
				chk.setChecked(false);
				GUIProdutosPedido.adapter.notifyDataSetChanged();
			}
		} else {
			Toast.makeText(b.getContext(),"Preço minimo fora do permitido: "+ CoreSFAndroid.formataMoeda(precoVend),Toast.LENGTH_LONG).show();
			chk.setChecked(false);
			GUIProdutosPedido.adapter.notifyDataSetChanged();
		}
	}
	
	public void DescontoComissao(double preco, double precoOrig){
		try {
			MBDE de = LocatorDAO.getInstancia().getMbde().findSelect(CoreSFAndroid.cod_cliente, pt.getC_prod_palm());
			if (!(de == null)) {
				double desc = de.getPORC_DESC() / 100;
				CoreSFAndroid.di.setdescTrab(desc * preco);
				double preco_desc = preco -  (desc * preco);
				CoreSFAndroid.di.setvalorCliente(CoreSFAndroid.formataMoeda(preco_desc));
				CoreSFAndroid.di.setValor(CoreSFAndroid.formataMoeda(preco_desc));
				CoreSFAndroid.di.setValorOriginal(CoreSFAndroid.formataMoeda(precoOrig));
                CoreSFAndroid.di.setNm_lista(CoreSFAndroid.nm_lista);
				CoreSFAndroid.di.setTotalvalor(preco * CoreSFAndroid.di.getQuantidade());
				double pre =  preco - (preco * (CoreSFAndroid.desc_condicao / 100));
				pre = pre - precoOrig;
				if (pre > 0 ) {
					 CoreSFAndroid.di.setComissao(pre);
				}
			}else{
				CoreSFAndroid.di.setvalorCliente(CoreSFAndroid.formataMoeda(preco));
				CoreSFAndroid.di.setValorOriginal(CoreSFAndroid.formataMoeda(precoOrig));
				CoreSFAndroid.di.setValor(CoreSFAndroid.formataMoeda(preco));
                CoreSFAndroid.di.setNm_lista(CoreSFAndroid.nm_lista);
				double prctotal = Double.valueOf(preco);
				CoreSFAndroid.di.setTotalvalor(prctotal * CoreSFAndroid.di.getQuantidade());
				double pre =  preco * (CoreSFAndroid.desc_condicao / 100);
				pre = preco - pre;
				pre = pre - precoOrig;
				if (pre < 0 ) {
					pre = 0;
					CoreSFAndroid.di.setComissao(pre);
				}else{
					CoreSFAndroid.di.setComissao(pre);
				}
			}									
	} catch (SFAndroidException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	   public class DecimalKeyListener extends DigitsKeyListener {
			private final char[] acceptedCharacters =
			new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','.'};


			protected char[] getAcceptedChars() {
			return acceptedCharacters;
			}

			public int getInputType() {
			return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
			}

		}
	   
	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}
}