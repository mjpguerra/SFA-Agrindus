package com.mobilefast.sfandroid.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilefast.sfandroid.model.LocatorDAO;
import com.mobilefast.sfandroid.model.MBCDCL;

import java.text.SimpleDateFormat;
import java.util.Date;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.ValidaCPF;
import sfa.mobilefast.agrindus.letti.R;

public class GUICadastroCliente extends Activity {

    String string[] = {"SP", "RJ", "MG", "BH", "BA", "SC", "ES"};
    EditText tx_bairro;
    EditText tx_cidade;
    EditText tx_cnpj;
    EditText tx_email;
    EditText tx_end;
    EditText tx_nome_cli;
    EditText tx_nome_fan;
    EditText tx_tele;
    EditText tx_cep;
    EditText tx_fax;
    EditText tx_inscr_estad;
    EditText tx_inscr_munic;
    EditText tx_rg;
    EditText tx_estado;
    EditText tx_contato;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControllerSFAndroid.getInstancia().setContext(this);


        this.setTitle("Cadastro Cliente");

        LinearLayout l = new LinearLayout(this);
        l.setGravity(Gravity.CENTER);
        l.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        l.setLayoutParams(p);

        TextView var4 = new TextView(this);
        var4.setText(" Razão Social");
        var4.setTextSize(CoreSFAndroid.tamanho_fonte);
        var4.setTextColor(-1);
        l.addView(var4);

        this.tx_nome_cli = new EditText(this);
        this.tx_nome_cli.setText("");
        this.tx_nome_cli.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_nome_cli.setBackgroundColor(-3355444);
        this.tx_nome_cli.setTextColor(-16777216);
        l.addView(this.tx_nome_cli);

        TextView var41 = new TextView(this);
        var41.setText(" Nome Fantasia");
        var41.setTextSize(CoreSFAndroid.tamanho_fonte);
        var41.setTextColor(-1);
        l.addView(var41);

        this.tx_nome_fan = new EditText(this);
        this.tx_nome_fan.setText("");
        this.tx_nome_fan.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_nome_fan.setBackgroundColor(-3355444);
        this.tx_nome_fan.setTextColor(-16777216);
        l.addView(this.tx_nome_fan);

        TextView var6 = new TextView(this);
        var6.setText(" Endereço");
        var6.setTextSize(CoreSFAndroid.tamanho_fonte);
        var6.setTextColor(-1);
        l.addView(var6);

        this.tx_end = new EditText(this);
        this.tx_end.setText("");
        this.tx_end.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_end.setBackgroundColor(-3355444);
        this.tx_end.setTextColor(-16777216);
        l.addView(this.tx_end);

        TextView var7 = new TextView(this);
        var7.setText(" Bairro");
        var7.setTextSize(CoreSFAndroid.tamanho_fonte);
        var7.setTextColor(-1);
        l.addView(var7);

        this.tx_bairro = new EditText(this);
        this.tx_bairro.setText("");
        this.tx_bairro.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_bairro.setBackgroundColor(-3355444);
        this.tx_bairro.setTextColor(-16777216);
        l.addView(this.tx_bairro);

        TextView var811 = new TextView(this);
        var811.setText(" Cidade");
        var811.setTextSize(CoreSFAndroid.tamanho_fonte);
        var811.setTextColor(-1);
        l.addView(var811);

        this.tx_cidade = new EditText(this);
        this.tx_cidade.setText("");
        this.tx_cidade.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cidade.setBackgroundColor(-3355444);
        this.tx_cidade.setTextColor(-16777216);
        l.addView(this.tx_cidade);

        TextView var8 = new TextView(this);
        var8.setText(" CEP");
        var8.setTextSize(CoreSFAndroid.tamanho_fonte);
        var8.setTextColor(-1);
        l.addView(var8);

        this.tx_cep = new EditText(this);
        this.tx_cep.setText("");
        this.tx_cep.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cep.setBackgroundColor(-3355444);
        this.tx_cep.setTextColor(-16777216);
        l.addView(this.tx_cep);

