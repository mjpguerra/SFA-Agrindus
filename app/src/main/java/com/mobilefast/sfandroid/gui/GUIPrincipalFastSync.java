package com.mobilefast.sfandroid.gui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fastsync.client.FSNativeLib;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.FastSync;
import com.mobilefast.sfandroid.model.LocatorDAO;

import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;


@SuppressLint({"ShowToast", "ShowToast"})
public class GUIPrincipalFastSync extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Sincronismo");
        ControllerSFAndroid.getInstancia().setContext(this);

        LinearLayout l = new LinearLayout(this);
        l.setGravity(Gravity.CENTER);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundResource(R.drawable.background);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        l.setLayoutParams(p);

        float[] outerR = new float[]{12, 12, 12, 12, 12, 12, 12, 12};
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[]{12, 12, 0, 0, 12, 12, 0, 0};

        ShapeDrawable shape1 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        shape1.getPaint().setColor(Color.argb(100, 10, 10, 10));


        LinearLayout ll = new LinearLayout(this);
        ll.setGravity(Gravity.CENTER);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(pp);
        ll.setBackgroundDrawable(shape1);

        final Button btfastsync = new Button(this);
        btfastsync.setBackgroundResource(R.drawable.sync);
        btfastsync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(GUIPrincipalFastSync.this,
                        "Por Favor, Espere...", "Sincronizando...");
                new Thread() {
                    public void run() {
                        try {
                            Looper.prepare();
                            while (true) {
                                Thread.sleep(1000);
                                Sincronizar();
                                Looper.loop();
                            }
                        } catch (Exception e) {
                            Log.e("tag", e.getMessage());
                        }
                        progressDialog.dismiss();
                    }
                }.start();
            }
        });
        ll.addView(btfastsync);
        l.addView(ll);
        setContentView(l);
    }


    public void Sincronizar() {
        FSNativeLib fs;
        List<FastSync> Lsync = null;
        int ret = -1;
        try {
            Lsync = LocatorDAO.getInstancia().getFastSync().findAll();
            for (Iterator<FastSync> iterator = Lsync.iterator(); iterator.hasNext(); ) {
                FastSync obFastSync = (FastSync) iterator.next();
                fs = new FSNativeLib();
                ret = fs.StartSync(obFastSync.getIp(), obFastSync.getPort(),
                        obFastSync.getUser(), obFastSync.getSerial(),
                        obFastSync.getTout(), obFastSync.getGroup(),
                        obFastSync.getDBPath(), obFastSync.getDBName());
            }

            msgSincronismo(ret);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "Configuração SYNC");
        menu.add(0, 2, 0, "Sair SYNC");
        menu.add(0, 3, 0, "LOG SYNC");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                try {
                    CoreSFAndroid.FASTSYNC_CORRENTE = LocatorDAO.getInstancia().getFastSync().findById(1);
                } catch (SFAndroidException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                CoreSFAndroid.getintance().abrirTelaFastSync();
                finish();
                return true;
            case 2:
                CoreSFAndroid.getintance().abrirTelaLogin();
                finish();
                return true;
            case 3:
                CoreSFAndroid.getintance().abrirTelaMsglog();
                finish();
                return true;
        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            CoreSFAndroid.getintance().abrirTelaPrincipal();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void msgSincronismo(int ret) {

        if (ret == 1108) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Erro Conectando ao Servidor, Tente Novamente!")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone
                    // de
                    // alerta
                    .setTitle("Atenção") // título do caixa de diálogo
                    // Evento disparado se clicar no botão Sim
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                                    finish();
                                    // TODO Auto-generated method stub
                                }

                            });
            AlertDialog alert = builder.create();
            alert.show(); // Chama a caixa de diálogo
            //new AlertDialog.Builder(this).setMessage("Erro Conectando ao Servidor").show();
        } else if (ret == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Queda de conecção, Tente conectar novamente!!")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone
                    // de
                    // alerta
                    .setTitle("Atenção") // título do caixa de diálogo
                    // Evento disparado se clicar no botão Sim
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                                    finish();
                                    // TODO Auto-generated method stub
                                }

                            });
            AlertDialog alert = builder.create();
            alert.show(); // Chama a caixa de diálogo
            //new AlertDialog.Builder(this).setMessage("Sincronismo realizado com Sucesso!").show();

        } else if (ret == 1117 || ret == 1110) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Queda de conecção, Tente conectar novamente!!")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone
                    // de
                    // alerta
                    .setTitle("Atenção") // título do caixa de diálogo
                    // Evento disparado se clicar no botão Sim
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                                    finish();
                                    // TODO Auto-generated method stub
                                }

                            });
            AlertDialog alert = builder.create();
            alert.show(); // Chama a caixa de diálogo
            //new AlertDialog.Builder(this).setMessage("Sincronismo realizado com Sucesso!").show();

        } else if (ret == 1111) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Usuario não cadastrado...")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone
                    // de
                    // alerta
                    .setTitle("Atenção") // título do caixa de diálogo
                    // Evento disparado se clicar no botão Sim
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                                    finish();
                                    // TODO Auto-generated method stub
                                }

                            });
            AlertDialog alert = builder.create();
            alert.show(); // Chama a caixa de diálogo
            //new AlertDialog.Builder(this).setMessage("Sincronismo realizado com Sucesso!").show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Sincronismo realizado com Sucesso...")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone
                    // de
                    // alerta
                    .setTitle("Atenção") // título do caixa de diálogo
                    // Evento disparado se clicar no botão Sim
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {

                                    CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                                    finish();
                                    // TODO Auto-generated method stub
                                }

                            });
            AlertDialog alert = builder.create();
            alert.show(); // Chama a caixa de diálogo
            //new AlertDialog.Builder(this).setMessage("Sincronismo realizado com Sucesso!").show();

        }

    }
}