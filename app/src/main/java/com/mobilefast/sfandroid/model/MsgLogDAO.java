package com.mobilefast.sfandroid.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sfa.android.ControllerSFAndroid;

public class MsgLogDAO {

    private static final String DATABASE_CREATE = "create table IF NOT EXISTS msglog (DATE DATE, HORA VARCHAR  (16) , USUARIO VARCHAR  (16) , LOGENTRY VARCHAR  (255) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static final String DATABASE_TABLE = "msglog";
    private static MsgLogDAO instancia = null;
    private Context context;
    private MsgLogDAO.DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna;


    private MsgLogDAO() {
        super();
        String[] var1 = new String[]{"R_E_C_N_O_", "DATE", "HORA", "USUARIO", "LOGENTRY"};
        this.nomeColuna = var1;
        this.dBHelper = new MsgLogDAO.DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MsgLogDAO getInstancia() {
        if (instancia == null) {
            instancia = new MsgLogDAO();
        }

        return instancia;
    }

    public void close() {
        this.db.close();
    }

    public boolean deletar(long var1) {
        this.open();
        boolean var3;
        if (this.db.delete("msglog", "_id=" + var1, (String[]) null) > 0) {
            var3 = true;
        } else {
            var3 = false;
        }

        this.close();
        return var3;
    }

    public List findAll() throws SFAndroidException {
        ArrayList var1 = new ArrayList();

        try {
            this.open();
            Cursor var3 = this.db.query("msglog", this.nomeColuna, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
            if (var3.moveToFirst()) {
                do {
                    if (var3.getInt(0) != 0 && (long) var3.getInt(0) > 0L) {
                        Msglog var4 = new Msglog();
                        var4.setId(var3.getInt(0));
                        String var5 = var3.getString(var3.getColumnIndex("DATE"));
                        if (var5 != null) {
                            var4.setData(new Date(Long.parseLong(var5)));
                        }

                        var4.setHora(var3.getString(var3.getColumnIndex("HORA")));
                        var4.setUsuario(var3.getString(var3.getColumnIndex("USUARIO")));
                        var4.setLogentry(var3.getString(var3.getColumnIndex("LOGENTRY")));
                        var1.add(var4);
                    }
                } while (var3.moveToNext());
            }

            this.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return var1;
    }

    public String getPath() {
        this.open();
        return this.db.getPath();
    }

    public void open() {
        this.db = this.dBHelper.getWritableDatabase();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context var1) {
            super(var1, "/sdcard/SFAndroid/SFAndroid.db", (CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase var1) {
            var1.execSQL("create table IF NOT EXISTS msglog (DATE DATE, HORA VARCHAR  (16) , USUARIO VARCHAR  (16) , LOGENTRY VARCHAR  (255) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)");
        }

        public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
            Log.w("DBAdapter", "Upgrading database from version " + var2 + " to " + var3 + ", which will destroy all old data");
            var1.execSQL("DROP TABLE IF EXISTS titles");
            this.onCreate(var1);
        }
    }
}
