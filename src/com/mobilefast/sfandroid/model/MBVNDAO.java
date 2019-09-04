package com.mobilefast.sfandroid.model;

import sfa.android.ControllerSFAndroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

public class MBVNDAO {
	 private static MBVNDAO instancia = null; 
	 private DatabaseHelper dBHelper; 
	 private SQLiteDatabase db; 
	 private String[] nomeColuna = {"R_E_C_N_O_", "VENDEDOR", "FILIAL_ALFA", "NOME_CONH", "PORC_COMISSAO", "SALDO", "SENHA", "MENS1", "MENS2", "STATUS", "PEDIDO_INI", "PEDIDO_FIM", "CLIENTE_INI", "CLIENTE_FIM", "PROXIMO_PED", "PROXIMO_CLI", "TITULO_LIVRE", "TIPO_LIVRE", "TAM_LIVRE", "DEC_LIVRE", "LOG_NUM", "LOG_STAT", "TRATA_LIMITE", "ASSINA_PED","D_I_R_T_Y_","D_E_L_E_T_" };

	 private static final String DATABASE_TABLE = "MBVN";
	 private static final String DATABASE_CREATE = "create table IF NOT EXISTS "+DATABASE_TABLE+" ( VENDEDOR VARCHAR  (4) , FILIAL_ALFA VARCHAR  (4) , NOME_CONH VARCHAR  (15) , PORC_COMISSAO NUMERIC  (9,4) , SALDO NUMERIC  (15,4) , SENHA VARCHAR  (6) , MENS1 VARCHAR  (60) , MENS2 VARCHAR  (60) , STATUS INTEGER  (5) , PEDIDO_INI VARCHAR  (8) , PEDIDO_FIM VARCHAR  (8) , CLIENTE_INI VARCHAR  (14) , CLIENTE_FIM VARCHAR  (14) , PROXIMO_PED VARCHAR  (8) , PROXIMO_CLI VARCHAR  (14) , TITULO_LIVRE VARCHAR  (12) , TIPO_LIVRE VARCHAR  (1) , TAM_LIVRE INTEGER  (5) , DEC_LIVRE INTEGER  (5) , LOG_NUM INTEGER  (5) , LOG_STAT INTEGER  (5) , TRATA_LIMITE VARCHAR  (1) , ASSINA_PED VARCHAR  (1) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
	 private MBVNDAO(){dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());} 
	 public void open(){	db = dBHelper.getWritableDatabase();} 
	 public void close(){db.close();} 
	 public String getPath(){this.open();return db.getPath();} 
	 public boolean deletar(long id){this.open();boolean b = db.delete(DATABASE_TABLE,ControllerSFAndroid.KEY_ROWID + "=" + id, null) > 0;this.close();return b;} 
	  
	 private static class DatabaseHelper extends SQLiteOpenHelper { 
	 	DatabaseHelper(Context context) {super(context, ControllerSFAndroid.DATABASE_NAME, null, ControllerSFAndroid.DATABASE_VERSION);} 
	 	@Override 
	 	public void onCreate(SQLiteDatabase db){db.execSQL(DATABASE_CREATE);} 
	 	@Override 
	 	public void onUpgrade(SQLiteDatabase db, int oldVersion,int	newVersion){Log.w(ControllerSFAndroid.TAG, "Upgrading database from version " + oldVersion	+ " to "+ newVersion + ", which will destroy all old data");db.execSQL("DROP TABLE IF EXISTS titles");onCreate(db);} 
	 } 
	     
	     public static MBVNDAO getInstancia(){ 
	         if(instancia == null){ 
	            instancia = new MBVNDAO(); 
	         } 
	         return instancia; 
	     } 

	public MBVN salvar(MBVN objeto) throws SFAndroidException {
		try {
			this.open();
			db.execSQL(DATABASE_CREATE);
			int id = 0;
			long idResult = 0;
			if (objeto != null) {
				if (objeto.getId() == 0) {
					ContentValues initialValues = new ContentValues();
				
					initialValues.put("MENS2", objeto.getMens2());
					initialValues.put("VENDEDOR", objeto.getVendedor());
					initialValues.put("FILIAL_ALFA", objeto.getFilial_alfa());
					initialValues.put("NOME_CONH", objeto.getNome_conh());
					initialValues.put("PORC_COMISSAO", objeto.getPorc_comissao());
					initialValues.put("SALDO", objeto.getSaldo());
					initialValues.put("SENHA", objeto.getSenha());
					initialValues.put("MENS1", objeto.getMens1());
					initialValues.put("STATUS", objeto.getStatus());
					initialValues.put("PEDIDO_INI", objeto.getPedido_ini());
					initialValues.put("PEDIDO_FIM", objeto.getPedido_fim());
					initialValues.put("PROXIMO_PED", objeto.getProximo_ped());
					initialValues.put("CLIENTE_INI", objeto.getCliente_ini());
					initialValues.put("CLIENTE_FIM", objeto.getCliente_fim());
					initialValues.put("PROXIMO_CLI", objeto.getProximo_cli());
					initialValues.put("TRATA_LIMITE", objeto.getTrata_limite());
					initialValues.put("ASSINA_PED", objeto.getAssina_ped());
					initialValues.put("TITULO_LIVRE", objeto.getTitulo_livre());
					initialValues.put("TIPO_LIVRE", objeto.getTipo_livre());
					initialValues.put("TAM_LIVRE", objeto.getTam_livre());
					initialValues.put("DEC_LIVRE", objeto.getDec_livre());
					initialValues.put("LOG_NUM", objeto.getLog_num());
					initialValues.put("LOG_STAT", objeto.getLog_stat());
					initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
					initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
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
					initialValues.put("MENS2", objeto.getMens2());
					initialValues.put("VENDEDOR", objeto.getVendedor());
					initialValues.put("FILIAL_ALFA", objeto.getFilial_alfa());
					initialValues.put("NOME_CONH", objeto.getNome_conh());
					initialValues.put("PORC_COMISSAO", objeto.getPorc_comissao());
					initialValues.put("SALDO", objeto.getSaldo());
					initialValues.put("SENHA", objeto.getSenha());
					initialValues.put("MENS1", objeto.getMens1());
					initialValues.put("STATUS", objeto.getStatus());
					initialValues.put("PEDIDO_INI", objeto.getPedido_ini());
					initialValues.put("PEDIDO_FIM", objeto.getPedido_fim());
					initialValues.put("PROXIMO_PED", objeto.getProximo_ped());
					initialValues.put("CLIENTE_INI", objeto.getCliente_ini());
					initialValues.put("CLIENTE_FIM", objeto.getCliente_fim());
					initialValues.put("PROXIMO_CLI", objeto.getProximo_cli());
					initialValues.put("TRATA_LIMITE", objeto.getTrata_limite());
					initialValues.put("ASSINA_PED", objeto.getAssina_ped());
					initialValues.put("TITULO_LIVRE", objeto.getTitulo_livre());
					initialValues.put("TIPO_LIVRE", objeto.getTipo_livre());
					initialValues.put("TAM_LIVRE", objeto.getTam_livre());
					initialValues.put("DEC_LIVRE", objeto.getDec_livre());
					initialValues.put("LOG_NUM", objeto.getLog_num());
					initialValues.put("LOG_STAT", objeto.getLog_stat());
					initialValues.put("D_I_R_T_Y_", objeto.getD_I_R_T_Y_());
					initialValues.put("D_E_L_E_T_", objeto.getD_E_L_E_T_());
					boolean is = db.update(DATABASE_TABLE, initialValues,
							"R_E_C_N_O_=" + objeto.getId(), null) > 0;
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

	public MBVN findById(int id) throws SFAndroidException {
		MBVN objeto = null;
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
						 objeto = new MBVN();	 				
						 objeto.setId(_id);
	                     objeto.setVendedor(c.getString(c.getColumnIndex("VENDEDOR")));
	                     objeto.setFilial_alfa(c.getString(c.getColumnIndex("FILIAL_ALFA")));
	                     objeto.setNome_conh(c.getString(c.getColumnIndex("NOME_CONH")));
	                     objeto.setPorc_comissao(c.getDouble(c.getColumnIndex("PORC_COMISSAO")));
	                     objeto.setSaldo(c.getDouble(c.getColumnIndex("SALDO")));
	                     objeto.setSenha(c.getString(c.getColumnIndex("SENHA")));
	                     objeto.setMens1(c.getString(c.getColumnIndex("MENS1")));
	                     objeto.setMens2(c.getString(c.getColumnIndex("MENS2")));
	                     objeto.setStatus(c.getShort(c.getColumnIndex("STATUS")));
	                     objeto.setPedido_ini(c.getString(c.getColumnIndex("PEDIDO_INI")));
	                     objeto.setPedido_fim(c.getString(c.getColumnIndex("PEDIDO_FIM")));
	                     objeto.setProximo_ped(c.getString(c.getColumnIndex("PROXIMO_PED")));
	                     objeto.setCliente_ini(c.getString(c.getColumnIndex("CLIENTE_INI")));
	                     objeto.setCliente_fim(c.getString(c.getColumnIndex("CLIENTE_FIM")));
	                     objeto.setProximo_cli(c.getString(c.getColumnIndex("PROXIMO_CLI")));
	                     objeto.setTrata_limite(c.getString(c.getColumnIndex("TRATA_LIMITE")));
	                     objeto.setAssina_ped(c.getString(c.getColumnIndex("ASSINA_PED")));
	                     objeto.setTitulo_livre(c.getString(c.getColumnIndex("TITULO_LIVRE")));
	                     objeto.setTipo_livre(c.getString(c.getColumnIndex("TIPO_LIVRE")));
	                     objeto.setTam_livre(c.getShort(c.getColumnIndex("TAM_LIVRE")));
	                     objeto.setDec_livre(c.getShort(c.getColumnIndex("DEC_LIVRE")));
	                     objeto.setLog_num(c.getShort(c.getColumnIndex("LOG_NUM")));
	                     objeto.setLog_stat(c.getShort(c.getColumnIndex("LOG_STAT")));
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

}
