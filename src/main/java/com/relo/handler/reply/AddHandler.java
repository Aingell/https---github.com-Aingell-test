package com.relo.handler.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.reply.ReplyService;
import com.relo.reply.ReplyVo;

public class AddHandler implements Handler {
	//댓글 추가
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용

		int styleNum = Integer.parseInt(request.getParameter("styleNum"));
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String repContent = request.getParameter("repContent"); 
		System.out.println();
		ObjectMapper mapper = new ObjectMapper();

		if(loginId == null) {
			String jsonStr = mapper.writeValueAsString("로그인을 해주세요.");
			return jsonStr;
		}
		else {

			ReplyService service = new ReplyService();
			try {
				service.addReply(new ReplyVo(0,styleNum,loginId,repContent,null));
				String jsonStr = mapper.writeValueAsString("댓글 작성완료");
				return jsonStr;
			} catch (FindException e) {
				e.printStackTrace();
				String jsonStr = mapper.writeValueAsString("댓글 작성오류");
				return jsonStr;
			}
		}
	}

}