package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class GUIRotasCliente extends Activity {
	private android.widget.ListView list = null;
	private android.widget.ArrayAdapter<String> adapter = null;
	private ArrayList<String> lista = null;
	private ArrayList<String> lstEstados_Encontrados = new ArrayList<String>();

	//private List<Rota> lrota = null;
	EditText pesq;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			ControllerSFAndroid.getInstancia().setContext(this);
			this.setTitle("Rotas do Cliente");

			// criar_grupo();
			//lista = new ArrayList<String>();
			//lrota = LocatorDAO.getInstancia().getRota().findSelect();
			//for (Iterator<Rota> iterator = lrota.iterator(); iterator.hasNext();) {
			//	Rota rot = iterator.next();
			//	lista.add(rot.getDESCR());
			//}
			//loadLista(lista);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
		
	public void loadLista( final List<String> lista) {

		LinearLayout l = new LinearLayout(this);
		l.setGravity(Gravity.CENTER_HORIZONTAL);
		l.setOrientation(LinearLayout.VERTICAL);
		l.setBackgroundResource(R.drawable.background);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		l.setLayoutParams(p);
	        
		final Button btalfa = new Button(this);
		btalfa.setId(1001);
		btalfa.setText("Clientes Alfa");
		btalfa.setTextSize(16);
		btalfa.setTextColor(Color.WHITE);
		// btGrupo.setWidth(150);
		btalfa.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CoreSFAndroid.flag_rota = 1;
		   	    lista.clear();
		   	  //  RotaDAO.getInstancia().offset = 0;
				CoreSFAndroid.getintance().abrirTelaListaCliente();
				finish();
				
			}
		});

		l.addView(btalfa);
		
		
		TextView tpesq = new TextView(this);
		tpesq.setText("Pesquisar Rota");
		tpesq.setTextColor(Color.WHITE);
		tpesq.setTextSize(16);
		tpesq.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		l.addView(tpesq);
		
		pesq = new EditText(this);
		pesq.setText("");
		pesq.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		l.addView(pesq);
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista);
		android.widget.RelativeLayout ll = new android.widget.RelativeLayout(
				this);
		ll.setHorizontalGravity(Gravity.CENTER);
		ll.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		//ll.setBackgroundResource(sfa.android.R.drawable.background);
		list = new ListView(this);
		list.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int i, long id) {
				//addItem(i);
				//finish();
			}
		});
		
		l.addView(list);
		//l.addView(ll);

		// l.addView(FactoryMyComponent.getInstance().getTextView(GUIConsumoCliente.PRODUTO,"Produto:",""));

		// ScrollView j = new ScrollView(this);
		// j.addView(ll);
		// setContentView(j);
		this.setContentView(l);
	}

	    
	@SuppressWarnings("unchecked")
	public void addItem(int valor) {
	//	try {
			//CoreSFAndroid.flag_rota = 2;
			//CoreSFAndroid.ROTA_CORRENTE = lrota.get(valor);
			//String id_rota = CoreSFAndroid.ROTA_CORRENTE.getROTA();
			///CoreSFAndroid.lRoteiro = LocatorDAO.getInstancia().getRoteiro().findByIDSuperior(id_rota);
			//CoreSFAndroid.lista_roteiro.clear();
			//for (Iterator<Roteiro> iterator = CoreSFAndroid.lRoteiro.iterator(); iterator.hasNext();) {
			//	Roteiro rote = iterator.next();
			//CoreSFAndroid.dro = new DefaultListModel();
			//	CoreSFAndroid.dro.setNome(rote.getAD7_NOMCLI());
			//	CoreSFAndroid.dro.setId(Integer.valueOf(rote.getAD7_CODCLI()));
			///	CoreSFAndroid.lista_roteiro.add(CoreSFAndroid.dro);
			///}
			//CoreSFAndroid.getintance().abrirTelaListaCliente();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}

	}
  
    
public boolean onKeyDown(int keyCode, KeyEvent event)  {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        // do something on back.
    	CoreSFAndroid.getintance().abrirTelaListaCliente();
    	finish();
        return true;
    }

    return super.onKeyDown(keyCode, event);
}
}