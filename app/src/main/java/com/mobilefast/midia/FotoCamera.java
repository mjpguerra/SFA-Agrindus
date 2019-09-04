package com.mobilefast.midia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;

import java.io.File;
import java.util.Date;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;

public class FotoCamera extends Activity {

    static File sdcard = Environment.getExternalStorageDirectory();
    public final static File SDDIRETORIO = new File(sdcard.getAbsolutePath() + "/SFAndroid/Camera");
    private Uri imageUri;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControllerSFAndroid.getInstancia().setContext(this);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (!SDDIRETORIO.exists()) {
            SDDIRETORIO.mkdirs();
        }
        //final String nome = CoreEntregaExpress.OCORRENCIA_CORRENTE.getIdObjeto()+"_"+new Date().getTime();
        String url = SDDIRETORIO.getAbsolutePath() + "/" + CoreSFAndroid.cod_cliente + "_" + new Date().getTime() + ".png";
        File photo = new File(url);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, 0);
        setContentView(sfa.mobilefast.agrindus.letti.R.layout.main);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ControllerSFAndroid.getInstancia().showMensagemSimples("Imagem salva com sucesso.");
        CoreSFAndroid.controler_consumo = 1;
        //CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Deseja realmente sair da camera?")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ?cone de alerta
                    .setTitle("Aten??o:") //t?tulo do caixa de di?logo

                    //Evento disparado se clicar no bot?o Sim
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            CoreSFAndroid.controler_consumo = 1;
                            //CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
                            finish();
                        }
                    })

                    //Event disparado se clicar no bot?o N?o
                    .setNegativeButton("N?o", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel(); //Cancela a caixa de di?logo e volta a tela anterior
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show(); //Chama a caixa de di?logo
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}