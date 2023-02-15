package com.relo.handler.notice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.notice.NoticeService;
import com.relo.notice.NoticeVo;

public class NoticeList implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String title = request.getParameter("title");
		String s = request.getParameter("category");

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
		NoticeService service = new NoticeService();
		List<NoticeVo> list = null;
		try {
			if (title != null) {
				list = service.getAllByTitle(title);
			} else if (s != null) {
				int category = Integer.parseInt(s);
				list = service.getAllByCategory(category);
			} else {
				list = service.getAll();
			}
			String jsonStr = mapper.writeValueAsString(list);
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