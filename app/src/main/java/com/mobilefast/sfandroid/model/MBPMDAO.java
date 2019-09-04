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
import sfa.android.CoreSFAndroid;

public class MBPMDAO {


    private static final int limite = 18;
    private static final String DATABASE_TABLE = "MBPM";
    private static final String DATABASE_CREATE = "create table IF NOT EXISTS " + DATABASE_TABLE + " (NUMERO_SFA VARCHAR  (15) , CLIENTE VARCHAR  (14) , LOJA_CLI VARCHAR  (2) , TIPO_PED VARCHAR  (1) , COND_PAGTO VARCHAR  (4) , VENDEDOR VARCHAR  (4) , TEXTO_ESP VARCHAR  (60) , DT_PED VARCHAR  (8) , NRO_LISTA VARCHAR  (6) , TIPO_CLI VARCHAR  (1) , STATUS VARCHAR  (2) , PORC_DESC1 NUMERIC  (9,4) , PORC_DESC2 NUMERIC  (9,4) , EMISSAO VARCHAR  (1) , N_FISCAL VARCHAR  (6) , SERIE VARCHAR  (3) , RESERVADO14 VARCHAR  (30) , DT_LIVRE VARCHAR  (8) , NR_DUP VARCHAR  (6) , PORC_DESC3 NUMERIC  (9,4) , CARGA_TOTAL NUMERIC  (15,4) , EMPRESA INTEGER  (5) , FILIAL INTEGER  (5) , NUMERO INTEGER  (10) , RESERVADO13 VARCHAR  (30) , RESERVADO5 NUMERIC  (15,6) , RESERVADO1 INTEGER  (10) , RESERVADO2 INTEGER  (10) , RESERVADO3 INTEGER  (10) , RESERVADO4 INTEGER  (10) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBPMDAO instancia = null;
    public String nome;
    public int offset = 0;
    private Context context;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "NUMERO_SFA", "CLIENTE",
            "LOJA_CLI", "TIPO_PED", "COND_PAGTO", "VENDEDOR", "TEXTO_ESP",
            "DT_PED", "NRO_LISTA", "TIPO_CLI", "STATUS", "PORC_DESC1",
            "PORC_DESC2", "EMISSAO", "N_FISCAL", "SERIE", "RESERVADO14",
            "DT_LIVRE", "NR_DUP", "PORC_DESC3", "CARGA_TOTAL", "EMPRESA",
            "FILIAL", "NUMERO", "RESERVADO13", "RESERVADO5", "RESERVADO1",
            "RESERVADO2", "RESERVADO3", "RESERVADO4", "D_I_R_T_Y_",
            "D_E_L_E_T_"};

    private MBPMDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBPMDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBPMDAO();
        }
        return instancia;
    }

    public void somarOffset() {
        offset = offset + 18;
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
        boolean b = db.delete("MBPM", "R_E_C_N_O_ =" + id, null) > 0;
        this.close();
        return b;
    }

    public List<MBPM> findAllIDCliente(String var1) throws SFAndroidException {
        List<MBPM> var2 = new ArrayList<MBPM>();
        try {
            this.open();
            Cursor c = db.query(DATABASE_TABLE, nomeColuna, " CLIENTE = '"
                    + var1 + "' ", null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        long _id = c.getInt(0);
                        if (_id > 0) {

                            MBPM var5 = new MBPM();
                            var5.setId(c.getInt(0));
                            var5.setNumero_sfa(c.getString(c
                                    .getColumnIndex("NUMERO_SFA")));
                            var5.setCliente(c.getString(c
                                    .getColumnIndex("CLIENTE")));
                            var5.setLoja_cli(c.getString(c
                                    .getColumnIndex("LOJA_CLI")));
                            var5.setTipo_ped(c.getString(c
                                    .getColumnIndex("TIPO_PED")));
                            var5.setCond_pagto(c.getString(c
                                    .getColumnIndex("COND_PAGTO")));
                            var5.setVendedor(c.getString(c
                                    .getColumnIndex("VENDEDOR")));
                            var5.setTexto_esp(c.getString(c
                                    .getColumnIndex("TEXTO_ESP")));
                            var5.setDt_ped(c.getString(c
                                    .getColumnIndex("DT_PED")));
                            var5.setNro_lista(c.getString(c
                                    .getColumnIndex("NRO_LISTA")));
                            var5.setTipo_cli(c.getString(c
                                    .getColumnIndex("TIPO_CLI")));
                            var5.setStatus(c.getString(c
                                    .getColumnIndex("STATUS")));
                            var5.setEmissao(c.getString(c
                                    .getColumnIndex("EMISSAO")));
                            var5.setN_fiscal(c.getString(c
                                    .getColumnIndex("N_FISCAL")));
                            var5.setSerie(c.getString(c.getColumnIndex("SERIE")));
                            var5.setDt_livre(c.getString(c
                                    .getColumnIndex("DT_LIVRE")));
                            var5.setNr_dup(c.getString(c
                                    .getColumnIndex("NR_DUP")));
                            var5.setPorc_desc1(c.getDouble(c
                                    .getColumnIndex("PORC_DESC1")));
                            var5.setPorc_desc2(c.getDouble(c
                                    .getColumnIndex("PORC_DESC2")));
                            var5.setPorc_desc3(c.getDouble(c
                                    .getColumnIndex("PORC_DESC3")));
                            var5.setCarga_total(c.getDouble(c
                                    .getColumnIndex("CARGA_TOTAL")));
                            var5.setEmpresa(c.getShort(c
                                    .getColumnIndex("EMPRESA")));
                            var5.setFilial(c.getShort(c
                                    .getColumnIndex("FILIAL")));
                            var5.setNumero(c.getInt(c.getColumnIndex("NUMERO")));
                            var5.setReservado2(c.getInt(c
                                    .getColumnIndex("RESERVADO2")));
                            var5.setReservado3(c.getInt(c
                                    .getColumnIndex("RESERVADO3")));
                            var5.setReservado4(c.getInt(c
                                    .getColumnIndex("RESERVADO4")));
                            var5.setReservado5(c.getDouble(c
                                    .getColumnIndex("RESERVADO5")));
                            var5.setReservado13(c.getString(c
                                    .getColumnIndex("RESERVADO13")));
                            var5.setReservado14(c.getString(c
                                    .getColumnIndex("RESERVADO14")));
                            var5.setD_I_R_T_Y_(c.getString(c
                                    .getColumnIndex("D_I_R_T_Y_")));
                            var5.setD_E_L_E_T_(c.getString(c
                                    .getColumnIndex("D_E_L_E_T_")));
                            var2.add(var5);
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

    public List<MBPM> findAll() throws SFAndroidException {
        List<MBPM> lista = new ArrayList<MBPM>();
        try {
            this.open();
            Cursor c = db.query(DATABASE_TABLE, nomeColuna, null, null, null,
                    null, null, null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        long _id = c.getInt(0);
                        if (_id > 0) {
                            MBPM var5 = new MBPM();
                            var5.setId(c.getInt(0));
                            var5.setNumero_sfa(c.getString(c
                                    .getColumnIndex("NUMERO_SFA")));
                            var5.setCliente(c.getString(c
                                    .getColumnIndex("CLIENTE")));
                            var5.setLoja_cli(c.getString(c
                                    .getColumnIndex("LOJA_CLI")));
                            var5.setTipo_ped(c.getString(c
                                    .getColumnIndex("TIPO_PED")));
                            var5.setCond_pagto(c.getString(c
                                    .getColumnIndex("COND_PAGTO")));
                            var5.setVendedor(c.getString(c
                                    .getColumnIndex("VENDEDOR")));
                            var5.setTexto_esp(c.getString(c
                                    .getColumnIndex("TEXTO_ESP")));
                            var5.setDt_ped(c.getString(c
                                    .getColumnIndex("DT_PED")));
                            var5.setNro_lista(c.getString(c
                                    .getColumnIndex("NRO_LISTA")));
                            var5.setTipo_cli(c.getString(c
                                    .getColumnIndex("TIPO_CLI")));
                            var5.setStatus(c.getString(c
                                    .getColumnIndex("STATUS")));
                            var5.setEmissao(c.getString(c
                                    .getColumnIndex("EMISSAO")));
                            var5.setN_fiscal(c.getString(c
                                    .getColumnIndex("N_FISCAL")));
                            var5.setSerie(c.getString(c.getColumnIndex("SERIE")));
                            var5.setDt_livre(c.getString(c
                                    .getColumnIndex("DT_LIVRE")));
                            var5.setNr_dup(c.getString(c
                                    .getColumnIndex("NR_DUP")));
                            var5.setPorc_desc1(c.getDouble(c
                                    .getColumnIndex("PORC_DESC1")));
                            var5.setPorc_desc2(c.getDouble(c
                                    .getColumnIndex("PORC_DESC2")));
                            var5.setPorc_desc3(c.getDouble(c
                                    .getColumnIndex("PORC_DESC3")));
                            var5.setCarga_total(c.getDouble(c
                                    .getColumnIndex("CARGA_TOTAL")));
                            var5.setEmpresa(c.getShort(c
                                    .getColumnIndex("EMPRESA")));
                            var5.setFilial(c.getShort(c
                                    .getColumnIndex("FILIAL")));
                            var5.setNumero(c.getInt(c.getColumnIndex("NUMERO")));
                            var5.setReservado2(c.getInt(c
                                    .getColumnIndex("RESERVADO2")));
                            var5.setReservado3(c.getInt(c
                                    .getColumnIndex("RESERVADO3")));
                            var5.setReservado4(c.getInt(c
                                    .getColumnIndex("RESERVADO4")));
                            var5.setReservado5(c.getDouble(c
                                    .getColumnIndex("RESERVADO5")));
                            var5.setReservado13(c.getString(c
                                    .getColumnIndex("RESERVADO13")));
                            var5.setReservado14(c.getString(c
                                    .getColumnIndex("RESERVADO14")));
                            var5.setD_I_R_T_Y_(c.getString(c
                                    .getColumnIndex("D_I_R_T_Y_")));
                            var5.setD_E_L_E_T_(c.getString(c
                                    .getColumnIndex("D_E_L_E_T_")));

                            lista.add(var5);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
            // SFAndroidException("ERRO NO SESSION FIND ALL [Rota] "+e.toString());
        }
        return lista;
    }

    public List<MBPM> findStatus(String Status_Ped) throws SFAndroidException {
        List<MBPM> lista = new ArrayList<MBPM>();
        try {
            this.open();
            Cursor c = null;
            if (Status_Ped.equals("Todos")) {
                c = db.query(DATABASE_TABLE, nomeColuna, null, null, null, null, null, null);
            } else if (Status_Ped.equals("Novos")) {
                c = db.query(DATABASE_TABLE, nomeColuna, " D_I_R_T_Y_ = 'T'", null, null, null, null, null);
            } else if (Status_Ped.equals("Cotação")) {
                c = db.query(DATABASE_TABLE, nomeColuna, " D_I_R_T_Y_ = 'C'", null, null, null, null, null);
            } else if (Status_Ped.equals("Transmitidos")) {
                c = db.query(DATABASE_TABLE, nomeColuna, " D_I_R_T_Y_ = 'F'", null, null, null, null, null);
            }

            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        int _id = c.getInt(0);
                        if (_id > 0) {

                            MBPM objeto = new MBPM();
                            objeto.setId(_id);
                            objeto.setEmpresa(c.getShort(c
                                    .getColumnIndex("EMPRESA")));
                            objeto.setFilial(c.getShort(c
                                    .getColumnIndex("FILIAL")));
                            objeto.setNumero(c.getInt(c
                                    .getColumnIndex("NUMERO")));
                            objeto.setNumero_sfa(c.getString(c
                                    .getColumnIndex("NUMERO_SFA")));
                            objeto.setCliente(c.getString(c
                                    .getColumnIndex("CLIENTE")));
                            objeto.setLoja_cli(c.getString(c
                                    .getColumnIndex("LOJA_CLI")));
                            objeto.setTipo_ped(c.getString(c
                                    .getColumnIndex("TIPO_PED")));
                            objeto.setCond_pagto(c.getString(c
                                    .getColumnIndex("COND_PAGTO")));
                            objeto.setVendedor(c.getString(c
                                    .getColumnIndex("VENDEDOR")));
                            objeto.setTexto_esp(c.getString(c
                                    .getColumnIndex("TEXTO_ESP")));
                            objeto.setDt_ped(c.getString(c
                                    .getColumnIndex("DT_PED")));
                            objeto.setNro_lista(c.getString(c
                                    .getColumnIndex("NRO_LISTA")));
                            objeto.setTipo_cli(c.getString(c
                                    .getColumnIndex("TIPO_CLI")));
                            objeto.setStatus(c.getString(c
                                    .getColumnIndex("STATUS")));
                            objeto.setEmissao(c.getString(c
                                    .getColumnIndex("EMISSAO")));
                            objeto.setN_fiscal(c.getString(c
                                    .getColumnIndex("N_FISCAL")));
                            objeto.setSerie(c.getString(c
                                    .getColumnIndex("SERIE")));
                            objeto.setTime_stamp(c.getShort(c
                                    .getColumnIndex("RESERVADO1")));
                            objeto.setDt_livre(c.getString(c
                                    .getColumnIndex("DT_LIVRE")));
                            objeto.setNr_dup(c.getString(c
                                    .getColumnIndex("NR_DUP")));
                            objeto.setPorc_desc1(c.getDouble(c
                                    .getColumnIndex("PORC_DESC1")));
                            objeto.setPorc_desc2(c.getDouble(c
                                    .getColumnIndex("PORC_DESC2")));
                            objeto.setPorc_desc3(c.getDouble(c
                                    .getColumnIndex("PORC_DESC3")));
                            objeto.setCarga_total(c.getDouble(c
                                    .getColumnIndex("CARGA_TOTAL")));
                            objeto.setReservado2(c.getInt(c
                                    .getColumnIndex("RESERVADO2")));
                            objeto.setReservado3(c.getInt(c
                                    .getColumnIndex("RESERVADO3")));
                            objeto.setReservado4(c.getInt(c
                                    .getColumnIndex("RESERVADO4")));
                            objeto.setReservado5(c.getDouble(c
                                    .getColumnIndex("RESERVADO5")));
                            objeto.setReservado13(c.getString(c
                                    .getColumnIndex("RESERVADO13")));
                            objeto.setReservado14(c.getString(c
                                    .getColumnIndex("RESERVADO14")));
                            objeto.setD_I_R_T_Y_(c.getString(c
                                    .getColumnIndex("D_I_R_T_Y_")));
                            objeto.setD_E_L_E_T_(c.getString(c
                                    .getColumnIndex("D_E_L_E_T_")));

                            lista.add(objeto);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
            // SFAndroidException("ERRO NO SESSION FIND ALL [CondicaoPagamento] "+e.toString());
        }
        return lista;
    }

    public List<MBPM> findAllStatus(String var1) throws SFAndroidException {
        List<MBPM> lista = new ArrayList<MBPM>();
        try {
            this.open();
            Cursor c = db.query(DATABASE_TABLE, nomeColuna, " STATUS = '"
                    + var1 + "' ", null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        int _id = c.getInt(0);
                        if (_id > 0) {

                            MBPM objeto = new MBPM();
                            objeto.setId(_id);
                            objeto.setEmpresa(c.getShort(c
                                    .getColumnIndex("EMPRESA")));
                            objeto.setFilial(c.getShort(c
                                    .getColumnIndex("FILIAL")));
                            objeto.setNumero(c.getInt(c
                                    .getColumnIndex("NUMERO")));
                            objeto.setNumero_sfa(c.getString(c
                                    .getColumnIndex("NUMERO_SFA")));
                            objeto.setCliente(c.getString(c
                                    .getColumnIndex("CLIENTE")));
                            objeto.setLoja_cli(c.getString(c
                                    .getColumnIndex("LOJA_CLI")));
                            objeto.setTipo_ped(c.getString(c
                                    .getColumnIndex("TIPO_PED")));
                            objeto.setCond_pagto(c.getString(c
                                    .getColumnIndex("COND_PAGTO")));
                            objeto.setVendedor(c.getString(c
                                    .getColumnIndex("VENDEDOR")));
                            objeto.setTexto_esp(c.getString(c
                                    .getColumnIndex("TEXTO_ESP")));
                            objeto.setDt_ped(c.getString(c
                                    .getColumnIndex("DT_PED")));
                            objeto.setNro_lista(c.getString(c
                                    .getColumnIndex("NRO_LISTA")));
                            objeto.setTipo_cli(c.getString(c
                                    .getColumnIndex("TIPO_CLI")));
                            objeto.setStatus(c.getString(c
                                    .getColumnIndex("STATUS")));
                            objeto.setEmissao(c.getString(c
                                    .getColumnIndex("EMISSAO")));
                            objeto.setN_fiscal(c.getString(c
                                    .getColumnIndex("N_FISCAL")));
                            objeto.setSerie(c.getString(c
                                    .getColumnIndex("SERIE")));
                            objeto.setTime_stamp(c.getShort(c
                                    .getColumnIndex("RESERVADO1")));
                            objeto.setDt_livre(c.getString(c
                                    .getColumnIndex("DT_LIVRE")));
                            objeto.setNr_dup(c.getString(c
                                    .getColumnIndex("NR_DUP")));
                            objeto.setPorc_desc1(c.getDouble(c
                                    .getColumnIndex("PORC_DESC1")));
                            objeto.setPorc_desc2(c.getDouble(c
                                    .getColumnIndex("PORC_DESC2")));
                            objeto.setPorc_desc3(c.getDouble(c
                                    .getColumnIndex("PORC_DESC3")));
                            objeto.setCarga_total(c.getDouble(c
                                    .getColumnIndex("CARGA_TOTAL")));
                            objeto.setReservado2(c.getInt(c
                                    .getColumnIndex("RESERVADO2")));
                            objeto.setReservado3(c.getInt(c
                                    .getColumnIndex("RESERVADO3")));
                            objeto.setReservado4(c.getInt(c
                                    .getColumnIndex("RESERVADO4")));
                            objeto.setReservado5(c.getDouble(c
                                    .getColumnIndex("RESERVADO5")));
                            objeto.setReservado13(c.getString(c
                                    .getColumnIndex("RESERVADO13")));
                            objeto.setReservado14(c.getString(c
                                    .getColumnIndex("RESERVADO14")));
                            objeto.setD_I_R_T_Y_(c.getString(c
                                    .getColumnIndex("D_I_R_T_Y_")));
                            objeto.setD_E_L_E_T_(c.getString(c
                                    .getColumnIndex("D_E_L_E_T_")));

                            lista.add(objeto);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
            // SFAndroidException("ERRO NO SESSION FIND ALL [CondicaoPagamento] "+e.toString());
        }
        return lista;
    }

    public MBPM findUltimoCliente(String codCliente) throws SFAndroidException {
        MBPM objeto = null;
        try {
            if (codCliente != "") {
                this.open();
                Cursor c = db.query(DATABASE_TABLE, nomeColuna,
                        " CLIENTE = '" + codCliente + "' ", null, null, null,
                        null, null);
                if (c != null) {
                    c.moveToLast();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBPM();
                        objeto.setId(c.getInt(0));
                        objeto.setEmpresa(c.getShort(c
                                .getColumnIndex("EMPRESA")));
                        objeto.setFilial(c.getShort(c
                                .getColumnIndex("FILIAL")));
                        objeto.setNumero(c.getInt(c
                                .getColumnIndex("NUMERO")));
                        objeto.setNumero_sfa(c.getString(c
                                .getColumnIndex("NUMERO_SFA")));
                        objeto.setCliente(c.getString(c
                                .getColumnIndex("CLIENTE")));
                        objeto.setLoja_cli(c.getString(c
                                .getColumnIndex("LOJA_CLI")));
                        objeto.setTipo_ped(c.getString(c
                                .getColumnIndex("TIPO_PED")));
                        objeto.setCond_pagto(c.getString(c
                                .getColumnIndex("COND_PAGTO")));
                        objeto.setVendedor(c.getString(c
                                .getColumnIndex("VENDEDOR")));
                        objeto.setTexto_esp(c.getString(c
                                .getColumnIndex("TEXTO_ESP")));
                        objeto.setDt_ped(c.getString(c
                                .getColumnIndex("DT_PED")));
                        objeto.setNro_lista(c.getString(c
                                .getColumnIndex("NRO_LISTA")));
                        objeto.setTipo_cli(c.getString(c
                                .getColumnIndex("TIPO_CLI")));
                        objeto.setStatus(c.getString(c
                                .getColumnIndex("STATUS")));
                        objeto.setEmissao(c.getString(c
                                .getColumnIndex("EMISSAO")));
                        objeto.setN_fiscal(c.getString(c
                                .getColumnIndex("N_FISCAL")));
                        objeto.setSerie(c.getString(c
                                .getColumnIndex("SERIE")));
                        objeto.setTime_stamp(c.getShort(c
                                .getColumnIndex("RESERVADO1")));
                        objeto.setDt_livre(c.getString(c
                                .getColumnIndex("DT_LIVRE")));
                        objeto.setNr_dup(c.getString(c
                                .getColumnIndex("NR_DUP")));
                        objeto.setPorc_desc1(c.getDouble(c
                                .getColumnIndex("PORC_DESC1")));
                        objeto.setPorc_desc2(c.getDouble(c
                                .getColumnIndex("PORC_DESC2")));
                        objeto.setPorc_desc3(c.getDouble(c
                                .getColumnIndex("PORC_DESC3")));
                        objeto.setCarga_total(c.getDouble(c
                                .getColumnIndex("CARGA_TOTAL")));
                        objeto.setReservado2(c.getInt(c
                                .getColumnIndex("RESERVADO2")));
                        objeto.setReservado3(c.getInt(c
                                .getColumnIndex("RESERVADO3")));
                        objeto.setReservado4(c.getInt(c
                                .getColumnIndex("RESERVADO4")));
                        objeto.setReservado5(c.getDouble(c
                                .getColumnIndex("RESERVADO5")));
                        objeto.setReservado13(c.getString(c
                                .getColumnIndex("RESERVADO13")));
                        objeto.setReservado14(c.getString(c
                                .getColumnIndex("RESERVADO14")));
                        objeto.setD_I_R_T_Y_(c.getString(c
                                .getColumnIndex("D_I_R_T_Y_")));
                        objeto.setD_E_L_E_T_(c.getString(c
                                .getColumnIndex("D_E_L_E_T_")));

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

    public MBPM findById(int id) throws SFAndroidException {
        MBPM objeto = null;
        try {
            if (id > 0) {
                this.open();
                Cursor c = db.query(true, DATABASE_TABLE, nomeColuna,
                        " R_E_C_N_O_ = " + id, null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBPM();
                        objeto.setId(_id);
                        objeto.setEmpresa(c.getShort(c
                                .getColumnIndex("EMPRESA")));
                        objeto.setFilial(c.getShort(c.getColumnIndex("FILIAL")));
                        objeto.setNumero(c.getInt(c.getColumnIndex("NUMERO")));
                        objeto.setNumero_sfa(c.getString(c
                                .getColumnIndex("NUMERO_SFA")));
                        objeto.setCliente(c.getString(c
                                .getColumnIndex("CLIENTE")));
                        objeto.setLoja_cli(c.getString(c
                                .getColumnIndex("LOJA_CLI")));
                        objeto.setTipo_ped(c.getString(c
                                .getColumnIndex("TIPO_PED")));
                        objeto.setCond_pagto(c.getString(c
                                .getColumnIndex("COND_PAGTO")));
                        objeto.setVendedor(c.getString(c
                                .getColumnIndex("VENDEDOR")));
                        objeto.setTexto_esp(c.getString(c
                                .getColumnIndex("TEXTO_ESP")));
                        objeto.setDt_ped(c.getString(c.getColumnIndex("DT_PED")));
                        objeto.setNro_lista(c.getString(c
                                .getColumnIndex("NRO_LISTA")));
                        objeto.setTipo_cli(c.getString(c
                                .getColumnIndex("TIPO_CLI")));
                        objeto.setStatus(c.getString(c.getColumnIndex("STATUS")));
                        objeto.setEmissao(c.getString(c
                                .getColumnIndex("EMISSAO")));
                        objeto.setN_fiscal(c.getString(c
                                .getColumnIndex("N_FISCAL")));
                        objeto.setSerie(c.getString(c.getColumnIndex("SERIE")));
                        objeto.setTime_stamp(c.getShort(c
                                .getColumnIndex("RESERVADO1")));
                        objeto.setDt_livre(c.getString(c
                                .getColumnIndex("DT_LIVRE")));
                        objeto.setNr_dup(c.getString(c.getColumnIndex("NR_DUP")));
                        objeto.setPorc_desc1(c.getDouble(c
                                .getColumnIndex("PORC_DESC1")));
                        objeto.setPorc_desc2(c.getDouble(c
                                .getColumnIndex("PORC_DESC2")));
                        objeto.setPorc_desc3(c.getDouble(c
                                .getColumnIndex("PORC_DESC3")));
                        objeto.setCarga_total(c.getDouble(c
                                .getColumnIndex("CARGA_TOTAL")));
                        objeto.setReservado2(c.getInt(c
                                .getColumnIndex("RESERVADO2")));
                        objeto.setReservado3(c.getInt(c
                                .getColumnIndex("RESERVADO3")));
                        objeto.setReservado4(c.getInt(c
                                .getColumnIndex("RESERVADO4")));
                        objeto.setReservado5(c.getDouble(c
                                .getColumnIndex("RESERVADO5")));
                        objeto.setReservado13(c.getString(c
                                .getColumnIndex("RESERVADO13")));
                        objeto.setReservado14(c.getString(c
                                .getColumnIndex("RESERVADO14")));
                        objeto.setD_I_R_T_Y_(c.getString(c
                                .getColumnIndex("D_I_R_T_Y_")));
                        objeto.setD_E_L_E_T_(c.getString(c
                                .getColumnIndex("D_E_L_E_T_")));
                    }
                }
            }
        } catch (Exception e) {
            this.close();
            e.printStackTrace();
            Log.w(ControllerSFAndroid.TAG,
                    "ERROR AO CARREGAR:  " + e.toString());
            // throw new SFAndroidException("Tabela pode estar vazia ");
        }
        this.close();
        return objeto;
    }

    public MBPM salvar(MBPM objeto) throws SFAndroidException {
        try {
            this.open();
            db.execSQL(DATABASE_CREATE);
            int id = 0;
            long idResult = 0;
            if (objeto != null) {
                if (objeto.getId() == 0) {
                    ContentValues initialValues = new ContentValues();
                    initialValues.put("NUMERO_SFA", objeto.getNumero_sfa());
                    initialValues.put("CLIENTE", objeto.getCliente());
                    initialValues.put("LOJA_CLI", objeto.getLoja_cli());
                    initialValues.put("TIPO_PED", objeto.getTipo_ped());
                    initialValues.put("COND_PAGTO", objeto.getCond_pagto());
                    initialValues.put("VENDEDOR", objeto.getVendedor());
                    initialValues.put("TEXTO_ESP", objeto.getTexto_esp());
                    initialValues.put("DT_PED", objeto.getDt_ped());
                    initialValues.put("NRO_LISTA", objeto.getNro_lista());
                    initialValues.put("TIPO_CLI", objeto.getTipo_cli());
                    initialValues.put("STATUS", objeto.getStatus());
                    initialValues.put("PORC_DESC1", objeto.getPorc_desc1());
                    initialValues.put("PORC_DESC2", objeto.getPorc_desc2());
                    initialValues.put("EMISSAO", objeto.getEmissao());
                    initialValues.put("N_FISCAL", objeto.getN_fiscal());
                    initialValues.put("SERIE", objeto.getSerie());
                    initialValues.put("RESERVADO14", objeto.getReservado14());
                    initialValues.put("RESERVADO1", objeto.getReservado1());
                    initialValues.put("DT_LIVRE", objeto.getDt_livre());
                    initialValues.put("NR_DUP", objeto.getNr_dup());
                    initialValues.put("PORC_DESC3", objeto.getPorc_desc3());
                    initialValues.put("CARGA_TOTAL", objeto.getCarga_total());
                    initialValues.put("EMPRESA", objeto.getEmpresa());
                    initialValues.put("FILIAL", objeto.getFilial());
                    initialValues.put("NUMERO", objeto.getNumero());
                    initialValues.put("RESERVADO2", objeto.getReservado2());
                    initialValues.put("RESERVADO3", objeto.getReservado3());
                    initialValues.put("RESERVADO4", objeto.getReservado4());
                    initialValues.put("RESERVADO5", objeto.getReservado5());
                    initialValues.put("RESERVADO13", objeto.getReservado13());
                    initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
                    initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
                    try {
                        idResult = db.insert(DATABASE_TABLE, null, initialValues);
                        id = Integer.parseInt(String.valueOf(idResult));
                        CoreSFAndroid.codigo_pedidoc = Integer.parseInt(String.valueOf(idResult));
                    } catch (Exception e) {
                        ControllerSFAndroid.getInstancia().showMessage(e.toString(),
                                "ERROR");
                    }
                } else {
                    ContentValues initialValues = new ContentValues();
                    initialValues.put("NUMERO_SFA", objeto.getNumero_sfa());
                    initialValues.put("CLIENTE", objeto.getCliente());
                    initialValues.put("LOJA_CLI", objeto.getLoja_cli());
                    initialValues.put("TIPO_PED", objeto.getTipo_ped());
                    initialValues.put("COND_PAGTO", objeto.getCond_pagto());
                    initialValues.put("VENDEDOR", objeto.getVendedor());
                    initialValues.put("TEXTO_ESP", objeto.getTexto_esp());
                    initialValues.put("DT_PED", objeto.getDt_ped());
                    initialValues.put("NRO_LISTA", objeto.getNro_lista());
                    initialValues.put("TIPO_CLI", objeto.getTipo_cli());
                    initialValues.put("STATUS", objeto.getStatus());
                    initialValues.put("PORC_DESC1", objeto.getPorc_desc1());
                    initialValues.put("PORC_DESC2", objeto.getPorc_desc2());
                    initialValues.put("EMISSAO", objeto.getEmissao());
                    initialValues.put("N_FISCAL", objeto.getN_fiscal());
                    initialValues.put("SERIE", objeto.getSerie());
                    initialValues.put("RESERVADO14", objeto.getReservado14());
                    initialValues.put("RESERVADO1", objeto.getReservado1());
                    initialValues.put("DT_LIVRE", objeto.getDt_livre());
                    initialValues.put("NR_DUP", objeto.getNr_dup());
                    initialValues.put("PORC_DESC3", objeto.getPorc_desc3());
                    initialValues.put("CARGA_TOTAL", objeto.getCarga_total());
                    initialValues.put("EMPRESA", objeto.getEmpresa());
                    initialValues.put("FILIAL", objeto.getFilial());
                    initialValues.put("NUMERO", objeto.getNumero());
                    initialValues.put("RESERVADO2", objeto.getReservado2());
                    initialValues.put("RESERVADO3", objeto.getReservado3());
                    initialValues.put("RESERVADO4", objeto.getReservado4());
                    initialValues.put("RESERVADO5", objeto.getReservado5());
                    initialValues.put("RESERVADO13", objeto.getReservado13());
                    initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
                    initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
                    boolean is = db.update(DATABASE_TABLE, initialValues, "R_E_C_N_O_ =" + objeto.getId(), null) > 0;
                    if (is) {
                        id = objeto.getId();
                        CoreSFAndroid.codigo_pedidoc = id;
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
