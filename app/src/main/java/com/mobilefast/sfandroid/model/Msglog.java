package com.mobilefast.sfandroid.model;


import java.io.Serializable;
import java.util.Date;

public class Msglog implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date data = null;
    private String hora = null;
    private int id;
    private String logentry = null;
    private String usuario = null;


    public Msglog() {
        super();
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date var1) {
        this.data = var1;
    }

    public Date getDataCriado() {
        return null;
    }

    public void setDataCriado(Date var1) {
    }

    public String getDescricao() {
        return null;
    }

    public void setDescricao(String var1) {
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(String var1) {
        this.hora = var1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getIdEmpresa() {
        return null;
    }

    public void setIdEmpresa(String var1) {
    }

    public String getIdObjeto() {
        return null;
    }

    public void setIdObjeto(String var1) {
    }

    public int getIdPersistencia() {
        return 0;
    }

    public void setIdPersistencia(int var1) {
    }

    public String getIdSuperior() {
        return null;
    }

    public void setIdSuperior(String var1) {
    }

    public String getLogentry() {
        return this.logentry;
    }

    public void setLogentry(String var1) {
        this.logentry = var1;
    }

    public String getNome() {
        return null;
    }

    public void setNome(String var1) {
    }

    public String getStatus() {
        return null;
    }

    public void setStatus(String var1) {
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String var1) {
        this.usuario = var1;
    }

    public int get_id() {
        return 0;
    }

    public void set_id(int var1) {
    }
}
