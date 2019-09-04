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

   public String getFrete() {
      return this.frete;
   }

   public int getId() {
      return this.id;
   }

   public String getMetaDataTag() {
      return this.metaDataTag;
   }

   public String getNome() {
      return this.nome;
   }

   public Object getObjeto() {
      return this.objeto;
   }

   public int getQuantidade() {
      return this.quantidade;
   }

   public String getScor() {
      return this.scor;
   }

   public String getValor() {
      return this.valor;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setDescricao(String var1) {
      this.descricao = var1;
   }

   public void setFrete(String var1) {
      this.frete = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setMetaDataTag(String var1) {
      this.metaDataTag = var1;
   }

   public void setNome(String var1) {
      this.nome = var1;
   }

   public void setObjeto(Object var1) {
      this.objeto = var1;
   }

   public void setQuantidade(int var1) {
      this.quantidade = var1;
   }

   public void setScor(String var1) {
      this.scor = var1;
   }

   public void setSelected(boolean var1) {
      this.selected = var1;
   }

   public void setValor(String var1) {
      this.valor = var1;
   }
}
