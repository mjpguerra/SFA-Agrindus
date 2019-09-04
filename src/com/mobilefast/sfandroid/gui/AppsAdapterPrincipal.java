package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AppsAdapterPrincipal extends BaseAdapter {

	private List<String> mApps ;
	//private String[] nomes = {"CLIENTE","OCORRÊNCIA","SINCRONISMO","CADASTRO","SAIR"};
	private String[] nomes = {"VISITAS", "PRODUTOS", "CLIENTES CADASTRADOS", "CLIENTES NOVOS", "FASTSYNC"};
	
	private int[] icones = {R.drawable.clie, R.drawable.produto, R.drawable.savecliente, R.drawable.save,R.drawable.eader};

	public AppsAdapterPrincipal() {	
		this.mApps = new ArrayList<String>();
		
	      this.mApps.add("Visitas");
	      this.mApps.add("Produtos");
	      this.mApps.add("Pesquisas");
	      this.mApps.add("Pesquisas");
	      this.mApps.add("Pesquisas");
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout l = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
		l.setGravity(Gravity.CENTER_HORIZONTAL);
		l.setOrientation(LinearLayout.VERTICAL);
		l.setPadding(10,10,10,10);
		
		ImageView i = null;

		
		if (convertView == null) {
			try {
				
				LinearLayout l1 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
				l1.setGravity(Gravity.CENTER_HORIZONTAL);
				l1.setOrientation(LinearLayout.VERTICAL);
				
				
				float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
				RectF   inset = new RectF(6, 6, 6, 6);
				float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
				ShapeDrawable shape = null;
				
				shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
				shape.getPaint().setColor(Color.argb(95,200,200,200));
			
				l1.setBackgroundDrawable(shape);
				
				i = new ImageView(ControllerSFAndroid.getInstancia().getContext());
				i.setAdjustViewBounds(true);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				i.setLayoutParams(new GridView.LayoutParams(150,150));
				i.setPadding(35,35,35,35);
				l1.addView(i);

				
				ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
				shape1.getPaint().setColor(Color.rgb(255,255,255));
				LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
				l2.setGravity(Gravity.CENTER_HORIZONTAL);
				l2.setOrientation(LinearLayout.VERTICAL);
				l2.setPadding(5,5,5,5);
				
				TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
				textView.setText(nomes[position].toUpperCase());
				textView.setTextSize(CoreSFAndroid.tamanho_fonte);
				textView.setTextColor(Color.rgb(0, 27, 134));
				textView.setGravity(Gravity.CENTER_HORIZONTAL);
				textView.setTypeface(Typeface.DEFAULT_BOLD);
				textView.setBackgroundDrawable(shape1);
				
				l2.addView(textView);
				l1.addView(l2);

				l.addView(l1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			l = (LinearLayout) convertView;
			
			l.removeAllViews();
			
			LinearLayout l1 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
			l1.setGravity(Gravity.CENTER_HORIZONTAL);
			l1.setOrientation(LinearLayout.VERTICAL);
			
			
			float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
			RectF   inset = new RectF(6, 6, 6, 6);
			float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
			ShapeDrawable shape = null;
			
			shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
			shape.getPaint().setColor(Color.argb(95,200,200,200));
		
			l1.setBackgroundDrawable(shape);
			
			i = new ImageView(ControllerSFAndroid.getInstancia().getContext());
			i.setAdjustViewBounds(true);
			i.setScaleType(ImageView.ScaleType.CENTER_CROP);
			i.setPadding(35,35,35,35);
			i.setLayoutParams(new GridView.LayoutParams(150,150));
			//i.setBackgroundResource(R.drawable.ic_launcher);
			l1.addView(i);
			
			ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
			shape1.getPaint().setColor(Color.rgb(255,255,255));
			
			LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
			l2.setGravity(Gravity.CENTER_HORIZONTAL);
			l2.setOrientation(LinearLayout.VERTICAL);
			l2.setPadding(5,5,5,5);
			
			TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
			textView.setText(nomes[position].toUpperCase());
			textView.setTextSize(CoreSFAndroid.tamanho_fonte);
			textView.setTextColor(Color.rgb(0, 27, 134));
			textView.setGravity(Gravity.CENTER_HORIZONTAL);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
			textView.setBackgroundDrawable(shape1);
			
			l2.addView(textView);
			l1.addView(l2);
			l.addView(l1);
			
		}

		if(i != null){
			//DA20ImageResorurce.getInstance().addContext(ControllerSFAndroid.getInstancia().getContext());
			//i.setImageDrawable(DA20ImageResorurce.getInstance().getDrawable(mApps.get(position)));
			i.setImageResource(icones[position]);
		}


		return l;
	}


	public final int getCount() {
		return mApps.size();
	}

	public final Object getItem(int position) {
		return mApps.get(position);
	}

	public final long getItemId(int position) {
		return position;
	}
}


