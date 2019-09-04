package com.mobilefast.sfandroid.model;

import java.util.ArrayList;
import java.util.List;

import sfa.android.ControllerSFAndroid;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilefast.sfandroid.exception.SFAndroidException;

public class MBPRDAO {

	private static final String DATABASE_CREATE = "CREATE TABLE MBPR(  C_PROD_PALM VARCHAR  (20) , DESCRICAO VARCHAR  (50) , UNIDADE VARCHAR  (2) , PORC_ICM NUMERIC  (9,4) , PORC_IPI NUMERIC  (9,4) , PESO_BRUTO NUMERIC  (15,6) , QDTE_P_EMBAL INTEGER  (10) , PRAZO_ENTREGA INTEGER  (4) , PORC_COMISSAO NUMERIC  (9,4) , COD_BARRA VARCHAR  (15) , RESERVADO8 VARCHAR  (30) , POSICAO_ESTQ VARCHAR  (23) , PRECO_VENDA NUMERIC  (15,6) , RESERVADO3 NUMERIC  (15,6) , RESERVADO4 NUMERIC  (15,6) , RESERVADO9 NUMERIC  (15,6) , RESERVADO10 NUMERIC  (15,6) , FATOR_UNI NUMERIC  (15,6) , RESERVADO7 VARCHAR  (14) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)";
	private static final String DATABASE_TABLE = "MBPR";
	private static MBPRDAO instancia = null;
	private static final int limite = 30;
	private Context context;
	private MBPRDAO.DatabaseHelper dBHelper;
	private SQLiteDatabase db;
	public static String nome;
	private String[] nomeColuna;
	public int offset = 0;

	private MBPRDAO() {
		super();
		String[] var1 = new String[] { "R_E_C_N_O_", "C_PROD_PALM",
				"DESCRICAO", "UNIDADE", "PORC_ICM", "PORC_IPI", "PESO_BRUTO",
				"QDTE_P_EMBAL", "PRAZO_ENTREGA", "PORC_COMISSAO", "COD_BARRA",
				"RESERVADO8", "POSICAO_ESTQ", "PRECO_VENDA", "RESERVADO3",
				"RESERVADO4", "RESERVADO9", "RESERVADO10", "FATOR_UNI", "RESERVADO7" };
		this.nomeColuna = var1;
		this.dBHelper = new MBPRDAO.DatabaseHelper(ControllerSFAndroid.getInstancia()
				.getContext());
	}
	public void somarOffset(int total) {
		offset = offset + 30;
}
	public static MBPRDAO getInstancia() {
		if (instancia == null) {
			instancia = new MBPRDAO();
		}

		return instancia;
	}

	public void close() {
		this.db.close();
	}

