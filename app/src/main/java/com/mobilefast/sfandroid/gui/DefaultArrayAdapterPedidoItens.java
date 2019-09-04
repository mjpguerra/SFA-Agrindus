package com.mobilefast.sfandroid.gui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.frame.mobilefast.ControllerFrameWAR;
import com.frame.mobilefast.DefaultListModel;
import com.frame.mobilefast.MyLinearLayout;
import com.frame.mobilefast.MyListener;
import com.frame.mobilefast.MyListenerComRetornoDoNomeDoComponente;
import com.frame.mobilefast.Thema;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBDE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

public class DefaultArrayAdapterPedidoItens extends ArrayAdapter<DefaultListModel> {


    static String idO;
    private final Map CACHE = new HashMap();
    private final Map CACHE_LAYOUT = new HashMap();
    private List<DefaultListModel> items;
    private int index = -1;
    private Display display = null;
    private int w = 0;
    private int h = 0;
    private MyListener listener;
    private ArrayList<String> lpreco = null;
    private MyListenerComRetornoDoNomeDoComponente listener2;

    public DefaultArrayAdapterPedidoItens(Context context, int textViewResourceId, List<DefaultListModel> lista) {
        super(context, textViewResourceId, lista);
        this.items = lista;

        display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        w = display.getWidth();
        h = display.getHeight();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout v1 = null;//(ViewItens) convertView;
        index = position;

        v1 = new LinearLayout(getContext());
        v1.setGravity(Gravity.LEFT);
        v1.setOrientation(LinearLayout.VERTICAL);
        v1.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

        if (convertView == null) {
           initControles(v1, position);
        } else {
            v1 = (LinearLayout) convertView;
            v1.removeAllViews();
            initControles(v1, position);
        }
        return v1;
    }

    public void initControles(LinearLayout v1, int position){

        CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(position);

        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};
        ShapeDrawable shape = null;

        shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape.getPaint().setColor(Color.argb(20, 0, 27, 134));

        ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape1.getPaint().setColor(Color.argb(90, 10, 10, 10));

        ShapeDrawable shape2 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape2.getPaint().setColor(Color.argb(90, 10, 10, 10));

        ShapeDrawable shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape3.getPaint().setColor(Color.argb(90, 10, 10, 10));


        LinearLayout fundo = new LinearLayout(getContext());
        fundo.setGravity(Gravity.LEFT);
        fundo.setOrientation(LinearLayout.VERTICAL);
        fundo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        fundo.setBackgroundDrawable(shape);
        fundo.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

        LinearLayout g = new LinearLayout(getContext());
        g.setGravity(Gravity.LEFT);
        g.setOrientation(LinearLayout.VERTICAL);
        g.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        g.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

        LinearLayout fundoCTX = new LinearLayout(getContext());
        fundoCTX.setGravity(Gravity.LEFT);
        fundoCTX.setOrientation(LinearLayout.HORIZONTAL);
        fundoCTX.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        fundoCTX.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

