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

public class MBCDCLDAO {
    private static final String DATABASE_TABLE = "MBCDCL";
    private static final String DATABASE_CREATE = "create table IF NOT EXISTS " + DATABASE_TABLE + " (CLIENTE VARCHAR  (14) ,  CGC_CPF VARCHAR  (14) , RAZ_SOCIAL VARCHAR  (60) , NOM_FANTASIA VARCHAR  (25) ,  ENDERECO VARCHAR  (35) , CIDADE VARCHAR  (20) , ESTADO VARCHAR  (3) , BAIRRO VARCHAR  (20) , CEP VARCHAR  (8) , TELEFONE VARCHAR  (15) , TELEFAX VARCHAR  (15) , INSCR_ESTAD VARCHAR  (15) , INSCR_MUNIC VARCHAR  (12) , VENDEDOR VARCHAR  (4) ,  RG VARCHAR  (15) ,  EMAIL VARCHAR  (60) ,  CONTATO VARCHAR  (60) ,D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
    private static MBCDCLDAO instancia = null;
    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;
    private String[] nomeColuna = {"R_E_C_N_O_", "CLIENTE", "CGC_CPF", "RAZ_SOCIAL", "NOM_FANTASIA", "ENDERECO", "CIDADE", "ESTADO", "BAIRRO", "CEP", "TELEFONE", "TELEFAX", "INSCR_ESTAD", "INSCR_MUNIC", "VENDEDOR", "RG", "EMAIL", "CONTATO", "D_I_R_T_Y_", "D_E_L_E_T_"};

