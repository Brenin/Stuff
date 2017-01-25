package com.lundin_pr.cotroceniOnIce.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notification {

	private StringProperty title;
	private StringProperty header;
	private StringProperty msg;
	private StringProperty link;
	private StringProperty note;
	
	private Date date;
	
	public Notification() {
		this(null, null, null, null);
	}

	public Notification(String title, String header, String msg, String link) {
		this.title = new SimpleStringProperty(title);
		this.header = new SimpleStringProperty(header);
		this.msg = new SimpleStringProperty(msg);
		this.link = new SimpleStringProperty(link);
		this.note = new SimpleStringProperty("");
		setDate(new Date());
	}

	public String getDate() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
		return dt.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StringProperty getTitleProperty() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public String getTitle(){
		return title.get();
	}

	public StringProperty getHeaderProperty() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header.set(header);
	}
	
	public String getHeader(){
		return header.get();
	}

	public StringProperty getMsgProperty() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg.set(msg);
	}
	
	public String getMsg(){
		return msg.get();
	}

	public StringProperty getLinkProperty() {
		return link;
	}

	public void setLink(String link) {
		this.link.set(link);
	}

	public String getLink(){
		return link.get();
	}
	
	public StringProperty getNoteProperty() {
		return note;
	}

	public void setNote(String note) {
		this.note.set(note);
	}

	public String getNote(){
		return note.get();
	}
}