        TextView var81 = new TextView(this);
        var81.setText(" Estado");
        var81.setTextSize(CoreSFAndroid.tamanho_fonte);
        var81.setTextColor(-1);
        l.addView(var81);

        this.tx_estado = new EditText(this);
        this.tx_estado.setText("");
        this.tx_estado.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_estado.setBackgroundColor(-3355444);
        this.tx_estado.setTextColor(-16777216);
        l.addView(this.tx_estado);

        TextView var9 = new TextView(this);
        var9.setText(" CPF/CNPJ");
        var9.setTextSize(CoreSFAndroid.tamanho_fonte);
        var9.setTextColor(-1);
        l.addView(var9);

        this.tx_cnpj = new EditText(this);
        this.tx_cnpj.setText("");
        this.tx_cnpj.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_cnpj.setBackgroundColor(-3355444);
        this.tx_cnpj.setTextColor(-16777216);
        l.addView(this.tx_cnpj);

        TextView var91 = new TextView(this);
        var91.setText(" Inscrição Estadual");
        var91.setTextSize(CoreSFAndroid.tamanho_fonte);
        var91.setTextColor(-1);
        l.addView(var91);

        this.tx_inscr_estad = new EditText(this);
        this.tx_inscr_estad.setText("");
        this.tx_inscr_estad.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_inscr_estad.setBackgroundColor(-3355444);
        this.tx_inscr_estad.setTextColor(-16777216);
        l.addView(this.tx_inscr_estad);

        TextView var911 = new TextView(this);
        var911.setText(" Inscrição Municipal");
        var911.setTextSize(CoreSFAndroid.tamanho_fonte);
        var911.setTextColor(-1);
        l.addView(var911);

        this.tx_inscr_munic = new EditText(this);
        this.tx_inscr_munic.setText("");
        this.tx_inscr_munic.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_inscr_munic.setBackgroundColor(-3355444);
        this.tx_inscr_munic.setTextColor(-16777216);
        l.addView(this.tx_inscr_munic);


        TextView var10 = new TextView(this);
        var10.setText(" RG");
        var10.setTextSize(CoreSFAndroid.tamanho_fonte);
        var10.setTextColor(-1);
        l.addView(var10);

        this.tx_rg = new EditText(this);
        this.tx_rg.setText("");
        this.tx_rg.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_rg.setBackgroundColor(-3355444);
        this.tx_rg.setTextColor(-16777216);
        l.addView(this.tx_rg);

        TextView var101 = new TextView(this);
        var101.setText(" Contato");
        var101.setTextSize(CoreSFAndroid.tamanho_fonte);
        var101.setTextColor(-1);
        l.addView(var101);

        this.tx_contato = new EditText(this);
        this.tx_contato.setText("");
        this.tx_contato.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_contato.setBackgroundColor(-3355444);
        this.tx_contato.setTextColor(-16777216);
        l.addView(this.tx_contato);

        TextView var1011 = new TextView(this);
        var1011.setText(" Email");
        var1011.setTextSize(CoreSFAndroid.tamanho_fonte);
        var1011.setTextColor(-1);
        l.addView(var1011);

        this.tx_email = new EditText(this);
        this.tx_email.setText("");
        this.tx_email.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_email.setBackgroundColor(-3355444);
        this.tx_email.setTextColor(-16777216);
        l.addView(this.tx_email);

        TextView var11 = new TextView(this);
        var11.setText(" Telefone");
        var11.setTextSize(CoreSFAndroid.tamanho_fonte);
        var11.setTextColor(-1);
        l.addView(var11);

        this.tx_tele = new EditText(this);
        this.tx_tele.setText("");
        this.tx_tele.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.tx_tele.setBackgroundColor(-3355444);
        this.tx_tele.setTextColor(-16777216);
        l.addView(this.tx_tele);


        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        ShapeDrawable shape3 = null;
        shape3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape3.getPaint().setColor(Color.argb(100, 89, 148, 103));
        Rect mRect = new Rect(0, 0, 120, 120);

        GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.rgb(0, 155, 52), Color.rgb(0, 105, 35)});
        grad.setCornerRadii(outerR);
        grad.setBounds(mRect);
        //grad.setGradientType(1);

        LinearLayout lpedido = new LinearLayout(this);
        lpedido.setGravity(Gravity.CENTER);
        lpedido.setOrientation(1);
        lpedido.setPadding(10, 10, 10, 10);
        lpedido.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));

        Button btpedido = new Button(this);
        btpedido.setText("Gravar Cliente");
        btpedido.setTextColor(Color.WHITE);
        btpedido.setTextSize(CoreSFAndroid.tamanho_fonte);
        btpedido.setBackgroundDrawable(grad);
        btpedido.setPadding(10, 10, 10, 10);
        btpedido.setGravity(Gravity.CENTER);
        btpedido.setTypeface(Typeface.DEFAULT_BOLD);
        btpedido.setTag(CoreSFAndroid.dcl);
        btpedido.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btpedido.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                salvar();

            }

        });
        lpedido.addView(btpedido);
        l.addView(lpedido);
        ScrollView sv = new ScrollView(this);
        sv.setBackgroundResource(R.drawable.background2);
        sv.addView(l);
        setContentView(sv);
    }


    public void salvar() {
        try {
            MBCDCL obCliente = null;
            if (obCliente == null) {
                obCliente = new MBCDCL();
            }
            Date dd = new Date();
            SimpleDateFormat codigoped = new SimpleDateFormat("yyMMddHHmm");
            CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
            CoreSFAndroid.codigo_vendedor = CoreSFAndroid.VENDEDOR_CORRENTE.getVendedor();
            String num = String.valueOf(codigoped.format(dd) + CoreSFAndroid.codigo_vendedor);

            if (ValidaCPF.isCPF(tx_cnpj.getText().toString()) == true) {
                obCliente.setCliente(num);
                obCliente.setRaz_social(tx_nome_cli.getText().toString());
                obCliente.setNom_fantasia(tx_nome_fan.getText().toString());
                obCliente.setEndereco(tx_end.getText().toString());
                obCliente.setBairro(tx_bairro.getText().toString());
                obCliente.setCidade(tx_cidade.getText().toString());
                obCliente.setCep(tx_cep.getText().toString());
                obCliente.setEstado(tx_estado.getText().toString());
                obCliente.setCgc_cpf(tx_cnpj.getText().toString());
                obCliente.setInscr_estad(tx_inscr_estad.getText().toString());
                obCliente.setInscr_munic(tx_inscr_munic.getText().toString());
                obCliente.setRg(tx_rg.getText().toString());
                obCliente.setContato(tx_contato.getText().toString());
                obCliente.setEmail(tx_email.getText().toString());
                obCliente.setTelefone(tx_tele.getText().toString());
                obCliente.setVendedor(CoreSFAndroid.codigo_vendedor);
                obCliente.setD_I_R_T_Y_("T");
                obCliente = LocatorDAO.getInstancia().getMbcdcl().salvar(obCliente);
                Toast.makeText(getBaseContext(), "Cliente cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
                CoreSFAndroid.getintance().abrirTelaPrincipal();
                finish();
            } else {
                Toast.makeText(getBaseContext(), "CPF Invalido!!", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair do Cadastro de Cliente?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
                .setTitle("Atenção:") //título do caixa de diálogo

                //Evento disparado se clicar no botão Sim
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CoreSFAndroid.getintance().abrirTelaPrincipal();
                        finish();
                    }
                })

                //Event disparado se clicar no botão Não
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel(); //Cancela a caixa de diálogo e volta a tela anterior
                    }
                });
        AlertDialog alert = builder.create();
        alert.show(); //Chama a caixa de diálogo
    }
}
