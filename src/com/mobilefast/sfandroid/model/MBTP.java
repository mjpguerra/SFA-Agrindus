package com.mobilefast.sfandroid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MBTP implements Serializable {

   private static final long serialVersionUID = 1L;
   private Timestamp data_alter;
   private String descricao;
   private String emissao;
   private short empresa;
   private short filial;
   private short hora_alter;
   private int id;
   private String intr;
   private String operador;
   private int reservado1;
   private int reservado2;
   private double reservado3;
   private double reservado4;
   private Timestamp reservado5;
   private Timestamp reservado6;
   private String reservado7;
   private String reservado8;
   private short time_stamp;
   private String vendedor;
   private int version;


   public MBTP() {
      super();
   }

   public Timestamp getData_alter() {
      return this.data_alter;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public String getEmissao() {
      return this.emissao;
   }

   public short getEmpresa() {
      return this.empresa;
   }

   public short getFilial() {
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

   public String getOperador() {
      return this.operador;
   }

   public int getReservado1() {
      return this.reservado1;
   }

   public int getReservado2() {
      return this.reservado2;
   }

   public double getReservado3() {
      return this.reservado3;
   }

   public double getReservado4() {
      return this.reservado4;
   }

   public Timestamp getReservado5() {
      return this.reservado5;
   }

   public Timestamp getReservado6() {
      return this.reservado6;
   }

   public String getReservado7() {
      return this.reservado7;
   }

   public String getReservado8() {
      return this.reservado8;
   }

   public short getTime_stamp() {
      return this.time_stamp;
   }

   public String getVendedor() {
      return this.vendedor;
   }

   public int getVersion() {
      return this.version;
   }

   public void setData_alter(Timestamp var1) {
      this.data_alter = var1;
   }

   public void setDescricao(String var1) {
      this.descricao = var1;
   }

   public void setEmissao(String var1) {
      this.emissao = var1;
   }

   public void setEmpresa(short var1) {
      this.empresa = var1;
   }

   public void setFilial(short var1) {
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

   public void setOperador(String var1) {
      this.operador = var1;
   }

   public void setReservado1(int var1) {
      this.reservado1 = var1;
   }

   public void setReservado2(int var1) {
      this.reservado2 = var1;
   }

   public void setReservado3(double var1) {
      this.reservado3 = var1;
   }

   public void setReservado4(double var1) {
      this.reservado4 = var1;
   }

   public void setReservado5(Timestamp var1) {
      this.reservado5 = var1;
   }

   public void setReservado6(Timestamp var1) {
      this.reservado6 = var1;
   }

   public void setReservado7(String var1) {
      this.reservado7 = var1;
   }

   public void setReservado8(String var1) {
      this.reservado8 = var1;
   }

   public void setTime_stamp(short var1) {
      this.time_stamp = var1;
   }

   public void setVendedor(String var1) {
      this.vendedor = var1;
   }

   public void setVersion(int var1) {
      this.version = var1;
   }
}
