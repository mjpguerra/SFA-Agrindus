package com.mobilefast.midia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mobilefast.FancyCoverFlow.FancyCoverFlow;
import com.mobilefast.FancyCoverFlow.FancyCoverFlowSampleAdapter;

@SuppressLint("SdCardPath")
public class GaleriaImagem extends Activity {

	private ImageView selectedImageView;
	private ImageView leftArrowImageView;
	private ImageView rightArrowImageView;
	private Gallery gallery;
	private int selectedImagePosition = 0;
	private List<String> tFileList;
	private GaleriaImagemAdapter galImageAdapter;
	static File sdcard = Environment.getExternalStorageDirectory();
	public final static File SDDIRETORIO	=  new File(sdcard.getAbsolutePath()+"/SFAndroid/Camera");	
	public static ActivityManager am;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ControllerSFAndroid.getInstancia().setContext(this);
		  setTitle("Galeria de Fotos do Cliente");
		
		ReadSDCard();
		
		LinearLayout localLinearLayout = new LinearLayout(this);
		//localLinearLayout.setGravity(Gravity.CENTER);
		localLinearLayout.setOrientation(LinearLayout.VERTICAL);
		localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		localLinearLayout.setBackgroundResource(R.drawable.background);
		
		FancyCoverFlow fancyCoverFlow = new FancyCoverFlow(this);
        fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter(this, tFileList));
        //fancyCoverFlow.setSpacing(50);
        fancyCoverFlow.setScaleDownGravity(0.2f);
        fancyCoverFlow.setMaxRotation(45);
        fancyCoverFlow.setUnselectedAlpha(0.3f);
        fancyCoverFlow.setUnselectedSaturation(0.0f);
        fancyCoverFlow.setUnselectedScale(0.4f);
        fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        fancyCoverFlow.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		 am =  ( ActivityManager ) getSystemService ( Context . ACTIVITY_SERVICE ); 
		 int memClassBytes = GaleriaImagem.am.getMemoryClass();
        localLinearLayout.addView(fancyCoverFlow);
        setContentView(localLinearLayout);
	}

	private void ReadSDCard() {

		tFileList = new ArrayList<String>();
		if(!SDDIRETORIO.exists()){
			SDDIRETORIO.mkdirs();
		}
		File f = new File(SDDIRETORIO.getAbsolutePath());
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			tFileList.add(file.getPath());
		}
	}
	
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);     
		    builder.setMessage("Deseja realmente sair da galeria?")            
		    .setCancelable(false)        
		    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
		    .setTitle("Atenção:") //título do caixa de diálogo
		            
		    //Evento disparado se clicar no botão Sim
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
		        public void onClick(DialogInterface dialog, int id) {	    
		        	CoreSFAndroid.controler_consumo = 1;
		        	//CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
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
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    
}