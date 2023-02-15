package com.relo.handler.zzim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.zzim.ZzimService;

public class ZzimInsert implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String id = request.getParameter("id");
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		System.out.println(id + pNum);

		// HttpSession session = (HttpSession) request.getSession(false);
		// String loginId = (String) session.getAttribute("loginId");
		// int pNum = 7;
		ObjectMapper mapper = new ObjectMapper();
		ZzimService service = new ZzimService();

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pNum", pNum);
		List<String> list;
		String message = "";
		try {
			list = service.getAll(map);
			System.out.println(list);
			if (list.isEmpty() == false) {
				message = "이미 찜하기 등록한 상품입니다.";
				String jsonStr = mapper.writeValueAsString(message);
				return jsonStr;
			}
			service.addZzim(map);
			message = "찜하기가 완료 되었습니다.";
			String jsonStr = mapper.writeValueAsString(message);
			return jsonStr;

		} catch (

		FindException e) {
			e.printStackTrace();
			Map<String, String> map1 = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map1);
			return jsonStr;
		}

	}
}