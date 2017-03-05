package com.deepcopy;

import java.io.Serializable;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void download() {
		System.out.println("Download [" + this.name + "");
	}
}
