package com.mobilefast.midia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.frame.mobilefast.Thema;

public class GridImagens extends Activity {
	public static List<String> tFileList;
	public static int posi;
	static File sdcard = Environment.getExternalStorageDirectory();
	public final static File SDDIRETORIO	=  new File(sdcard.getAbsolutePath()+"/SFAndroid/ImagemProduto");	
	ImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ControllerSFAndroid.getInstancia().setContext(this);
       // ControllerFrameRAY.getInstancia().setContext(this);
        this.setTitle("Principal");  
        
        LinearLayout ll = new LinearLayout(ControllerSFAndroid.getInstancia().getContext());
		ll.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		ll.setPadding(Thema.VAL_L, Thema.VAL_T, Thema.VAL_R,
				Thema.VAL_B);
		
        ReadSDCard();

        
        //GridView gridview = (GridView) findViewById(R.id.grid_view);
        GridView gridview = new GridView(ControllerSFAndroid.getInstancia().getContext()); 
		gridview.setNumColumns(3);  
		gridview.setVerticalScrollBarEnabled(false);
		//gridview.setNumColumns(GridView.AUTO_FIT);
		gridview.setScrollBarStyle(GridView.SCROLLBARS_OUTSIDE_OVERLAY);
		gridview.setFastScrollEnabled(true);
		gridview.setOverScrollMode(GridView.OVER_SCROLL_NEVER);
		//gridview.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
		gridview.setSelector(R.color.white);
		gridview.setGravity(Gravity.CENTER);  
		imageAdapter = new ImageAdapter(tFileList);		
		gridview.setAdapter(imageAdapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				posi = position;
				// Sending image id to FullScreenActivity
 	            CoreSFAndroid.getintance().abrirTelaFullImagem();
 	           // finish();
			}
		});
		
        ll.addView(gridview);
        setContentView(ll);
	}
	
	
	private void ReadSDCard() { 
		
		 tFileList = new ArrayList<String>();  
		  
		 //It have to be matched with the directory in SDCard  
			if(!SDDIRETORIO.exists()){
				SDDIRETORIO.mkdirs();
			}
			
		 File f = new File(SDDIRETORIO.getAbsolutePath());  
		  
		 File[] files = f.listFiles();  
		  
		 for(int i = 0; i < files.length; i++)  
		 {  
		  File file = files[i];  
		  /*It's assumed that all file in the path 
		    are in supported type*/  
		  tFileList.add(file.getPath());  
		 }  
		  
		 //return tFileList;  
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
		        	CoreSFAndroid.getintance().abrirTelaLogin();
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