package com.fastsync.client;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class UseFastSyncActivity extends Activity {
	FSNativeLib fs;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        
		File f = this.getApplicationContext().getFilesDir();

		String ip = "192.168.2.4";
		String user = "000019";
		String serial = "123456789";
		String group = "";
		String dbPath = f.toString(); //"data/sfa.android/databases/";
		String dbName = "SFAndroid.db";
		int port = 5002;
		int tout = 60;
		int ret = -1;

		fs = new FSNativeLib();    
		ret = fs.StartSync(ip, port, user, serial, tout, group, dbPath, dbName);
   	 
        new AlertDialog.Builder(this).setMessage(Integer.toString(ret)).show();        
        
        finish();
    }
}