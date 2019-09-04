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

public class FonteEquipamentoDAO {
	 private static FonteEquipamentoDAO instancia = null; 
	 private DatabaseHelper dBHelper; 
	 private SQLiteDatabase db; 
	 private String[] nomeColuna = {"R_E_C_N_O_", "TAMANHO"};
	 private static final String DATABASE_TABLE = "FONTEQUIPA"; 
	 private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" (TAMANHO VARCHAR  (2) ,R_E_C_N_O_ INTEGER PRIMARY KEY autoincrement)";
	 private FonteEquipamentoDAO(){dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());} 
	 public void open(){	db = dBHelper.getWritableDatabase();} 
	 public void close(){db.close();} 
	 public String getPath(){this.open();return db.getPath();} 
	 public boolean deletar(long id){this.open();boolean b = db.delete(DATABASE_TABLE,"R_E_C_N_O_ =" + id, null) > 0;this.close();return b;} 
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper { 
	 	DatabaseHelper(Context context) {super(context, ControllerSFAndroid.DATABASE_NAME, null, ControllerSFAndroid.DATABASE_VERSION);} 
	 	@Override 
	 	public void onCreate(SQLiteDatabase db){db.execSQL(DATABASE_CREATE);} 
	 	@Override 
	 	public void onUpgrade(SQLiteDatabase db, int oldVersion,int	newVersion){Log.w(ControllerSFAndroid.TAG, "Upgrading database from version " + oldVersion	+ " to "+ newVersion + ", which will destroy all old data");db.execSQL("DROP TABLE IF EXISTS titles");onCreate(db);} 
	 } 
	     
	public static FonteEquipamentoDAO getInstancia() {
		if (instancia == null) {
			instancia = new FonteEquipamentoDAO();
		}
		return instancia;
	}

	public FonteEquipamento salvar(FonteEquipamento objeto) throws SFAndroidException {
		try {
			this.open();
			db.execSQL(DATABASE_CREATE);
			int id = 0;
			long idResult = 0;
			if (objeto != null) {
				if (objeto.getId() == 0) {
					ContentValues initialValues = new ContentValues();
					initialValues.put("TAMANHO", objeto.gettamanho());

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
					initialValues.put("TAMANHO", objeto.gettamanho());
					
					boolean is = db.update(DATABASE_TABLE, initialValues,
							"R_E_C_N_O_ =" + objeto.getId(), null) > 0;
					if (is) {
						id = objeto.getId();
					}
				}
			}
			if (id != -1) {
				objeto = findById(id);
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
	
	public FonteEquipamento findById(int id) throws SFAndroidException {
		FonteEquipamento objeto = null;
		try {
			if (id > 0) {
				this.open();
				Cursor c = db.query(true, DATABASE_TABLE, nomeColuna, "R_E_C_N_O_ ="
						+ id, null, null, null, null, null);
				if (c != null) {
					c.moveToFirst();
				}
				if (c.getInt(0) != 0) {
					int _id = c.getInt(0);
					if (_id > 0) {
						objeto = new FonteEquipamento();
						objeto.setId(_id);
						objeto.settamanho(c.getInt(c.getColumnIndex("TAMANHO")));

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
	  public List<FonteEquipamento> findAll() throws SFAndroidException {
	      ArrayList<FonteEquipamento> var1 = new ArrayList<FonteEquipamento>();

	      try {
	         this.open();
	         Cursor var3 = this.db.query(DATABASE_TABLE, nomeColuna, null, null, null, null, null, null);
	         if(var3.moveToFirst()) {
	            do {
	               if(var3.getInt(0) != 0 && (long)var3.getInt(0) > 0L) {
	            	   FonteEquipamento objeto = new FonteEquipamento();
	            	   objeto.setId(var3.getInt(0));
	                  objeto.settamanho(var3.getInt(var3.getColumnIndex("TAMANHO")));
	                  var1.add(objeto);
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
