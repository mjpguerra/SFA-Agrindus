package com.mobilefast.sfandroid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MBCP implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cod_tes;
    private String cond_pgto;
    private short cp_inteligente;
    private Timestamp data_alter;
    private double desconto;
    private String descricao;
    private short dias_adic_entr;
    private short empresa;
    private short filial;
    private short forma_pagto;
    private short hora_alter;
    private int id;
    private String intr;
    private String nro_lista;
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
    private String tipo_pagto;
    private int usa_desc;
    private short usa_dias_adic;
    private short usa_lista;
    private short usa_tes;
    private short usa_tipo_pagto;
    private String vendedor;
    private int version;
    private double vlr_min_ped;


    public MBCP() {
        super();
    }

    public String getCod_tes() {
        return this.cod_tes;
    }

    public void setCod_tes(String var1) {
        this.cod_tes = var1;
    }

    public String getCond_pgto() {
        return this.cond_pgto;
    }

    public void setCond_pgto(String var1) {
        this.cond_pgto = var1;
    }

    public short getCp_inteligente() {
        return this.cp_inteligente;
    }

    public void setCp_inteligente(short var1) {
        this.cp_inteligente = var1;
    }

    public Timestamp getData_alter() {
        return this.data_alter;
    }

    public void setData_alter(Timestamp var1) {
        this.data_alter = var1;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double var1) {
        this.desconto = var1;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String var1) {
        this.descricao = var1;
    }

    public short getDias_adic_entr() {
        return this.dias_adic_entr;
    }

    public void setDias_adic_entr(short var1) {
        this.dias_adic_entr = var1;
    }

    public short getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(short var1) {
        this.empresa = var1;
    }

    public short getFilial() {
        return this.filial;
    }

    public void setFilial(short var1) {
        this.filial = var1;
    }

    public short getForma_pagto() {
        return this.forma_pagto;
    }

    public void setForma_pagto(short var1) {
        this.forma_pagto = var1;
    }

    public short getHora_alter() {
        return this.hora_alter;
    }

    public void setHora_alter(short var1) {
        this.hora_alter = var1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getIntr() {
        return this.intr;
    }

    public void setIntr(String var1) {
        this.intr = var1;
    }

    public String getNro_lista() {
        return this.nro_lista;
    }

    public void setNro_lista(String var1) {
        this.nro_lista = var1;
    }

    public String getOperador() {
        return this.operador;
    }

    public void setOperador(String var1) {
        this.operador = var1;
    }

    public int getReservado1() {
        return this.reservado1;
    }

    public void setReservado1(int var1) {
        this.reservado1 = var1;
    }

    public int getReservado2() {
        return this.reservado2;
    }

    public void setReservado2(int var1) {
        this.reservado2 = var1;
    }

    public double getReservado3() {
        return this.reservado3;
    }

    public void setReservado3(double var1) {
        this.reservado3 = var1;
    }

    public double getReservado4() {
        return this.reservado4;
    }

    public void setReservado4(double var1) {
        this.reservado4 = var1;
    }

    public Timestamp getReservado5() {
        return this.reservado5;
    }

    public void setReservado5(Timestamp var1) {
        this.reservado5 = var1;
    }

    public Timestamp getReservado6() {
        return this.reservado6;
    }

    public void setReservado6(Timestamp var1) {
        this.reservado6 = var1;
    }

    public String getReservado7() {
        return this.reservado7;
    }

    public void setReservado7(String var1) {
        this.reservado7 = var1;
    }

    public String getReservado8() {
        return this.reservado8;
    }

    public void setReservado8(String var1) {
        this.reservado8 = var1;
    }

    public short getTime_stamp() {
        return this.time_stamp;
    }

    public void setTime_stamp(short var1) {
        this.time_stamp = var1;
    }

    public String getTipo_pagto() {
        return this.tipo_pagto;
    }

    public void setTipo_pagto(String var1) {
        this.tipo_pagto = var1;
    }

    public int getUsa_desc() {
        return this.usa_desc;
    }

    public void setUsa_desc(short var1) {
        this.usa_desc = var1;
    }

    public short getUsa_dias_adic() {
        return this.usa_dias_adic;
    }

    public void setUsa_dias_adic(short var1) {
        this.usa_dias_adic = var1;
    }

    public short getUsa_lista() {
        return this.usa_lista;
    }

    public void setUsa_lista(short var1) {
        this.usa_lista = var1;
    }

    public short getUsa_tes() {
        return this.usa_tes;
    }

    public void setUsa_tes(short var1) {
        this.usa_tes = var1;
    }

    public short getUsa_tipo_pagto() {
        return this.usa_tipo_pagto;
    }

    public void setUsa_tipo_pagto(short var1) {
        this.usa_tipo_pagto = var1;
    }

    public String getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(String var1) {
        this.vendedor = var1;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int var1) {
        this.version = var1;
    }

    public double getVlr_min_ped() {
        return this.vlr_min_ped;
    }

    public void setVlr_min_ped(double var1) {
        this.vlr_min_ped = var1;
    }
}
