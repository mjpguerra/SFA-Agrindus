package com.mobilefast.sfandroid.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;

public class MBTMDAO {

    private static final String DATABASE_TABLE = "MBTM";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (TIPO_CLI VARCHAR  (1) , TIPO_MOVTO VARCHAR  (10) , DESCRICAO VARCHAR  (50) , DT_ENT INTEGER  (10) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBTMDAO instancia = null;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "TIPO_CLI", "TIPO_MOVTO", "DESCRICAO", "DT_ENT", "D_I_R_T_Y_", "D_E_L_E_T_"};

    private MBTMDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBTMDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBTMDAO();
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

    public MBTM findTipoCliente(String codProduto) throws SFAndroidException {
        MBTM objeto = null;
        try {
            if (codProduto != "") {
                this.open();
                Cursor c = db.query(DATABASE_TABLE, nomeColuna, " TIPO_CLI = '" + codProduto + "' ", null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBTM();
                        objeto.setId(c.getInt(0));
                        objeto.setTIPO_CLI(c.getString(c.getColumnIndex("TIPO_CLI")));
                        objeto.setTIPO_MOVTO(c.getString(c.getColumnIndex("TIPO_MOVTO")));
                        objeto.setDESCRICAO(c.getString(c.getColumnIndex("DESCRICAO")));
                        objeto.setDT_ENT(c.getInt(c.getColumnIndex("DT_ENT")));
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

    public List<MBTM> findAll() throws SFAndroidException {
        ArrayList<MBTM> var1 = new ArrayList<MBTM>();

        try {
            this.open();
            Cursor c = this.db.query("MBTM", this.nomeColuna, (String) null,
                    (String[]) null, (String) null, (String) null,
                    (String) null, (String) null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0 && (long) c.getInt(0) > 0L) {
                        MBTM objeto = new MBTM();
                        objeto.setId(c.getInt(0));
                        objeto.setTIPO_CLI(c.getString(c.getColumnIndex("TIPO_CLI")));
                        objeto.setTIPO_MOVTO(c.getString(c.getColumnIndex("TIPO_MOVTO")));
                        objeto.setDESCRICAO(c.getString(c.getColumnIndex("DESCRICAO")));
                        objeto.setDT_ENT(c.getInt(c.getColumnIndex("DT_ENT")));
                        objeto.setD_I_R_T_Y_(c.getString(c.getColumnIndex("D_I_R_T_Y_")));
                        objeto.setD_E_L_E_T_(c.getString(c.getColumnIndex("D_E_L_E_T_")));
                        var1.add(objeto);
                    }
                } while (c.moveToNext());
            }

            this.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return var1;
    }

    public MBLP findSelect(int nr_lista, String cod_prod) throws SFAndroidException {
        MBLP objeto = null;
        try {
            if (cod_prod != "") {
                this.open();

                StringBuilder sql = new StringBuilder();
                sql.append("select * from ").append(DATABASE_TABLE);
                sql.append(" where NRO_LISTA = ").append(nr_lista).append(" and C_PROD_PALM = '").append(cod_prod).append("'");
                Cursor c = db.rawQuery(sql.toString(), null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBLP();
                        objeto.setId(c.getInt(8));
                        objeto.setC_PROD(c.getString(c.getColumnIndex("C_PROD")));
                        objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
                        objeto.setUNIDADE(c.getString(c.getColumnIndex("UNIDADE")));
                        objeto.setDT_VIG_DE(c.getString(c.getColumnIndex("DT_VIG_DE")));
                        objeto.setPRECO(c.getDouble(c.getColumnIndex("PRECO")));
                        objeto.setNRO_LISTA(c.getInt(c.getColumnIndex("NRO_LISTA")));
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
