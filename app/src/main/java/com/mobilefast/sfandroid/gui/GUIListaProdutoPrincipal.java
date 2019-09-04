package com.mobilefast.sfandroid.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBPR;
import com.mobilefast.sfandroid.model.MBPRDAO;

import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class GUIListaProdutoPrincipal extends Activity {

    public static String COD_CONDPAGTO = "CODIGO";
    public static String DESCR_CONDPAGTO = "DESCRICAO";
    public static String PESQ_PROD = "PESQUISAR PRODUTO";
    static EditText pesq;
    int total_prod;
    int total_prodpesq;
    int flag_item = 0;
    private android.widget.ListView list = null;
    private ArrayAdapterProdutoPrincipal adapter = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ControllerSFAndroid.getInstancia().setContext(this);
           // requestWindowFeature(Window.FEATURE_NO_TITLE);
           // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.setTitle("Produtos");
            //salvar();

            LinearLayout l = new LinearLayout(this);
            l.setGravity(Gravity.CENTER_HORIZONTAL);
            l.setOrientation(LinearLayout.VERTICAL);
            l.setBackgroundResource(R.drawable.background2);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
            l.setLayoutParams(p);


            LinearLayout ll = new LinearLayout(this);
            ll.setGravity(Gravity.LEFT);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            final Button btPesq = new Button(this);
            btPesq.setText("Pesq.");
            btPesq.setTextSize(CoreSFAndroid.tamanho_fonte);
            btPesq.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    CoreSFAndroid.lista_prod.clear();
                    CoreSFAndroid.mProdutos.clear();
                    MBPRDAO.getInstancia().offset = 0;
                    MBPRDAO.nome = pesq.getText().toString();
                    CoreSFAndroid.getintance().addProdList();
                    try {
                        if (!MBPRDAO.nome.equals("")) {
                            List<MBPR> Produto = LocatorDAO.getInstancia().getMbpr().findSelectpesq();
                            total_prodpesq = Produto.size();
                        } else {
                            flag_item = 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            });
            ll.addView(btPesq);

            pesq = new EditText(this);
            pesq.setText("");
            pesq.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            ll.addView(pesq);
            l.addView(ll);

            adapter = new ArrayAdapterProdutoPrincipal(this, android.R.layout.simple_list_item_1, CoreSFAndroid.lista_prod);
            list = new ListView(this);
            list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            list.setAdapter(adapter);
            try {
                List<MBPR> Produto = LocatorDAO.getInstancia().getMbpr().findAll();
                total_prod = Produto.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // TODO Auto-generated method stub
                    //System.out.println(1);
                }

                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {
                    // TODO Auto-generated method stub
                    if (totalItemCount > 0) {
                        int lastInScreen = firstVisibleItem + visibleItemCount;
                        if (lastInScreen == totalItemCount) {
                            if (flag_item == 0) {
                                if (total_prod == totalItemCount) {
                                    flag_item++;
                                }
                                if (total_prodpesq == totalItemCount) {
                                    flag_item++;
                                }

                                CoreSFAndroid.getintance().addProdList();
                                MBPRDAO.getInstancia().somarOffset(totalItemCount);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            });
            l.addView(list);
            this.setContentView(l);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            CoreSFAndroid.flag_grupo = 1;
            CoreSFAndroid.getintance().abrirTelaPrincipal();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