    private MBCDCLDAO() {
        dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());
    }

    public static MBCDCLDAO getInstancia() {
        if (instancia == null) {
            instancia = new MBCDCLDAO();
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

    public MBCDCL salvar(MBCDCL objeto) throws SFAndroidException {
        try {
            this.open();
            db.execSQL(DATABASE_CREATE);
            int id = 0;
            long idResult = 0;
            if (objeto != null) {
                if (objeto.getId() == 0) {

                    ContentValues initialValues = new ContentValues();
                    initialValues.put("VENDEDOR", objeto.getVendedor());
                    initialValues.put("CLIENTE", objeto.getCliente());
                    initialValues.put("RAZ_SOCIAL", objeto.getRaz_social());
                    initialValues.put("NOM_FANTASIA", objeto.getNom_fantasia());
                    initialValues.put("ENDERECO", objeto.getEndereco());
                    initialValues.put("CIDADE", objeto.getCidade());
                    initialValues.put("ESTADO", objeto.getEstado());
                    initialValues.put("BAIRRO", objeto.getBairro());
                    initialValues.put("CEP", objeto.getCep());
                    initialValues.put("TELEFONE", objeto.getTelefone());
                    initialValues.put("TELEFAX", objeto.getTelefax());
                    initialValues.put("CGC_CPF", objeto.getCgc_cpf());
                    initialValues.put("INSCR_ESTAD", objeto.getInscr_estad());
                    initialValues.put("INSCR_MUNIC", objeto.getInscr_munic());
                    initialValues.put("RG", objeto.getRg());
                    initialValues.put("EMAIL", objeto.getEmail());
                    initialValues.put("CONTATO", objeto.getContato());
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
                    initialValues.put("VENDEDOR", objeto.getVendedor());
                    initialValues.put("CLIENTE", objeto.getCliente());
                    initialValues.put("RAZ_SOCIAL", objeto.getRaz_social());
                    initialValues.put("NOM_FANTASIA", objeto.getNom_fantasia());
                    initialValues.put("ENDERECO", objeto.getEndereco());
                    initialValues.put("CIDADE", objeto.getCidade());
                    initialValues.put("ESTADO", objeto.getEstado());
                    initialValues.put("BAIRRO", objeto.getBairro());
                    initialValues.put("CEP", objeto.getCep());
                    initialValues.put("TELEFONE", objeto.getTelefone());
                    initialValues.put("TELEFAX", objeto.getTelefax());
                    initialValues.put("CGC_CPF", objeto.getCgc_cpf());
                    initialValues.put("INSCR_ESTAD", objeto.getInscr_estad());
                    initialValues.put("INSCR_MUNIC", objeto.getInscr_munic());
                    initialValues.put("RG", objeto.getRg());
                    initialValues.put("EMAIL", objeto.getEmail());
                    initialValues.put("CONTATO", objeto.getContato());
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

    public MBCDCL findById(int id) throws SFAndroidException {
        MBCDCL objeto = null;
        try {
            if (id > 0) {
                this.open();
                Cursor c = db.query(true, DATABASE_TABLE, nomeColuna,
                        "R_E_C_N_O_ =" + id, null, null, null,
                        null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBCDCL();
                        objeto.setId(_id);
                        objeto.setVendedor(c.getString(c.getColumnIndex("VENDEDOR")));
                        objeto.setCliente(c.getString(c.getColumnIndex("CLIENTE")));
                        objeto.setRaz_social(c.getString(c.getColumnIndex("RAZ_SOCIAL")));
                        objeto.setNom_fantasia(c.getString(c.getColumnIndex("NOM_FANTASIA")));
                        objeto.setEndereco(c.getString(c.getColumnIndex("ENDERECO")));
                        objeto.setCidade(c.getString(c.getColumnIndex("CIDADE")));
                        objeto.setEstado(c.getString(c.getColumnIndex("ESTADO")));
                        objeto.setBairro(c.getString(c.getColumnIndex("BAIRRO")));
                        objeto.setCep(c.getString(c.getColumnIndex("CEP")));
                        objeto.setTelefone(c.getString(c.getColumnIndex("TELEFONE")));
                        objeto.setTelefax(c.getString(c.getColumnIndex("TELEFAX")));
                        objeto.setCgc_cpf(c.getString(c.getColumnIndex("CGC_CPF")));
                        objeto.setInscr_estad(c.getString(c.getColumnIndex("INSCR_ESTAD")));
                        objeto.setInscr_munic(c.getString(c.getColumnIndex("INSCR_MUNIC")));
                        objeto.setRg(c.getString(c.getColumnIndex("RG")));
                        objeto.setEmail(c.getString(c.getColumnIndex("EMAIL")));
                        objeto.setContato(c.getString(c.getColumnIndex("CONTATO")));
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

    public List<MBCDCL> findAll() throws SFAndroidException {
        List<MBCDCL> lista = new ArrayList<MBCDCL>();
        try {
            this.open();
            Cursor c = db.query(DATABASE_TABLE, nomeColuna, null, null, null,
                    null, null, null);
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0) != 0) {
                        long _id = c.getInt(0);
                        if (_id > 0) {
                            MBCDCL objeto = new MBCDCL();
                            objeto.setId(c.getInt(0));
                            objeto.setVendedor(c.getString(c.getColumnIndex("VENDEDOR")));
                            objeto.setCliente(c.getString(c.getColumnIndex("CLIENTE")));
                            objeto.setRaz_social(c.getString(c.getColumnIndex("RAZ_SOCIAL")));
                            objeto.setNom_fantasia(c.getString(c.getColumnIndex("NOM_FANTASIA")));
                            objeto.setEndereco(c.getString(c.getColumnIndex("ENDERECO")));
                            objeto.setCidade(c.getString(c.getColumnIndex("CIDADE")));
                            objeto.setEstado(c.getString(c.getColumnIndex("ESTADO")));
                            objeto.setBairro(c.getString(c.getColumnIndex("BAIRRO")));
                            objeto.setCep(c.getString(c.getColumnIndex("CEP")));
                            objeto.setTelefone(c.getString(c.getColumnIndex("TELEFONE")));
                            objeto.setTelefax(c.getString(c.getColumnIndex("TELEFAX")));
                            objeto.setCgc_cpf(c.getString(c.getColumnIndex("CGC_CPF")));
                            objeto.setInscr_estad(c.getString(c.getColumnIndex("INSCR_ESTAD")));
                            objeto.setInscr_munic(c.getString(c.getColumnIndex("INSCR_MUNIC")));
                            objeto.setRg(c.getString(c.getColumnIndex("RG")));
                            objeto.setEmail(c.getString(c.getColumnIndex("EMAIL")));
                            objeto.setContato(c.getString(c.getColumnIndex("CONTATO")));
                            objeto.setD_I_R_T_Y_(c.getString(c.getColumnIndex("D_I_R_T_Y_")));
                            objeto.setD_E_L_E_T_(c.getString(c.getColumnIndex("D_E_L_E_T_")));
                            lista.add(objeto);
                        }
                    }
                } while (c.moveToNext());
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
            // SFAndroidException("ERRO NO SESSION FIND ALL [Grupo] "+e.toString());
        }
        return lista;
    }

    public MBCDCL findNomeCliente(String codProduto) throws SFAndroidException {
        MBCDCL objeto = null;
        try {
            if (codProduto != "") {
                this.open();
                Cursor c = db.query(DATABASE_TABLE, nomeColuna, " RAZ_SOCIAL = '" + codProduto + "' ", null, null, null, null, null);
                if (c != null) {
                    c.moveToFirst();
                }
                if (c.getInt(0) != 0) {
                    int _id = c.getInt(0);
                    if (_id > 0) {
                        objeto = new MBCDCL();
                        objeto.setId(c.getInt(0));
                        objeto.setVendedor(c.getString(c.getColumnIndex("VENDEDOR")));
                        objeto.setCliente(c.getString(c.getColumnIndex("CLIENTE")));
                        objeto.setRaz_social(c.getString(c.getColumnIndex("RAZ_SOCIAL")));
                        objeto.setNom_fantasia(c.getString(c.getColumnIndex("NOM_FANTASIA")));
                        objeto.setEndereco(c.getString(c.getColumnIndex("ENDERECO")));
                        objeto.setCidade(c.getString(c.getColumnIndex("CIDADE")));
                        objeto.setEstado(c.getString(c.getColumnIndex("ESTADO")));
                        objeto.setBairro(c.getString(c.getColumnIndex("BAIRRO")));
                        objeto.setCep(c.getString(c.getColumnIndex("CEP")));
                        objeto.setTelefone(c.getString(c.getColumnIndex("TELEFONE")));
                        objeto.setTelefax(c.getString(c.getColumnIndex("TELEFAX")));
                        objeto.setCgc_cpf(c.getString(c.getColumnIndex("CGC_CPF")));
                        objeto.setInscr_estad(c.getString(c.getColumnIndex("INSCR_ESTAD")));
                        objeto.setInscr_munic(c.getString(c.getColumnIndex("INSCR_MUNIC")));
                        objeto.setRg(c.getString(c.getColumnIndex("RG")));
                        objeto.setEmail(c.getString(c.getColumnIndex("EMAIL")));
                        objeto.setContato(c.getString(c.getColumnIndex("CONTATO")));
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
            super(context, ControllerSFAndroid.DATABASE_NAME, null,
                    ControllerSFAndroid.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(ControllerSFAndroid.TAG, "Upgrading database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }

}
