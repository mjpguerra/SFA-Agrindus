package com.mobilefast.sfandroid.gui;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mobilefast.sfandroid.model.MBPRDAO;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class GUIProdutosPedido extends Activity {
    public static String COD_CONDPAGTO = "CODIGO";
    public static String DESCR_CONDPAGTO = "DESCRICAO";
    public static String PESQ_PROD = "PESQUISAR PRODUTO";
    public static Button btvalor = null;
    static DefaultArrayAdapterItens adapter = null;
    static EditText pesq;
    int total_prod;
    int total_prodpesq;
    int flag_item = 0;
    private android.widget.ListView list = null;
    // A quantidade mínima de itens para ter abaixo da sua posição de rolagem atual
    // Antes de carregar mais.
    private int visibleThreshold = 30;
    // O atual índice de deslocamento de dados que você tenha carregado
    private int currentPage = 0;
    // O número total de itens no conjunto de dados após a última carga
    private int previousTotalItemCount = 0;
    // True se ainda estamos à espera para o último conjunto de dados para carregar.
    private boolean loading = true;
    // Define o índice de página inicial
    private int startingPageIndex = 0;
    OnScrollListener onScroll_List = new OnScrollListener() {
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub
            if (totalItemCount < previousTotalItemCount) {
                currentPage = startingPageIndex;
                previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    loading = true;
                }
            }
            // Se ele ainda está carregando, vamos verificar para ver se a contagem de conjunto de dados tem
            // Mudou, se assim podemos concluir que terminar de carregar e atualizar a página atual
            // Número e contagem de itens total.
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
                currentPage++;
            }
            // Se ele não está a carregar, vamos verificar para ver se temos violado
            // O visibleThreshold e precisa recarregar mais dados.
            // Se nós precisamos de recarregar mais alguns dados, nós executamos onLoadMore para buscar os dados.
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                onLoadMore(currentPage + 1, totalItemCount);
                loading = true;
            }
        }

        // Defines the process for actually loading more data based on page
        public void onLoadMore(int page, int totalItemsCount) {
            MBPRDAO.getInstancia().somarOffset();
            CoreSFAndroid.getintance().addProdList();
            adapter.notifyDataSetChanged();
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ControllerSFAndroid.getInstancia().setContext(this);

            this.setTitle("Pedido");
            //salvar();

            LinearLayout l = new LinearLayout(this);
            l.setGravity(Gravity.LEFT);
            l.setOrientation(LinearLayout.VERTICAL);
            /// l.requestFocus();
            l.setBackgroundResource(R.drawable.background2);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
            l.setLayoutParams(p);

            btvalor = new Button(this);
            //btvalor.setTextColor(Color.rgb(192, 255, 62));
            //btvalor.setTypeface(Typeface.DEFAULT_BOLD);
            btvalor.setTextSize(CoreSFAndroid.tamanho_fonte);
            btvalor.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CoreSFAndroid.lista_prod.clear();
                    CoreSFAndroid.mProdutos.clear();
                    MBPRDAO.getInstancia().offset = 0;
                    MBPRDAO.nome = "";
                    pesq.setText("");
                    CoreSFAndroid.getintance().addProdList();
                    adapter.notifyDataSetChanged();
                }
            });
            CoreSFAndroid.gerar_total_itens();
            l.addView(btvalor);

            float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
            ShapeDrawable shape3 = null;
            shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            shape3.getPaint().setColor(Color.argb(100, 89, 148, 103));
            Rect mRect = new Rect(0, 0, 120, 120);


            GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.rgb(102, 102, 102), Color.rgb(239, 239, 239)});
            //grad.setShape(GradientDrawable.);
            grad.setCornerRadii(outerR);
            grad.setBounds(mRect);
            //grad.setGradientType(1);

            LinearLayout lpedido = new LinearLayout(this);
            lpedido.setGravity(Gravity.LEFT);
            lpedido.setOrientation(1);
            lpedido.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));

            Button btGrupo = new Button(this);
            btGrupo.setText("Grupo de Produtos");
            btGrupo.setTextColor(Color.rgb(0, 27, 134));
            btGrupo.setBackgroundDrawable(grad);
            btGrupo.setTextSize(CoreSFAndroid.tamanho_fonte);
            btGrupo.setGravity(Gravity.CENTER);
            btGrupo.setTypeface(Typeface.DEFAULT_BOLD);
            //btGrupo.setTag(CoreSFAndroid.dcl);
            btGrupo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btGrupo.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    CoreSFAndroid.getintance().abrirTelaGrupoProduto();
                    finish();
                }

            });
            //	lpedido.addView(btGrupo);
            //    l.addView(lpedido);

            LinearLayout ll = new LinearLayout(this);
            ll.setGravity(Gravity.LEFT);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            final Button btPesq = new Button(this);
            btPesq.setText("Pesq.");
            btPesq.setTextSize(CoreSFAndroid.tamanho_fonte);
            btPesq.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    visibleThreshold = 30;
                    currentPage = 0;
                    previousTotalItemCount = 0;
                    loading = true;
                    startingPageIndex = 0;
                    CoreSFAndroid.lista_prod.clear();
                    CoreSFAndroid.mProdutos.clear();
                    MBPRDAO.getInstancia().offset = 0;
                    MBPRDAO.nome = pesq.getText().toString();
                    CoreSFAndroid.getintance().addProdList();
                    adapter.notifyDataSetChanged();
                }
            });
            ll.addView(btPesq);

            pesq = new EditText(this);
            pesq.setText("");
            pesq.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            ll.addView(pesq);
            l.addView(ll);

            adapter = new DefaultArrayAdapterItens(this, android.R.layout.simple_list_item_1, CoreSFAndroid.lista_prod);
            list = new ListView(this);
            list.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            list.setAdapter(adapter);
            list.setTextFilterEnabled(true);
            list.setOnScrollListener(onScroll_List);
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
            CoreSFAndroid.getintance().abrirTelaPrincipalPedido();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
