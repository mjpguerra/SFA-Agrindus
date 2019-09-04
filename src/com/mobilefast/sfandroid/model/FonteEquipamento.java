package com.mobilefast.sfandroid.model;

import java.io.Serializable;



public class FonteEquipamento implements Serializable{

   private static final long serialVersionUID = 1L;
   private int id;
   private int tamanho;


   public FonteEquipamento() {
      super();
   }


   public int getId() {
      return this.id;
   }

   public void setId(int var1) {
      this.id = var1;
   }
   
   public int gettamanho() {
	      return this.tamanho;
	   }

	   public void settamanho(int var1) {
	      this.tamanho = var1;
	   }
}
