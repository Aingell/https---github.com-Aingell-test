package com.relo.handler.zzim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.zzim.ZzimService;

public class ZzimDelete implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String id = request.getParameter("id");
		int pNum = Integer.parseInt(request.getParameter("pNum"));

		// 테스트용
		// String id = "bbb";
		// int pNum = 7;
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pNum", pNum);
		ZzimService service = new ZzimService();
		try {
			service.delZzim(map);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/zzim/list.do";
	}

}