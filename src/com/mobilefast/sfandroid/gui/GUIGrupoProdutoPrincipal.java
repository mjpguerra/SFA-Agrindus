package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.frame.mobilefast.DefaultListModel;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBGR;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;

public class GUIGrupoProdutoPrincipal extends Activity {

   public static String COD_CONDPAGTO = "CODIGO";
   public static String DESCR_CONDPAGTO = "DESCRICAO";
   public static String PESQ_PROD = "PESQUISAR PRODUTO";
   private ArrayAdapter adapter = null;
   private List lgrupo = null;
   private ListView list = null;
   private ArrayList lista = null;
   private List lp = null;


   public GUIGrupoProdutoPrincipal() {
      super();
   }

   public void addItem(int var1) {
      try {
         CoreSFAndroid.flag_grupo = 2;
         CoreSFAndroid.GRUPO_CORRENTE = (MBGR)this.lgrupo.get(var1);
         String var3 = CoreSFAndroid.GRUPO_CORRENTE.getGrupo();
         this.lp = LocatorDAO.getInstancia().getMbpr().findGrupoProduto(var3);
         CoreSFAndroid.lista_prodGrupo.clear();
         Iterator var4 = this.lp.iterator();

         while(var4.hasNext()) { 
		       MBPR var5 = (MBPR)var4.next();
		       CoreSFAndroid.dd = new DefaultListModel();
		       CoreSFAndroid.dd.setMetaDataTag(var5.getC_prod_palm());
		       CoreSFAndroid.dd.setNome(String.valueOf(var5.getId()));
		       CoreSFAndroid.dd.setDescricao(var5.getDescricao());
		       CoreSFAndroid.dd.setValor(CoreSFAndroid.formataMoeda(var5.getPreco_venda()));
		       CoreSFAndroid.dd.setQuantidade(1);
		       CoreSFAndroid.dd.setFrete(var5.getPosicao_estq());
		       CoreSFAndroid.dd.setObjeto(var5);
			   CoreSFAndroid.lista_prodGrupo.add(CoreSFAndroid.dd);
         }

         CoreSFAndroid.getintance().abrirTelaProdutoPrincipal();
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public void loadLista(List var1) {
      LinearLayout var2 = new LinearLayout(this);
      var2.setGravity(1);
      var2.setOrientation(1);
      var2.setBackgroundResource(2130837507);
      var2.setLayoutParams(new LayoutParams(-1, -1));
      Button var3 = new Button(this);
      var3.setId(1001);
      var3.setText("Todos Produtos");
      var3.setTextSize(16.0F);
      var3.setTextColor(Color.rgb(192, 255, 62));
      var3.setOnClickListener(new OnClickListener() {
         public void onClick(View var1) {
            CoreSFAndroid.lista_prod.clear();
            MBPRDAO.getInstancia().offset = 0;
            CoreSFAndroid.getintance().addProdList();
            CoreSFAndroid.getintance().abrirTelaProdutoPrincipal();
            GUIGrupoProdutoPrincipal.this.finish();
         }
      });
      var2.addView(var3);
      this.adapter = new ArrayAdapter(this, 17367043, var1);
      RelativeLayout var4 = new RelativeLayout(this);
      var4.setHorizontalGravity(17);
      var4.setLayoutParams(new LayoutParams(-1, -1));
      this.list = new ListView(this);
      this.list.setLayoutParams(new LayoutParams(-1, -1));
      this.list.setAdapter(this.adapter);
      this.list.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            MBPRDAO.getInstancia().offset = 0;
            GUIGrupoProdutoPrincipal.this.addItem(var3);
            GUIGrupoProdutoPrincipal.this.finish();
         }
      });
      var4.addView(this.list);
      var2.addView(var4);
      this.setContentView(var2);
   }

   public void onCreate(Bundle var1) {
      try {
         super.onCreate(var1);
         ControllerSFAndroid.getInstancia().setContext(this);
         this.setTitle("Grupo de Produtos");
         this.lista = new ArrayList();
         this.lgrupo = LocatorDAO.getInstancia().getMbgr().findAll();
         Iterator var3 = this.lgrupo.iterator();

         while(var3.hasNext()) {
            MBGR var4 = (MBGR)var3.next();
            this.lista.add(var4.getDescricao());
         }

         this.loadLista(this.lista);
      } catch (Exception var5) {
         ;
      }

   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      boolean var3;
      if(var1 == 4 && var2.getRepeatCount() == 0) {
         CoreSFAndroid.getintance().abrirTelaProdutoPrincipal();
         this.finish();
         var3 = true;
      } else {
         var3 = super.onKeyDown(var1, var2);
      }

      return var3;
   }
}
