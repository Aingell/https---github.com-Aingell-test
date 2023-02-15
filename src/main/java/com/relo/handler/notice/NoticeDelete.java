package com.relo.handler.notice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.notice.NoticeService;

public class NoticeDelete implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		int nNum = Integer.parseInt(request.getParameter("nNum"));

		ObjectMapper mapper = new ObjectMapper();
		NoticeService service = new NoticeService();

		try {
			service.delNotice(nNum);
			String message = "삭제되었음";
			String jsonStr = mapper.writeValueAsString(message);
			return jsonStr;
		} catch (FindException | JsonProcessingException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = null;
			try {
				jsonStr = mapper.writeValueAsString(map);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
			return jsonStr;
		}
	}
}