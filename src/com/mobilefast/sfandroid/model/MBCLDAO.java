package com.mobilefast.sfandroid.model;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

public class MBCLDAO {
	 private static MBCLDAO instancia = null; 
	 private DatabaseHelper dBHelper; 
	 private SQLiteDatabase db; 
	 public int offset = 0;
	 private static final int limite = 30;	
	 public static String nome;
	
	 public void somarOffset() {offset += 30;}
	 private String[] nomeColuna = {"R_E_C_N_O_", "CLIENTE",
				"FILIAL_ALFA", "LOJA", "CGC_CPF", "RAZ_SOCIAL", "NOM_FANTASIA",
				"TIPO_CLI", "ENDERECO", "CIDADE", "ESTADO", "BAIRRO", "CEP",
				"TELEFONE", "TELEFAX", "INSCR_ESTAD", "INSCR_MUNIC",
				"VENDEDOR", "TERR_1", "COND_PAGTO", "DESCONTO", "PRIORIDADE",
				"RISCO", "LM_CREDITO", "DT_VENCTO_LM", "CLASSE", "VLR_MA_COM",
				"MD_ATRASO", "VR_MA_ACUM", "NRO_COMPRAS", "DT_PR_COMP",
				"DT_ULT_COMP", "DT_ULT_VISITA", "QT_DUP_PG", "SD_ATUAL",
				"SD_CART_PD_LIB", "VLR_ATRASOS", "VLR_ACUM_VE", "SD_CART_PD",
				"QT_DP_PG_CART", "DT_ULT_DEV", "QT_CHEQ_DV", "DT_CH_DEV",
				"MA_ATR", "VLR_MA_FAT", "NRO_PG_ATR", "RG", "DT_NASC", "EMAIL",
				"RESERVADO1", "FLAG_CLIENTE", "FLAG_COND_PGTO" };
	 private static final String DATABASE_TABLE = "MBCL"; 
	 private static final String DATABASE_CREATE = "create table IF NOT EXISTS " + DATABASE_TABLE + " (CLIENTE VARCHAR  (14) , FILIAL_ALFA VARCHAR  (4) , LOJA VARCHAR  (2) , CGC_CPF VARCHAR  (14) , RAZ_SOCIAL VARCHAR  (60) , NOM_FANTASIA VARCHAR  (25) , TIPO_CLI VARCHAR  (1) , ENDERECO VARCHAR  (35) , CIDADE VARCHAR  (20) , ESTADO VARCHAR  (3) , BAIRRO VARCHAR  (20) , CEP VARCHAR  (8) , TELEFONE VARCHAR  (15) , TELEFAX VARCHAR  (15) , INSCR_ESTAD VARCHAR  (15) , INSCR_MUNIC VARCHAR  (12) , VENDEDOR VARCHAR  (4) , TERR_1 VARCHAR  (4) , COND_PAGTO VARCHAR  (4) , DESCONTO NUMERIC  (9,4) , PRIORIDADE VARCHAR  (1) , RISCO VARCHAR  (2) , LM_CREDITO INTEGER  (10) , DT_VENCTO_LM VARCHAR  (8) , CLASSE VARCHAR  (4) , VLR_MA_COM NUMERIC  (15,4) , MD_ATRASO INTEGER  (5) , VR_MA_ACUM NUMERIC  (15,4) , NRO_COMPRAS INTEGER  (5) , DT_PR_COMP VARCHAR  (8) , DT_ULT_COMP VARCHAR  (8) , DT_ULT_VISITA VARCHAR  (8) , QT_DUP_PG INTEGER  (5) , SD_ATUAL NUMERIC  (15,4) , SD_CART_PD_LIB NUMERIC  (15,4) , VLR_ATRASOS NUMERIC  (15,4) , VLR_ACUM_VE NUMERIC  (15,4) , SD_CART_PD NUMERIC  (15,4) , QT_DP_PG_CART INTEGER  (5) , DT_ULT_DEV VARCHAR  (8) , QT_CHEQ_DV INTEGER  (5) , DT_CH_DEV VARCHAR  (8) , MA_ATR INTEGER  (5) , VLR_MA_FAT NUMERIC  (15,4) , NRO_PG_ATR NUMERIC  (15,4) , RG VARCHAR  (15) , DT_NASC VARCHAR  (8) , EMAIL VARCHAR  (60) , RESERVADO1 INTEGER  (10) , FLAG_CLIENTE VARCHAR  (1) , FLAG_COND_PGTO INTEGER  (5) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";	 
	 private MBCLDAO(){dBHelper = new DatabaseHelper(ControllerSFAndroid.getInstancia().getContext());} 
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
	     
