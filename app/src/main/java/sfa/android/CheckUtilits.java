package sfa.android;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import sfa.mobilefast.agrindus.letti.R;

public class CheckUtilits implements PermissonInterface{

    private Activity activity;
    private Context context;
    private ArrayList<String> permissionCheck;

    private static String tag = "CheckUtilits";

    public CheckUtilits(Activity activity){
        this.activity = activity;
        this.context = activity;
    }

    private boolean permissionCheck(String manifestPermission){
        int permCheck = ContextCompat.checkSelfPermission(activity,
                manifestPermission);

        return permCheck == PackageManager.PERMISSION_GRANTED;
    }

    private boolean permissionCheckAll(){
        String[] manifestPermission = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,

        };
        permissionCheck = new ArrayList<>();
        for(String permission : manifestPermission){
            if(!permissionCheck(permission)){
                permissionCheck.add(permission);
                Log.i(tag,permission);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isCheckedAll() {
        Log.d(tag,"Start Check Permisson All");
        return permissionCheckAll();
    }

    public boolean isNetworkOnline() {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    protected boolean isFisthStart(SharedPreferences preferences, SharedPreferences.Editor editor) {
        boolean isFisthStart = !preferences.contains(context.getString(R.string.pref_first_rum));
        if (isFisthStart) {
            editor.putBoolean(context.getString(R.string.pref_first_rum), true);
/* O commit(), é executado de forma síncrona e ele retorna um booleano se realmente foi salva.
   O apply() é gerado um processo assíncrono não possui retorno.*/
            editor.apply();
        }
        return isFisthStart;
    }
}
