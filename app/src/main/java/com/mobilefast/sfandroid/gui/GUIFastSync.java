package com.mobilefast.sfandroid.gui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilefast.sfandroid.model.FastSync;
import com.mobilefast.sfandroid.model.LocatorDAO;

import java.util.Iterator;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;
import sfa.mobilefast.agrindus.letti.R;

@SuppressLint("ParserError")
public class GUIFastSync extends AppCompatActivity {

    public static String TITULO = "TITULO";
    public static String IP = "IP SERVIDOR";
    public static String USER = "VENDEDOR/USUARIO";
    public static String SERIAL = "SERIAL DEVICE";
    public static String TIMEOUT = "TEMPO DE CONEXAO";
    public static String PORT = "PORTA SERVIDOR";
    EditText txip;
    EditText txport;
    TextView txserial;
    EditText txtime;
    EditText txuser;
    private List<FastSync> lfast = null;

    @SuppressWarnings("unused")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Configuração Sincronismo");

        ControllerSFAndroid.getInstancia().setContext(this);

        LinearLayout var2 = new LinearLayout(this);
        var2.setGravity(1);
        var2.setOrientation(1);
        var2.setBackgroundResource(R.drawable.background);
        var2.setLayoutParams(new LayoutParams(-1, -1));

        float[] var3 = new float[]{12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F, 12.0F};

        ShapeDrawable var4 = new ShapeDrawable(new RoundRectShape(var3, (RectF) null, (float[]) null));
        var4.getPaint().setColor(Color.rgb(200, 200, 200));
        ShapeDrawable var44 = new ShapeDrawable(new RoundRectShape(var3, (RectF) null, (float[]) null));
        var44.getPaint().setColor(Color.rgb(200, 200, 200));

        TextView var5 = new TextView(this);
        var5.setText("CONFIGURAÇÂO DO FAST SYNC");
        var5.setTextColor(Color.rgb(192, 255, 62));
        var5.setTextSize(CoreSFAndroid.tamanho_fonte);
        var2.addView(var5);

        TextView var6 = new TextView(this);
        var6.setText("SERIAL DEVICE: ");
        var6.setTextSize(CoreSFAndroid.tamanho_fonte);
        var6.setTextColor(-1);
        var2.addView(var6);

        this.txserial = new TextView(this);
        this.txserial.setText(this.findDeviceID());
        this.txserial.setTextColor(-16777216);
        //this.txserial.setHeight(30);
        txserial.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.txserial.setBackgroundDrawable(var44);

        var2.addView(this.txserial);

        TextView var7 = new TextView(this);
        var7.setText("IP DO SERVIDOR: ");
        var7.setTextSize(CoreSFAndroid.tamanho_fonte);
        var7.setTextColor(-1);
        var2.addView(var7);

        this.txip = new EditText(this);
        this.txip.findFocus();
        this.txip.setInputType(8192);
        this.txip.setText("");
        this.txip.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.txip.setTextColor(-16777216);
        this.txip.setBackgroundDrawable(var4);
        var2.addView(this.txip);

        TextView var9 = new TextView(this);
        var9.setText("USUARIO: ");
        var9.setTextSize(20.0F);
        var9.setTextColor(-1);
        var2.addView(var9);

        this.txuser = new EditText(this);
        this.txuser.setText("");
        this.txuser.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.txuser.setTextColor(-16777216);
        this.txuser.setBackgroundDrawable(var4);
        var2.addView(this.txuser);

        TextView var10 = new TextView(this);
        var10.setText("PORTA DO SERVIDOR: ");
        var10.setTextSize(CoreSFAndroid.tamanho_fonte);
        var10.setTextColor(-1);
        var2.addView(var10);

        this.txport = new EditText(this);
        this.txport.setInputType(2);
        this.txport.setText("");
        this.txport.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.txport.setTextColor(-16777216);
        this.txport.setBackgroundDrawable(var4);
        var2.addView(this.txport);

