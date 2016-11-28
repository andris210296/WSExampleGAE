package br.com.wsexamplegae.model;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class Informacao {

	private Key keyInfo;
	private String stringInfo;
	private int intInfo;
	private Date dateInfo;
	private double doubleInfo;

	public Informacao(String stringInfo, int intInfo, Date dateInfo, double doubleInfo) {
		this.stringInfo = stringInfo;
		this.intInfo = intInfo;
		this.dateInfo = dateInfo;
		this.doubleInfo = doubleInfo;
	}

	public Informacao() {

	}

	public String getStringInfo() {
		return stringInfo;
	}
	public void setStringInfo(String stringInfo) {
		this.stringInfo = stringInfo;
	}
	public int getIntInfo() {
		return intInfo;
	}
	public void setIntInfo(int intInfo) {
		this.intInfo = intInfo;
	}
	public Date getDateInfo() {
		return dateInfo;
	}
	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}
	public double getDoubleInfo() {
		return doubleInfo;
	}
	public void setDoubleInfo(double doubleInfo) {
		this.doubleInfo = doubleInfo;
	}
	public Key getKeyInfo() {
		return keyInfo;
	}
	public void setKeyInfo(Key keyInfo) {
		this.keyInfo = keyInfo;
	}	
	
}
