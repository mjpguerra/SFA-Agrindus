package com.mobilefast.sfandroid.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class MBCL implements Serializable {

    private static final long serialVersionUID = 1L;
    private String bairro;
    private String cep;
    private String cgc_cpf;
    private String cidade;
    private String classe;
    private long cliente;
    private String cliente_alfa;
    private String cond_pagto;
    private Timestamp data_alter;
    private int desconto;
    private String dt_ch_dev;
    private String dt_nasc;
    private String dt_pr_comp;
    private String dt_ult_comp;
    private String dt_ult_dev;
    private String dt_ult_visita;
    private String dt_vencto_lm;
    private String email;
    private short empresa;
    private String endereco;
    private String estado;
    private short filial;
    private String filial_alfa;
    private String fis_jurid;
    private String flag_cliente;
    private short flag_cond_pgto;
    private short hora_alter;
    private int id;
    private String inscr_estad;
    private String inscr_munic;
    private String intr;
    private int lm_credito;
    private String loja;
    private short ma_atr;
    private short md_atraso;
    private String nom_fantasia;
    private short nro_compras;
    private double nro_pg_atr;
    private String operador;
    private int prioridade;
    private short qt_cheq_dv;
    private short qt_dp_pg_cart;
    private short qt_dup_pg;
    private String raz_social;
    private int reservado1;
    private Timestamp reservado10;
    private Timestamp reservado11;
    private Timestamp reservado12;
    private String reservado13;
    private String reservado14;
    private String reservado15;
    private String reservado16;
    private int reservado2;
    private int reservado3;
    private int reservado4;
    private double reservado5;
    private double reservado6;
    private double reservado7;
    private double reservado8;
    private Timestamp reservado9;
    private String rg;
    private String risco;
    private double sd_atual;
    private double sd_cart_pd;
    private double sd_cart_pd_lib;
    private String telefax;
    private String telefone;
    private String terr_1;
    private short time_stamp;
    private String tipo_cli;
    private short tpo_consult;
    private String vendedor;
    private int version;
    private double vlr_acum_ve;
    private double vlr_atrasos;
    private double vlr_ma_com;
    private double vlr_ma_fat;
    private double vr_ma_acum;


    public MBCL() {
        super();
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String var1) {
        this.bairro = var1;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String var1) {
        this.cep = var1;
    }

    public String getCgc_cpf() {
        return this.cgc_cpf;
    }

    public void setCgc_cpf(String var1) {
        this.cgc_cpf = var1;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String var1) {
        this.cidade = var1;
    }

    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String var1) {
        this.classe = var1;
    }

    public long getCliente() {
        return this.cliente;
    }

    public void setCliente(long var1) {
        this.cliente = var1;
    }

    public String getCliente_alfa() {
        return this.cliente_alfa;
    }

    public void setCliente_alfa(String var1) {
        this.cliente_alfa = var1;
    }

    public String getCond_pagto() {
        return this.cond_pagto;
    }

    public void setCond_pagto(String var1) {
        this.cond_pagto = var1;
    }

    public Date getDataCriado() {
        return null;
    }

    public void setDataCriado(Date var1) {
    }

    public Timestamp getData_alter() {
        return this.data_alter;
    }

    public void setData_alter(Timestamp var1) {
        this.data_alter = var1;
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setDesconto(int var1) {
        this.desconto = var1;
    }

    public String getDescricao() {
        return null;
    }

    public void setDescricao(String var1) {
    }

    public String getDt_ch_dev() {
        return this.dt_ch_dev;
    }

    public void setDt_ch_dev(String var1) {
        this.dt_ch_dev = var1;
    }

    public String getDt_nasc() {
        return this.dt_nasc;
    }

    public void setDt_nasc(String var1) {
        this.dt_nasc = var1;
    }

    public String getDt_pr_comp() {
        return this.dt_pr_comp;
    }

    public void setDt_pr_comp(String var1) {
        this.dt_pr_comp = var1;
    }

    public String getDt_ult_comp() {
        return this.dt_ult_comp;
    }

    public void setDt_ult_comp(String var1) {
        this.dt_ult_comp = var1;
    }

    public String getDt_ult_dev() {
        return this.dt_ult_dev;
    }

    public void setDt_ult_dev(String var1) {
        this.dt_ult_dev = var1;
    }

    public String getDt_ult_visita() {
        return this.dt_ult_visita;
    }

    public void setDt_ult_visita(String var1) {
        this.dt_ult_visita = var1;
    }

    public String getDt_vencto_lm() {
        return this.dt_vencto_lm;
    }

    public void setDt_vencto_lm(String var1) {
        this.dt_vencto_lm = var1;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String var1) {
        this.email = var1;
    }

    public short getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(short var1) {
        this.empresa = var1;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String var1) {
        this.endereco = var1;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String var1) {
        this.estado = var1;
    }

    public short getFilial() {
        return this.filial;
    }

    public void setFilial(short var1) {
        this.filial = var1;
    }

    public String getFilial_alfa() {
        return this.filial_alfa;
    }

    public void setFilial_alfa(String var1) {
        this.filial_alfa = var1;
    }

    public String getFis_jurid() {
        return this.fis_jurid;
    }

    public void setFis_jurid(String var1) {
        this.fis_jurid = var1;
    }

    public String getFlag_cliente() {
        return this.flag_cliente;
    }

    public void setFlag_cliente(String var1) {
        this.flag_cliente = var1;
    }

    public short getFlag_cond_pgto() {
        return this.flag_cond_pgto;
    }

    public void setFlag_cond_pgto(short var1) {
        this.flag_cond_pgto = var1;
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

    public String getInscr_estad() {
        return this.inscr_estad;
    }

    public void setInscr_estad(String var1) {
        this.inscr_estad = var1;
    }

    public String getInscr_munic() {
        return this.inscr_munic;
    }

    public void setInscr_munic(String var1) {
        this.inscr_munic = var1;
    }

    public String getIntr() {
        return this.intr;
    }

    public void setIntr(String var1) {
        this.intr = var1;
    }

    public int getLm_credito() {
        return this.lm_credito;
    }

    public void setLm_credito(int var1) {
        this.lm_credito = var1;
    }

    public String getLoja() {
        return this.loja;
    }

    public void setLoja(String var1) {
        this.loja = var1;
    }

    public short getMa_atr() {
        return this.ma_atr;
    }

    public void setMa_atr(short var1) {
        this.ma_atr = var1;
    }

    public short getMd_atraso() {
        return this.md_atraso;
    }

    public void setMd_atraso(short var1) {
        this.md_atraso = var1;
    }

    public String getNom_fantasia() {
        return this.nom_fantasia;
    }

    public void setNom_fantasia(String var1) {
        this.nom_fantasia = var1;
    }

    public String getNome() {
        return null;
    }

    public void setNome(String var1) {
    }

    public short getNro_compras() {
        return this.nro_compras;
    }

    public void setNro_compras(short var1) {
        this.nro_compras = var1;
    }

    public double getNro_pg_atr() {
        return this.nro_pg_atr;
    }

    public void setNro_pg_atr(double var1) {
        this.nro_pg_atr = var1;
    }

    public String getOperador() {
        return this.operador;
    }

    public void setOperador(String var1) {
        this.operador = var1;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int var1) {
        this.prioridade = var1;
    }

    public short getQt_cheq_dv() {
        return this.qt_cheq_dv;
    }

    public void setQt_cheq_dv(short var1) {
        this.qt_cheq_dv = var1;
    }

    public short getQt_dp_pg_cart() {
        return this.qt_dp_pg_cart;
    }

    public void setQt_dp_pg_cart(short var1) {
        this.qt_dp_pg_cart = var1;
    }

    public short getQt_dup_pg() {
        return this.qt_dup_pg;
    }

    public void setQt_dup_pg(short var1) {
        this.qt_dup_pg = var1;
    }

    public String getRaz_social() {
        return this.raz_social;
    }

    public void setRaz_social(String var1) {
        this.raz_social = var1;
    }

    public int getReservado1() {
        return this.reservado1;
    }

    public void setReservado1(int var1) {
        this.reservado1 = var1;
    }

    public Timestamp getReservado10() {
        return this.reservado10;
    }

    public void setReservado10(Timestamp var1) {
        this.reservado10 = var1;
    }

    public Timestamp getReservado11() {
        return this.reservado11;
    }

    public void setReservado11(Timestamp var1) {
        this.reservado11 = var1;
    }

    public Timestamp getReservado12() {
        return this.reservado12;
    }

    public void setReservado12(Timestamp var1) {
        this.reservado12 = var1;
    }

    public String getReservado13() {
        return this.reservado13;
    }

    public void setReservado13(String var1) {
        this.reservado13 = var1;
    }

    public String getReservado14() {
        return this.reservado14;
    }

    public void setReservado14(String var1) {
        this.reservado14 = var1;
    }

    public String getReservado15() {
        return this.reservado15;
    }

    public void setReservado15(String var1) {
        this.reservado15 = var1;
    }

    public String getReservado16() {
        return this.reservado16;
    }

    public void setReservado16(String var1) {
        this.reservado16 = var1;
    }

    public int getReservado2() {
        return this.reservado2;
    }

    public void setReservado2(int var1) {
        this.reservado2 = var1;
    }

    public int getReservado3() {
        return this.reservado3;
    }

    public void setReservado3(int var1) {
        this.reservado3 = var1;
    }

    public int getReservado4() {
        return this.reservado4;
    }

    public void setReservado4(int var1) {
        this.reservado4 = var1;
    }

    public double getReservado5() {
        return this.reservado5;
    }

    public void setReservado5(double var1) {
        this.reservado5 = var1;
    }

    public double getReservado6() {
        return this.reservado6;
    }

    public void setReservado6(double var1) {
        this.reservado6 = var1;
    }

    public double getReservado7() {
        return this.reservado7;
    }

    public void setReservado7(double var1) {
        this.reservado7 = var1;
    }

    public double getReservado8() {
        return this.reservado8;
    }

    public void setReservado8(double var1) {
        this.reservado8 = var1;
    }

    public Timestamp getReservado9() {
        return this.reservado9;
    }

    public void setReservado9(Timestamp var1) {
        this.reservado9 = var1;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String var1) {
        this.rg = var1;
    }

    public String getRisco() {
        return this.risco;
    }

    public void setRisco(String var1) {
        this.risco = var1;
    }

    public double getSd_atual() {
        return this.sd_atual;
    }

    public void setSd_atual(double var1) {
        this.sd_atual = var1;
    }

    public double getSd_cart_pd() {
        return this.sd_cart_pd;
    }

    public void setSd_cart_pd(double var1) {
        this.sd_cart_pd = var1;
    }

    public double getSd_cart_pd_lib() {
        return this.sd_cart_pd_lib;
    }

    public void setSd_cart_pd_lib(double var1) {
        this.sd_cart_pd_lib = var1;
    }

    public String getStatus() {
        return null;
    }

    public void setStatus(String var1) {
    }

    public String getTelefax() {
        return this.telefax;
    }

    public void setTelefax(String var1) {
        this.telefax = var1;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String var1) {
        this.telefone = var1;
    }

    public String getTerr_1() {
        return this.terr_1;
    }

    public void setTerr_1(String var1) {
        this.terr_1 = var1;
    }

    public short getTime_stamp() {
        return this.time_stamp;
    }

    public void setTime_stamp(short var1) {
        this.time_stamp = var1;
    }

    public String getTipo_cli() {
        return this.tipo_cli;
    }

    public void setTipo_cli(String var1) {
        this.tipo_cli = var1;
    }

    public short getTpo_consult() {
        return this.tpo_consult;
    }

    public void setTpo_consult(short var1) {
        this.tpo_consult = var1;
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

    public double getVlr_acum_ve() {
        return this.vlr_acum_ve;
    }

    public void setVlr_acum_ve(double var1) {
        this.vlr_acum_ve = var1;
    }

    public double getVlr_atrasos() {
        return this.vlr_atrasos;
    }

    public void setVlr_atrasos(double var1) {
        this.vlr_atrasos = var1;
    }

    public double getVlr_ma_com() {
        return this.vlr_ma_com;
    }

    public void setVlr_ma_com(double var1) {
        this.vlr_ma_com = var1;
    }

    public double getVlr_ma_fat() {
        return this.vlr_ma_fat;
    }

    public void setVlr_ma_fat(double var1) {
        this.vlr_ma_fat = var1;
    }

    public double getVr_ma_acum() {
        return this.vr_ma_acum;
    }

    public void setVr_ma_acum(double var1) {
        this.vr_ma_acum = var1;
    }

    public int get_id() {
        return 0;
    }

    public void set_id(int var1) {
    }

    public String toString() {
        StringBuffer var1 = new StringBuffer();
        var1.append("com.akcess.vo.Mbcl :");
        var1.append("empresa=\'" + this.empresa + "\'");
        var1.append(", filial=\'" + this.filial + "\'");
        var1.append(", vendedor=\'" + this.vendedor + "\'");
        var1.append(", cliente=\'" + this.cliente + "\'");
        var1.append(", cliente_alfa=\'" + this.cliente_alfa + "\'");
        var1.append(", filial_alfa=\'" + this.filial_alfa + "\'");
        var1.append(", loja=\'" + this.loja + "\'");
        var1.append(", raz_social=\'" + this.raz_social + "\'");
        var1.append(", nom_fantasia=\'" + this.nom_fantasia + "\'");
        var1.append(", endereco=\'" + this.endereco + "\'");
        var1.append(", cidade=\'" + this.cidade + "\'");
        var1.append(", estado=\'" + this.estado + "\'");
        var1.append(", bairro=\'" + this.bairro + "\'");
        var1.append(", cep=\'" + this.cep + "\'");
        var1.append(", telefone=\'" + this.telefone + "\'");
        var1.append(", telefax=\'" + this.telefax + "\'");
        var1.append(", fis_jurid=\'" + this.fis_jurid + "\'");
        var1.append(", cgc_cpf=\'" + this.cgc_cpf + "\'");
        var1.append(", inscr_estad=\'" + this.inscr_estad + "\'");
        var1.append(", inscr_munic=\'" + this.inscr_munic + "\'");
        var1.append(", tipo_cli=\'" + this.tipo_cli + "\'");
        var1.append(", terr_1=\'" + this.terr_1 + "\'");
        var1.append(", cond_pagto=\'" + this.cond_pagto + "\'");
        var1.append(", desconto=\'" + this.desconto + "\'");
        var1.append(", prioridade=\'" + this.prioridade + "\'");
        var1.append(", risco=\'" + this.risco + "\'");
        var1.append(", lm_credito=\'" + this.lm_credito + "\'");
        var1.append(", dt_vencto_lm=\'" + this.dt_vencto_lm + "\'");
        var1.append(", classe=\'" + this.classe + "\'");
        var1.append(", vlr_ma_com=\'" + this.vlr_ma_com + "\'");
        var1.append(", md_atraso=\'" + this.md_atraso + "\'");
        var1.append(", vr_ma_acum=\'" + this.vr_ma_acum + "\'");
        var1.append(", nro_compras=\'" + this.nro_compras + "\'");
        var1.append(", dt_pr_comp=\'" + this.dt_pr_comp + "\'");
        var1.append(", dt_ult_comp=\'" + this.dt_ult_comp + "\'");
        var1.append(", dt_ult_visita=\'" + this.dt_ult_visita + "\'");
        var1.append(", qt_dup_pg=\'" + this.qt_dup_pg + "\'");
        var1.append(", sd_atual=\'" + this.sd_atual + "\'");
        var1.append(", sd_cart_pd_lib=\'" + this.sd_cart_pd_lib + "\'");
        var1.append(", sd_cart_pd=\'" + this.sd_cart_pd + "\'");
        var1.append(", vlr_atrasos=\'" + this.vlr_atrasos + "\'");
        var1.append(", vlr_acum_ve=\'" + this.vlr_acum_ve + "\'");
        var1.append(", qt_dp_pg_cart=\'" + this.qt_dp_pg_cart + "\'");
        var1.append(", dt_ult_dev=\'" + this.dt_ult_dev + "\'");
        var1.append(", qt_cheq_dv=\'" + this.qt_cheq_dv + "\'");
        var1.append(", dt_ch_dev=\'" + this.dt_ch_dev + "\'");
        var1.append(", ma_atr=\'" + this.ma_atr + "\'");
        var1.append(", vlr_ma_fat=\'" + this.vlr_ma_fat + "\'");
        var1.append(", nro_pg_atr=\'" + this.nro_pg_atr + "\'");
        var1.append(", rg=\'" + this.rg + "\'");
        var1.append(", dt_nasc=\'" + this.dt_nasc + "\'");
        var1.append(", email=\'" + this.email + "\'");
        var1.append(", tpo_consult=\'" + this.tpo_consult + "\'");
        var1.append(", flag_cliente=\'" + this.flag_cliente + "\'");
        var1.append(", flag_cond_pgto=\'" + this.flag_cond_pgto + "\'");
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
