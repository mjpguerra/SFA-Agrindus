package com.mobilefast.sfandroid.model;

public class MBDE {
	private String C_PROD_PALM;
	private String CLIENTE;
	private double PORC_DESC;
	private String DT_VALIDADE;
	private String D_E_L_E_T_ = null;
	private String D_I_R_T_Y_ = null;
	private int id;

	public void setCLIENTE(String var1) {
		this.CLIENTE = var1;
	}

	public String getCLIENTE() {
		return this.CLIENTE;
	}

	public void setDT_VALIDADE(String var1) {
		this.DT_VALIDADE = var1;
	}

	public String getDT_VALIDADE() {
		return this.DT_VALIDADE;
	}

	public void setC_prod_palm(String var1) {
		this.C_PROD_PALM = var1;
	}

	public String getC_prod_palm() {
		return this.C_PROD_PALM;
	}

	public double getPORC_DESC() {
		return this.PORC_DESC;
	}

	public void setPORC_DESC(double var1) {
		this.PORC_DESC = var1;
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
