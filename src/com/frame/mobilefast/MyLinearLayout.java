package com.frame.mobilefast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

   private List COMPONENTES = new ArrayList();
   private Map COMPONENTE_MAP = new HashMap();
   private String nomeComponent = null;


   public MyLinearLayout(Context var1, String var2) {
      super(var1);
      this.nomeComponent = var2;
   }

   public MyLinearLayout(Context var1, String var2, int var3) {
      super(var1);
      this.nomeComponent = var2;
      this.setHorizontalGravity(3);
      this.setOrientation(1);
   }

   public MyLinearLayout(Context var1, String var2, int var3, int var4) {
      super(var1);
      this.nomeComponent = var2;
      this.setHorizontalGravity(3);
      this.setOrientation(1);
   }

   public void addView(View var1) {
      super.addView(var1);
      this.COMPONENTES.add(var1);
      this.COMPONENTE_MAP.put(var1, var1);
   }

   public void addView(String var1, View var2) {
      super.addView(var2);
      this.COMPONENTE_MAP.put(var1, var2);
   }

   public List getCOMPONENTES() {
      return this.COMPONENTES;
   }

   public Map getCOMPONENTE_MAP() {
      return this.COMPONENTE_MAP;
   }

   public String getNomeComponent() {
      return this.nomeComponent;
   }

   public void setCOMPONENTES(List var1) {
      this.COMPONENTES = var1;
   }

   public void setCOMPONENTE_MAP(Map var1) {
      this.COMPONENTE_MAP = var1;
   }

   public void setNomeComponent(String var1) {
      this.nomeComponent = var1;
   }
}
