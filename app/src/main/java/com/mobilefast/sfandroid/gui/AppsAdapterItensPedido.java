package com.mobilefast.sfandroid.gui;

import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;


public class AppsAdapterItensPedido extends BaseAdapter {

    private List<String> mApps;
    //private String[] nomes = {"CLIENTE","OCORRÊNCIA","SINCRONISMO","CADASTRO","SAIR"};
    private String[] nomes = {"P00001  /  10  /  15,23"};

    public AppsAdapterItensPedido() {
        this.mApps = new ArrayList<String>();
        this.mApps.add("Visitas");

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout l = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
        l.setGravity(Gravity.CENTER_HORIZONTAL);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(10, 10, 10, 10);

        ImageView i = null;

        if (convertView == null) {
            try {

                LinearLayout l1 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
                l1.setGravity(Gravity.CENTER_HORIZONTAL);
                l1.setOrientation(LinearLayout.VERTICAL);

                float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
                RectF inset = new RectF(6, 6, 6, 6);
                float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};
                ShapeDrawable shape = null;

                shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                shape.getPaint().setColor(Color.argb(95, 200, 200, 200));

                l1.setBackgroundDrawable(shape);

                ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                shape1.getPaint().setColor(Color.rgb(255, 255, 255));

                LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
                l2.setGravity(Gravity.CENTER_HORIZONTAL);
                l2.setOrientation(LinearLayout.VERTICAL);
                l2.setPadding(5, 5, 5, 5);

                TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
                textView.setText(nomes[position].toUpperCase());
                textView.setTextSize(12);
                textView.setTextColor(Color.BLACK);
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


            float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
            RectF inset = new RectF(6, 6, 6, 6);
            float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};
            ShapeDrawable shape = null;

            shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape.getPaint().setColor(Color.argb(95, 200, 200, 200));

            l1.setBackgroundDrawable(shape);

            ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape1.getPaint().setColor(Color.rgb(255, 255, 255));

            LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
            l2.setGravity(Gravity.CENTER_HORIZONTAL);
            l2.setOrientation(LinearLayout.VERTICAL);
            l2.setPadding(5, 5, 5, 5);

            TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
            textView.setText(nomes[position].toUpperCase());
            textView.setTextSize(12);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setBackgroundDrawable(shape1);

            l2.addView(textView);
            l1.addView(l2);
            l.addView(l1);

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


