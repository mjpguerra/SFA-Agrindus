package sfa.android;
 
 import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.widget.Toast;

public class ControllerSFAndroid {  
  
 private static ControllerSFAndroid instancia             = null; 
 public static final String KEY_ROWID 		= "_id";
 public static final String KEY_ROWIDOBJETO 	= "idObjeto";
 public static final String DATABASE_NAME 	= "/sdcard/SFAndroid/SFAndroid.db";
 public static final String URL_DATABASE 	= "/sdcard/SFAndroid";
 public static final int DATABASE_VERSION 	= 1;
 public static final String TAG 			= "DBAdapter";
 private Context context                  = null;
 private MediaRecorder recorder                  = null;
 private static ProgressDialog progressDialog = null;
 public static String idEmpresa = null;
 public static String idUsuario = null;
 public static Format formatterDia = new SimpleDateFormat("dd/MM/yyyy");
 public static Format formatterDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 public static Format formatterHora = new SimpleDateFormat("HH:mm:ss");

    
public static ControllerSFAndroid getInstancia() {
	if (instancia == null) {
		instancia = new ControllerSFAndroid();
	}
	return instancia;
}

public boolean isLogado(){
         return idEmpresa != null && idUsuario != null;
 }

public Context getContext() {
 	return context;
}

 public void showMessage(String caption, String title){
     AlertDialog.Builder bilder = new AlertDialog.Builder(this.getContext());
     bilder.setMessage(caption);
     bilder.setNeutralButton("Ok", null);
     AlertDialog alert = bilder.create();
     alert.setTitle(title);
     alert.show();
 }

public void progressBar(String mensagem){
    progressDialog = new ProgressDialog(this.getContext());
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMessage(mensagem);
    progressDialog.setCancelable(false);
    progressDialog.show();
}

public void progressBarMax(int valor){
        if(progressDialog != null){
                progressDialog.setMax(valor);
        }
}

public void progressBarValor(int valor){
        if(progressDialog != null){
                progressDialog.setProgress(valor);
        }
}

public void progressBarClose(int valor){
        if(progressDialog != null){
                progressDialog.dismiss();
        }
}

public void showMensagemSimples(String txt){
         Toast.makeText(this.getContext(),txt, Toast.LENGTH_SHORT).show();
}

// DA20 construiu para envio de SMS 
public void sendSMS(String numeroCelular,String mensagem){
    SmsManager m = SmsManager.getDefault();
    String destinationNumber = numeroCelular;
    String text = mensagem; 
    m.sendTextMessage(destinationNumber, null, text, null, null);
}

// DA20 construiu para vibrar o celular por um segundo 
public void vibrador(){
    Vibrator vibrator = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE); 
    vibrator.vibrate(1000);
}

// DA20 construiu para envio de email para uma pessoa 
public void sendMail(String emailDestino, String titulo, String conteudo){
    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
    emailIntent.setType("plain/text");  
    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailDestino});  
    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,titulo);  
    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,conteudo);  
    this.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail via DA20"));
}

// DA20 construiu para envio de email para MAIS DE uma pessoa 
public void sendMail(String[] emailDestinos, String titulo, String conteudo){
    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
    emailIntent.setType("plain/text");  
    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,emailDestinos);  
    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,titulo);  
    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,conteudo);  
    this.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail via DA20")); 
}

// DA20 construiu para executar determinado som 
public void executarSom(String urlSom) throws IllegalArgumentException, IllegalStateException, IOException{
    MediaPlayer mp = new MediaPlayer();
    mp.setDataSource(urlSom);
    mp.prepare();
    mp.start();
}

// DA20 construiu para gravar determinado som 
public void gravarSom(String urlSom) throws IllegalStateException, IOException{
    recorder = new MediaRecorder();
    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    recorder.setOutputFile(urlSom); // The file must already exist
    recorder.prepare();
    recorder.start();
}

public void stopRecordSound(){
    if(this.recorder != null){
            this.recorder.stop();
            this.recorder.release();
    }
}


public void preferenciaCreate(String nome, String valor){
    SharedPreferences preferences = this.getContext().getSharedPreferences(nome, 0);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(nome,valor);
    editor.commit();
}

public String preferenciaGet(String nome){
    SharedPreferences preferences = this.getContext().getSharedPreferences(nome, 0);
    String preferenceValue = preferences.getString(nome, null);
    return preferenceValue;
}

     
public static HttpURLConnection getHttpConnection() throws IOException{
	URL url = new URL("http://xxxcnn4761.hospedagemdesites.ws:8080/mysmsWeb/servicexo");
 URLConnection conn = url.openConnection();
 HttpURLConnection httpConn = (HttpURLConnection) conn;
 httpConn.setRequestProperty("Content-Language", "pt-BR");
 httpConn.setRequestProperty("User-Agent","Profile/Android, Configuration/Android 1.6");
 httpConn.setRequestProperty("Content-type", "binary/x-java-serialized");
 httpConn.setAllowUserInteraction(false);
 httpConn.setInstanceFollowRedirects(true);
 httpConn.setRequestMethod("POST");
 httpConn.setDoInput(true);
 httpConn.setDoOutput(true);
 httpConn.setConnectTimeout(15000);
 httpConn.setReadTimeout(40000);
 
 
 return httpConn;
    }

public void abrirTelaLogon(){ 
    Intent i = new Intent(this.getContext(), com.mobilefast.sfandroid.gui.GUILogin.class);
    this.getContext().startActivity(i);
}
 
public void setContext(Context bulletedMenuLista) {
	this.context = bulletedMenuLista;
}

} 