        LinearLayout fundoCTX2 = new LinearLayout(getContext());
        fundoCTX2.setGravity(Gravity.LEFT);
        fundoCTX2.setOrientation(LinearLayout.HORIZONTAL);
        fundoCTX2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        fundoCTX2.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R, Thema.VAL_B);

        TextView nomeValor2 = new TextView(getContext());
        nomeValor2.setTextColor(Color.WHITE);
        nomeValor2.setText(CoreSFAndroid.di.getMetaDataTag() + " - " + CoreSFAndroid.di.getDescricao());
        nomeValor2.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor2.setTypeface(Typeface.DEFAULT_BOLD);
        nomeValor2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nomeValor2.setPadding(10, Thema.VAL_T, 10, Thema.VAL_B);
        nomeValor2.setBackgroundDrawable(shape3);
        fundoCTX2.addView(nomeValor2);
        g.addView(fundoCTX2);

        TextView nomeValor11 = new TextView(getContext());
        nomeValor11.setTextColor(Color.WHITE);
        nomeValor11.setText("Quantidade:");
        nomeValor11.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor11.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nomeValor11.setPadding(10, Thema.VAL_T, 10, Thema.VAL_B);
        fundoCTX.addView(nomeValor11);

        ShapeDrawable shape22 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape22.getPaint().setColor(Color.argb(90, 10, 10, 10));
        TextView nomeValor22 = new TextView(getContext());
        nomeValor22.setTextColor(Color.WHITE);
        nomeValor22.setText(String.valueOf(CoreSFAndroid.di.getQuantidade()));
        nomeValor22.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor22.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nomeValor22.setPadding(10, Thema.VAL_T, 10, Thema.VAL_B);
        nomeValor22.setBackgroundDrawable(shape22);
        fundoCTX.addView(nomeValor22);

        TextView nomeValor3 = new TextView(getContext());
        nomeValor3.setTextColor(Color.WHITE);
        nomeValor3.setText("Preço:");
        nomeValor3.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nomeValor3.setPadding(10, Thema.VAL_T, 10, Thema.VAL_B);
        fundoCTX.addView(nomeValor3);

        ShapeDrawable shape4 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape4.getPaint().setColor(Color.argb(90, 10, 10, 10));
        TextView nomeValor4 = new TextView(getContext());
        nomeValor4.setTextColor(Color.WHITE);
        nomeValor4.setText(CoreSFAndroid.di.getValor());
        nomeValor4.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor4.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nomeValor4.setPadding(10, Thema.VAL_T, 10, Thema.VAL_B);
        nomeValor4.setBackgroundDrawable(shape4);
        fundoCTX.addView(nomeValor4);


        TextView nomeValor61 = new TextView(getContext());
        nomeValor61.setTextColor(Color.WHITE);
        nomeValor61.setText("  ");
        nomeValor61.setTextSize(CoreSFAndroid.tamanho_fonte);
        nomeValor61.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        fundoCTX.addView(nomeValor61);
        g.addView(fundoCTX);

        LinearLayout localLinearLayout5 = new LinearLayout(getContext());
        localLinearLayout5.setGravity(Gravity.LEFT);
        localLinearLayout5.setOrientation(0);
        localLinearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        localLinearLayout5.setPadding(10, 5, 0, 0);

        LinearLayout localLinearLayout6 = new LinearLayout(getContext());
        localLinearLayout6.setGravity(Gravity.LEFT);
        localLinearLayout6.setOrientation(1);
        localLinearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        //localLinearLayout6.setPadding(2, 2, 2, 2);

        ShapeDrawable shape12 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape12.getPaint().setColor(Color.rgb(211, 211, 211));

        Rect mRect = new Rect(0, 0, 120, 120);
        GradientDrawable grad1 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.rgb(102, 102, 102), Color.rgb(239, 239, 239)});
        grad1.setCornerRadii(outerR);
        grad1.setBounds(mRect);


        Button localButton1 = new Button(getContext());
        localButton1.setTextColor(Color.argb(100, 0, 27, 134));
        localButton1.setBackgroundDrawable(grad1);
        localButton1.setTextSize(CoreSFAndroid.tamanho_fonte);
        localButton1.setGravity(Gravity.CENTER);
        localButton1.setTypeface(Typeface.DEFAULT_BOLD);
        localButton1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        localButton1.setText("Alterar");
        localButton1.setTag(CoreSFAndroid.di);
        localButton1.setOnClickListener(new OnClickListener() {
            public void onClick(View paramView) {
                idO = "";
                CoreSFAndroid.di = (DefaultListModel) paramView.getTag();
                idO = CoreSFAndroid.di.getNome();
                gerar_itensPedido();
            }
        });

        localLinearLayout6.addView(localButton1);
        localLinearLayout5.addView(localLinearLayout6);

        LinearLayout localLinearLayout7 = new LinearLayout(getContext());
        localLinearLayout7.setGravity(Gravity.LEFT);
        localLinearLayout7.setOrientation(1);
        localLinearLayout7.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        localLinearLayout7.setPadding(10, 0, 0, 0);


        GradientDrawable grad11 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.rgb(102, 102, 102), Color.rgb(239, 239, 239)});
        grad11.setCornerRadii(outerR);
        grad11.setBounds(mRect);

        Button localButton2 = new Button(getContext());
        localButton2.setTextColor(Color.argb(100, 0, 27, 134));
        localButton2.setBackgroundDrawable(grad11);
        //  localButton2.setPadding(15,15,15,15);
        localButton2.setTextSize(CoreSFAndroid.tamanho_fonte);
        localButton2.setGravity(Gravity.CENTER);
        localButton2.setTypeface(Typeface.DEFAULT_BOLD);
        localButton2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        localButton2.setText("Excluir");
        localButton2.setTag(CoreSFAndroid.di);
        localButton2.setOnClickListener(new OnClickListener() {
            public void onClick(View paramView) {

                CoreSFAndroid.di = (DefaultListModel) paramView.getTag();
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramView.getContext());
                localBuilder.setMessage("Deseja realmente remover o Item?").setCancelable(false).setIcon(17301543).setTitle("Atenção:").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //CoreSFAndroid.flag_grupo = 1;
                        String str = CoreSFAndroid.di.getNome();
                        for (int i = 0; i < CoreSFAndroid.lista_itensPedido.size(); i++) {
                            CoreSFAndroid.di = CoreSFAndroid.lista_itensPedido.get(i);
                            if (CoreSFAndroid.di.getNome().equals(str)) {
                                CoreSFAndroid.lista_itensPedido.remove(i);
                                CoreSFAndroid.mCarrinho.remove(str);
                            }
                        }
                        CoreSFAndroid.gerar_total_itens();
                        //CoreSFAndroid.PEDIDO_CORRENTE.setC5_condpag(null);
                        //GUIMovimentoPedido.btvalor.setText(String.valueOf(CoreSFAndroid.PEDIDO_CORRENTE.getC5_VALOR()));
                        GUIPedidoCarrinho.adapter.notifyDataSetChanged();
                    }

                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.cancel();
                    }
                });
                localBuilder.create().show();
            }
        });

        localLinearLayout7.addView(localButton2);
        localLinearLayout5.addView(localLinearLayout7);
        //fundoCTX.addView(localLinearLayout5);
        g.addView(localLinearLayout5);


        fundo.addView(g);

        v1.addView(fundo);
    }


    public void gerar_itensPedido() {
        // GUIMovimentoPedido.preco = "";
        double preco = Double.valueOf(CoreSFAndroid.di.getValor());

        LinearLayout var0 = new LinearLayout(getContext());
        var0.setGravity(3);
        var0.setOrientation(1);
        var0.setBackgroundColor(getContext().getResources().getColor(R.color.background));

        new LayoutParams(-2, -2);
        new LayoutParams(-1, -2);
        float[] var3 = new float[]{12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F};
        new RectF(6.0F, 6.0F, 6.0F, 6.0F);
        float[] var5 = new float[]{12.0F, 12.0F, 0.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F};

        ShapeDrawable var6 = new ShapeDrawable(new RoundRectShape(var3, (RectF) null, (float[]) null));
        var6.getPaint().setColor(Color.argb(20, 0, 27, 134));

        final Dialog var7 = new Dialog(getContext());
        var7.setTitle("Informe os Dados");
        var7.setCancelable(true);

        LinearLayout var8 = new LinearLayout(getContext());
        var8.setGravity(3);
        var8.setOrientation(1);
        var8.setPadding(10, 10, 10, 10);
        var8.setBackgroundDrawable(var6);

        TextView var9 = new TextView(getContext());
        var9.setText("Qtde Item: ");
        var9.setTextSize(CoreSFAndroid.tamanho_fonte);
        var9.setTextColor(-1);
        var8.addView(var9);

        final EditText txqtde = new EditText(getContext());
        txqtde.findFocus();
        txqtde.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txqtde.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        txqtde.setTextSize(CoreSFAndroid.tamanho_fonte);
        txqtde.setText(String.valueOf(CoreSFAndroid.di.getQuantidade()));
        int pos = txqtde.getText().length();
        txqtde.setSelection(pos);
        var8.addView(txqtde);

        TextView var25 = new TextView(getContext());
        var25.setText("Preço: ");
        var25.setTextSize(CoreSFAndroid.tamanho_fonte);
        var25.setTextColor(-1);
        var8.addView(var25);

        final EditText txpreco = new EditText(getContext());
        DecimalKeyListener myDecimalKeyListener = new DecimalKeyListener();
        txpreco.setKeyListener(myDecimalKeyListener);
        txpreco.setText("" + preco);
        if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
            txpreco.setEnabled(false);
        } else {
            txpreco.setEnabled(true);
        }
        var8.addView(txpreco);

        final TextView var255 = new TextView(getContext());
        var255.setText("-");
        var255.setTextColor(-1);
        var8.addView(var255);


        Button var12 = new Button(getContext());
        var12.setHeight(50);
        var12.setText("Salvar");
        var8.addView(var12);
        var12.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                String qtdeText = txqtde.getText().toString();

                for (int var3 = 0; var3 < CoreSFAndroid.lista_itensPedido.size(); ++var3) {
                    CoreSFAndroid.di = (DefaultListModel) CoreSFAndroid.lista_itensPedido.get(var3);
                    if (CoreSFAndroid.di.getNome().equals(idO)) {
                        if (!qtdeText.equals("") && !qtdeText.equals("0")) {
                            int var44 = Integer.valueOf(txqtde.getText().toString()).intValue();
                            CoreSFAndroid.di.setQuantidade(var44);
                            double digit = Double.valueOf(txpreco.getText().toString());

                            if (CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 315 || CoreSFAndroid.PEDIDO_CORRENTE.getReservado2() == 480) {
                                DescontoComissao(digit);
                                var7.dismiss();
                            } else {
                                double precoCli = Double.valueOf(CoreSFAndroid.di.getvalorCliente());
                                double precoVend = Double.valueOf(CoreSFAndroid.di.getValorOriginal());
                                double validar = 0.25 * precoCli;
                                double resultMax = validar + precoCli;
                                if (!(digit < precoVend)) {
                                    if (!(digit > resultMax)) {
                                        digit = digit - (digit * (CoreSFAndroid.desc_contratual / 1000));
                                        DescontoComissao(digit);
                                        var7.dismiss();
                                    } else {
                                        Toast.makeText(var1.getContext(), "Preço maximo fora do permitido: " + CoreSFAndroid.formataMoeda(resultMax), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(var1.getContext(), "Preço minimo fora do permitido: " + CoreSFAndroid.formataMoeda(precoVend), Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                    }
                }
            }
        });
        var0.addView(var8);
        var7.setContentView(var0);
        var7.show();
    }


    public void DescontoComissao(double digit) {
        CoreSFAndroid.di.setcontratual(digit * (CoreSFAndroid.desc_contratual / 1000));
        try {
            MBDE de = LocatorDAO.getInstancia().getMbde().findSelect(CoreSFAndroid.cod_cliente, CoreSFAndroid.di.getMetaDataTag());
            if (!(de == null)) {
                double desc = de.getPORC_DESC() / 100;
                CoreSFAndroid.di.setdescTrab(desc * digit);
                double preco_desc = digit - (desc * digit);
                CoreSFAndroid.di.setValor(CoreSFAndroid.formataMoeda(preco_desc));
                CoreSFAndroid.di.setTotalvalor(digit * CoreSFAndroid.di.getQuantidade());
                double pre = digit - (digit * (CoreSFAndroid.desc_condicao / 100));
                CoreSFAndroid.di.setComissao((pre - digit));
            } else {
                CoreSFAndroid.di.setValor(CoreSFAndroid.formataMoeda(digit));
                double prctotal = Double.valueOf(digit);
                CoreSFAndroid.di.setTotalvalor(prctotal * CoreSFAndroid.di.getQuantidade());
                double pre = digit - (digit * (CoreSFAndroid.desc_condicao / 100));
                CoreSFAndroid.di.setComissao((pre - digit));
            }
        } catch (SFAndroidException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CoreSFAndroid.gerar_total_itens();
        GUIPedidoCarrinho.adapter.notifyDataSetChanged();
    }


    public String toString() {
        return items.get(index).getNome();
    }

    public MyLinearLayout getTextView(String var1, String var2, final String var3, final List var4, int var5) {
        final CharSequence[] var6 = new CharSequence[var4.size()];
        int var7 = 0;
        int var10000;
        if (var5 == 0) {
            var10000 = ControllerFrameWAR.getInstancia().getW() / 2;
        } else if (var5 == 1) {
            ControllerFrameWAR.getInstancia().getW();
        } else if (var5 == 2) {
            var10000 = -80 + ControllerFrameWAR.getInstancia().getW();
        } else if (var5 == 3) {
            var10000 = ControllerFrameWAR.getInstancia().getW() / 3;
        }

        int var19;
        for (Iterator<?> var9 = var4.iterator(); var9.hasNext(); var7 = var19) {
            DefaultListModel var18 = (DefaultListModel) var9.next();
            var19 = var7 + 1;
            var6[var7] = var18.getNome();
        }

        MyLinearLayout var10 = new MyLinearLayout(getContext(), var1);
        var10.setGravity(3);
        var10.setOrientation(0);
        var10.setLayoutParams(new LayoutParams(-1, -2));
        var10.setPadding(2, 2, 2, 5);

        MyLinearLayout var11 = new MyLinearLayout(getContext(), var1);
        var11.setPadding(0, 0, 0, 0);
        var11.setGravity(3);
        var11.setOrientation(0);
        var11.setLayoutParams(new LayoutParams(-1, -1));

        MyLinearLayout var12 = new MyLinearLayout(getContext(), var1);
        var12.setPadding(5, 8, 0, 5);
        var12.setOrientation(1);
        var12.setLayoutParams(new LayoutParams(-1, -2));

        TextView var13 = new TextView(getContext());
        //var13.setTextSize((float)Thema.FONTE_TITULO);
        var13.setText(var3);
        var13.setTextColor(Color.rgb(192, 255, 62));
        var13.setPadding(0, 0, 0, 0);
        var12.addView(var13);

        float[] var14 = new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
        ShapeDrawable var15 = new ShapeDrawable(new RoundRectShape(var14, (RectF) null, (float[]) null));
        var15.getPaint().setColor(Color.rgb(200, 200, 200));

        final Button var16 = new Button(getContext());
        var16.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView var1, int var2, KeyEvent var3x) {
                if (listener2 != null) {
                    listener2.onEditorAction(var1, var2, var3x, var3);
                }

                return false;
            }
        });
        var16.setText(var2);
        //var16.setTextSize((float)Thema.FONTE_CONTEXT);
        //var16.setHeight(Thema.SIZE_CAMPO_H);
        //var16.setTextColor(-16777216);
        var16.setBackgroundDrawable(var15);
        var16.setPadding(0, 5, 0, 5);
        var16.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                //(new MyButton(var1.getContext())).clickDesclik();
                Builder var2 = new Builder(var1.getContext());
                var2.setTitle("Selecionar Item");
                var2.setItems(var6, new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface var1, int var2) {
                        DefaultListModel var3x = (DefaultListModel) var4.get(var2);
                        var16.setText(var3x.getNome());
                        if (listener != null) {
                            listener.onClick(var16, var2);
                        }

                        if (listener2 != null) {
                            listener2.onClick(var16, var2, var3);
                            listener2.refresh(var3);
                            listener2.onClick(var16, var3);
                        }

                        var1.cancel();
                        var1.dismiss();
                    }
                });
                var2.create().show();
            }
        });
        var12.addView(var16);
        var11.addView(var12);
        var10.addView(var11);
        CACHE_LAYOUT.put(var1, var10);
        return var10;
    }

    private Map getCACHE() {
        return this.CACHE;
    }

    private Map getCACHE_LAYOUT() {
        return this.CACHE_LAYOUT;
    }

    public class DecimalKeyListener extends DigitsKeyListener {
        private final char[] acceptedCharacters =
                new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};


        @Override
        protected char[] getAcceptedChars() {
            return acceptedCharacters;
        }

        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
        }

    }
}