package com.relo.handler.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.member.MemberService;
import com.relo.member.MemberVo;

public class MemberIdCheck implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String id = request.getParameter("id");

		ObjectMapper mapper = new ObjectMapper();
		MemberService service = new MemberService();
		try {
			MemberVo m = service.getOne(id);
			int status = -1;
			Map<String, Object> map = new HashMap<>();
			String message = "";
			if (m != null) {
				message = "중복된 아이디";
				status = 0;
			} else {
				message = "사용 가능한 아이디";
				status = 1;
			}
			map.put("message", message);
			map.put("status", status);
			String jsonStr = mapper.writeValueAsString(map);
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