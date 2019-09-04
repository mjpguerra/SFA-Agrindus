package com.mobilefast.sfandroid.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

import sfa.android.ControllerSFAndroid;

public class MBDEDAO {

    private static final String DATABASE_TABLE = "MBDE";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (CLIENTE VARCHAR  (14) , C_PROD_PALM VARCHAR  (20) , PORC_DESC NUMERIC  (15,4) , DT_VALIDADE VARCHAR  (50) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBDEDAO instancia = null;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "CLIENTE", "C_PROD_PALM", "PORC_DESC", "DT_VALIDADE", "D_I_R_T_Y_", "D_E_L_E_T_"};

    private MBDEDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBDEDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBDEDAO();
        }
        return instancia;
    }

    public void open() {
        db = dBHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public String getPath() {
        this.open();
        return db.getPath();
    }

    public boolean deletar(long id) {
        this.open();
        boolean b = db.delete(DATABASE_TABLE, "R_E_C_N_O_ =" + id, null) > 0;
        this.close();
        return b;
    }

    public MBDE findCodProduto(String codProduto) throws SFAndroidException {
        MBDE objeto = null;
        try {
            if (codProduto != "") {
                this.open();
                Cursor c = db.query(DATABASE_TABLE, nomeColuna, " C_PROD_PALM = '" + codProduto + "' ", null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBDE();
                        objeto.setId(c.getInt(0));
                        objeto.setCLIENTE(c.getString(c.getColumnIndex("CLIENTE")));
                        objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
                        objeto.setDT_VALIDADE(c.getString(c.getColumnIndex("DT_VALIDADE")));
                        objeto.setPORC_DESC(c.getDouble(c.getColumnIndex("PORC_DESC")));
                        objeto.setD_I_R_T_Y_(c.getString(c.getColumnIndex("D_I_R_T_Y_")));
                        objeto.setD_E_L_E_T_(c.getString(c.getColumnIndex("D_E_L_E_T_")));

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

    public MBDE findSelect(String cliente, String cod_prod) throws SFAndroidException {
        MBDE objeto = null;
        try {
            if (cod_prod != "") {
                this.open();

                StringBuilder sql = new StringBuilder();
                sql.append("select * from ").append(DATABASE_TABLE);
                sql.append(" where CLIENTE = '").append(cliente).append("' and C_PROD_PALM = '").append(cod_prod).append("'");
                Cursor c = db.rawQuery(sql.toString(), null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(6) != 0) {
                    int _id = c.getInt(6);
                    if (_id > 0) {
                        objeto = new MBDE();
                        objeto.setId(c.getInt(6));
                        objeto.setCLIENTE(c.getString(c.getColumnIndex("CLIENTE")));
                        objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
                        objeto.setDT_VALIDADE(c.getString(c.getColumnIndex("DT_VALIDADE")));
                        objeto.setPORC_DESC(c.getDouble(c.getColumnIndex("PORC_DESC")));
                        objeto.setD_I_R_T_Y_(c.getString(c.getColumnIndex("D_I_R_T_Y_")));
                        objeto.setD_E_L_E_T_(c.getString(c.getColumnIndex("D_E_L_E_T_")));
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

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, ControllerSFAndroid.DATABASE_NAME, null, ControllerSFAndroid.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(ControllerSFAndroid.TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }
}
