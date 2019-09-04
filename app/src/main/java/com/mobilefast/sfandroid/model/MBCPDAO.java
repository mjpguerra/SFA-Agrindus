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

public class MBCPDAO {
    private static final String DATABASE_TABLE = "MBCP";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (COND_PGTO VARCHAR  (4) , DESCRICAO VARCHAR  (30) , CP_INTELIGENTE INTEGER  (5) , NRO_LISTA VARCHAR  (6) , FORMA_PAGTO INTEGER  (5) , DIAS_ADIC_ENTR INTEGER  (5) , USA_DIAS_ADIC INTEGER  (5) , TIPO_PAGTO VARCHAR  (2) , USA_TIPO_PAGTO INTEGER  (5) , COD_TES VARCHAR  (3) , USA_TES INTEGER  (5) , VLR_MIN_PED NUMERIC  (15,4) , USA_DESC INTEGER  (5) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBCPDAO instancia = null;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "COND_PGTO", "DESCRICAO",
            "CP_INTELIGENTE", "NRO_LISTA", "FORMA_PAGTO", "DIAS_ADIC_ENTR",
            "USA_DIAS_ADIC", "TIPO_PAGTO", "USA_TIPO_PAGTO", "COD_TES",
            "USA_TES", "VLR_MIN_PED", "USA_DESC"};

    private MBCPDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBCPDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBCPDAO();
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

    public List<MBCP> findAll() throws SFAndroidException {
        ArrayList<MBCP> var1 = new ArrayList<MBCP>();

        try {
            this.open();
            Cursor var3 = this.db.query("MBCP", this.nomeColuna, (String) null,
                    (String[]) null, (String) null, (String) null,
                    (String) null, (String) null);
            if (var3.moveToFirst()) {
                do {
                    if (var3.getInt(0) != 0 && (long) var3.getInt(0) > 0L) {
                        MBCP var4 = new MBCP();
                        var4.setId(var3.getInt(0));
                        var4.setCond_pgto(var3.getString(var3
                                .getColumnIndex("COND_PGTO")));
                        var4.setDescricao(var3.getString(var3
                                .getColumnIndex("DESCRICAO")));
                        var4.setCp_inteligente(var3.getShort(var3
                                .getColumnIndex("CP_INTELIGENTE")));
                        var4.setNro_lista(var3.getString(var3
                                .getColumnIndex("NRO_LISTA")));
                        var4.setForma_pagto(var3.getShort(var3
                                .getColumnIndex("FORMA_PAGTO")));
                        var4.setDias_adic_entr(var3.getShort(var3
                                .getColumnIndex("DIAS_ADIC_ENTR")));
                        var4.setUsa_dias_adic(var3.getShort(var3
                                .getColumnIndex("USA_DIAS_ADIC")));
                        var4.setTipo_pagto(var3.getString(var3
                                .getColumnIndex("TIPO_PAGTO")));
                        var4.setUsa_tipo_pagto(var3.getShort(var3
                                .getColumnIndex("USA_TIPO_PAGTO")));
                        var4.setCod_tes(var3.getString(var3
                                .getColumnIndex("COD_TES")));
                        var4.setUsa_tes(var3.getShort(var3
                                .getColumnIndex("USA_TES")));
                        var4.setUsa_desc(var3.getShort(var3
                                .getColumnIndex("USA_DESC")));
                        var4.setVlr_min_ped(var3.getDouble(var3
                                .getColumnIndex("VLR_MIN_PED")));
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

    public MBCP findCodPagto(String codProduto) throws SFAndroidException {
        MBCP objeto = null;
        try {
            if (codProduto != "") {
                this.open();
                Cursor c = db.query(DATABASE_TABLE, nomeColuna, " COND_PGTO = '" + codProduto + "' ", null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBCP();
                        objeto.setId(c.getInt(0));
                        objeto.setCond_pgto(c.getString(c.getColumnIndex("COND_PGTO")));
                        objeto.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
                        objeto.setCp_inteligente(c.getShort(c.getColumnIndex("CP_INTELIGENTE")));
                        objeto.setNro_lista(c.getString(c.getColumnIndex("NRO_LISTA")));
                        objeto.setForma_pagto(c.getShort(c.getColumnIndex("FORMA_PAGTO")));
                        objeto.setDias_adic_entr(c.getShort(c.getColumnIndex("DIAS_ADIC_ENTR")));
                        objeto.setUsa_dias_adic(c.getShort(c.getColumnIndex("USA_DIAS_ADIC")));
                        objeto.setTipo_pagto(c.getString(c.getColumnIndex("TIPO_PAGTO")));
                        objeto.setUsa_tipo_pagto(c.getShort(c.getColumnIndex("USA_TIPO_PAGTO")));
                        objeto.setCod_tes(c.getString(c.getColumnIndex("COD_TES")));
                        objeto.setUsa_tes(c.getShort(c.getColumnIndex("USA_TES")));
                        objeto.setUsa_desc(c.getShort(c.getColumnIndex("USA_DESC")));
                        objeto.setVlr_min_ped(c.getDouble(c.getColumnIndex("VLR_MIN_PED")));

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

    public List<MBCP> findBycodigo(final String idSuperior) throws SFAndroidException {
        List<MBCP> lista = new ArrayList<MBCP>();
        try {
            this.open();
            Cursor c = db.query(DATABASE_TABLE, nomeColuna, " COND_PGTO = '" + idSuperior + "' ", null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        long _id = c.getInt(0);
                        if (_id > 0) {
                            MBCP objeto = new MBCP();
                            objeto.setId(c.getInt(0));
                            objeto.setCond_pgto(c.getString(c.getColumnIndex("COND_PGTO")));
                            objeto.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
                            objeto.setCp_inteligente(c.getShort(c.getColumnIndex("CP_INTELIGENTE")));
                            objeto.setNro_lista(c.getString(c.getColumnIndex("NRO_LISTA")));
                            objeto.setForma_pagto(c.getShort(c.getColumnIndex("FORMA_PAGTO")));
                            objeto.setDias_adic_entr(c.getShort(c.getColumnIndex("DIAS_ADIC_ENTR")));
                            objeto.setUsa_dias_adic(c.getShort(c.getColumnIndex("USA_DIAS_ADIC")));
                            objeto.setTipo_pagto(c.getString(c.getColumnIndex("TIPO_PAGTO")));
                            objeto.setUsa_tipo_pagto(c.getShort(c.getColumnIndex("USA_TIPO_PAGTO")));
                            objeto.setCod_tes(c.getString(c.getColumnIndex("COD_TES")));
                            objeto.setUsa_tes(c.getShort(c.getColumnIndex("USA_TES")));
                            objeto.setUsa_desc(c.getShort(c.getColumnIndex("USA_DESC")));
                            objeto.setVlr_min_ped(c.getDouble(c.getColumnIndex("VLR_MIN_PED")));
                            lista.add(objeto);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            //throw new SFAndroidException("ERRO NO SESSION FIND ALL [CondicaoPagamento] "+e.toString());
        }
        return lista;
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
