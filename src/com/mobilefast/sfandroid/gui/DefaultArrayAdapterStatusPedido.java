package com.mobilefast.sfandroid.gui;

import java.util.List;

import sfa.android.CoreSFAndroid;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.Thema;

public class DefaultArrayAdapterStatusPedido extends ArrayAdapter<DefaultListModel>{

	
	private List<DefaultListModel> items;
	private int index = -1;
	
	private Display display 			= null;
	private int w 						= 0;
	private int h 						= 0;
	static String idO;

	   
	public DefaultArrayAdapterStatusPedido(Context context, int textViewResourceId, List<DefaultListModel> lista) {
		super(context, textViewResourceId, lista);
		this.items = lista;
		
		 display 	= ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
		 w 			= display.getWidth();  
		 h 			= display.getHeight();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout v1 = null;//(ViewItens) convertView;
		index = position;
		CoreSFAndroid.ps = CoreSFAndroid.lista_pedidos.get(position);
		
		float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
		RectF   inset = new RectF(6, 6, 6, 6);
		float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
		ShapeDrawable shape = null;
		
		shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape.getPaint().setColor(Color.argb(90,211,211,211));
		
		if(convertView == null) {

			v1 = new LinearLayout(getContext());
			v1.setGravity(Gravity.LEFT);
			v1.setOrientation(LinearLayout.VERTICAL);
			v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
					Thema.VAL_B);		
		
			LinearLayout fundo = new LinearLayout(getContext());
			fundo.setGravity(Gravity.LEFT);
			fundo.setOrientation(LinearLayout.VERTICAL);
			fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			fundo.setBackgroundDrawable(shape);
			fundo.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
					Thema.VAL_B);
			LinearLayout g = new LinearLayout(getContext());
			g.setGravity(Gravity.LEFT);
			g.setOrientation(LinearLayout.VERTICAL);
			g.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	        g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
					Thema.VAL_B);
			
			LinearLayout fundoCTX = new LinearLayout(getContext());
			fundoCTX.setGravity(Gravity.LEFT);
			fundoCTX.setOrientation(LinearLayout.HORIZONTAL);
			fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
					Thema.VAL_B);
			
			ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
			shape1.getPaint().setColor(Color.argb(100,10,10,10));
			
			
			TextView nomeValor = new TextView(getContext());
			nomeValor.setTextColor(Color.rgb(192, 255, 62));
			nomeValor.setText("Pedido: " + CoreSFAndroid.ps.getNome() + " - Data: " + CoreSFAndroid.ps.getDescricao());
			nomeValor.setTextSize(14);
			//nomeValor.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			nomeValor.setPadding(10, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
			nomeValor.setBackgroundDrawable(shape1);
			g.addView(nomeValor);
			
		    nomeValor = new TextView(getContext());
			//nomeValor.setTextColor(Color.rgb(192, 255, 62));
			nomeValor.setText("Cliente: " + CoreSFAndroid.ps.getFrete() + " - Pedido Total: " + CoreSFAndroid.ps.getValor() + " - Peso Total: " + CoreSFAndroid.ps.getPeso_item() + " Comissão: " + CoreSFAndroid.formataMoeda(CoreSFAndroid.ps.getComissao()));
			nomeValor.setTextSize(14);
			//nomeValor.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			nomeValor.setPadding(10, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
			nomeValor.setBackgroundDrawable(shape1);
			g.addView(nomeValor);
			

			fundo.addView(g);
			
			v1.addView(fundo);
		}else{
			v1 = (LinearLayout) convertView;
			v1.removeAllViews();
			
			try {
				LinearLayout fundo = new LinearLayout(getContext());
				fundo.setGravity(Gravity.LEFT);
				fundo.setOrientation(LinearLayout.VERTICAL);
				fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
				fundo.setBackgroundDrawable(shape);
				fundo.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
						Thema.VAL_B);
				LinearLayout g = new LinearLayout(getContext());
				g.setGravity(Gravity.LEFT);
				g.setOrientation(LinearLayout.VERTICAL);
				g.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		        g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
						Thema.VAL_B);
				

				LinearLayout fundoCTX = new LinearLayout(getContext());
				fundoCTX.setGravity(Gravity.LEFT);
				fundoCTX.setOrientation(LinearLayout.HORIZONTAL);
				fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
				fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
						Thema.VAL_B);
				
				ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
				shape1.getPaint().setColor(Color.argb(100,10,10,10));
				
				
				TextView nomeValor = new TextView(getContext());
				nomeValor.setTextColor(Color.rgb(192, 255, 62));
				nomeValor.setText("Pedido: " + CoreSFAndroid.ps.getNome() + " - Data: " + CoreSFAndroid.ps.getDescricao());
				nomeValor.setTextSize(14);
				//nomeValor.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				nomeValor.setPadding(10, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
				nomeValor.setBackgroundDrawable(shape1);
				g.addView(nomeValor);
				
			    nomeValor = new TextView(getContext());
				//nomeValor.setTextColor(Color.rgb(192, 255, 62));
				nomeValor.setText("Cliente: " + CoreSFAndroid.ps.getFrete() + " - Pedido Total: " + CoreSFAndroid.ps.getValor() + " - Peso Total: " + CoreSFAndroid.ps.getPeso_item() + " Comissão: " + CoreSFAndroid.formataMoeda(CoreSFAndroid.ps.getComissao()));
				nomeValor.setTextSize(14);
				//nomeValor.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				nomeValor.setPadding(10, Thema.VAL_T, Thema.VAL_R,Thema.VAL_B);
				nomeValor.setBackgroundDrawable(shape1);
				g.addView(nomeValor);

				fundo.addView(g);
				
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
	
}