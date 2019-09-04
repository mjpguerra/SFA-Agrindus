package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.frame.mobilefast.DefaultListModel;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBGR;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;


public class GUIGrupoProdutos extends Activity {
	private android.widget.ListView list = null;
	private android.widget.ArrayAdapter<String> adapter = null;
	private ArrayList<String> lista = null;

	public static String COD_CONDPAGTO = "CODIGO";
	public static String DESCR_CONDPAGTO = "DESCRICAO";
	public static String PESQ_PROD = "PESQUISAR PRODUTO";

	private List<MBGR> lgrupo = null;
	private List<MBPR> lp = null;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			ControllerSFAndroid.getInstancia().setContext(this);
			this.setTitle("Grupo de Produtos");
			
			lista = new ArrayList<String>();
			lgrupo = LocatorDAO.getInstancia().getMbgr().findAll();
			for (Iterator<MBGR> iterator = lgrupo.iterator(); iterator.hasNext();) {
				MBGR gru = iterator.next();
				TextView g = new TextView(this);
				g.setText(gru.getDescricao());
				g.setTextSize(12);
				
				lista.add(g.getText().toString());
			}
			loadLista(lista);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
		
	public void loadLista(List<String> lista) {

		LinearLayout l = new LinearLayout(this);
		l.setGravity(Gravity.CENTER_HORIZONTAL);
		l.setOrientation(LinearLayout.VERTICAL);
		//l.setBackgroundResource(sfa.android.R.drawable.background);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		l.setLayoutParams(p);
	        
		final Button btGrupo = new Button(this);
		//btGrupo.setId(1001);
		btGrupo.setText("Todos Produtos");
		btGrupo.setTextSize(16);
		btGrupo.setTextColor(Color.rgb(192, 255, 62));
		// btGrupo.setWidth(150);
		btGrupo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CoreSFAndroid.flag_grupo = 3;
				CoreSFAndroid.lista_prod.clear();
				MBPRDAO.getInstancia().offset = 0;
				MBPRDAO.getInstancia().nome = "";
				CoreSFAndroid.getintance().addProdList();
				CoreSFAndroid.getintance().abrirTelaPrincipalPedido();
				finish();
			}
		});

		l.addView(btGrupo);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista);
		android.widget.RelativeLayout ll = new android.widget.RelativeLayout(
				this);
		ll.setHorizontalGravity(Gravity.CENTER);
		ll.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		ll.setBackgroundResource(R.drawable.background);
		list = new ListView(this);
		list.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int i, long id) {
				addItem(i);
				MBPRDAO.getInstancia().offset = 0;
				MBPRDAO.getInstancia().nome = "";
				CoreSFAndroid.getintance().abrirTelaPrincipalPedido();
				finish();
			}
		});
		ll.addView(list);
		l.addView(ll);

		// l.addView(FactoryMyComponent.getInstance().getTextView(GUIConsumoCliente.PRODUTO,"Produto:",""));

		// ScrollView j = new ScrollView(this);
		// j.addView(ll);
		// setContentView(j);
		this.setContentView(l);
	}
	    
	    
	@SuppressWarnings("unchecked")
	public void addItem(int valor) {
		try {
			CoreSFAndroid.flag_grupo = 2;

			CoreSFAndroid.GRUPO_CORRENTE = lgrupo.get(valor);
			String id_grupo = CoreSFAndroid.GRUPO_CORRENTE.getGrupo();
			lp = (List<MBPR>) LocatorDAO.getInstancia().getMbpr().findGrupoProduto(id_grupo);
			CoreSFAndroid.lista_prodGrupo.clear();
			for (Iterator<MBPR> iterator = lp.iterator(); iterator.hasNext();) {
			       MBPR var3 = iterator.next();
			       CoreSFAndroid.dd = new DefaultListModel();
			       CoreSFAndroid.dd.setMetaDataTag(var3.getC_prod_palm());
			       CoreSFAndroid.dd.setNome(String.valueOf(var3.getId()));
			       CoreSFAndroid.dd.setDescricao(var3.getDescricao());
			       CoreSFAndroid.dd.setValor(CoreSFAndroid.formataMoeda(var3.getPreco_venda()));
			       CoreSFAndroid.dd.setQuantidade(1);
			       CoreSFAndroid.dd.setFrete(var3.getPosicao_estq());
			       CoreSFAndroid.dd.setObjeto(var3);
				   CoreSFAndroid.lista_prodGrupo.add(CoreSFAndroid.dd);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
  
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
        	CoreSFAndroid.flag_grupo = 3;
        	CoreSFAndroid.getintance().abrirTelaPrincipalPedido();
        	finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}