package com.frame.mobilefast;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaRecorder;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class ControllerFrameWAR
{
  private static ControllerFrameWAR instancia = null;
  public static final String KEY_ROWID = "_id";
  public static final String KEY_ROWIDOBJETO = "idObjeto";
  public static final String DATABASE_NAME = "MobiColeta.db";
  public static final String URL_DATABASE = "/sdcard/DA20_DPO/MobiColeta";
  public static final int DATABASE_VERSION = 1;
  private static ControllerFrameWAR instance = null;
  public static final String TAG = "DBAdapter";
  private static Context context = null;
  private double latitute;
  private double longitude = 0.0D;
  private MediaRecorder recorder = null;
  private static ProgressDialog progressDialog = null;
  public static String idEmpresa = null;
  public static String idUsuario = null;
  public static final String VERSAO = "FrameRay - 1.0.1 - Quimera";
  private Display display = null;
  private int w = 0;
  private int h = 0;
  private int orientation = -1;

  public static ControllerFrameWAR getInstancia()
  {
    if (instancia == null) {
      instancia = new ControllerFrameWAR();
    }

    instancia.validarLicence();

    return instancia;
  }

  public void validarLicence() {
   // long l = 1338519600000L;
   // Date expireDate = new Date(l);

   // if (new Date().getTime() >= expireDate.getTime()) {
      System.out.println("==============================================================================================");
      System.out.println("Licenca de uso vencida, Contactar Ray da Costa - raydacosta@gmail.com - tel: +55-011-89628603");
      System.out.println("20/11/2011 - 11:44:22");
      System.out.println("==============================================================================================");
     // System.exit(0);
  //  }
  }

  public boolean isLogado() {
    return (idEmpresa != null) && (idUsuario != null);
  }
  public Context getContext() {
    return context;
  }
  public void setContext(Context context) {
    context = context;
    try
    {
      if (context == null) {
        System.out.println("Context não pode ser carregado o mesmo está nulo.");
        return;
      }

      this.display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
      this.w = this.display.getWidth();
      this.h = this.display.getHeight();

      if (this.h > 640)
        Thema.AJUSTE_TELA = 80;
      else {
        Thema.AJUSTE_TELA = 50;
      }

      this.orientation = this.display.getOrientation();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void showMessage(String caption, String title)
  {
    AlertDialog.Builder bilder = new AlertDialog.Builder(getContext());
    bilder.setMessage(caption);
    bilder.setNeutralButton("Ok", null);
    AlertDialog alert = bilder.create();
    alert.setTitle(title);
    alert.show();
  }

  public int getOrientation()
  {
    return this.orientation;
  }

  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }

  public int getW() {
    return this.w;
  }

  public void setW(int w) {
    this.w = w;
  }

  public int getH() {
    return this.h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public void showMensagemSimples(String txt) {
    Toast.makeText(getContext(), txt, 0).show();
  }

  public int getAjusteTelaList() {
    if (this.h > 640) {
      return 150;
    }
    return 100;
  }
}