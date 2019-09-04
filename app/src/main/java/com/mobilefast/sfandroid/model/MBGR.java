package com.mobilefast.sfandroid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MBGR implements Serializable {

    private static final long serialVersionUID = 1L;
    private Timestamp data_alter;
    private String descricao;
    private short empresa;
    private short filial;
    private String grupo;
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


    public MBGR() {
        super();
    }

    public Timestamp getData_alter() {
        return this.data_alter;
    }

    public void setData_alter(Timestamp var1) {
        this.data_alter = var1;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String var1) {
        this.descricao = var1;
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

    public String getGrupo() {
        return this.grupo;
    }

    public void setGrupo(String var1) {
        this.grupo = var1;
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
}