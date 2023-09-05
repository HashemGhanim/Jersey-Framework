package com.dome.restDemo.Model;

public class RestDomeException extends Exception{
	public RestDomeException() {
		super("{\n \"message\" : \"" + "There is an Internal Error" + "\"\n}");
	}
	public RestDomeException(String message) {
		super("{\n \"message\" : \"" + message + "\"\n}");
	}
}
