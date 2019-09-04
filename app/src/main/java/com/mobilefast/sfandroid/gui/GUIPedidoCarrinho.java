package com.mobilefast.sfandroid.gui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.mobilefast.DefaultListModel;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class GUIPedidoCarrinho extends Activity {

    public static DefaultArrayAdapterPedidoItens adapter = null;
    public static Button var12;
    public static TextView tx;
    public static String Status_Ped = "";
    public static String Status_data = "";
    public static String CONDPAGTO = "CONDPAGTO";
    public static CheckBox chdata;
    public static Button btvalor;
    List<DefaultListModel> lStatus = new ArrayList<DefaultListModel>();
    private android.widget.ListView list = null;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ControllerSFAndroid.getInstancia().setContext(this);

        this.setTitle("Lista de produtos");
        LinearLayout var3 = new LinearLayout(this);
        var3.setGravity(Gravity.TOP);
        var3.setOrientation(LinearLayout.VERTICAL);
        var3.setBackgroundResource(R.drawable.background2);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT);
        var3.setLayoutParams(p);

        btvalor = new Button(this);
        //btvalor.setTextColor(Color.rgb(192, 255, 62));
        //btvalor.setTypeface(Typeface.DEFAULT_BOLD);
        btvalor.setTextSize(CoreSFAndroid.tamanho_fonte);
        btvalor.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                //GUIMovimentoPedido.gerar_total_itens();
                //GUIMovimentoPedido.valor_total();
            }
        });
        CoreSFAndroid.gerar_total_itens();

        var3.addView(btvalor);

        LayoutParams var4 = new LayoutParams(-1, -1);
        var3.setLayoutParams(var4);
        float[] var5 = new float[]{12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F};
        new RectF(6.0F, 6.0F, 6.0F, 6.0F);
        ShapeDrawable var8 = new ShapeDrawable(new RoundRectShape(var5, (RectF) null, (float[]) null));
        var8.getPaint().setColor(Color.argb(90, 211, 211, 211));

        adapter = new DefaultArrayAdapterPedidoItens(this, android.R.layout.browser_link_context_header, CoreSFAndroid.lista_itensPedido);
        //this.adapter = new DefaultArrayAdapterPedidoItens(this, 17367054, CoreSFAndroid.lista_itensPedido);
        RelativeLayout var19 = new RelativeLayout(this);
        var19.setHorizontalGravity(1);
        var19.setLayoutParams(new LayoutParams(-1, -1));

        list = new ListView(this);
        list.setLayoutParams(new LayoutParams(-1, -1));
        list.setAdapter((ListAdapter) adapter);
        var19.addView(this.list);
        var3.addView(var19);
        setContentView(var3);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
