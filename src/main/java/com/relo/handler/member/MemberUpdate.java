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

public class MemberUpdate implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("loginId");
		String pwd = request.getParameter("pwd");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String mName = request.getParameter("mName");

		MemberVo member = new MemberVo(id, pwd, tel, email, 0, null, mName);

		ObjectMapper mapper = new ObjectMapper();

		MemberService service = new MemberService();
		try {
			service.editMember(member);
			String message = "해당 변경사항이 수정되었습니다.";
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