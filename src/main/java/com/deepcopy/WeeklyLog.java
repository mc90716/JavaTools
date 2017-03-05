package com.deepcopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WeeklyLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Attachment attachment;
	private String name;
	private String date;
	private String content;

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Attachment getAttachment() {
		return this.attachment;
	}

	public String getName() {
		return this.name;
	}

	public String getDate() {
		return this.date;
	}

	public String getContent() {
		return this.content;
	}
	
	public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
		//Write Object to Stream
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bao);
		oos.writeObject(this);
		//Read Object from Stream
		ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		WeeklyLog cloneObj =  (WeeklyLog)ois.readObject();
		bao.close();
		bis.close();
		return cloneObj;
	}
	
	public static void main(String[] args) {
		WeeklyLog log_previous = null, log_new = null;
		log_previous = new WeeklyLog();
		Attachment attachment = new Attachment();
		log_previous.setAttachment(attachment);
		try {
			log_new = log_previous.deepClone();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Clone Failed");
		}
		System.out.println(log_previous == log_new);
		System.out.println(log_previous.getAttachment() == log_new.getAttachment());
	}
}
