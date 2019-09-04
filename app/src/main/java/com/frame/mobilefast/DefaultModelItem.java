package com.frame.mobilefast;


public class DefaultModelItem {

    private String descricao = null;
    private String frete = null;
    private int id = 0;
    private String metaDataTag = null;
    private String nome = null;
    private Object objeto = null;
    private int quantidade = 0;
    private String scor = null;
    private boolean selected = false;
    private String valor = null;


    public DefaultModelItem() {
        super();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String var1) {
        this.descricao = var1;
    }

    public String getFrete() {
        return this.frete;
    }

    public void setFrete(String var1) {
        this.frete = var1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getMetaDataTag() {
        return this.metaDataTag;
    }

    public void setMetaDataTag(String var1) {
        this.metaDataTag = var1;
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

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean var1) {
        this.selected = var1;
    }
}
