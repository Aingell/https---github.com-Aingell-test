package com.relo.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요청을 처리할 클래스의 조상
public interface Handler {
	String process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException; 
}