package com.relo.handler.notice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.notice.NoticeService;
import com.relo.notice.NoticeVo;

public class NoticeAdd implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		int nCategory = Integer.parseInt(request.getParameter("nCategory"));
		String nFile = request.getParameter("nFile");
		
		ObjectMapper mapper = new ObjectMapper();
		NoticeVo notice = new NoticeVo(0, id, nTitle, nContent, null, nCategory, nFile);
		NoticeService service = new NoticeService();
		try {
			service.addNotice(notice);
			return null;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}
}