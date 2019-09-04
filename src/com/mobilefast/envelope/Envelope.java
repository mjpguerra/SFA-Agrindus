package com.mobilefast.envelope;
 
/*
 *     Classe de Envelope troca de dados DTO 
 *     Criada pelo JDavincci de Ray da Costa 
 *     http://www.raydacosta.com 
 *     raydacosta@ray.system.nom.br 
 *     Descrição: Objeto que será utilizado par transportar dados, objetos
 *     @author raydacosta + JDavincci 
 */
 
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class Envelope implements Serializable{  
      private String envelope_IP = null;
      private static final long serialVersionUID = 1L;
      private Map parametro = new HashMap();
      private  int envelope_cmd = 0;
      private  int id = 0;
      private  int envelope_tpoPedido = 0;
      private  int envelope_tpoMens = 0;
      private  int envelope_statusProc = 0;
      private  int envelope_tpoRetorno = 0;
      private  String envelope_status = null;
      private  String envelope_data = null;
      private  String envelope_destinatario = null;
      private  String envelope_idObjeto = null;
      private  String envelope_descricao = null;
      private  String envelope_dados = null;
      private  String envelope_idUsuario = null;
      private  String envelope_idEmpresa = null;
      private  String envelope_email = null;
      private  String envelope_nome = null;
      private  String envelope_remetente = null;
      private  String envelope_idSuperior = null;
      private  String envelope_idSessao = null;
      private  int envelope_idPersistencia = 0;
      private  Vector lista = new Vector();
     public Envelope(){}
     public Envelope(String nome){this.setEnvelope_nome(nome);}
     public int getEnvelope_tpoPedido() {return envelope_tpoPedido;}
     public void setEnvelope_tpoPedido(int envelope_tpoPedido) {this.envelope_tpoPedido = envelope_tpoPedido;}
     public String getEnvelope_status() {return envelope_status;}
     public void setEnvelope_status(String envelope_status) {this.envelope_status = envelope_status;}
     public int getEnvelope_tpoMens() {return envelope_tpoMens;}
     public void setEnvelope_tpoMens(int envelope_tpoMens) {this.envelope_tpoMens = envelope_tpoMens;}
     public int getEnvelope_statusProc() {return envelope_statusProc;}
     public void setEnvelope_statusProc(int envelope_statusProc) {this.envelope_statusProc = envelope_statusProc;}
     public String getEnvelope_destinatario() {return envelope_destinatario;}
     public void setEnvelope_destinatario(String envelope_destinatario) {this.envelope_destinatario = envelope_destinatario;}
     public String getEnvelope_idObjeto() {return envelope_idObjeto;}
     public void setEnvelope_idObjeto(String envelope_idObjeto) {this.envelope_idObjeto = envelope_idObjeto;}
     public String getEnvelope_descricao() {return envelope_descricao;}
     public void setEnvelope_descricao(String envelope_descricao) {this.envelope_descricao = envelope_descricao;}
     public String getEnvelope_dados() {return envelope_dados;}
     public void setEnvelope_dados(String envelope_dados) {this.envelope_dados = envelope_dados;}
     public int getEnvelope_cmd() {return envelope_cmd;}
     public void setEnvelope_cmd(int envelope_cmd) {this.envelope_cmd = envelope_cmd;}
     public String getEnvelope_idUsuario() {return envelope_idUsuario;}
     public void setEnvelope_idUsuario(String envelope_idUsuario) {this.envelope_idUsuario = envelope_idUsuario;}
     public String getEnvelope_idEmpresa() {return envelope_idEmpresa;}
     public void setEnvelope_idEmpresa(String envelope_idEmpresa) {this.envelope_idEmpresa = envelope_idEmpresa;}
     public int getId() {return id;}
     public void setId(int id) {this.id = id;}
     public String getEnvelope_email() {return envelope_email;}
     public void setEnvelope_email(String envelope_email) {this.envelope_email = envelope_email;}
     public String getEnvelope_nome() {return envelope_nome;}
     public void setEnvelope_nome(String envelope_nome) {this.envelope_nome = envelope_nome;}
     public String getEnvelope_remetente() {return envelope_remetente;}
     public void setEnvelope_remetente(String envelope_remetente) {this.envelope_remetente = envelope_remetente;}
     public String getEnvelope_idSuperior() {return envelope_idSuperior;}
     public void setEnvelope_idSuperior(String envelope_idSuperior) {this.envelope_idSuperior = envelope_idSuperior;}
     public int getEnvelope_idPersistencia() {return envelope_idPersistencia;}
     public void setEnvelope_idPersistencia(int envelope_idPersistencia) {this.envelope_idPersistencia = envelope_idPersistencia;}
     public int getEnvelope_tpoRetorno() {return envelope_tpoRetorno;}
     public void setEnvelope_tpoRetorno(int envelope_tpoRetorno) {this.envelope_tpoRetorno = envelope_tpoRetorno;}
     public String getEnvelope_idSessao() {return envelope_idSessao;}
     public void setEnvelope_idSessao(String envelope_idSessao) {this.envelope_idSessao = envelope_idSessao;}
     public String getEnvelope_data() {return envelope_data;}
     public void setEnvelope_data(String envelope_data) {this.envelope_data = envelope_data;}
     public Vector getLista() {return lista;}
     public void setLista(Vector lista) {this.lista = lista;}
     public Map getParametro() {return parametro;}
     public void setParametro(Map parametro) {this.parametro = parametro;}
     public String getEnvelope_IP() {return envelope_IP;}
     public void setEnvelope_IP(String envelope_IP) {this.envelope_IP = envelope_IP;}

} 