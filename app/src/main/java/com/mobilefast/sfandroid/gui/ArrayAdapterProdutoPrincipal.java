package com.mobilefast.sfandroid.gui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.MyListener;
import com.frame.mobilefast.MyListenerComRetornoDoNomeDoComponente;
import com.frame.mobilefast.Thema;
import com.mobilefast.sfandroid.model.MBPR;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class ArrayAdapterProdutoPrincipal extends ArrayAdapter<DefaultListModel> {

    public static String QTDE_ITEM = "QUANTIDADE";
    public static String PRECO_ITEM = "PRECO";
    public static int qtdeItem = 0;
    public static Double precoVendido = 0.00;
    public static View vv;
    public static CheckBox chk;
    public static DecimalFormat decimal = new DecimalFormat("##,##00.00");
    public static EditText txpreco;
    public static EditText txqtde;
    private final Map CACHE = new HashMap();
    private final Map CACHE_LAYOUT = new HashMap();
    List lpr = null;
    private ArrayList<String> lpreco = null;
    private List<DefaultListModel> items;
    private int index = -1;
    private Display display = null;
    private int w = 0;
    private int h = 0;
    private MyListener listener;
    private MyListenerComRetornoDoNomeDoComponente listener2;

    public ArrayAdapterProdutoPrincipal(Context context,
                                        int textViewResourceId, List<DefaultListModel> lista) {
        super(context, textViewResourceId, lista);
        this.items = lista;

        display = ((WindowManager) context
                .getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        w = display.getWidth();
        h = display.getHeight();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout v1 = null;// (ViewItens) convertView;
        index = position;
        CoreSFAndroid.dd = items.get(position);

        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};
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

            v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

            ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR,
                    null, null));
            shape1.getPaint().setColor(Color.argb(100, 10, 10, 10));

            LinearLayout fundo = new LinearLayout(getContext());
            fundo.setGravity(Gravity.LEFT);
            fundo.setOrientation(LinearLayout.VERTICAL);

            fundo.setPadding(5, 5, 5, 5);
            fundo.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            fundo.setBackgroundDrawable(shape1);

            LinearLayout g = new LinearLayout(getContext());
            g.setGravity(Gravity.LEFT);
            g.setOrientation(LinearLayout.VERTICAL);
            g.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            g.setBackgroundDrawable(shape2);
            //g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
            //		Thema.VAL_B);

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
            img.setLayoutParams(new GridView.LayoutParams(100, 100));
            img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);
            img.setImageResource(R.drawable.tablet);

            // fundoIMG.addView(img);
            //fundo.addView(img);

            LinearLayout fundoCTX = new LinearLayout(getContext());
            //fundoCTX.setGravity(Gravity.LEFT);
            fundoCTX.setOrientation(LinearLayout.VERTICAL);
            fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                    Thema.VAL_B);

            LinearLayout fundoCTX3 = new LinearLayout(getContext());
            fundoCTX3.setGravity(1);
            fundoCTX3.setOrientation(0);
            fundoCTX3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            fundoCTX3.setPadding(0, 5, 0, 0);

            TextView nomeValor = new TextView(getContext());
            nomeValor.setTextColor(Color.WHITE);
            nomeValor.setText(" " + CoreSFAndroid.dd.getMetaDataTag() + " - " + CoreSFAndroid.dd.getDescricao());
            fundoCTX3.addView(nomeValor);

            LinearLayout fundoCTX4 = new LinearLayout(getContext());
            fundoCTX4.setGravity(5);
            fundoCTX4.setOrientation(0);
            fundoCTX4.setLayoutParams(new LayoutParams(-1, -2));

            chk = new CheckBox(getContext());
            //chk.setOnCheckedChangeListener(this);
            //pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
            chk.setTag(CoreSFAndroid.dd);
            //if(CoreSFAndroid.getintance().isCarrinho(pt.getId())){
            //	chk.setChecked(true);
            //}else{
            //	chk.setChecked(false);
            //}

            //fundoCTX4.addView(chk);
            fundoCTX3.addView(fundoCTX4);

            fundoCTX.addView(fundoCTX3);

            LinearLayout fundoCTX2 = new LinearLayout(getContext());
            fundoCTX2.setGravity(Gravity.LEFT);
            fundoCTX2.setOrientation(LinearLayout.HORIZONTAL);
            fundoCTX2.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            //fundoCTX2.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
            //		Thema.VAL_B);

            TextView subdescri = new TextView(getContext());
            subdescri.setTextColor(Color.rgb(192, 255, 62));
            subdescri.setText("Preço Sugerido R$: " + CoreSFAndroid.dd.getValor());
            //subdescri.setTextSize(20);
            //subdescri.setLayoutParams(new LinearLayout.LayoutParams(
            //		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            subdescri.setPadding(5, 0, 0, 0);
            fundoCTX2.addView(subdescri);


            TextView txestoque = new TextView(getContext());
            //String est = CoreSFAndroid.dd.getFrete();
            ///Double estoque = Double.valueOf((CoreSFAn2droid.dd.getFrete()));
            //Double valorDouble = Double.parseDouble(est.replaceAll("\\.","").replace(",","."));
            //BigDecimal valor = new BigDecimal(est.replaceAll("\\.","").replace(",","."));
            //  int esto = valor.intValue();// = estoque;
            //  if(esto > 0){
            txestoque.setTextColor(Color.rgb(0, 139, 0));
            // }else if (esto <= 0){
            //	 txestoque.setTextColor(Color.rgb(139, 0, 0));
            //  }
            //txestoque.setText("  Estoque: " + String.valueOf(esto));
            //txestoque.setTextSize(20);
            //txestoque.setLayoutParams(new LinearLayout.LayoutParams(
            //		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            txestoque.setPadding(5, 0, 0, 0);
            fundoCTX2.addView(txestoque);

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
            //btInfo.setTextColor(Color.WHITE);
            MBPR pt = (MBPR) CoreSFAndroid.dd.getObjeto();
            btInfo.setTag(CoreSFAndroid.dd);
            btInfo.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btInfo.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setTitle("Detalhes Produto");
                    dialog.setCancelable(true);

                    LinearLayout ld = new LinearLayout(getContext());
                    ld.setGravity(Gravity.CENTER_VERTICAL);
                    ld.setOrientation(LinearLayout.VERTICAL);
                    //ld.setBackgroundResource(R.drawable.background);
                    LinearLayout.LayoutParams pd = new LinearLayout.LayoutParams(
                            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    ld.setLayoutParams(pd);

                    float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
                    ShapeDrawable shape = null;
                    shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                    shape.getPaint().setColor(Color.argb(90, 1, 0, 0));

                    LinearLayout lld = new LinearLayout(getContext());
                    lld.setGravity(Gravity.CENTER_HORIZONTAL);
                    lld.setOrientation(LinearLayout.VERTICAL);
                    lld.setPadding(10, 10, 10, 10);
                    lld.setLayoutParams(pd);

                    //MBPR pt = null;
                    if (v.getTag() == null) {
                        ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
                    } else {
                        CoreSFAndroid.dd = (DefaultListModel) v.getTag();
                        MBPR pt = (MBPR) CoreSFAndroid.dd.getObjeto();
                        vv = v;
                        TextView var12 = new TextView(getContext());
                        var12.setText(pt.getC_prod_palm() + " - " + pt.getDescricao());
                        var12.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var12.setTextColor(Color.rgb(192, 255, 62));
                        ld.addView(var12);

                        TextView var13 = new TextView(getContext());
                        var13.setText("Preço Produto: " + CoreSFAndroid.dd.getValor());
                        var13.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var13.setTextColor(-1);
                        ld.addView(var13);

                        TextView var14 = new TextView(getContext());
                        var14.setText("Peso Bruto: " + pt.getPeso_bruto());
                        var14.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var14.setTextColor(-1);
                        ld.addView(var14);

                        TextView var15 = new TextView(getContext());
                        var15.setText("Unidade: " + pt.getUnidade());
                        var15.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var15.setTextColor(-1);
                        ld.addView(var15);

                        TextView var17 = new TextView(getContext());
                        var17.setText("Prazo de Entrega: " + pt.getPrazo_entrega());
                        var17.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var17.setTextColor(-1);
                        ld.addView(var17);

                        TextView var18 = new TextView(getContext());
                        var18.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var18.setTextColor(-1);

                        TextView var19 = new TextView(getContext());
                        var19.setText("Quantidade Embalagem: " + pt.getQtde_p_embal());
                        var19.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var19.setTextColor(-1);
                        ld.addView(var19);

                        TextView var20 = new TextView(getContext());
                        //  var20.setText("Grupo: " + pt.getGrupo());
                        var20.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var20.setTextColor(-1);
                        //  ld.addView(var20);

                        TextView var21 = new TextView(getContext());
                        var21.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var21.setTextColor(-1);

                        TextView var22 = new TextView(getContext());
                        var22.setText("% IPI: " + CoreSFAndroid.formataMoeda(pt.getPorc_ipi()));
                        var22.setTextSize(CoreSFAndroid.tamanho_fonte);
                        var22.setTextColor(-1);
                        ld.addView(var22);

                        TextView var255 = new TextView(getContext());
                        var255.setText("-");
                        var255.setTextColor(-1);
                        ld.addView(var255);
                    }
                    Button okButton = new Button(getContext());
                    okButton.setHeight(50);
                    okButton.setText("Ok");
                    ld.addView(okButton);
                    okButton.setOnClickListener(new OnClickListener() {

                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    //ld.addView(lld);
                    dialog.setContentView(ld);
                    dialog.show();
                }

            });
            linfo.addView(btInfo);
            lCHK.addView(linfo);
            fundo.addView(lCHK);

            v1.addView(fundo);
        } else {
            v1 = (LinearLayout) convertView;
            v1.removeAllViews();

            try {
                ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(
                        outerR, null, null));
                shape1.getPaint().setColor(Color.argb(100, 10, 10, 10));

                LinearLayout fundo = new LinearLayout(getContext());
                fundo.setGravity(Gravity.LEFT);
                fundo.setOrientation(LinearLayout.VERTICAL);

                fundo.setPadding(5, 5, 5, 5);
                fundo.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                fundo.setBackgroundDrawable(shape1);

                LinearLayout g = new LinearLayout(getContext());
                g.setGravity(Gravity.LEFT);
                g.setOrientation(LinearLayout.VERTICAL);
                g.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                g.setBackgroundDrawable(shape2);
                //g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                //		Thema.VAL_B);

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
                img.setLayoutParams(new GridView.LayoutParams(100, 100));
                img.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                        Thema.VAL_B);
                img.setImageResource(R.drawable.tablet);

                // fundoIMG.addView(img);
                //fundo.addView(img);

                LinearLayout fundoCTX = new LinearLayout(getContext());
                //fundoCTX.setGravity(Gravity.LEFT);
                fundoCTX.setOrientation(LinearLayout.VERTICAL);
                fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                        Thema.VAL_B);

                LinearLayout fundoCTX3 = new LinearLayout(getContext());
                fundoCTX3.setGravity(1);
                fundoCTX3.setOrientation(0);
                fundoCTX3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                fundoCTX3.setPadding(0, 5, 0, 0);

                TextView nomeValor = new TextView(getContext());
                nomeValor.setTextColor(Color.WHITE);
                nomeValor.setText(" " + CoreSFAndroid.dd.getMetaDataTag() + " - " + CoreSFAndroid.dd.getDescricao());
                fundoCTX3.addView(nomeValor);

                LinearLayout fundoCTX4 = new LinearLayout(getContext());
                fundoCTX4.setGravity(5);
                fundoCTX4.setOrientation(0);
                fundoCTX4.setLayoutParams(new LayoutParams(-1, -2));

                chk = new CheckBox(getContext());
                //chk.setOnCheckedChangeListener(this);
                //pt =  (MBPR) CoreSFAndroid.dd.getObjeto();
                chk.setTag(CoreSFAndroid.dd);
                //if(CoreSFAndroid.getintance().isCarrinho(pt.getId())){
                //	chk.setChecked(true);
                //}else{
                //	chk.setChecked(false);
                //}

                //fundoCTX4.addView(chk);
                fundoCTX3.addView(fundoCTX4);

                fundoCTX.addView(fundoCTX3);

                LinearLayout fundoCTX2 = new LinearLayout(getContext());
                fundoCTX2.setGravity(Gravity.LEFT);
                fundoCTX2.setOrientation(LinearLayout.HORIZONTAL);
                fundoCTX2.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                //fundoCTX2.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
                //		Thema.VAL_B);

                TextView subdescri = new TextView(getContext());
                subdescri.setTextColor(Color.rgb(192, 255, 62));
                subdescri.setText("Preço Sugerido R$: " + CoreSFAndroid.dd.getValor());
                //subdescri.setTextSize(20);
                //subdescri.setLayoutParams(new LinearLayout.LayoutParams(
                //		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                subdescri.setPadding(5, 0, 0, 0);
                fundoCTX2.addView(subdescri);


                TextView txestoque = new TextView(getContext());
                //String est = CoreSFAndroid.dd.getFrete();
                ///Double estoque = Double.valueOf((CoreSFAn2droid.dd.getFrete()));
                //Double valorDouble = Double.parseDouble(est.replaceAll("\\.","").replace(",","."));
                //BigDecimal valor = new BigDecimal(est.replaceAll("\\.","").replace(",","."));
                //int esto = valor.intValue();// = estoque;
                ///  if(esto > 0){
                txestoque.setTextColor(Color.rgb(0, 139, 0));
                //  }else if (esto <= 0){
                //	 txestoque.setTextColor(Color.rgb(139, 0, 0));
                //  }
                //txestoque.setText("  Estoque: " + String.valueOf(esto));
                //txestoque.setTextSize(20);
                //txestoque.setLayoutParams(new LinearLayout.LayoutParams(
                //		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                txestoque.setPadding(5, 0, 0, 0);
                fundoCTX2.addView(txestoque);

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
                //btInfo.setTextColor(Color.WHITE);
                MBPR pt = (MBPR) CoreSFAndroid.dd.getObjeto();
                btInfo.setTag(CoreSFAndroid.dd);
                btInfo.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                btInfo.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setTitle("Detalhes Produto");
                        dialog.setCancelable(true);

                        LinearLayout ld = new LinearLayout(getContext());
                        ld.setGravity(Gravity.CENTER_VERTICAL);
                        ld.setOrientation(LinearLayout.VERTICAL);
                        ld.setBackgroundResource(R.drawable.background);
                        LinearLayout.LayoutParams pd = new LinearLayout.LayoutParams(
                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        ld.setLayoutParams(pd);

                        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
                        ShapeDrawable shape = null;
                        shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
                        shape.getPaint().setColor(Color.argb(90, 1, 0, 0));

                        LinearLayout lld = new LinearLayout(getContext());
                        lld.setGravity(Gravity.CENTER_HORIZONTAL);
                        lld.setOrientation(LinearLayout.VERTICAL);
                        lld.setPadding(10, 10, 10, 10);
                        lld.setLayoutParams(pd);

                        //MBPR pt = null;
                        if (v.getTag() == null) {
                            ControllerSFAndroid.getInstancia().showMensagemSimples("Produto Invalido!");
                        } else {
                            CoreSFAndroid.dd = (DefaultListModel) v.getTag();
                            MBPR pt = (MBPR) CoreSFAndroid.dd.getObjeto();
                            vv = v;
                            TextView var12 = new TextView(getContext());
                            var12.setText(pt.getC_prod_palm() + " - " + pt.getDescricao());
                            var12.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var12.setTextColor(Color.rgb(192, 255, 62));
                            ld.addView(var12);

                            TextView var13 = new TextView(getContext());
                            var13.setText("Preço Produto: " + CoreSFAndroid.dd.getValor());
                            var13.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var13.setTextColor(-1);
                            ld.addView(var13);

                            TextView var14 = new TextView(getContext());
                            var14.setText("Peso Bruto: " + pt.getPeso_bruto());
                            var14.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var14.setTextColor(-1);
                            ld.addView(var14);

                            TextView var15 = new TextView(getContext());
                            var15.setText("Unidade: " + pt.getUnidade());
                            var15.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var15.setTextColor(-1);
                            ld.addView(var15);

                            TextView var17 = new TextView(getContext());
                            var17.setText("Prazo de Entrega: " + pt.getPrazo_entrega());
                            var17.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var17.setTextColor(-1);
                            ld.addView(var17);

                            TextView var18 = new TextView(getContext());
                            var18.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var18.setTextColor(-1);

                            TextView var19 = new TextView(getContext());
                            var19.setText("Quantidade Embalagem: " + pt.getQtde_p_embal());
                            var19.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var19.setTextColor(-1);
                            ld.addView(var19);

                            TextView var20 = new TextView(getContext());
                            //  var20.setText("Grupo: " + pt.getGrupo());
                            var20.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var20.setTextColor(-1);
                            //  ld.addView(var20);

                            TextView var21 = new TextView(getContext());
                            var21.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var21.setTextColor(-1);

                            TextView var22 = new TextView(getContext());
                            var22.setText("% IPI: " + CoreSFAndroid.formataMoeda(pt.getPorc_ipi()));
                            var22.setTextSize(CoreSFAndroid.tamanho_fonte);
                            var22.setTextColor(-1);
                            ld.addView(var22);

                            TextView var255 = new TextView(getContext());
                            var255.setText("-");
                            var255.setTextColor(-1);
                            ld.addView(var255);

                        }
                        Button okButton = new Button(getContext());
                        okButton.setHeight(50);
                        okButton.setText("Ok");
                        ld.addView(okButton);
                        okButton.setOnClickListener(new OnClickListener() {

                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        //ld.addView(lld);
                        dialog.setContentView(ld);
                        dialog.show();
                    }

                });

                linfo.addView(btInfo);
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

}