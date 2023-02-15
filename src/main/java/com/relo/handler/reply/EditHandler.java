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

public class EditHandler implements Handler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//댓글 수정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String id = request.getParameter("id");
		int repNum = Integer.parseInt(request.getParameter("repNum"));
		String repContent = request.getParameter("repContent");
		
		ReplyService service = new ReplyService();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			service.updateRep(new ReplyVo(repNum,0,id,repContent,null));
			String jsonStr = mapper.writeValueAsString("댓글 수정 성공");
			return jsonStr;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}

}