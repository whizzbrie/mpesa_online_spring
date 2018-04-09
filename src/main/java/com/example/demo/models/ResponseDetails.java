package com.example.demo.models;

import java.util.Date;

public class ResponseDetails {
	
	private Date timestamp;
	private String message;
	private String details;
	
	public ResponseDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp =timestamp;
		this.message = message;
		this.details = details;
	}
}
