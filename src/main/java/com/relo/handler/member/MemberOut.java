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

public class MemberOut implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String pwd = request.getParameter("pwd");
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("loginId", "fff");
		String loginId = (String) session.getAttribute("loginId");
		
		MemberService service = new MemberService();
		ObjectMapper mapper = new ObjectMapper();
		
		int status = 0;
		String message = "";
		try {
			MemberVo m = service.getOne(loginId);
			if (pwd.equals(m.getPwd())) {
				int num = service.checkOutTerms(loginId);
				if (num != 1) {
					message = "탈퇴가 제한된 경우에 해당되므로 탈퇴할 수 없습니다.";
				} else {
					service.delMember(loginId);
					status = 1;
					message = "탈퇴되었습니다.";
				}
			} else {
				message = "비밀번호 불일치로 탈퇴할 수 없습니다.";
			}
			Map<String,Object> map = new HashMap<>();
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