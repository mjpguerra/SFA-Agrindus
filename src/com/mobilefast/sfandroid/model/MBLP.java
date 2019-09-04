package com.mobilefast.sfandroid.model;

public class MBLP {
	private int NRO_LISTA;
	private String C_PROD_PALM;
	private String UNIDADE;
	private double PRECO;
	private String DT_VIG_DE;
	private String C_PROD;
	private String D_E_L_E_T_ = null;
	private String D_I_R_T_Y_ = null;
	private int id;

	public void setUNIDADE(String var1) {
		this.UNIDADE = var1;
	}

	public String getUNIDADE() {
		return this.UNIDADE;
	}

	public void setDT_VIG_DE(String var1) {
		this.DT_VIG_DE = var1;
	}

	public String getDT_VIG_DE() {
		return this.DT_VIG_DE;
	}

	public void setC_PROD(String var1) {
		this.C_PROD = var1;
	}

	public String getC_PROD() {
		return this.C_PROD;
	}

	public void setC_prod_palm(String var1) {
		this.C_PROD_PALM = var1;
	}

	public String getC_prod_palm() {
		return this.C_PROD_PALM;
	}

	public void setNRO_LISTA(int var1) {
		this.NRO_LISTA = var1;
	}

	public int getNRO_LISTA() {
		return this.NRO_LISTA;
	}

	public double getPRECO() {
		return this.PRECO;
	}

	public void setPRECO(double var1) {
		this.PRECO = var1;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int var1) {
		this.id = var1;
	}

	public String getD_E_L_E_T_() {
		return this.D_E_L_E_T_;
	}

	public String getD_I_R_T_Y_() {
		return this.D_I_R_T_Y_;
	}

	public void setD_E_L_E_T_(String var1) {
		this.D_E_L_E_T_ = var1;
	}

	public void setD_I_R_T_Y_(String var1) {
		this.D_I_R_T_Y_ = var1;
	}
}
