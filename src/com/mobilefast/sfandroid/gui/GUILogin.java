 package com.mobilefast.sfandroid.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.android.agrindus.letti.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilefast.midia.BrScreenMetrics;
import com.mobilefast.sfandroid.exception.SFAndroidException;
import com.mobilefast.sfandroid.model.FonteEquipamento;
import com.mobilefast.sfandroid.model.LocatorDAO;

public class GUILogin extends Activity {

	private EditText edt_logon = null;
	private EditText edt_senha = null;
	private OnClickListener onClicker;
	public static String USUARIO = "USUARIO";
	public static String SENHA = "SENHA_USUARIO";

	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
	private long fileSize = 0;
	
	  public static EditText txedsenha;
	  public static TextView txusua;
	  
	 
	  @SuppressWarnings({ "unused", "deprecation" })
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ControllerSFAndroid.getInstancia().setContext(this);    
        BrScreenMetrics.setMetrics(this);
        
		this.setTitle("Automação de Força de Venda");
		CoreSFAndroid.criarFonte();
		
		LinearLayout l = new LinearLayout(this);
		l.setGravity(Gravity.CENTER);
		l.setOrientation(LinearLayout.VERTICAL);
		l.setBackgroundResource(R.drawable.background);
		l.setLayoutParams(new LinearLayout.LayoutParams(BrScreenMetrics.getScreenWidthPX(),BrScreenMetrics.getScreenHeightPX()));
	    
