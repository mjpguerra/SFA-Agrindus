package com.mobilefast.midia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;

public class ImageAdapter extends BaseAdapter {

    private List<String> plotsImages;

    // Constructor
    public ImageAdapter(List<String> plotsImages) {

        this.plotsImages = plotsImages;

    }

    public int getCount() {
        return plotsImages.size();
    }


    public Object getItem(int position) {
        return plotsImages.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        LinearLayout l = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
        l.setGravity(Gravity.CENTER_HORIZONTAL);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(10, 10, 10, 10);

        ImageButton imageView = null;


        if (convertView == null) {
            try {

                LinearLayout l1 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
                l1.setGravity(Gravity.CENTER_HORIZONTAL);
                l1.setOrientation(LinearLayout.VERTICAL);


                float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
                ShapeDrawable shape = null;

                shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                shape.getPaint().setColor(Color.argb(80, 200, 200, 200));

                l1.setBackgroundDrawable(shape);

                imageView = new ImageButton(ControllerSFAndroid.getInstancia().getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // imageView.setPadding(35,35,35,35);
                imageView.setBackgroundColor(Color.TRANSPARENT);
                imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
                l1.addView(imageView);

                ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                shape1.getPaint().setColor(Color.rgb(255, 255, 255));
                LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
                l2.setGravity(Gravity.CENTER_HORIZONTAL);
                l2.setOrientation(LinearLayout.VERTICAL);
                l2.setPadding(5, 5, 5, 5);

                TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
                textView.setText("Teste");
                //textView.setTextSize(12);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setBackgroundDrawable(shape1);

                l2.addView(textView);


                Button btlogin = new Button(ControllerSFAndroid.getInstancia().getContext());
                btlogin.setText("Descrição");
                btlogin.setTextColor(-1);
                btlogin.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("logn", "logn");
                        Toast.makeText(ControllerSFAndroid.getInstancia().getContext(), "BOTAO ATIVOO", Toast.LENGTH_SHORT).show();

                    }
                });

                l2.addView(btlogin);
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
            ShapeDrawable shape = null;

            shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape.getPaint().setColor(Color.argb(95, 200, 200, 200));

            l1.setBackgroundDrawable(shape);

            imageView = new ImageButton(ControllerSFAndroid.getInstancia().getContext());
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //imageView.setPadding(35,35,35,35);
            imageView.setBackgroundColor(Color.TRANSPARENT);
            imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GridImagens.posi = position;
                    // Sending image id to FullScreenActivity
                    CoreSFAndroid.getintance().abrirTelaFullImagem();
                }
            });
            l1.addView(imageView);

            ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape1.getPaint().setColor(Color.rgb(255, 255, 255));
            LinearLayout l2 = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
            l2.setGravity(Gravity.CENTER_HORIZONTAL);
            l2.setOrientation(LinearLayout.VERTICAL);
            l2.setPadding(5, 5, 5, 5);

            TextView textView = new TextView(ControllerSFAndroid.getInstancia().getContext());
            textView.setText("Teste");
            //textView.setTextSize(12);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setBackgroundDrawable(shape1);

            l2.addView(textView);

            Button btlogin = new Button(ControllerSFAndroid.getInstancia().getContext());
            btlogin.setText("Descrição");
            btlogin.setTextColor(-1);
            btlogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("logn", "logn");
                    Toast.makeText(ControllerSFAndroid.getInstancia().getContext(), "BOTAO ATIVOO", Toast.LENGTH_SHORT).show();

                }
            });

            l2.addView(btlogin);
            l1.addView(l2);

            l.addView(l1);

        }

        if (imageView != null) {
            Bitmap bm = BitmapFactory.decodeFile(plotsImages.get(position).toString());
            imageView.setImageBitmap(bm);
        }

        return l;
    }


}
