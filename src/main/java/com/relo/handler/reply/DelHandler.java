package com.relo.handler.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.reply.ReplyService;
import com.relo.reply.ReplyVo;

public class DelHandler implements Handler {
	//댓글 삭제
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String id = request.getParameter("id");
		int repNum = Integer.parseInt(request.getParameter("repNum"));
		
		ObjectMapper mapper = new ObjectMapper();
		ReplyService service = new ReplyService();
		try {
			service.delReply(new ReplyVo(repNum,0,id,null,null));
			String jsonStr = mapper.writeValueAsString("댓글 삭제 성공");
			return jsonStr;
		} catch (FindException e) {
			e.printStackTrace();
			String jsonStr = mapper.writeValueAsString("댓글 삭제 오류");
			return jsonStr;
		}
		
	}

}