	     public static MBCLDAO getInstancia(){ 
	         if(instancia == null){ 
	            instancia = new MBCLDAO(); 
	         } 
	         return instancia; 
	     } 

	

	public List<MBCL> findCodCliente(String var1) throws SFAndroidException {
		ArrayList<MBCL> var2 = new ArrayList<MBCL>();

		try {
			this.open();
			Cursor var4 = this.db.query("MBCL", this.nomeColuna,
					" CLIENTE = \'" + var1 + "\' ", (String[]) null,
					(String) null, (String) null, (String) null, (String) null);
			if (var4.moveToFirst()) {
				do {
					if (var4.getInt(0) != 0 && (long) var4.getInt(0) > 0L) {
						MBCL var5 = new MBCL();
						var5.setId(var4.getInt(0));
						var5.setVendedor(var4.getString(var4
								.getColumnIndex("VENDEDOR")));
						var5.setCliente_alfa(var4.getString(var4
								.getColumnIndex("CLIENTE")));
						var5.setFilial_alfa(var4.getString(var4
								.getColumnIndex("FILIAL_ALFA")));
						var5.setLoja(var4.getString(var4.getColumnIndex("LOJA")));
						var5.setRaz_social(var4.getString(var4
								.getColumnIndex("RAZ_SOCIAL")));
						var5.setNom_fantasia(var4.getString(var4
								.getColumnIndex("NOM_FANTASIA")));
						var5.setEndereco(var4.getString(var4
								.getColumnIndex("ENDERECO")));
						var5.setCidade(var4.getString(var4
								.getColumnIndex("CIDADE")));
						var5.setEstado(var4.getString(var4
								.getColumnIndex("ESTADO")));
						var5.setBairro(var4.getString(var4
								.getColumnIndex("BAIRRO")));
						var5.setCep(var4.getString(var4.getColumnIndex("CEP")));
						var5.setTelefone(var4.getString(var4
								.getColumnIndex("TELEFONE")));
						var5.setTelefax(var4.getString(var4
								.getColumnIndex("TELEFAX")));
						var5.setCgc_cpf(var4.getString(var4
								.getColumnIndex("CGC_CPF")));
						var5.setInscr_estad(var4.getString(var4
								.getColumnIndex("INSCR_ESTAD")));
						var5.setInscr_munic(var4.getString(var4
								.getColumnIndex("INSCR_MUNIC")));
						var5.setTipo_cli(var4.getString(var4
								.getColumnIndex("TIPO_CLI")));
						var5.setTerr_1(var4.getString(var4
								.getColumnIndex("TERR_1")));
						var5.setCond_pagto(var4.getString(var4
								.getColumnIndex("COND_PAGTO")));
						var5.setDesconto(var4.getInt(var4
								.getColumnIndex("DESCONTO")));
						var5.setPrioridade(var4.getInt(var4
								.getColumnIndex("PRIORIDADE")));
						var5.setRisco(var4.getString(var4
								.getColumnIndex("RISCO")));
						var5.setLm_credito(var4.getInt(var4
								.getColumnIndex("LM_CREDITO")));
						var5.setDt_vencto_lm(var4.getString(var4
								.getColumnIndex("DT_VENCTO_LM")));
						var5.setClasse(var4.getString(var4
								.getColumnIndex("CLASSE")));
						var5.setVlr_ma_com(var4.getDouble(var4
								.getColumnIndex("VLR_MA_COM")));
						var5.setMd_atraso(var4.getShort(var4
								.getColumnIndex("MD_ATRASO")));
						var5.setVr_ma_acum(var4.getDouble(var4
								.getColumnIndex("VR_MA_ACUM")));
						var5.setNro_compras(var4.getShort(var4
								.getColumnIndex("NRO_COMPRAS")));
						var5.setDt_pr_comp(var4.getString(var4
								.getColumnIndex("DT_PR_COMP")));
						var5.setDt_ult_comp(var4.getString(var4
								.getColumnIndex("DT_ULT_COMP")));
						var5.setDt_ult_visita(var4.getString(var4
								.getColumnIndex("DT_ULT_VISITA")));
						var5.setQt_dup_pg(var4.getShort(var4
								.getColumnIndex("QT_DUP_PG")));
						var5.setSd_atual(var4.getDouble(var4
								.getColumnIndex("SD_ATUAL")));
						var5.setSd_cart_pd_lib(var4.getDouble(var4
								.getColumnIndex("SD_CART_PD_LIB")));
						var5.setSd_cart_pd(var4.getDouble(var4
								.getColumnIndex("SD_CART_PD")));
						var5.setVlr_atrasos(var4.getDouble(var4
								.getColumnIndex("VLR_ATRASOS")));
						var5.setVlr_acum_ve(var4.getDouble(var4
								.getColumnIndex("VLR_ACUM_VE")));
						var5.setQt_dp_pg_cart(var4.getShort(var4
								.getColumnIndex("QT_DP_PG_CART")));
						var5.setDt_ult_dev(var4.getString(var4
								.getColumnIndex("DT_ULT_DEV")));
						var5.setQt_cheq_dv(var4.getShort(var4
								.getColumnIndex("QT_CHEQ_DV")));
						var5.setDt_ch_dev(var4.getString(var4
								.getColumnIndex("DT_CH_DEV")));
						var5.setMa_atr(var4.getShort(var4
								.getColumnIndex("MA_ATR")));
						var5.setVlr_ma_fat(var4.getDouble(var4
								.getColumnIndex("VLR_MA_FAT")));
						var5.setNro_pg_atr(var4.getDouble(var4
								.getColumnIndex("NRO_PG_ATR")));
						var5.setRg(var4.getString(var4.getColumnIndex("RG")));
						var5.setDt_nasc(var4.getString(var4
								.getColumnIndex("DT_NASC")));
						var5.setEmail(var4.getString(var4
								.getColumnIndex("EMAIL")));
						var5.setFlag_cliente(var4.getString(var4
								.getColumnIndex("FLAG_CLIENTE")));
						var5.setFlag_cond_pgto(var4.getShort(var4
								.getColumnIndex("FLAG_COND_PGTO")));
						var2.add(var5);
					}
				} while (var4.moveToNext());
			}

			this.close();
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var2;
	}

	public List<MBCL> findSelect() throws SFAndroidException {
		List<MBCL> lista = new ArrayList<MBCL>();
		try {
			this.open();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from ").append(DATABASE_TABLE);

			if (nome != null && !nome.trim().equals("")) {
				sql.append(" where (NOM_FANTASIA like '%' || '").append(nome).append("' || '%' ").append( "or CLIENTE like '%' || '").append(nome).append("' || '%') ");
			}			
			sql.append(" ORDER BY NOM_FANTASIA ASC limit ").append(String.valueOf(limite)).append(" offset ").append(String.valueOf(offset));
			Cursor c = db.rawQuery(sql.toString(), null);
			if (c.moveToFirst()) {
				do {
					if (c.getInt(53) != 0) { 
						long _id = c.getInt(53);
						if (_id > 0) {
						MBCL var7 = new MBCL();
						var7.setId(c.getInt(53));
						var7.setVendedor(c.getString(c
								.getColumnIndex("VENDEDOR")));
						var7.setCliente_alfa(c.getString(c
								.getColumnIndex("CLIENTE")));
						var7.setFilial_alfa(c.getString(c
								.getColumnIndex("FILIAL_ALFA")));
						var7.setLoja(c.getString(c.getColumnIndex("LOJA")));
						var7.setRaz_social(c.getString(c
								.getColumnIndex("RAZ_SOCIAL")));
						var7.setNom_fantasia(c.getString(c
								.getColumnIndex("NOM_FANTASIA")));
						var7.setEndereco(c.getString(c
								.getColumnIndex("ENDERECO")));
						var7.setCidade(c.getString(c
								.getColumnIndex("CIDADE")));
						var7.setEstado(c.getString(c
								.getColumnIndex("ESTADO")));
						var7.setBairro(c.getString(c
								.getColumnIndex("BAIRRO")));
						var7.setCep(c.getString(c.getColumnIndex("CEP")));
						var7.setTelefone(c.getString(c
								.getColumnIndex("TELEFONE")));
						var7.setTelefax(c.getString(c
								.getColumnIndex("TELEFAX")));
						var7.setCgc_cpf(c.getString(c
								.getColumnIndex("CGC_CPF")));
						var7.setInscr_estad(c.getString(c
								.getColumnIndex("INSCR_ESTAD")));
						var7.setInscr_munic(c.getString(c
								.getColumnIndex("INSCR_MUNIC")));
						var7.setTipo_cli(c.getString(c
								.getColumnIndex("TIPO_CLI")));
						var7.setTerr_1(c.getString(c
								.getColumnIndex("TERR_1")));
						var7.setCond_pagto(c.getString(c
								.getColumnIndex("COND_PAGTO")));
						var7.setDesconto(c.getInt(c
								.getColumnIndex("DESCONTO")));
						var7.setPrioridade(c.getInt(c
								.getColumnIndex("PRIORIDADE")));
						var7.setRisco(c.getString(c
								.getColumnIndex("RISCO")));
						var7.setLm_credito(c.getInt(c
								.getColumnIndex("LM_CREDITO")));
						var7.setDt_vencto_lm(c.getString(c
								.getColumnIndex("DT_VENCTO_LM")));
						var7.setClasse(c.getString(c
								.getColumnIndex("CLASSE")));
						var7.setVlr_ma_com(c.getDouble(c
								.getColumnIndex("VLR_MA_COM")));
						var7.setMd_atraso(c.getShort(c
								.getColumnIndex("MD_ATRASO")));
						var7.setVr_ma_acum(c.getDouble(c
								.getColumnIndex("VR_MA_ACUM")));
						var7.setNro_compras(c.getShort(c
								.getColumnIndex("NRO_COMPRAS")));
						var7.setDt_pr_comp(c.getString(c
								.getColumnIndex("DT_PR_COMP")));
						var7.setDt_ult_comp(c.getString(c
								.getColumnIndex("DT_ULT_COMP")));
						var7.setDt_ult_visita(c.getString(c
								.getColumnIndex("DT_ULT_VISITA")));
						var7.setQt_dup_pg(c.getShort(c
								.getColumnIndex("QT_DUP_PG")));
						var7.setSd_atual(c.getDouble(c
								.getColumnIndex("SD_ATUAL")));
						var7.setSd_cart_pd_lib(c.getDouble(c
								.getColumnIndex("SD_CART_PD_LIB")));
						var7.setSd_cart_pd(c.getDouble(c
								.getColumnIndex("SD_CART_PD")));
						var7.setVlr_atrasos(c.getDouble(c
								.getColumnIndex("VLR_ATRASOS")));
						var7.setVlr_acum_ve(c.getDouble(c
								.getColumnIndex("VLR_ACUM_VE")));
						var7.setQt_dp_pg_cart(c.getShort(c
								.getColumnIndex("QT_DP_PG_CART")));
						var7.setDt_ult_dev(c.getString(c
								.getColumnIndex("DT_ULT_DEV")));
						var7.setQt_cheq_dv(c.getShort(c
								.getColumnIndex("QT_CHEQ_DV")));
						var7.setDt_ch_dev(c.getString(c
								.getColumnIndex("DT_CH_DEV")));
						var7.setMa_atr(c.getShort(c
								.getColumnIndex("MA_ATR")));
						var7.setVlr_ma_fat(c.getDouble(c
								.getColumnIndex("VLR_MA_FAT")));
						var7.setNro_pg_atr(c.getDouble(c
								.getColumnIndex("NRO_PG_ATR")));
						var7.setRg(c.getString(c.getColumnIndex("RG")));
						var7.setDt_nasc(c.getString(c
								.getColumnIndex("DT_NASC")));
						var7.setEmail(c.getString(c
								.getColumnIndex("EMAIL"))); 
						var7.setFlag_cliente(c.getString(c
								.getColumnIndex("FLAG_CLIENTE")));
						var7.setFlag_cond_pgto(c.getShort(c
								.getColumnIndex("FLAG_COND_PGTO")));

						lista.add(var7);
					}
				}
			} while (c.moveToNext());
		}

		this.close();
	} catch (Exception e) {
		e.printStackTrace();
		// throw new
		// SFAndroidException("ERRO NO SESSION FIND ALL [Produto] "+e.toString());
	}
	return lista;
}

	public List<MBCL> findSelectPesq() throws SFAndroidException {
		List<MBCL> lista = new ArrayList<MBCL>();
		try {
			this.open();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from ").append(DATABASE_TABLE);

			if (nome != null && !nome.trim().equals("")) {
				sql.append(" where (NOM_FANTASIA like '%' || '").append(nome)
						.append("' || '%' ")
						.append("or CLIENTE like '%' || '").append(nome)
						.append("' || '%') ");
			}

			sql.append(" ORDER BY RAZ_SOCIAL ASC ");
			Cursor c = db.rawQuery(sql.toString(), null);
			if (c.moveToFirst()) {
				do {
					if (c.getInt(53) != 0) { 
						long _id = c.getInt(53);
						if (_id > 0) {
						MBCL var7 = new MBCL();
						var7.setId(c.getInt(53));
						var7.setVendedor(c.getString(c
								.getColumnIndex("VENDEDOR")));
						var7.setCliente_alfa(c.getString(c
								.getColumnIndex("CLIENTE")));
						var7.setFilial_alfa(c.getString(c
								.getColumnIndex("FILIAL_ALFA")));
						var7.setLoja(c.getString(c.getColumnIndex("LOJA")));
						var7.setRaz_social(c.getString(c
								.getColumnIndex("RAZ_SOCIAL")));
						var7.setNom_fantasia(c.getString(c
								.getColumnIndex("NOM_FANTASIA")));
						var7.setEndereco(c.getString(c
								.getColumnIndex("ENDERECO")));
						var7.setCidade(c.getString(c
								.getColumnIndex("CIDADE")));
						var7.setEstado(c.getString(c
								.getColumnIndex("ESTADO")));
						var7.setBairro(c.getString(c
								.getColumnIndex("BAIRRO")));
						var7.setCep(c.getString(c.getColumnIndex("CEP")));
						var7.setTelefone(c.getString(c
								.getColumnIndex("TELEFONE")));
						var7.setTelefax(c.getString(c
								.getColumnIndex("TELEFAX")));
						var7.setCgc_cpf(c.getString(c
								.getColumnIndex("CGC_CPF")));
						var7.setInscr_estad(c.getString(c
								.getColumnIndex("INSCR_ESTAD")));
						var7.setInscr_munic(c.getString(c
								.getColumnIndex("INSCR_MUNIC")));
						var7.setTipo_cli(c.getString(c
								.getColumnIndex("TIPO_CLI")));
						var7.setTerr_1(c.getString(c
								.getColumnIndex("TERR_1")));
						var7.setCond_pagto(c.getString(c
								.getColumnIndex("COND_PAGTO")));
						var7.setDesconto(c.getInt(c
								.getColumnIndex("DESCONTO")));
						var7.setPrioridade(c.getInt(c
								.getColumnIndex("PRIORIDADE")));
						var7.setRisco(c.getString(c
								.getColumnIndex("RISCO")));
						var7.setLm_credito(c.getInt(c
								.getColumnIndex("LM_CREDITO")));
						var7.setDt_vencto_lm(c.getString(c
								.getColumnIndex("DT_VENCTO_LM")));
						var7.setClasse(c.getString(c
								.getColumnIndex("CLASSE")));
						var7.setVlr_ma_com(c.getDouble(c
								.getColumnIndex("VLR_MA_COM")));
						var7.setMd_atraso(c.getShort(c
								.getColumnIndex("MD_ATRASO")));
						var7.setVr_ma_acum(c.getDouble(c
								.getColumnIndex("VR_MA_ACUM")));
						var7.setNro_compras(c.getShort(c
								.getColumnIndex("NRO_COMPRAS")));
						var7.setDt_pr_comp(c.getString(c
								.getColumnIndex("DT_PR_COMP")));
						var7.setDt_ult_comp(c.getString(c
								.getColumnIndex("DT_ULT_COMP")));
						var7.setDt_ult_visita(c.getString(c
								.getColumnIndex("DT_ULT_VISITA")));
						var7.setQt_dup_pg(c.getShort(c
								.getColumnIndex("QT_DUP_PG")));
						var7.setSd_atual(c.getDouble(c
								.getColumnIndex("SD_ATUAL")));
						var7.setSd_cart_pd_lib(c.getDouble(c
								.getColumnIndex("SD_CART_PD_LIB")));
						var7.setSd_cart_pd(c.getDouble(c
								.getColumnIndex("SD_CART_PD")));
						var7.setVlr_atrasos(c.getDouble(c
								.getColumnIndex("VLR_ATRASOS")));
						var7.setVlr_acum_ve(c.getDouble(c
								.getColumnIndex("VLR_ACUM_VE")));
						var7.setQt_dp_pg_cart(c.getShort(c
								.getColumnIndex("QT_DP_PG_CART")));
						var7.setDt_ult_dev(c.getString(c
								.getColumnIndex("DT_ULT_DEV")));
						var7.setQt_cheq_dv(c.getShort(c
								.getColumnIndex("QT_CHEQ_DV")));
						var7.setDt_ch_dev(c.getString(c
								.getColumnIndex("DT_CH_DEV")));
						var7.setMa_atr(c.getShort(c
								.getColumnIndex("MA_ATR")));
						var7.setVlr_ma_fat(c.getDouble(c
								.getColumnIndex("VLR_MA_FAT")));
						var7.setNro_pg_atr(c.getDouble(c
								.getColumnIndex("NRO_PG_ATR")));
						var7.setRg(c.getString(c.getColumnIndex("RG")));
						var7.setDt_nasc(c.getString(c
								.getColumnIndex("DT_NASC")));
						var7.setEmail(c.getString(c
								.getColumnIndex("EMAIL"))); 
						var7.setFlag_cliente(c.getString(c
								.getColumnIndex("FLAG_CLIENTE")));
						var7.setFlag_cond_pgto(c.getShort(c
								.getColumnIndex("FLAG_COND_PGTO")));

						lista.add(var7);
					}
				}
			} while (c.moveToNext());
		}

		this.close();
	} catch (Exception e) {
		e.printStackTrace();
		// throw new
		// SFAndroidException("ERRO NO SESSION FIND ALL [Produto] "+e.toString());
	}
	return lista;
}


}
