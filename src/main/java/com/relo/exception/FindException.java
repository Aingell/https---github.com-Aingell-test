package com.relo.exception;

public class FindException extends Exception {

	public FindException() {
		//부모 생성자 호출
		super();
	}

	public FindException(String message) {
		super(message);
	}
	
}