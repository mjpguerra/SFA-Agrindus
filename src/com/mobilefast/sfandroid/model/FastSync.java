package com.mobilefast.sfandroid.model;

import java.io.Serializable;
import java.util.Date;

public class FastSync implements Serializable {

   private static final long serialVersionUID = 1L;
   private int _id = 0;
   private String dbName = null;
   private String dbPath = null;
   private String group = null;
   private String ip = null;
   private int port = 0;
   private int ret = 0;
   private String serial = null;
   private int tout = 0;
   private String user = null;


   public FastSync() {
      super();
   }

   public String getDBName() {
      return this.dbName;
   }

   public String getDBPath() {
      return this.dbPath;
   }

   public Date getDataCriado() {
      return null;
   }

   public String getDescricao() {
      return null;
   }

   public String getGroup() {
      return this.group;
   }

   public int getId() {
      return this.get_id();
   }

   public String getIdEmpresa() {
      return null;
   }

   public String getIdObjeto() {
      return null;
   }

   public int getIdPersistencia() {
      return 0;
   }

   public String getIdSuperior() {
      return null;
   }

   public String getIp() {
      return this.ip;
   }

   public String getNome() {
      return null;
   }

   public int getPort() {
      return this.port;
   }

   public int getRet() {
      return this.ret;
   }

   public String getSerial() {
      return this.serial;
   }

   public String getStatus() {
      return null;
   }

   public int getTout() {
      return this.tout;
   }

   public String getUser() {
      return this.user;
   }

   public int get_id() {
      return this._id;
   }

   public void setDBName(String var1) {
      this.dbName = var1;
   }

   public void setDBPath(String var1) {
      this.dbPath = var1;
   }

   public void setDataCriado(Date var1) {}

   public void setDescricao(String var1) {}

   public void setGroup(String var1) {
      this.group = var1;
   }

   public void setId(int var1) {
      this.set_id(var1);
   }

   public void setIdEmpresa(String var1) {}

   public void setIdObjeto(String var1) {}

   public void setIdPersistencia(int var1) {}

   public void setIdSuperior(String var1) {}

   public void setIp(String var1) {
      this.ip = var1;
   }

   public void setNome(String var1) {}

   public void setPort(int var1) {
      this.port = var1;
   }

   public void setRet(int var1) {
      this.ret = var1;
   }

   public void setSerial(String var1) {
      this.serial = var1;
   }

   public void setStatus(String var1) {}

   public void setTout(int var1) {
      this.tout = var1;
   }

   public void setUser(String var1) {
      this.user = var1;
   }

   public void set_id(int var1) {
      this._id = var1;
   }
}
