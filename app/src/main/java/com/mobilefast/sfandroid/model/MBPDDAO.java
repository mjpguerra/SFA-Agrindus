package com.mobilefast.sfandroid.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;

public class MBPDDAO {

    private static final String DATABASE_TABLE = "MBPD";
    private static final String DATABASE_CREATE = "create table IF NOT EXISTS " + DATABASE_TABLE + " ( NUMERO_SFA VARCHAR  (15) , SEQUENCIA VARCHAR  (3) , C_PROD_PALM VARCHAR  (20) , QTDE NUMERIC  (15,6) , RESERVADO13 VARCHAR  (30) , VLR_UNIT NUMERIC  (15,6) , VLR_TOTAL NUMERIC  (15,6) , DT_ENTREGA VARCHAR  (8) , UNIDADE VARCHAR  (2) , TES VARCHAR  (3) , RESERVADO16 VARCHAR  (30) , PORC_DESC NUMERIC  (9,4) , QTDE_ALT NUMERIC  (15,6) , VLR_IPI NUMERIC  (15,4) , GRUPO VARCHAR  (4) , UNID_ALT VARCHAR  (2) , EMPRESA INTEGER  (5) , FILIAL INTEGER  (5) , RESERVADO1 INTEGER  (10) , RESERVADO5 NUMERIC  (15,6) , RESERVADO14 NUMERIC  (15,6) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBPDDAO instancia = null;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "NUMERO_SFA", "SEQUENCIA", "C_PROD_PALM", "QTDE",
            "RESERVADO13", "VLR_UNIT", "VLR_TOTAL", "DT_ENTREGA", "UNIDADE", "TES", "RESERVADO16",
            "PORC_DESC", "QTDE_ALT", "VLR_IPI", "GRUPO", "UNID_ALT", "EMPRESA", "FILIAL",
            "RESERVADO1", "RESERVADO5", "RESERVADO14", "D_I_R_T_Y_", "D_E_L_E_T_"};

    private MBPDDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBPDDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBPDDAO();
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
        boolean b = db.delete(DATABASE_TABLE, "R_E_C_N_O_ = " + id, null) > 0;
        this.close();
        return b;
    }

    public MBPD findById(int id) throws SFAndroidException {
        MBPD objeto = null;
        try {
            if (id > 0) {
                open();
                Cursor c = db.query(true, DATABASE_TABLE, nomeColuna, " R_E_C_N_O_ = " + id, null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBPD();
                        objeto.setId(c.getInt(_id));
                        objeto.setEmpresa(c.getShort(c.getColumnIndex("EMPRESA")));
                        objeto.setFilial(c.getShort(c.getColumnIndex("FILIAL")));
                        objeto.setNumero_sfa(c.getString(c.getColumnIndex("NUMERO_SFA")));
                        objeto.setSequencia(c.getString(c.getColumnIndex("SEQUENCIA")));
                        objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
                        objeto.setQtde(c.getDouble(c.getColumnIndex("QTDE")));
                        objeto.setVlr_unit(c.getDouble(c.getColumnIndex("VLR_UNIT")));
                        objeto.setVlr_total(c.getDouble(c.getColumnIndex("VLR_TOTAL")));
                        objeto.setDt_entrega(c.getString(c.getColumnIndex("DT_ENTREGA")));
                        objeto.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
                        objeto.setTes(c.getString(c.getColumnIndex("TES")));
                        objeto.setPorc_desc(c.getDouble(c.getColumnIndex("PORC_DESC")));
                        objeto.setVlr_ipi(c.getDouble(c.getColumnIndex("VLR_IPI")));
                        objeto.setGrupo(c.getString(c.getColumnIndex("GRUPO")));
                        objeto.setUnid_alt(c.getString(c.getColumnIndex("UNID_ALT")));
                        objeto.setQtde_alt(c.getDouble(c.getColumnIndex("QTDE_ALT")));
                        objeto.setReservado5(c.getDouble(c.getColumnIndex("RESERVADO5")));
                        objeto.setReservado6(c.getDouble(c.getColumnIndex("RESERVADO14")));
                        objeto.setReservado13(c.getString(c.getColumnIndex("RESERVADO13")));
                        objeto.setReservado16(c.getString(c.getColumnIndex("RESERVADO16")));
                        objeto.setTime_stamp(c.getShort(c.getColumnIndex("RESERVADO1")));
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

    public List<MBPD> findByIDSuperior(String var1) throws SFAndroidException {
        List<MBPD> var2 = new ArrayList<MBPD>();
        try {
            this.open();
            StringBuilder sql = new StringBuilder();
            sql.append("select * from ").append(DATABASE_TABLE);
            sql.append(" where (NUMERO_SFA like '%' || '").append(var1).append("' || '%') ").append(" ORDER BY SEQUENCIA");
            Cursor c = db.rawQuery(sql.toString(), null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(23) != 0) {
                        long _id = c.getInt(23);
                        if (_id > 0) {
                            MBPD objeto = new MBPD();
                            objeto.setId(c.getInt(23));
                            objeto.setEmpresa(c.getShort(c.getColumnIndex("EMPRESA")));
                            objeto.setFilial(c.getShort(c.getColumnIndex("FILIAL")));
                            objeto.setNumero_sfa(c.getString(c.getColumnIndex("NUMERO_SFA")));
                            objeto.setSequencia(c.getString(c.getColumnIndex("SEQUENCIA")));
                            objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
                            objeto.setQtde(c.getDouble(c.getColumnIndex("QTDE")));
                            objeto.setVlr_unit(c.getDouble(c.getColumnIndex("VLR_UNIT")));
                            objeto.setVlr_total(c.getDouble(c.getColumnIndex("VLR_TOTAL")));
                            objeto.setDt_entrega(c.getString(c.getColumnIndex("DT_ENTREGA")));
                            objeto.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
                            objeto.setTes(c.getString(c.getColumnIndex("TES")));
                            objeto.setPorc_desc(c.getDouble(c.getColumnIndex("PORC_DESC")));
                            objeto.setVlr_ipi(c.getDouble(c.getColumnIndex("VLR_IPI")));
                            objeto.setGrupo(c.getString(c.getColumnIndex("GRUPO")));
                            objeto.setUnid_alt(c.getString(c.getColumnIndex("UNID_ALT")));
                            objeto.setQtde_alt(c.getDouble(c.getColumnIndex("QTDE_ALT")));
                            objeto.setReservado5(c.getDouble(c.getColumnIndex("RESERVADO5")));
                            objeto.setReservado6(c.getDouble(c.getColumnIndex("RESERVADO14")));
                            objeto.setReservado13(c.getString(c.getColumnIndex("RESERVADO13")));
                            objeto.setReservado16(c.getString(c.getColumnIndex("RESERVADO16")));
                            objeto.setTime_stamp(c.getShort(c.getColumnIndex("RESERVADO1")));
                            objeto.setD_I_R_T_Y_(c.getString(c.getColumnIndex("D_I_R_T_Y_")));
                            objeto.setD_E_L_E_T_(c.getString(c.getColumnIndex("D_E_L_E_T_")));
                            var2.add(objeto);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
            // SFAndroidException("ERRO NO SESSION FIND ALL [Consumo] "+e.toString());
        }
        return var2;
    }

    public MBPD salvar(MBPD objeto) throws SFAndroidException {
        try {
            this.open();
            db.execSQL(DATABASE_CREATE);
            int id = 0;
            long idResult = 0;
            if (objeto != null) {
                if (objeto.getId() == 0) {
                    ContentValues initialValues = new ContentValues();
                    initialValues.put("EMPRESA", objeto.getEmpresa());
                    initialValues.put("FILIAL", objeto.getFilial());
                    initialValues.put("NUMERO_SFA", objeto.getNumero_sfa());
                    initialValues.put("SEQUENCIA", objeto.getSequencia());
                    initialValues.put("C_PROD_PALM", objeto.getC_prod_palm());
                    initialValues.put("QTDE", objeto.getQtde());
                    initialValues.put("VLR_UNIT", objeto.getVlr_unit());
                    initialValues.put("VLR_TOTAL", objeto.getVlr_total());
                    initialValues.put("DT_ENTREGA", objeto.getDt_entrega());
                    initialValues.put("UNIDADE", objeto.getUnidade());
                    initialValues.put("TES", objeto.getTes());
                    initialValues.put("PORC_DESC", objeto.getPorc_desc());
                    initialValues.put("VLR_IPI", objeto.getVlr_ipi());
                    initialValues.put("GRUPO", objeto.getGrupo());
                    initialValues.put("UNID_ALT", objeto.getUnid_alt());
                    initialValues.put("QTDE_ALT", objeto.getQtde_alt());
                    initialValues.put("RESERVADO5", objeto.getReservado5());
                    initialValues.put("RESERVADO14", objeto.getReservado6());
                    initialValues.put("RESERVADO13", objeto.getReservado13());
                    initialValues.put("RESERVADO16", objeto.getReservado16());
                    initialValues.put("RESERVADO1", objeto.getTime_stamp());
                    initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
                    initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
                    try {
                        idResult = db.insert(DATABASE_TABLE, null, initialValues);
                        id = Integer.parseInt(String.valueOf(idResult));
                        //CoreSFAndroid.codigo_pedidoc = Integer.parseInt(String.valueOf(idResult));
                    } catch (Exception e) {
                        ControllerSFAndroid.getInstancia().showMessage(e.toString(),
                                "ERROR");
                    }
                } else {
                    ContentValues initialValues = new ContentValues();
                    initialValues.put("EMPRESA", objeto.getEmpresa());
                    initialValues.put("FILIAL", objeto.getFilial());
                    initialValues.put("NUMERO_SFA", objeto.getNumero_sfa());
                    initialValues.put("SEQUENCIA", objeto.getSequencia());
                    initialValues.put("C_PROD_PALM", objeto.getC_prod_palm());
                    initialValues.put("QTDE", objeto.getQtde());
                    initialValues.put("VLR_UNIT", objeto.getVlr_unit());
                    initialValues.put("VLR_TOTAL", objeto.getVlr_total());
                    initialValues.put("DT_ENTREGA", objeto.getDt_entrega());
                    initialValues.put("UNIDADE", objeto.getUnidade());
                    initialValues.put("TES", objeto.getTes());
                    initialValues.put("PORC_DESC", objeto.getPorc_desc());
                    initialValues.put("VLR_IPI", objeto.getVlr_ipi());
                    initialValues.put("GRUPO", objeto.getGrupo());
                    initialValues.put("UNID_ALT", objeto.getUnid_alt());
                    initialValues.put("QTDE_ALT", objeto.getQtde_alt());
                    initialValues.put("RESERVADO5", objeto.getReservado5());
                    initialValues.put("RESERVADO14", objeto.getReservado6());
                    initialValues.put("RESERVADO13", objeto.getReservado13());
                    initialValues.put("RESERVADO16", objeto.getReservado16());
                    initialValues.put("RESERVADO1", objeto.getTime_stamp());
                    initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
                    initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
                    boolean is = db.update(DATABASE_TABLE, initialValues, "R_E_C_N_O_ =" + objeto.getId(), null) > 0;
                    if (is) {
                        id = objeto.getId();
                        //CoreSFAndroid.codigo_pedidoc = id;
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