		float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };

		ShapeDrawable shape = null;
		shape = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape.getPaint().setColor(Color.argb(80, 211, 211, 211));

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(BrScreenMetrics.getScreenWidthPX() - 100, LayoutParams.WRAP_CONTENT );
        params.leftMargin = 0;
        params.topMargin =  100;
        
		LinearLayout ll = new LinearLayout(this);
		ll.setGravity(Gravity.CENTER);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(new LinearLayout.LayoutParams(BrScreenMetrics.getScreenWidthPX() - 100,LayoutParams.WRAP_CONTENT ));
		ll.setBackgroundDrawable(shape);         

		LinearLayout lld = new LinearLayout(this);
		lld.setGravity(Gravity.CENTER);
		lld.setOrientation(LinearLayout.VERTICAL);
		lld.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		lld.setPadding(5, 10, 5, 10);

		LinearLayout ll4 = new LinearLayout(this);
		ll4.setGravity(17);
		ll4.setOrientation(0);
		ll4.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
		

		TextView lbversao = new TextView(this);
		lbversao.setText("Ver.1.1.7 Letti Pro 15-02-2017");
		lbversao.setTextColor(Color.rgb(0, 27, 134));
		lbversao.setTypeface(Typeface.DEFAULT_BOLD);

		ll4.addView(lbversao);
		lld.addView(ll4);
	         
		ShapeDrawable shape2 = null;
		shape2 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
		shape2.getPaint().setColor(Color.rgb(200, 200, 200));
	         
	    TextView lbUsuario = new TextView(this); 
	    lbUsuario.setText("Usuário:");
	    lbUsuario.setTextSize(CoreSFAndroid.tamanho_fonte);
	    lbUsuario.setTextColor(Color.rgb(0, 27, 134));
	    lbUsuario.setTypeface(Typeface.DEFAULT_BOLD);
	    lld.addView(lbUsuario);
	    
	    txusua =  new TextView(this);
	    txusua.setText("   MobileFast");
	    txusua.setTextSize(CoreSFAndroid.tamanho_fonte);
	    txusua.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	    txusua.setTypeface(Typeface.DEFAULT_BOLD);
	    txusua.setTextColor(-16777216);
	    txusua.setBackgroundDrawable(shape2);
	    
	    lld.addView(txusua);
	    ll.addView(lld);
	    
	    LinearLayout lls = new LinearLayout(this);
	    lls.setGravity(17);
	    lls.setOrientation(1);
	    lls.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
	    lls.setPadding(5, 0, 5, 5);
	    
	    TextView lbsenha = new TextView(this); 
	    lbsenha.setText("Senha:");
	    lbsenha.setTextSize(CoreSFAndroid.tamanho_fonte);
	    lbsenha.setTextColor(Color.rgb(0, 27, 134));
	    lbsenha.setTypeface(Typeface.DEFAULT_BOLD);
	    lls.addView(lbsenha);
	    
	    txedsenha = new EditText(this);	    
	    txedsenha.setText("");
	    txedsenha.setTextSize(CoreSFAndroid.tamanho_fonte);
	    txedsenha.setTextColor(-16777216);
	    txedsenha.setBackgroundDrawable(shape2);
	    
	    lls.addView(txedsenha);
	    ll.addView(lls);
	    	   
	    LinearLayout lle = new LinearLayout(this); 
	    lle.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	    lle.setOrientation(0);
	    lle.setGravity(17);
	    lle.setPadding(5, 5, 5, 5);
	    lle.setId(3912);
	    
	    Button btlogin = new Button(this); 
	    btlogin.setText("Login");
	    btlogin.setTypeface(Typeface.DEFAULT_BOLD);
	    btlogin.setTextColor(-16777216);
	    btlogin.setTextSize(CoreSFAndroid.tamanho_fonte);
	    btlogin.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    btlogin.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            Log.i("logn","logn");
	            Toast.makeText(getBaseContext(),"Login realizado com sucesso!", Toast.LENGTH_SHORT).show();           
	            CoreSFAndroid.getintance().abrirTelaPrincipal();
	            finish();
	        }
	    });
	    lle.addView(btlogin);	  
	    
 	    Button btassinatura = new Button(this); 
 	    btassinatura.setText("Foto do Cliente");
 	    btassinatura.setOnClickListener(new View.OnClickListener() {
 	        public void onClick(View v) {          
 	            CoreSFAndroid.getintance().abrirTelaGrid();
 	            finish();
 	        }
 	    });
 	    
 	   //lle.addView(btassinatura);
	    ll.addView(lle);
	         
		LinearLayout rt = new LinearLayout(this);
		rt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		rt.setOrientation(LinearLayout.HORIZONTAL);
		rt.setGravity(Gravity.CENTER);
		rt.setPadding(5, 5, 5, 5);

		TextView labellti = new TextView(this);
		labellti.setText(" Bem-vindo ao sistema SFA!");
		labellti.setTextColor(Color.WHITE);
		labellti.setTypeface(Typeface.DEFAULT_BOLD);
		rt.addView(labellti);
		ll.addView(rt);

		l.addView(ll);

		ImageView i = new ImageView(this);
		i.setImageResource(R.drawable.logo3);
		// i.setLayoutParams(li);
		i.setPadding(0, 400, 0, 20);
	//	l.addView(i);

		// ImageView i1 = new ImageView(this);
		// i1.setImageResource(R.drawable.logo);
		// i1.setLayoutParams(ll);
		// i1.setPadding(0, 300, 0, 20);
		// l.addView(i1);

		l.setBackgroundResource(R.drawable.background);

		setContentView(l);

		// salvar(obUsuario);
		this.setUsuario();
	     }
	  
	    public void setUsuario (){
	    	try {
	        ControllerSFAndroid.idEmpresa = "ABC71";
	        CoreSFAndroid.VENDEDOR_CORRENTE = LocatorDAO.getInstancia().getMbvn().findById(1);
	        txusua.setText("  "+CoreSFAndroid.VENDEDOR_CORRENTE.getNome_conh());
	        CoreSFAndroid.codigo_vendedor = CoreSFAndroid.VENDEDOR_CORRENTE.getVendedor();
	        CoreSFAndroid.PEDINI = CoreSFAndroid.VENDEDOR_CORRENTE.getPedido_ini();
	        CoreSFAndroid.PROXPED = CoreSFAndroid.VENDEDOR_CORRENTE.getProximo_ped();
	        CoreSFAndroid.filial = CoreSFAndroid.VENDEDOR_CORRENTE.getFilial();
	        CoreSFAndroid.empresa = CoreSFAndroid.VENDEDOR_CORRENTE.getEmpresa();
	    	} catch (Exception e) {
				// TODO: handle exception
			}
	    }
    
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "SINCRONIZADOR FAST SYNC");
		menu.add(0, 2, 0, "MUDAR EQUIPAMENTO");
		menu.add(0, 3, 0, "SAIR");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
			return true;
		case 2:
			AlertDialog.Builder builder12 = new AlertDialog.Builder(this);
			builder12.setMessage("Escolha seu equipamento...")
					.setCancelable(false)
					.setIcon(android.R.drawable.ic_dialog_alert)
					// ícone de alerta
					.setTitle("Atenção")
					// título do caixa de diálogo

					// Evento disparado se clicar no botão Sim
					.setPositiveButton("TABLET",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {								
									try {
										List<FonteEquipamento> lfont = new ArrayList<FonteEquipamento>();
										lfont = LocatorDAO.getInstancia().getFonteEquipamento().findAll();
										for (Iterator<FonteEquipamento> iterator = lfont.iterator(); iterator.hasNext();) {
											CoreSFAndroid.FONTE_CORRENTE = iterator.next();
											LocatorDAO.getInstancia().getFonteEquipamento().deletar(CoreSFAndroid.FONTE_CORRENTE.getId());
										}
									     CoreSFAndroid.FONTE_CORRENTE = new FonteEquipamento();
										 CoreSFAndroid.FONTE_CORRENTE.settamanho(14);
										 CoreSFAndroid.FONTE_CORRENTE = LocatorDAO.getInstancia().getFonteEquipamento().salvar(CoreSFAndroid.FONTE_CORRENTE);
									} catch (SFAndroidException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									finish();
									CoreSFAndroid.getintance().abrirTelaLogin();
								}
							})

					// Event disparado se clicar no botão Não
					.setNegativeButton("SMARTPHONE",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									
									try {
										List<FonteEquipamento> lfont = new ArrayList<FonteEquipamento>();
										lfont = LocatorDAO.getInstancia().getFonteEquipamento().findAll();
										for (Iterator<FonteEquipamento> iterator = lfont.iterator(); iterator.hasNext();) {
											CoreSFAndroid.FONTE_CORRENTE = iterator.next();
											LocatorDAO.getInstancia().getFonteEquipamento().deletar(CoreSFAndroid.FONTE_CORRENTE.getId());
										}
									     CoreSFAndroid.FONTE_CORRENTE = new FonteEquipamento();
										 CoreSFAndroid.FONTE_CORRENTE.settamanho(11);
										 CoreSFAndroid.FONTE_CORRENTE = LocatorDAO.getInstancia().getFonteEquipamento().salvar(CoreSFAndroid.FONTE_CORRENTE);
									} catch (SFAndroidException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									finish();
									CoreSFAndroid.getintance().abrirTelaLogin();
								}
							});
			AlertDialog alert = builder12.create();
			alert.show(); // Chama a caixa de diálogo
			return true;
		case 3:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);     
		    builder.setMessage("Deseja realmente sair do Sales Force Automation?")            
		    .setCancelable(false)        
		    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
		    .setTitle("Atenção:") //título do caixa de diálogo
		            
		    //Evento disparado se clicar no botão Sim
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
		        public void onClick(DialogInterface dialog, int id) {	        			
                       finish();
		        }            
		    })            

		    //Event disparado se clicar no botão Não
		    .setNegativeButton("Não", new DialogInterface.OnClickListener() {                
		        public void onClick(DialogInterface dialog, int id) {                     
		            dialog.cancel(); //Cancela a caixa de diálogo e volta a tela anterior
		        }            
		    });     
		    AlertDialog alert1 = builder.create();     
		    alert1.show(); //Chama a caixa de diálogo
			return true;
		}
		return false;
	}
	    
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);     
		    builder.setMessage("Deseja realmente sair do Sales Force Automation?")            
		    .setCancelable(false)        
		    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
		    .setTitle("Atenção:") //título do caixa de diálogo
		            
		    //Evento disparado se clicar no botão Sim
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
		        public void onClick(DialogInterface dialog, int id) {	        			
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
    
    public void validarLicence()
    {
      Date localDate = new Date(1338519600000L);
      if (new Date().getTime() >= localDate.getTime())
      {
        System.out.println("==============================================================================================");
        System.out.println("FrameWar - 1.0.0 - Fiora");
        System.out.println("Licenca de uso vencida, Contactar Mario Guerra Filho - mfilho@mobilefast.com.br - tel: +55-011-25893820");
        System.out.println("20/11/2011 - 11:44:22");
        System.out.println("==============================================================================================");
        System.exit(0);
      }
    }
	} 