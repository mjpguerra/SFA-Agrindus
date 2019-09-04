package com.mobilefast.sfandroid.model;


	 
import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

	public class FastSyncDAO { 
	 	 
	 private static FastSyncDAO instancia = null; 
	 private DatabaseHelper dBHelper; 
	 private Context context; 
	 private SQLiteDatabase db; 
	 private String[] nomeColuna = {"id","ip","serial","user","grop","dbPath","dbName","port","tout","ret"};
	 private static final String DATABASE_TABLE = "FASTSYNC"; 
	 private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" (id integer primary key autoincrement , ip VARCHAR (20) , serial VARCHAR (20), user VARCHAR (10), grop VARCHAR (10), dbPath VARCHAR (30), dbName VARCHAR (12), port INTEGER, tout INTEGER, ret INTEGER)";
	 private FastSyncDAO(){dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());} 
	 public void open(){	db = dBHelper.getWritableDatabase();} 
	 public void close(){db.close();} 
	 public String getPath(){this.open();return db.getPath();} 
	 public boolean deletar(long id){this.open();boolean b = db.delete(DATABASE_TABLE,"id =" + id, null) > 0;this.close();return b;} 
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper { 
	 	DatabaseHelper(Context context) {super(context, ControllerSFAndroid.DATABASE_NAME, null, ControllerSFAndroid.DATABASE_VERSION);} 
	 	@Override 
	 	public void onCreate(SQLiteDatabase db){db.execSQL(DATABASE_CREATE);} 
	 	@Override 
	 	public void onUpgrade(SQLiteDatabase db, int oldVersion,int	newVersion){Log.w(ControllerSFAndroid.TAG, "Upgrading database from version " + oldVersion	+ " to "+ newVersion + ", which will destroy all old data");db.execSQL("DROP TABLE IF EXISTS titles");onCreate(db);} 
	 } 
	     
	public static FastSyncDAO getInstancia() {
		if (instancia == null) {
			instancia = new FastSyncDAO();
		}
		return instancia;
	}
	     
	public FastSync salvar(FastSync objeto) throws SFAndroidException {
		try {
			this.open();
			db.execSQL(DATABASE_CREATE);
			int id = 0;
			long idResult = 0;
			if (objeto != null) {
				if (objeto.getId() == 0) {
					ContentValues initialValues = new ContentValues();
					initialValues.put("ip", objeto.getIp());
					initialValues.put("user", objeto.getUser());
					initialValues.put("serial", objeto.getSerial());
					initialValues.put("grop", objeto.getGroup());
					initialValues.put("dbPath", objeto.getDBPath());
					initialValues.put("dbName", objeto.getDBName());
					initialValues.put("port", objeto.getPort());
					initialValues.put("tout", objeto.getTout());
					initialValues.put("ret", objeto.getRet());
					try {
						idResult = db.insert(DATABASE_TABLE, null,
								initialValues);
						id = Integer.parseInt(String.valueOf(idResult));
					} catch (Exception e) {
						ControllerSFAndroid.getInstancia().showMessage(e.toString(),
								"ERROR");
					}
				} else {
					ContentValues initialValues = new ContentValues();
					initialValues.put("ip", objeto.getIp());
					initialValues.put("user", objeto.getUser());
					initialValues.put("serial", objeto.getSerial());
					initialValues.put("grop", objeto.getGroup());
					initialValues.put("dbPath", objeto.getDBPath());
					initialValues.put("dbName", objeto.getDBName());
					initialValues.put("port", objeto.getPort());
					initialValues.put("tout", objeto.getTout());
					initialValues.put("ret", objeto.getRet());
					
					boolean is = db.update(DATABASE_TABLE, initialValues,
							"id =" + objeto.getId(), null) > 0;
					if (is) {
						id = objeto.getId();
					}
				}
			}
			if (id != -1) {
				objeto = this.findById(id);
			}

		} catch (Exception e) {
			this.close();
			e.printStackTrace();
			Log.w(ControllerSFAndroid.TAG, "ERROR AO CARREGAR:  " + e.toString());
			throw new SFAndroidException("Tabela pode estar vazia ");
		}
		this.close();
		return objeto;
	}
	
	public FastSync findById(int id) throws SFAndroidException {
		FastSync objeto = null;
		try {
			if (id > 0) {
				this.open();
				Cursor c = db.query(true, DATABASE_TABLE, nomeColuna, "id ="
						+ id, null, null, null, null, null);
				if (c != null) {
					c.moveToFirst();
				}
				if (c.getInt(0) != 0) {
					int _id = c.getInt(0);
					if (_id > 0) {
						objeto = new FastSync();
						objeto.setId(_id);
						objeto.setIp(c.getString(c.getColumnIndex("ip")));
						objeto.setUser(c.getString(c.getColumnIndex("user")));
						objeto.setSerial(c.getString(c.getColumnIndex("serial")));
						objeto.setGroup(c.getString(c.getColumnIndex("grop")));
						objeto.setDBPath(c.getString(c.getColumnIndex("dbPath")));
						objeto.setDBName(c.getString(c.getColumnIndex("dbName")));
						objeto.setPort(c.getInt(c.getColumnIndex("port")));
						objeto.setTout(c.getInt(c.getColumnIndex("tout")));
						objeto.setRet(c.getInt(c.getColumnIndex("ret")));
					}
				}
			}
		} catch (Exception e) {
			this.close();
			e.printStackTrace();
			Log.w(ControllerSFAndroid.TAG, "ERROR AO CARREGAR:  " + e.toString());
			// throw new SFAndroidException("Tabela pode estar vazia ");
		}
		this.close();
		return objeto;
	}
	  public List findAll() throws SFAndroidException {
	      ArrayList var1 = new ArrayList();

	      try {
	         this.open();
	         Cursor var3 = this.db.query("FASTSYNC", this.nomeColuna, (String)null, (String[])null, (String)null, (String)null, (String)null, (String)null);
	         if(var3.moveToFirst()) {
	            do {
	               if(var3.getInt(0) != 0 && (long)var3.getInt(0) > 0L) {
	                  FastSync var4 = new FastSync();
	                  var4.setId(var3.getInt(0));
	                  var4.setIp(var3.getString(var3.getColumnIndex("ip")));
	                  var4.setUser(var3.getString(var3.getColumnIndex("user")));
	                  var4.setSerial(var3.getString(var3.getColumnIndex("serial")));
	                  var4.setGroup(var3.getString(var3.getColumnIndex("grop")));
	                  var4.setDBPath(var3.getString(var3.getColumnIndex("dbPath")));
	                  var4.setDBName(var3.getString(var3.getColumnIndex("dbName")));
	                  var4.setPort(var3.getInt(var3.getColumnIndex("port")));
	                  var4.setTout(var3.getInt(var3.getColumnIndex("tout")));
	                  var4.setRet(var3.getInt(var3.getColumnIndex("ret")));
	                  var1.add(var4);
	               }
	            } while(var3.moveToNext());
	         }

	         this.close();
	      } catch (Exception var5) {
	         var5.printStackTrace();
	      }

	      return var1;
	   }
}