        TextView var11 = new TextView(this);
        var11.setText("TIME OUT: ");
        var11.setTextSize(CoreSFAndroid.tamanho_fonte);
        var11.setTextColor(-1);
        var2.addView(var11);

        this.txtime = new EditText(this);
        this.txtime.setInputType(2);
        this.txtime.setText("");
        this.txtime.setTextSize(CoreSFAndroid.tamanho_fonte);
        this.txtime.setTextColor(-16777216);
        this.txtime.setBackgroundDrawable(var4);
        var2.addView(this.txtime);


        String androidID = android.provider.Settings.System.getString(this.getContentResolver(), android.provider.Settings.System.ANDROID_ID);

        this.setContentView(var2);
        this.setFastSync();

    }


    @SuppressLint("SdCardPath")
    @SuppressWarnings("unchecked")
    public void salvar() {
        try {
            lfast = LocatorDAO.getInstancia().getFastSync().findAll();
            Iterator<FastSync> var2 = this.lfast.iterator();

            while (var2.hasNext()) {
                long var3 = (long) var2.next().get_id();
                LocatorDAO.getInstancia().getFastSync().deletar(var3);
            }

            CoreSFAndroid.FASTSYNC_CORRENTE = new FastSync();
            CoreSFAndroid.FASTSYNC_CORRENTE.setIp(this.txip.getText().toString());
            CoreSFAndroid.FASTSYNC_CORRENTE.setUser(this.txuser.getText().toString());
            CoreSFAndroid.FASTSYNC_CORRENTE.setSerial(this.findDeviceID());
            CoreSFAndroid.FASTSYNC_CORRENTE.setDBPath("/sdcard/SFAndroid");
            CoreSFAndroid.FASTSYNC_CORRENTE.setDBName("SFAndroid.db");
            CoreSFAndroid.FASTSYNC_CORRENTE.setGroup("");
            CoreSFAndroid.FASTSYNC_CORRENTE.setPort(Integer.valueOf(this.txport.getText().toString()).intValue());
            CoreSFAndroid.FASTSYNC_CORRENTE.setTout(Integer.valueOf(this.txtime.getText().toString()).intValue());
            CoreSFAndroid.FASTSYNC_CORRENTE.setRet(-1);
            CoreSFAndroid.FASTSYNC_CORRENTE = LocatorDAO.getInstancia().getFastSync().salvar(CoreSFAndroid.FASTSYNC_CORRENTE);

        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void setFastSync() {
        try {
            lfast = LocatorDAO.getInstancia().getFastSync().findAll();
            Iterator<FastSync> var2 = this.lfast.iterator();

            while (var2.hasNext()) {
                FastSync var3 = var2.next();
                if (var3 == null) {
                    this.txserial.setText("  " + this.findDeviceID());
                } else {
                    this.txserial.setText("   " + var3.getSerial().toString());
                    this.txip.setText(var3.getIp().toString());
                    this.txuser.setText(var3.getUser().toString());
                    this.txport.setText(String.valueOf(var3.getPort()).toString());
                    this.txtime.setText(String.valueOf(var3.getTout()).toString());
                }
            }
        } catch (Exception var4) {
            ;
        }

    }


    public String findDeviceID() {
        String deviceID = null;
        String serviceName = Context.TELEPHONY_SERVICE;
        TelephonyManager m_telephonyManager = (TelephonyManager) getSystemService(serviceName);
        int deviceType = m_telephonyManager.getPhoneType();
        switch (deviceType) {
            case (TelephonyManager.PHONE_TYPE_GSM):
                break;
            case (TelephonyManager.PHONE_TYPE_CDMA):
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                break;
            default:
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return null;
            }
        }
        deviceID = m_telephonyManager.getDeviceId();
        return deviceID;

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "OK");
        menu.add(0, 2, 0, "CANCELAR");
        // menu.add(0,3, 0, "VOLTAR");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                salvar();
                CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                finish();
                return true;
            case 2:
                CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
                finish();
                return true;
        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            CoreSFAndroid.getintance().abrirTelaPrincipalFastSync();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
