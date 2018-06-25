package com.bytepoet.undp.platformdemo.controllers;

public enum Document {

	ZOP("PotvrdaZOP.docx"),
	ZKP("PotvrdaZKP.docx"),
	CRIM("KrivicnaPrijava.docx"),
	FORM("OBRAZAC.docx");
	
	private final String fileName;

	private Document(String name) {
		this.fileName = name;
	}

	public String getFileName() {
		return fileName;
	}
	
	
}
