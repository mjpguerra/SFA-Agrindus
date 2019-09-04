package com.frame.mobilefast;

import java.util.ArrayList;
import java.util.List;

public class DefaultListModel {
    private int Sequencia_item = 0;
    private String descricao = null;
    private String frete = null;
    private int id = 0;
    private List listaModel = new ArrayList();
    private List listaObjetos = new ArrayList();
    private String metaDataTag = null;
    private DefaultModelItem model = new DefaultModelItem();
    private String nome = null;
    private Object objeto = null;
    private int quantidade = 0;
    private String scor = null;
    private boolean selected = false;
    private String valor = null;
    private String valor_original = null;
    private String valor_Cliente = null;
    private int nm_lista = 0;
    private String valor_ipi = null;
    private double peso_item = 0;
    private double totalvalor = 0.00;
    private double comissao = 0.00;
    private double contratual = 0.00;
    private double descTrab = 0.00;

    public DefaultListModel() {
        super();
    }

    public void setvalorCliente(String valor_Cliente) {
        this.valor_Cliente = valor_Cliente;
    }

    public String getvalorCliente() {
        return this.valor_Cliente;
    }


    public void setdescTrab(double descTrab) {
        this.descTrab = descTrab;
    }

    public double getdescTrab() {
        return this.descTrab;
    }

    public void setcontratual(double contratual) {
        this.contratual = contratual;
    }

    public double getcontratual() {
        return this.contratual;
    }

    public double getTotalvalor() {
        return this.totalvalor;
    }

    public void setTotalvalor(double totalvalor) {
        this.totalvalor = totalvalor;
    }

    public double getComissao() {
        return this.comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String var1) {
        this.descricao = var1;
    }

    public int getNm_lista() {
        return this.nm_lista;
    }

    public void setNm_lista(int var1) {
        this.nm_lista = var1;
    }

    public String getVlr_ipi() {
        return this.valor_ipi;
    }

    public void setVlr_ipi(String var1) {
        this.valor_ipi = var1;
    }

    public String getFrete() {
        return this.frete;
    }

    public void setFrete(String var1) {
        this.frete = var1;
    }

    public int getSequencia_item() {
        return this.Sequencia_item;
    }

    public void setSequencia_item(int var1) {
        this.Sequencia_item = var1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public List getListaModel() {
        return this.listaModel;
    }

    public void setListaModel(List var1) {
        this.listaModel = var1;
    }

    public List getListaObjetos() {
        return this.listaObjetos;
    }

    public void setListaObjetos(List var1) {
        this.listaObjetos = var1;
    }

    public String getMetaDataTag() {
        return this.metaDataTag;
    }

    public void setMetaDataTag(String var1) {
        this.metaDataTag = var1;
    }

    public DefaultModelItem getModel() {
        return this.model;
    }

    public void setModel(DefaultModelItem var1) {
        this.model = var1;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public Object getObjeto() {
        return this.objeto;
    }

    public void setObjeto(Object var1) {
        this.objeto = var1;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int var1) {
        this.quantidade = var1;
    }

    public double getPeso_item() {
        return this.peso_item;
    }

    public void setPeso_item(double var1) {
        this.peso_item = var1;
    }

    public String getScor() {
        return this.scor;
    }

    public void setScor(String var1) {
        this.scor = var1;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String var1) {
        this.valor = var1;
    }

    public String getValorOriginal() {
        return this.valor_original;
    }

    public void setValorOriginal(String var1) {
        this.valor_original = var1;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean var1) {
        this.selected = var1;
    }
}
