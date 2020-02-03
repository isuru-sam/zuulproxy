package com.example.zuuldemo.controller;

public class VndError {
private String id;
private String message;


public VndError(String id, String message) {

	this.id = id;
	this.message = message;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}


}
