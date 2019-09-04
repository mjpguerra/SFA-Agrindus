package com.mobilefast.sfandroid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MBPM implements Serializable {

   private static final long serialVersionUID = 1L;
   private String D_E_L_E_T_ = null;
   private String D_I_R_T_Y_ = null;
   private int assina;
   private double carga_total;
   private String cliente;
   private String cond_pagto;
   private Timestamp data_alter;
   private String dt_livre;
   private String dt_ped;
   private String emissao;
   private short emite_nf;
   private int empresa;
   private int filial;
   private short hora_alter;
   private int id;
   private String intr;
   private String loja_cli;
   private String n_fiscal;
   private String nr_dup;
   private String nro_lista;
   private int numero;
   private String numero_integ;
   private String numero_sfa;
   private String operador;
   private double porc_desc1;
   private double porc_desc2;
   private double porc_desc3;
   private int reservado1;
   private Timestamp reservado10;
   private Timestamp reservado11;
   private Timestamp reservado12;
   private String reservado13;
   private String reservado14;
   private String reservado15;
   private String reservado16;
   private int reservado2 = 0;
   private int reservado3;
   private int reservado4;
   private double reservado5;
   private double reservado6;
   private double reservado7;
   private double reservado8;
   private Timestamp reservado9;
   private String serie;
   private String status;
   private String texto_esp;
   private short time_stamp;
   private String tipo_cli;
   private String tipo_ped;
   private String vendedor;
   private int version;
   private double vlr_fat;
   private double vlr_ped;


   public MBPM() {
      super();
   }

   public int getAssina() {
      return this.assina;
   }

   public double getCarga_total() {
      return this.carga_total;
   }

   public String getCliente() {
      return this.cliente;
   }

   public String getCond_pagto() {
      return this.cond_pagto;
   }

   public String getD_E_L_E_T_() {
      return this.D_E_L_E_T_;
   }

   public String getD_I_R_T_Y_() {
      return this.D_I_R_T_Y_;
   }

   public Timestamp getData_alter() {
      return this.data_alter;
   }

   public String getDt_livre() {
      return this.dt_livre;
   }

   public String getDt_ped() {
      return this.dt_ped;
   }

   public String getEmissao() {
      return this.emissao;
   }

   public short getEmite_nf() {
      return this.emite_nf;
   }

   public int getEmpresa() {
      return this.empresa;
   }

   public int getFilial() {
      return this.filial;
   }

   public short getHora_alter() {
      return this.hora_alter;
   }

   public int getId() {
      return this.id;
   }

   public String getIntr() {
      return this.intr;
   }

   public String getLoja_cli() {
      return this.loja_cli;
   }

   public String getN_fiscal() {
      return this.n_fiscal;
   }

   public String getNr_dup() {
      return this.nr_dup;
   }

   public String getNro_lista() {
      return this.nro_lista;
   }

   public int getNumero() {
      return this.numero;
   }

   public String getNumero_integ() {
      return this.numero_integ;
   }

   public String getNumero_sfa() {
      return this.numero_sfa;
   }

   public String getOperador() {
      return this.operador;
   }

   public double getPorc_desc1() {
      return this.porc_desc1;
   }

   public double getPorc_desc2() {
      return this.porc_desc2;
   }

   public double getPorc_desc3() {
      return this.porc_desc3;
   }

   public int getReservado1() {
      return this.reservado1;
   }

   public Timestamp getReservado10() {
      return this.reservado10;
   }

   public Timestamp getReservado11() {
      return this.reservado11;
   }

   public Timestamp getReservado12() {
      return this.reservado12;
   }

   public String getReservado13() {
      return this.reservado13;
   }

   public String getReservado14() {
      return this.reservado14;
   }

   public String getReservado15() {
      return this.reservado15;
   }

   public String getReservado16() {
      return this.reservado16;
   }

   public int getReservado2() {
      return this.reservado2;
   }

   public int getReservado3() {
      return this.reservado3;
   }

   public int getReservado4() {
      return this.reservado4;
   }

   public double getReservado5() {
      return this.reservado5;
   }

   public double getReservado6() {
      return this.reservado6;
   }

   public double getReservado7() {
      return this.reservado7;
   }

   public double getReservado8() {
      return this.reservado8;
   }

   public Timestamp getReservado9() {
      return this.reservado9;
   }

   public String getSerie() {
      return this.serie;
   }

   public String getStatus() {
      return this.status;
   }

   public String getTexto_esp() {
      return this.texto_esp;
   }

   public short getTime_stamp() {
      return this.time_stamp;
   }

   public String getTipo_cli() {
      return this.tipo_cli;
   }

   public String getTipo_ped() {
      return this.tipo_ped;
   }

   public String getVendedor() {
      return this.vendedor;
   }

   public int getVersion() {
      return this.version;
   }

   public double getVlr_fat() {
      return this.vlr_fat;
   }

   public double getVlr_ped() {
      return this.vlr_ped;
   }

   public void setAssina(int var1) {
      this.assina = var1;
   }

   public void setCarga_total(double var1) {
      this.carga_total = var1;
   }

   public void setCliente(String var1) {
      this.cliente = var1;
   }

   public void setCond_pagto(String var1) {
      this.cond_pagto = var1;
   }

   public void setD_E_L_E_T_(String var1) {
      this.D_E_L_E_T_ = var1;
   }

   public void setD_I_R_T_Y_(String var1) {
      this.D_I_R_T_Y_ = var1;
   }

   public void setData_alter(Timestamp var1) {
      this.data_alter = var1;
   }

   public void setDt_livre(String var1) {
      this.dt_livre = var1;
   }

   public void setDt_ped(String var1) {
      this.dt_ped = var1;
   }

   public void setEmissao(String var1) {
      this.emissao = var1;
   }

   public void setEmite_nf(short var1) {
      this.emite_nf = var1;
   }

   public void setEmpresa(int var1) {
      this.empresa = var1;
   }

   public void setFilial(int var1) {
      this.filial = var1;
   }

   public void setHora_alter(short var1) {
      this.hora_alter = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setIntr(String var1) {
      this.intr = var1;
   }

   public void setLoja_cli(String var1) {
      this.loja_cli = var1;
   }

   public void setN_fiscal(String var1) {
      this.n_fiscal = var1;
   }

   public void setNr_dup(String var1) {
      this.nr_dup = var1;
   }

   public void setNro_lista(String var1) {
      this.nro_lista = var1;
   }

   public void setNumero(int var1) {
      this.numero = var1;
   }

   public void setNumero_integ(String var1) {
      this.numero_integ = var1;
   }

   public void setNumero_sfa(String var1) {
      this.numero_sfa = var1;
   }

   public void setOperador(String var1) {
      this.operador = var1;
   }

   public void setPorc_desc1(double var1) {
      this.porc_desc1 = var1;
   }

   public void setPorc_desc2(double var1) {
      this.porc_desc2 = var1;
   }

   public void setPorc_desc3(double var1) {
      this.porc_desc3 = var1;
   }

   public void setReservado1(int var1) {
      this.reservado1 = var1;
   }

   public void setReservado10(Timestamp var1) {
      this.reservado10 = var1;
   }

   public void setReservado11(Timestamp var1) {
      this.reservado11 = var1;
   }

   public void setReservado12(Timestamp var1) {
      this.reservado12 = var1;
   }

   public void setReservado13(String var1) {
      this.reservado13 = var1;
   }

   public void setReservado14(String var1) {
      this.reservado14 = var1;
   }

   public void setReservado15(String var1) {
      this.reservado15 = var1;
   }

   public void setReservado16(String var1) {
      this.reservado16 = var1;
   }

   public void setReservado2(int var1) {
      this.reservado2 = var1;
   }

   public void setReservado3(int var1) {
      this.reservado3 = var1;
   }

   public void setReservado4(int var1) {
      this.reservado4 = var1;
   }

   public void setReservado5(double var1) {
      this.reservado5 = var1;
   }

   public void setReservado6(double var1) {
      this.reservado6 = var1;
   }

   public void setReservado7(double var1) {
      this.reservado7 = var1;
   }

   public void setReservado8(double var1) {
      this.reservado8 = var1;
   }

   public void setReservado9(Timestamp var1) {
      this.reservado9 = var1;
   }

   public void setSerie(String var1) {
      this.serie = var1;
   }

   public void setStatus(String var1) {
      this.status = var1;
   }

   public void setTexto_esp(String var1) {
      this.texto_esp = var1;
   }

   public void setTime_stamp(short var1) {
      this.time_stamp = var1;
   }

   public void setTipo_cli(String var1) {
      this.tipo_cli = var1;
   }

   public void setTipo_ped(String var1) {
      this.tipo_ped = var1;
   }

   public void setVendedor(String var1) {
      this.vendedor = var1;
   }

   public void setVersion(int var1) {
      this.version = var1;
   }

   public void setVlr_fat(double var1) {
      this.vlr_fat = var1;
   }

   public void setVlr_ped(double var1) {
      this.vlr_ped = var1;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append("com.akcess.vo.Mbpm :");
      var1.append("empresa=\'" + this.empresa + "\'");
      var1.append(", filial=\'" + this.filial + "\'");
      var1.append(", numero=\'" + this.numero + "\'");
      var1.append(", numero_sfa=\'" + this.numero_sfa + "\'");
      var1.append(", cliente=\'" + this.cliente + "\'");
      var1.append(", loja_cli=\'" + this.loja_cli + "\'");
      var1.append(", tipo_ped=\'" + this.tipo_ped + "\'");
      var1.append(", cond_pagto=\'" + this.cond_pagto + "\'");
      var1.append(", vendedor=\'" + this.vendedor + "\'");
      var1.append(", texto_esp=\'" + this.texto_esp + "\'");
      var1.append(", dt_ped=\'" + this.dt_ped + "\'");
      var1.append(", nro_lista=\'" + this.nro_lista + "\'");
      var1.append(", tipo_cli=\'" + this.tipo_cli + "\'");
      var1.append(", status=\'" + this.status + "\'");
      var1.append(", assina=\'" + this.assina + "\'");
      var1.append(", emite_nf=\'" + this.emite_nf + "\'");
      var1.append(", emissao=\'" + this.emissao + "\'");
      var1.append(", n_fiscal=\'" + this.n_fiscal + "\'");
      var1.append(", serie=\'" + this.serie + "\'");
      var1.append(", numero_integ=\'" + this.numero_integ + "\'");
      var1.append(", dt_livre=\'" + this.dt_livre + "\'");
      var1.append(", nr_dup=\'" + this.nr_dup + "\'");
      var1.append(", vlr_ped=\'" + this.vlr_ped + "\'");
      var1.append(", vlr_fat=\'" + this.vlr_fat + "\'");
      var1.append(", porc_desc1=\'" + this.porc_desc1 + "\'");
      var1.append(", porc_desc2=\'" + this.porc_desc2 + "\'");
      var1.append(", porc_desc3=\'" + this.porc_desc3 + "\'");
      var1.append(", carga_total=\'" + this.carga_total + "\'");
      var1.append(", reservado1=\'" + this.reservado1 + "\'");
      var1.append(", reservado2=\'" + this.reservado2 + "\'");
      var1.append(", reservado3=\'" + this.reservado3 + "\'");
      var1.append(", reservado4=\'" + this.reservado4 + "\'");
      var1.append(", reservado5=\'" + this.reservado5 + "\'");
      var1.append(", reservado6=\'" + this.reservado6 + "\'");
      var1.append(", reservado7=\'" + this.reservado7 + "\'");
      var1.append(", reservado8=\'" + this.reservado8 + "\'");
      var1.append(", reservado9=\'" + this.reservado9 + "\'");
      var1.append(", reservado10=\'" + this.reservado10 + "\'");
      var1.append(", reservado11=\'" + this.reservado11 + "\'");
      var1.append(", reservado12=\'" + this.reservado12 + "\'");
      var1.append(", reservado13=\'" + this.reservado13 + "\'");
      var1.append(", reservado14=\'" + this.reservado14 + "\'");
      var1.append(", reservado15=\'" + this.reservado15 + "\'");
      var1.append(", reservado16=\'" + this.reservado16 + "\'");
      var1.append(", intr=\'" + this.intr + "\'");
      var1.append(", operador=\'" + this.operador + "\'");
      var1.append(", data_alter=\'" + this.data_alter + "\'");
      var1.append(", hora_alter=\'" + this.hora_alter + "\'");
      var1.append(", time_stamp=\'" + this.time_stamp + "\'");
      var1.append(", version=\'" + this.version + "\'");
      return var1.toString();
   }
}
