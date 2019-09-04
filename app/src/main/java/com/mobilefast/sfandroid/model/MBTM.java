package com.mobilefast.sfandroid.model;

public class MBTM {
    private int DT_ENT;
    private String TIPO_CLI;
    private String TIPO_MOVTO;
    private String DESCRICAO;
    private String D_E_L_E_T_ = null;
    private String D_I_R_T_Y_ = null;
    private int id;

    public String getTIPO_CLI() {
        return this.TIPO_CLI;
    }

    public void setTIPO_CLI(String var1) {
        this.TIPO_CLI = var1;
    }

    public String getTIPO_MOVTO() {
        return this.TIPO_MOVTO;
    }

    public void setTIPO_MOVTO(String var1) {
        this.TIPO_MOVTO = var1;
    }

    public String getDESCRICAO() {
        return this.DESCRICAO;
    }

    public void setDESCRICAO(String var1) {
        this.DESCRICAO = var1;
    }

    public int getDT_ENT() {
        return this.DT_ENT;
    }

    public void setDT_ENT(int var1) {
        this.DT_ENT = var1;
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

    public void setD_E_L_E_T_(String var1) {
        this.D_E_L_E_T_ = var1;
    }

    public String getD_I_R_T_Y_() {
        return this.D_I_R_T_Y_;
    }

    public void setD_I_R_T_Y_(String var1) {
        this.D_I_R_T_Y_ = var1;
    }
}
