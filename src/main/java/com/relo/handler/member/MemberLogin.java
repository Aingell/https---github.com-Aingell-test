package com.relo.handler.member;

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
import com.relo.member.MemberService;
import com.relo.member.MemberVo;

public class MemberLogin implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		ObjectMapper mapper = new ObjectMapper();
		MemberService service = new MemberService();
		try {
			MemberVo m = service.getOne(id);
			String message = "";
			if (m == null) {
				message = "가입된 회원이 아님";
			} else {
				if (!id.equals(m.getId()) || !pwd.equals(m.getPwd()))
					message = "아이디, 비밀번호 불일치";
				else {
					HttpSession session = request.getSession();
					session.setAttribute("loginId", id);
					if (m.getType() == 1) // 관리자 모드
						return null;
					else
						return null; // 판매자 및 구매자 모드
				}
			}
			String jsonStr = mapper.writeValueAsString(message);
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