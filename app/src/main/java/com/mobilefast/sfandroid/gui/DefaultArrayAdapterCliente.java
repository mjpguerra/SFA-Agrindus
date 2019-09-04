package com.mobilefast.sfandroid.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.Thema;

import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;

public class DefaultArrayAdapterCliente extends ArrayAdapter<DefaultListModel> {


    private List<DefaultListModel> items;
    private int index = -1;

    private Display display = null;
    private int w = 0;
    private int h = 0;


    public DefaultArrayAdapterCliente(Context context, int textViewResourceId, List<DefaultListModel> lista) {
        super(context, textViewResourceId, lista);
        this.items = lista;

        display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        w = display.getWidth();
        h = display.getHeight();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout v1 = null;//(ViewItens) convertView;
        index = position;
        CoreSFAndroid.dcl = items.get(position);

        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};

        ShapeDrawable shape3 = null;
        shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape3.getPaint().setColor(Color.argb(100, 10, 10, 10));

        ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape1.getPaint().setColor(Color.argb(100, 10, 10, 10));
        if (convertView == null) {

            v1 = new LinearLayout(getContext());
            v1.setGravity(Gravity.CENTER);
            v1.setOrientation(LinearLayout.VERTICAL);
            v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

            LinearLayout fundo = new LinearLayout(getContext());
            fundo.setGravity(Gravity.CENTER);
            fundo.setOrientation(LinearLayout.HORIZONTAL);
            fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            fundo.setBackgroundDrawable(shape1);
            fundo.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);


            Button btCliente = new Button(getContext());
            btCliente.setText("Cod: " + CoreSFAndroid.dcl.getDescricao() + " - " + CoreSFAndroid.dcl.getNome());// + " - " + CoreSFAndroid.dd.getDescricao());
            btCliente.setTextColor(Color.WHITE);
            btCliente.setPadding(10, 10, 10, 10);
            btCliente.setTag(CoreSFAndroid.dcl);
            btCliente.setTextSize(12);
            btCliente.setGravity(Gravity.CENTER);
            btCliente.setTypeface(Typeface.DEFAULT_BOLD);
            btCliente.setBackgroundDrawable(shape3);
            btCliente.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            btCliente.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (v.getTag() == null) {
                        ControllerSFAndroid.getInstancia().showMensagemSimples("Cliente Invalido!");
                    } else {
                        CoreSFAndroid.dcl = (DefaultListModel) v.getTag();
                        CoreSFAndroid.cod_cliente = CoreSFAndroid.dcl.getDescricao();
                        CoreSFAndroid.nome_cliente = CoreSFAndroid.dcl.getNome();
                        CoreSFAndroid.tipo_cliente = CoreSFAndroid.dcl.getMetaDataTag();
                        CoreSFAndroid.nm_lista = CoreSFAndroid.dcl.getNm_lista();
                        CoreSFAndroid.Cond_Pagto = CoreSFAndroid.dcl.getScor();
                        CoreSFAndroid.desc_contratual = CoreSFAndroid.dcl.getSequencia_item();

                        CoreSFAndroid.controler_consumo = 1;
                        Intent i = new Intent(v.getContext(), GUITabInfoCliente.class);
                        v.getContext().startActivity(i);
                        ((Activity) getContext()).finish();

                    }
                }
            });

            fundo.addView(btCliente);
            v1.addView(fundo);
        } else {
            v1 = (LinearLayout) convertView;
            v1.removeAllViews();

            try {
                LinearLayout fundo = new LinearLayout(getContext());
                fundo.setGravity(Gravity.CENTER);
                fundo.setOrientation(LinearLayout.HORIZONTAL);
                fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                fundo.setBackgroundDrawable(shape1);
                fundo.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

                Button btCliente = new Button(getContext());
                btCliente.setText("Cod: " + CoreSFAndroid.dcl.getDescricao() + " - " + CoreSFAndroid.dcl.getNome());// + " - " + CoreSFAndroid.dd.getDescricao());
                btCliente.setTextColor(Color.WHITE);
                btCliente.setPadding(10, 10, 10, 10);
                btCliente.setTag(CoreSFAndroid.dcl);
                btCliente.setTextSize(12);
                btCliente.setGravity(Gravity.CENTER);
                btCliente.setTypeface(Typeface.DEFAULT_BOLD);
                btCliente.setBackgroundDrawable(shape3);
                btCliente.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                btCliente.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (v.getTag() == null) {
                            ControllerSFAndroid.getInstancia().showMensagemSimples("Cliente Invalido!");
                        } else {
                            CoreSFAndroid.dcl = (DefaultListModel) v.getTag();
                            CoreSFAndroid.cod_cliente = CoreSFAndroid.dcl.getDescricao();
                            CoreSFAndroid.nome_cliente = CoreSFAndroid.dcl.getNome();
                            CoreSFAndroid.tipo_cliente = CoreSFAndroid.dcl.getMetaDataTag();
                            CoreSFAndroid.nm_lista = CoreSFAndroid.dcl.getNm_lista();
                            CoreSFAndroid.Cond_Pagto = CoreSFAndroid.dcl.getScor();
                            CoreSFAndroid.desc_contratual = CoreSFAndroid.dcl.getSequencia_item();
                            CoreSFAndroid.controler_consumo = 1;
                            Intent i = new Intent(v.getContext(), GUITabInfoCliente.class);
                            v.getContext().startActivity(i);
                            ((Activity) getContext()).finish();

                        }
                    }
                });

                fundo.addView(btCliente);
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