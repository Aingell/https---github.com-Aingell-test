package com.relo.req;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.relo.handler.Handler;


/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//명령어와 실행할 handler 객체를 쌍으로 저장할 맵 생성
	private Map<String, Handler> map = new HashMap<>();

	/** 
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//init() : 서블릿 페이지가 처음 실행될 때 초기화시키는 메서드 → 클래스 객체 생성ㅋ
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		Properties prop = new Properties(); //properties > Map과 비슷하게 키와 값 저장하는 클래스
		String path = "/WEB-INF/command.properties"; //properties 파일의 경로
		String path2 = getServletContext().getRealPath(path); //웹에서 접근할 수 있는 주소로 변경
		try {
			//파일의 내용을 읽어서 properties에 (키/값)으로 저장 
			prop.load(new FileReader(path2)); //FileReader : 문자 단위로 읽는 스트림
			
			Iterator iter = prop.keySet().iterator(); //properties의 키 값 묶음에 반복자 붙여서
			while (iter.hasNext()) { //읽을 키 값이 있을 동안 반복
				String url = (String) iter.next(); //키를 하나씩 읽어서 url에 담고
				String className = prop.getProperty(url); //키에 해당하는 값(=클래스명)을 className에 저장
				try {
					//url키와 매핑이 되는 클래스명을 불러와서, 그 이름으로 클래스 정보를 읽고 반환
					Class<?> handlerClass = Class.forName(className); 
					
					//Class 객체의 newInstance()메서드 = 객체 생성
					Constructor<?> cons = handlerClass.getConstructor(null);
					Handler handler = (Handler) cons.newInstance();	 
					
					//28행에 생성한 map에 넣기
					//명령어와 handler 객체를 쌍으로 등록
					map.put(url, handler);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getServletPath(); //요청 url
		System.out.println(url);
		Handler handler = map.get(url); //명령어로 등록되어있는 handler 객체를 꺼냄
		
		//모두 process 메서드를 갖고 있기에 그걸 호출해서 view페이지 경로를 반환 받음 
		//	여기서 redirect인지 forward인지 확인해서 맞는 방법으로 보내줌
//		String view = handler.process(request, response);
//		if (view.startsWith("redirect:")) {
//			String path = view.split(":")[1];
//			response.sendRedirect(request.getContextPath() + path);
//		} else if(view.startsWith("responsebody/")) {
//			response.getWriter().append(view.split("/")[1]);
//		} else {
//			RequestDispatcher dis = request.getRequestDispatcher(view);
//			dis.forward(request, response);
//		}
		
		String jsonResult = handler.process(request, response);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}