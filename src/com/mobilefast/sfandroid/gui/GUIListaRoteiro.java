package com.mobilefast.sfandroid.gui;
 
import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

 
public class GUIListaRoteiro extends Activity {

	public static String NOME_CLI = "NOME CLIENTE";
	public static String PRODUTO = "PRODUTO";	
	private android.widget.ListView list = null;
	private DefaultArrayAdapterRoteiro adapter = null;	
	int aux;

	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			ControllerSFAndroid.getInstancia().setContext(this);

			this.setTitle("Lista Clientes");
			
			LinearLayout l = new LinearLayout(this);
		    l.setGravity(Gravity.CENTER_HORIZONTAL);
		    l.setOrientation(LinearLayout.VERTICAL);
		    //l.setBackgroundResource(sfa.android.R.drawable.background);
		    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		    l.setLayoutParams(p);

			
	         final Button btRota = new Button(this);
	         btRota.setId(1001);
	         btRota.setText("Rotas do Cliente");
	         //btGrupo.setWidth(150);        
	         btRota.setTextSize(16);
	         btRota.setTextColor(Color.WHITE);
	             btRota.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	              CoreSFAndroid.getintance().abrirTelaRotaCliente();
	              finish();
	             }
	         });
	         l.addView(btRota);
 
			TextView tpesq = new TextView(this);
			tpesq.setText("Pesquisar Cliente");
			tpesq.setTextColor(Color.WHITE);
			tpesq.setTextSize(16);
			tpesq.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			l.addView(tpesq);
			
			EditText pesq = new EditText(this);
			pesq.setText("");
			pesq.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			l.addView(pesq);

			// MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,
			// lista);
			
			adapter = new DefaultArrayAdapterRoteiro(this,android.R.layout.simple_list_item_1, CoreSFAndroid.lista_roteiro);	
			//adapter = new DefaultArrayAdapterRoteiro(this,android.R.layout.browser_link_context_header,CoreSFAndroid.lista_roteiro);
			//adapter = new DefaultArrayAdapterItens(this,	android.R.layout.simple_list_item_1, CoreSFAndroid.lista_prod);			
			list = new ListView(this);
			list.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			list.setAdapter(adapter);
			list.setTextFilterEnabled(true);
			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View v, int i,
						long id) {
					addItem(i);

				}
			});

			//ll.setBackgroundResource(R.drawable.back);
			//ll.addView(list);
			l.addView(list);
			this.setContentView(l);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

public void addItem(int valor){
  	CoreSFAndroid.dro = CoreSFAndroid.lista_roteiro.get(valor);
  	//CoreSFAndroid.ROTEIRO_CORRENTE = CoreSFAndroid.lRoteiro.get(CoreSFAndroid.dro.getId());
  	CoreSFAndroid.cod_cliente = CoreSFAndroid.dro.getDescricao();
    CoreSFAndroid.getintance().abrirTelaPrincipalCliente();  
    finish();
}

public boolean onKeyDown(int keyCode, KeyEvent event)  {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        // do something on back.
    	CoreSFAndroid.flag_rota = 1;
    	CoreSFAndroid.getintance().abrirTelaListaCliente();
    	finish();
        return true;
    }

    return super.onKeyDown(keyCode, event);
}
} 