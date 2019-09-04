package com.fastsync.client;

public class FSNativeLib {

	  static {
	    System.loadLibrary("fastsync");
	  }
	  
	  public native int StartSync(String ip, int port, String user, String serial, int tout, String group, String dbpath, String dbname);  
	  public native String hello();
	}