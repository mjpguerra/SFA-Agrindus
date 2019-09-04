package com.mobilefast.sfandroid.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;

public class MBGRDAO {

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS MBGR (GRUPO VARCHAR  (4) , DESCRICAO VARCHAR  (25) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static final String DATABASE_TABLE = "MBGR";
    private static final int limite = 18;
    private static MBGRDAO instancia = null;
    public String nome;
    public int offset = 0;
    private Context context;
    private MBGRDAO.DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna;


    private MBGRDAO() {
        super();
        String[] var1 = new String[]{"R_E_C_N_O_", "GRUPO", "DESCRICAO"};
        this.nomeColuna = var1;
        this.dBHelper = new MBGRDAO.DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBGRDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBGRDAO();
        }

        return instancia;
    }

    public void close() {
        this.db.close();
    }

    public boolean deletar(long var1) {
        this.open();
        boolean var3;
        if (this.db.delete("MBGR", "R_E_C_N_O_ =" + var1, (String[]) null) > 0) {
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
            Cursor var3 = this.db.query("MBGR", this.nomeColuna, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
            if (var3.moveToFirst()) {
                do {
                    if (var3.getInt(0) != 0 && (long) var3.getInt(0) > 0L) {
                        MBGR var4 = new MBGR();
                        var4.setId(var3.getInt(0));
                        var4.setGrupo(var3.getString(var3.getColumnIndex("GRUPO")));
                        var4.setDescricao(var3.getString(var3.getColumnIndex("DESCRICAO")));
                        var1.add(var4);
                    }
                } while (var3.moveToNext());
            }

            this.close();
        } catch (Exception var5) {
            var5.printStackTrace();
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

    public void somarOffset() {
        this.offset += 18;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context var1) {
            super(var1, "/sdcard/SFAndroid/SFAndroid.db", (CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase var1) {
            var1.execSQL("CREATE TABLE IF NOT EXISTS MBGR (GRUPO VARCHAR  (4) , DESCRICAO VARCHAR  (25) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)");
        }

        public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
            Log.w("DBAdapter", "Upgrading database from version " + var2 + " to " + var3 + ", which will destroy all old data");
            var1.execSQL("DROP TABLE IF EXISTS titles");
            this.onCreate(var1);
        }
    }
}