	public boolean deletar(long var1) {
		this.open();
		boolean var3;
		if (this.db.delete("MBPR", "R_E_C_N_O_ =" + var1, (String[]) null) > 0) {
			var3 = true;
		} else {
			var3 = false;
		}

		this.close();
		return var3;
	}


  	 
	public List<MBPR> findAll() throws SFAndroidException {
		List<MBPR> lista = new ArrayList<MBPR>();
		try {
			this.open();
			Cursor c = db.query(DATABASE_TABLE, nomeColuna, null, null, null,
					null, null, null);
			if (c.moveToFirst()) {
				do {
					if (c.getInt(0) != 0) {
						long _id = c.getInt(0);
						if (_id > 0) {
							MBPR objeto = new MBPR();
							objeto.setId(c.getInt(0));
							objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
							objeto.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
							objeto.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
							objeto.setPorc_icm(c.getDouble(c.getColumnIndex("PORC_ICM")));
							objeto.setPorc_ipi(c.getDouble(c.getColumnIndex("PORC_IPI")));
							objeto.setPeso_bruto(c.getInt(c.getColumnIndex("PESO_BRUTO")));
							objeto.setQtde_p_embal(c.getDouble(c.getColumnIndex("QDTE_P_EMBAL")));
							objeto.setPrazo_entrega(c.getInt(c.getColumnIndex("PRAZO_ENTREGA")));
							objeto.setPorc_comissao(c.getDouble(c.getColumnIndex("PORC_COMISSAO")));
							objeto.setCod_barra(c.getString(c.getColumnIndex("COD_BARRA")));
							objeto.setPosicao_estq(c.getString(c.getColumnIndex("POSICAO_ESTQ")));
							objeto.setFator_uni(c.getDouble(c.getColumnIndex("FATOR_UNI")));
							objeto.setPreco_venda(c.getDouble(c.getColumnIndex("PRECO_VENDA")));
							objeto.setReservado1(c.getInt(c.getColumnIndex("RESERVADO8")));
							objeto.setReservado3(c.getDouble(c.getColumnIndex("RESERVADO3")));
							objeto.setReservado4(c.getDouble(c.getColumnIndex("RESERVADO4")));
							objeto.setReservado7(c.getString(c.getColumnIndex("RESERVADO7")));
							objeto.setReservado9(c.getDouble(c.getColumnIndex("RESERVADO9")));
							objeto.setReservado10(c.getDouble(c.getColumnIndex("RESERVADO10")));
							lista.add(objeto);
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
	public MBPR findCodProduto(String codProduto) throws SFAndroidException {
		MBPR var7 = null;
		try {
			if (codProduto != "") {
				this.open();
				Cursor var6 = db.query(DATABASE_TABLE, nomeColuna," C_PROD_PALM = '" + codProduto + "' ", null, null, null,
						null, null);
				if (var6 != null) {
					var6.moveToFirst();
				} 
				if (var6.getInt(0) != 0) { 
					int _id = var6.getInt(0);
					if (_id > 0) {
						var7 = new MBPR();
						var7.setId(var6.getInt(0));
						var7.setC_prod_palm(var6.getString(var6.getColumnIndex("C_PROD_PALM")));
						var7.setDescricao(var6.getString(var6.getColumnIndex("DESCRICAO")));
						var7.setUnidade(var6.getString(var6.getColumnIndex("UNIDADE")));
						var7.setPorc_icm(var6.getDouble(var6.getColumnIndex("PORC_ICM")));
						var7.setPorc_ipi(var6.getDouble(var6.getColumnIndex("PORC_IPI")));
						var7.setPeso_bruto(var6.getInt(var6.getColumnIndex("PESO_BRUTO")));
						var7.setQtde_p_embal(var6.getDouble(var6.getColumnIndex("QDTE_P_EMBAL")));
						var7.setPrazo_entrega(var6.getInt(var6.getColumnIndex("PRAZO_ENTREGA")));
						var7.setPorc_comissao(var6.getDouble(var6.getColumnIndex("PORC_COMISSAO")));
						var7.setCod_barra(var6.getString(var6.getColumnIndex("COD_BARRA")));
						var7.setPosicao_estq(var6.getString(var6.getColumnIndex("POSICAO_ESTQ")));
						var7.setFator_uni(var6.getDouble(var6.getColumnIndex("FATOR_UNI")));
						var7.setPreco_venda(var6.getDouble(var6.getColumnIndex("PRECO_VENDA")));
						var7.setReservado1(var6.getInt(var6.getColumnIndex("RESERVADO8")));
						var7.setReservado3(var6.getDouble(var6.getColumnIndex("RESERVADO3")));
						var7.setReservado4(var6.getDouble(var6.getColumnIndex("RESERVADO4")));
						var7.setReservado7(var6.getString(var6.getColumnIndex("RESERVADO7")));
						var7.setReservado9(var6.getDouble(var6.getColumnIndex("RESERVADO9")));
						var7.setReservado10(var6.getDouble(var6.getColumnIndex("RESERVADO10")));
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
		return var7;
	}

	public List findGrupoProduto(String var1) throws SFAndroidException {
		ArrayList var2 = new ArrayList();

		try {
			this.open();
			Cursor var6 = this.db.query("MBPR", this.nomeColuna, " GRUPO = \'"
					+ var1 + "\' ", (String[]) null, (String) null,
					(String) null, (String) null, (String) null);
			if (var6.moveToFirst()) {
				do {
					if (var6.getInt(0) != 0 && (long) var6.getInt(0) > 0L) {
						MBPR var7 = new MBPR();
						var7.setId(var6.getInt(0));
						var7.setC_prod_palm(var6.getString(var6.getColumnIndex("C_PROD_PALM")));
						var7.setDescricao(var6.getString(var6.getColumnIndex("DESCRICAO")));
						var7.setUnidade(var6.getString(var6.getColumnIndex("UNIDADE")));
						var7.setPorc_icm(var6.getDouble(var6.getColumnIndex("PORC_ICM")));
						var7.setPorc_ipi(var6.getDouble(var6.getColumnIndex("PORC_IPI")));
						var7.setPeso_bruto(var6.getInt(var6.getColumnIndex("PESO_BRUTO")));
						var7.setQtde_p_embal(var6.getDouble(var6.getColumnIndex("QDTE_P_EMBAL")));
						var7.setPrazo_entrega(var6.getInt(var6.getColumnIndex("PRAZO_ENTREGA")));
						var7.setPorc_comissao(var6.getDouble(var6.getColumnIndex("PORC_COMISSAO")));
						var7.setCod_barra(var6.getString(var6.getColumnIndex("COD_BARRA")));
						var7.setPosicao_estq(var6.getString(var6.getColumnIndex("POSICAO_ESTQ")));
						var7.setFator_uni(var6.getDouble(var6.getColumnIndex("FATOR_UNI")));
						var7.setPreco_venda(var6.getDouble(var6.getColumnIndex("PRECO_VENDA")));
						var7.setReservado1(var6.getInt(var6.getColumnIndex("RESERVADO8")));
						var7.setReservado3(var6.getDouble(var6.getColumnIndex("RESERVADO3")));
						var7.setReservado4(var6.getDouble(var6.getColumnIndex("RESERVADO4")));
						var7.setReservado7(var6.getString(var6.getColumnIndex("RESERVADO7")));
						var7.setReservado9(var6.getDouble(var6.getColumnIndex("RESERVADO9")));
						var7.setReservado10(var6.getDouble(var6.getColumnIndex("RESERVADO10")));
					}
				} while (var6.moveToNext());
			}

			this.close();
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var2;
	}
	public List<MBPR> findSelectpesq() throws SFAndroidException {
		List<MBPR> lista = new ArrayList<MBPR>();
		try {
			open();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(DATABASE_TABLE);
			if (nome != null && !nome.trim().equals("")) {
				sql.append(" where (DESCRICAO like '%' || '").append(nome).append("' || '%' ").append( "or C_PROD_PALM like '%' || '").append(nome).append("' || '%') ");
			}		
			sql.append(" ORDER BY DESCRICAO ASC ");
			Cursor c = db.rawQuery(sql.toString(), null);
			if (c.moveToFirst()) {
				do {
					if (c.getInt(21) != 0) {
						long _id = c.getInt(21);
						if (_id > 0) {
							MBPR objeto = new MBPR();
					        objeto.setId(c.getInt(21));
							objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
							objeto.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
							objeto.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
							objeto.setPorc_icm(c.getDouble(c.getColumnIndex("PORC_ICM")));
							objeto.setPorc_ipi(c.getDouble(c.getColumnIndex("PORC_IPI")));
							objeto.setPeso_bruto(c.getInt(c.getColumnIndex("PESO_BRUTO")));
							objeto.setQtde_p_embal(c.getInt(c.getColumnIndex("QDTE_P_EMBAL")));
							objeto.setPrazo_entrega(c.getInt(c.getColumnIndex("PRAZO_ENTREGA")));
							objeto.setPorc_comissao(c.getDouble(c.getColumnIndex("PORC_COMISSAO")));
							objeto.setCod_barra(c.getString(c.getColumnIndex("COD_BARRA")));
							objeto.setPosicao_estq(c.getString(c.getColumnIndex("POSICAO_ESTQ")));
							objeto.setFator_uni(c.getDouble(c.getColumnIndex("FATOR_UNI")));
							objeto.setPreco_venda(c.getDouble(c.getColumnIndex("PRECO_VENDA")));
							objeto.setReservado1(c.getInt(c.getColumnIndex("RESERVADO8")));
							objeto.setReservado3(c.getDouble(c.getColumnIndex("RESERVADO3")));
							objeto.setReservado4(c.getDouble(c.getColumnIndex("RESERVADO4")));
							objeto.setReservado7(c.getString(c.getColumnIndex("RESERVADO7")));
							objeto.setReservado9(c.getDouble(c.getColumnIndex("RESERVADO9")));
							objeto.setReservado10(c.getDouble(c.getColumnIndex("RESERVADO10")));
							lista.add(objeto);
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
	public List<MBPR> findSelect() throws SFAndroidException {
		List<MBPR> lista = new ArrayList<MBPR>();
		try {
			this.open();
		    
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ").append(DATABASE_TABLE);
 			if (nome != null && !nome.trim().equals("")) {
 				sql.append(" where (DESCRICAO like '%' || '").append(nome).append("' || '%' ").append("or C_PROD_PALM like '%' || '").append(nome).append("' || '%') ");
 			}			
		
		   sql.append(" ORDER BY DESCRICAO ASC");
		   sql.append(" limit ").append(String.valueOf(limite)).append(" offset ").append(String.valueOf(offset));
 			Cursor c = db.rawQuery(sql.toString(), null);
			if (c.moveToFirst()) {
				do {
					if (c.getInt(21) != 0) {
						long _id = c.getInt(21);
						if (_id > 0) { 
							MBPR objeto = new MBPR();
							    objeto.setId(c.getInt(21)); 
								objeto.setC_prod_palm(c.getString(c.getColumnIndex("C_PROD_PALM")));
								objeto.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
								objeto.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
								objeto.setPorc_icm(c.getDouble(c.getColumnIndex("PORC_ICM")));
								objeto.setPorc_ipi(c.getDouble(c.getColumnIndex("PORC_IPI")));
								objeto.setPeso_bruto(c.getDouble(c.getColumnIndex("PESO_BRUTO")));
								objeto.setQtde_p_embal(c.getDouble(c.getColumnIndex("QDTE_P_EMBAL")));
								objeto.setPrazo_entrega(c.getInt(c.getColumnIndex("PRAZO_ENTREGA")));
								objeto.setPorc_comissao(c.getDouble(c.getColumnIndex("PORC_COMISSAO")));
								objeto.setCod_barra(c.getString(c.getColumnIndex("COD_BARRA")));
								objeto.setPosicao_estq(c.getString(c.getColumnIndex("POSICAO_ESTQ")));
								objeto.setFator_uni(c.getDouble(c.getColumnIndex("FATOR_UNI")));
								objeto.setPreco_venda(c.getDouble(c.getColumnIndex("PRECO_VENDA")));
								objeto.setReservado1(c.getInt(c.getColumnIndex("RESERVADO8")));
								objeto.setReservado3(c.getDouble(c.getColumnIndex("RESERVADO3")));
								objeto.setReservado4(c.getDouble(c.getColumnIndex("RESERVADO4")));
								objeto.setReservado7(c.getString(c.getColumnIndex("RESERVADO7")));
								objeto.setReservado9(c.getDouble(c.getColumnIndex("RESERVADO9")));
								objeto.setReservado10(c.getDouble(c.getColumnIndex("RESERVADO10")));
							lista.add(objeto);
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


	public String getPath() {
		this.open();
		return this.db.getPath();
	}

	public void open() {
		this.db = this.dBHelper.getWritableDatabase();
	}

	public void somarOffset() {
		this.offset += 30;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context var1) {
			super(var1, "/sdcard/SFAndroid/SFAndroid.db", (CursorFactory) null,
					1);
		}

		public void onCreate(SQLiteDatabase var1) {
			var1.execSQL("CREATE TABLE IF NOT EXISTS MBPR (C_PROD_PALM VARCHAR  (20) , DESCRICAO VARCHAR  (50) , UNIDADE VARCHAR  (2) , PORC_ICM INTEGER  (9) , PORC_IPI INTEGER  (9) , PESO_BRUTO INTEGER  (15) , RESERVADO8 INTEGER  (10) , PRAZO_ENTREGA INTEGER  (10) , PORC_COMISSAO INTEGER  (9) , COD_BARRA VARCHAR  (15) , RESERVADO7 VARCHAR  (30) , POSICAO_ESTQ VARCHAR  (23) , PRECO_VENDA INTEGER  (15) , RESERVADO3 INTEGER  (15) , RESERVADO4 INTEGER  (15) , RESERVADO9 INTEGER  (15) , RESERVADO10 INTEGER  (15) , QTDE_P_EMBAL INTEGER  (15) , FATOR_UNI INTEGER  (15) , C_PROD VARCHAR  (20) , GRUPO VARCHAR  (4) , D_I_R_T_Y_ BIT (1), D_E_L_E_T_ BIT (1),R_E_C_N_O_ INTEGER  PRIMARY KEY)");
		}

		public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
			Log.w("DBAdapter", "Upgrading database from version " + var2
					+ " to " + var3 + ", which will destroy all old data");
			var1.execSQL("DROP TABLE IF EXISTS titles");
			this.onCreate(var1);
		}
	}
}
