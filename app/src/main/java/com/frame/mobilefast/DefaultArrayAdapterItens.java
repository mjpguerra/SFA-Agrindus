package com.frame.mobilefast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DefaultArrayAdapterItens extends ArrayAdapter<DefaultListModel> {
    private List<DefaultListModel> items;
    private int index = -1;

    private Display display = null;
    private int w = 0;
    private int h = 0;

    public DefaultArrayAdapterItens(Context context, int textViewResourceId, List<DefaultListModel> lista) {
        super(context, textViewResourceId, lista);
        this.items = lista;

        this.display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.w = this.display.getWidth();
        this.h = this.display.getHeight();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout v1 = null;
        this.index = position;
        DefaultListModel o = (DefaultListModel) this.items.get(position);

        float[] outerR = {12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F};
        RectF inset = new RectF(6.0F, 6.0F, 6.0F, 6.0F);
        float[] innerR = {12.0F, 12.0F, 0.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F};
        ShapeDrawable shape = null;

        shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape.getPaint().setColor(Color.rgb(100, 150, 240));

        if (convertView == null) {
            v1 = new LinearLayout(getContext());
            v1.setGravity(3);
            v1.setOrientation(1);
            v1.setPadding(12, 12, 12, 12);

            LinearLayout fundo = new LinearLayout(getContext());
            fundo.setGravity(3);
            fundo.setOrientation(1);
            fundo.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            fundo.setPadding(2, 2, 2, 2);

            LinearLayout g = new LinearLayout(getContext());
            g.setGravity(3);
            g.setOrientation(0);
            g.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            g.setPadding(2, 2, 2, 2);

            LinearLayout fundoIMG = new LinearLayout(getContext());
            fundoIMG.setGravity(3);
            fundoIMG.setOrientation(1);
            fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            fundoIMG.setPadding(2, 2, 2, 2);

            LinearLayout fundoCTX = new LinearLayout(getContext());
            fundoCTX.setGravity(3);
            fundoCTX.setOrientation(1);
            fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            fundoCTX.setPadding(2, 2, 2, 2);

            ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape1.getPaint().setColor(Thema.COR_BACKGROND_SUBTITULO);

            TextView nomeValor = new TextView(getContext());
            nomeValor.setTextColor(-1);
            nomeValor.setText(o.getNome());
            nomeValor.setTextSize(12.0F);
            nomeValor.setWidth(this.w);
            nomeValor.setTypeface(Typeface.DEFAULT_BOLD);
            nomeValor.setGravity(3);
            nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            nomeValor.setPadding(2, 2, 2, 2);
            nomeValor.setBackgroundDrawable(shape1);
            fundoCTX.addView(nomeValor);

            nomeValor = new TextView(getContext());
            nomeValor.setTextColor(-16777216);
            if (o.getObjeto() != null) {
                DefaultListModel d = (DefaultListModel) o.getObjeto();
                nomeValor.setText(d.getDescricao() + "...");
            } else {
                nomeValor.setText("");
            }
            nomeValor.setTextSize(10.0F);
            nomeValor.setGravity(3);
            nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            nomeValor.setPadding(2, 2, 2, 2);
            fundoCTX.addView(nomeValor);

            LinearLayout fundoQuant = new LinearLayout(getContext());
            fundoQuant.setGravity(3);
            fundoQuant.setOrientation(0);
            fundoQuant.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            fundoQuant.setPadding(2, 2, 2, 2);

            nomeValor = new TextView(getContext());
            nomeValor.setTextColor(-16777216);
            nomeValor.setText("Quantidade: " + o.getQuantidade());
            nomeValor.setTextSize(12.0F);
            nomeValor.setGravity(3);
            nomeValor.setWidth(ControllerFrameWAR.getInstancia().getW() / 2);
            nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            nomeValor.setPadding(2, 2, 2, 2);
            fundoQuant.addView(nomeValor);

            nomeValor = new TextView(getContext());
            nomeValor.setTextColor(-16777216);
            nomeValor.setText(" " + o.getValor());
            nomeValor.setTextSize(12.0F);
            nomeValor.setWidth(ControllerFrameWAR.getInstancia().getW() / 2);
            nomeValor.setTypeface(Typeface.DEFAULT_BOLD);
            nomeValor.setGravity(5);
            nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            nomeValor.setPadding(2, 2, 2, 2);
            fundoQuant.addView(nomeValor);

            fundoCTX.addView(fundoQuant);

            g.addView(fundoCTX);

            fundo.addView(g);

            v1.addView(fundo);
        } else {
            v1 = (LinearLayout) convertView;
            v1.removeAllViews();
            try {
                LinearLayout fundo = new LinearLayout(getContext());
                fundo.setGravity(3);
                fundo.setOrientation(1);
                fundo.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                fundo.setBackgroundDrawable(shape);
                fundo.setPadding(7, 2, 2, 2);

                LinearLayout g = new LinearLayout(getContext());
                g.setGravity(3);
                g.setOrientation(0);
                g.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                g.setPadding(7, 2, 2, 2);

                LinearLayout fundoIMG = new LinearLayout(getContext());
                fundoIMG.setGravity(3);
                fundoIMG.setOrientation(1);
                fundoIMG.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                fundoIMG.setPadding(7, 2, 2, 2);

                LinearLayout fundoCTX = new LinearLayout(getContext());
                fundoCTX.setGravity(3);
                fundoCTX.setOrientation(1);
                fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                fundoCTX.setPadding(7, 2, 2, 2);

                ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                shape1.getPaint().setColor(Thema.COR_BACKGROND_SUBTITULO);

                TextView nomeValor = new TextView(getContext());
                nomeValor.setTextColor(-1);
                nomeValor.setText(o.getNome());
                nomeValor.setTextSize(12.0F);
                nomeValor.setWidth(this.w);
                nomeValor.setTypeface(Typeface.DEFAULT_BOLD);
                nomeValor.setGravity(3);
                nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                nomeValor.setPadding(7, 2, 2, 2);
                nomeValor.setBackgroundDrawable(shape1);
                fundoCTX.addView(nomeValor);

                nomeValor = new TextView(getContext());
                nomeValor.setTextColor(-16777216);
                if (o.getObjeto() != null) {
                    DefaultListModel d = (DefaultListModel) o.getObjeto();
                    nomeValor.setText(d.getDescricao() + "...");
                } else {
                    nomeValor.setText("");
                }
                nomeValor.setTextSize(10.0F);
                nomeValor.setGravity(3);
                nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                nomeValor.setPadding(7, 2, 2, 2);
                fundoCTX.addView(nomeValor);

                LinearLayout fundoQuant = new LinearLayout(getContext());
                fundoQuant.setGravity(1);
                fundoQuant.setOrientation(0);
                fundoQuant.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                fundoQuant.setPadding(2, 2, 2, 2);

                nomeValor = new TextView(getContext());
                nomeValor.setTextColor(-16777216);
                nomeValor.setText("Quantidade: " + o.getQuantidade());
                nomeValor.setTextSize(12.0F);
                nomeValor.setWidth(ControllerFrameWAR.getInstancia().getW() / 2);
                nomeValor.setTypeface(Typeface.DEFAULT_BOLD);
                nomeValor.setGravity(3);
                nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                nomeValor.setPadding(2, 2, 2, 2);
                fundoQuant.addView(nomeValor);

                nomeValor = new TextView(getContext());
                nomeValor.setTextColor(-16777216);
                nomeValor.setText(" " + o.getValor());
                nomeValor.setTextSize(12.0F);
                nomeValor.setWidth(ControllerFrameWAR.getInstancia().getW() / 2);
                nomeValor.setTypeface(Typeface.DEFAULT_BOLD);
                nomeValor.setGravity(5);
                nomeValor.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                nomeValor.setPadding(2, 2, 2, 2);
                fundoQuant.addView(nomeValor);

                fundoCTX.addView(fundoQuant);

                g.addView(fundoCTX);

                fundo.addView(g);

                v1.addView(fundo);
            } catch (Exception localException) {
            }
        }
        return v1;
    }

    public String toString() {
        return ((DefaultListModel) this.items.get(this.index)).getNome();
    }
}