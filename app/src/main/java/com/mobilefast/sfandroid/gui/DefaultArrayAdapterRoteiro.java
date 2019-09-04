package com.mobilefast.sfandroid.gui;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.Thema;

import java.util.List;

import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class DefaultArrayAdapterRoteiro extends ArrayAdapter<DefaultListModel> {


    private List<DefaultListModel> items;
    private int index = -1;

    private Display display = null;
    private int w = 0;
    private int h = 0;


    public DefaultArrayAdapterRoteiro(Context context, int textViewResourceId, List<DefaultListModel> lista) {
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
        CoreSFAndroid.dro = CoreSFAndroid.lista_roteiro.get(position);

        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};
        ShapeDrawable shape = null;

        shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape.getPaint().setColor(Color.argb(90, 211, 211, 211));

        if (convertView == null) {

            v1 = new LinearLayout(getContext());
            v1.setGravity(Gravity.LEFT);
            v1.setOrientation(LinearLayout.VERTICAL);
            v1.setBackgroundResource(R.drawable.background);
            v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);

            ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR,
                    null, null));
            shape1.getPaint().setColor(Color.argb(100, 10, 10, 10));

            LinearLayout fundo = new LinearLayout(getContext());
            fundo.setGravity(Gravity.LEFT);
            fundo.setOrientation(LinearLayout.HORIZONTAL);

            fundo.setPadding(15, 15, 15, 15);
            fundo.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            //fundo.setBackgroundDrawable(shape1);


            // //imagem
            // --------------------------------------------------------------------
            LinearLayout fundoIMG = new LinearLayout(getContext());
            fundoIMG.setGravity(Gravity.LEFT);
            fundoIMG.setOrientation(LinearLayout.VERTICAL);
            fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            fundoIMG.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);
            //
            ImageView img = new ImageView(getContext());
            img.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            img.setLayoutParams(new GridView.LayoutParams(32, 32));
            img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);
            img.layout(4, 4, 10, 0);
            img.setImageResource(R.drawable.cliente);

            //fundoIMG.addView(img);
            fundo.addView(img);

            LinearLayout fundoCTX = new LinearLayout(getContext());
            fundoCTX.setGravity(Gravity.LEFT);
            fundoCTX.setOrientation(LinearLayout.VERTICAL);
            fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);
            //fundoCTX.setBackgroundDrawable(shape1);

            TextView nomeValor = new TextView(getContext());
            nomeValor.setTextColor(Color.WHITE);
            nomeValor.setText(String.valueOf(CoreSFAndroid.dro.getId()) + " - " + CoreSFAndroid.dro.getNome());// + " - " + CoreSFAndroid.dd.getDescricao());
            nomeValor.setTextSize(24);
            nomeValor.setGravity(Gravity.LEFT);
            nomeValor.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            nomeValor.setPadding(5, 0, 0, 0);
            // nomeValor.setBackgroundDrawable(shape1);
            //fundoCTX.addView(nomeValor);

            fundo.addView(nomeValor);
            v1.addView(fundo);
        } else {
            v1 = (LinearLayout) convertView;
            v1.removeAllViews();

            try {
                LinearLayout fundo = new LinearLayout(getContext());
                fundo.setGravity(Gravity.LEFT);
                fundo.setOrientation(LinearLayout.HORIZONTAL);

                fundo.setPadding(15, 15, 15, 15);
                fundo.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                //fundo.setBackgroundDrawable(shape1);


                // //imagem
                // --------------------------------------------------------------------
                LinearLayout fundoIMG = new LinearLayout(getContext());
                fundoIMG.setGravity(Gravity.LEFT);
                fundoIMG.setOrientation(LinearLayout.VERTICAL);
                fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                fundoIMG.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                        Thema.VAL_B);
                //
                ImageView img = new ImageView(getContext());
                img.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                img.setLayoutParams(new GridView.LayoutParams(32, 32));
                img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                        Thema.VAL_B);
                img.layout(4, 4, 10, 0);
                img.setImageResource(R.drawable.cliente);

                //fundoIMG.addView(img);
                fundo.addView(img);

                LinearLayout fundoCTX = new LinearLayout(getContext());
                fundoCTX.setGravity(Gravity.LEFT);
                fundoCTX.setOrientation(LinearLayout.VERTICAL);
                fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                        Thema.VAL_B);
                //fundoCTX.setBackgroundDrawable(shape1);

                TextView nomeValor = new TextView(getContext());
                nomeValor.setTextColor(Color.WHITE);
                nomeValor.setText(String.valueOf(CoreSFAndroid.dro.getId()) + " - " + CoreSFAndroid.dro.getNome());// + " - " + CoreSFAndroid.dd.getDescricao());
                nomeValor.setTextSize(24);
                nomeValor.setGravity(Gravity.LEFT);
                nomeValor.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                nomeValor.setPadding(5, 0, 0, 0);
                // nomeValor.setBackgroundDrawable(shape1);
                //fundoCTX.addView(nomeValor);

                fundo.addView(nomeValor);
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