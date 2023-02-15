package com.relo.handler.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.member.MemberService;
import com.relo.member.MemberVo;

public class MemberIdOrPwdFind implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// flag -> 1이면 아이디 찾기, 2이면 비번 찾기
		int flag = Integer.parseInt(request.getParameter("flag"));

		ObjectMapper mapper = new ObjectMapper();
		MemberService service = new MemberService();
		MemberVo m = null;
		JSONObject obj = new JSONObject();
		String message = null;
		try {
			String jsonStr = null;
			// 아이디 찾는 경우
			if (flag == 1) {
				String mName = request.getParameter("mName");
				String email = request.getParameter("email");

				Map<String, String> param = new HashMap<>();
				param.put("mName", mName);
				param.put("email", email);
				m = service.findId(param);

				if (m != null) {
					obj.put("id", m.getId());
					return jsonStr = mapper.writeValueAsString(obj);
				} else {
					message = "일치하는 아이디 없음";
					return jsonStr = mapper.writeValueAsString(message);
				}
				// 비번 찾는 경우
			} else {
				String id = request.getParameter("id");
				String email = request.getParameter("email");

				m = service.getOne(id);
				if (m != null && email.equals(m.getEmail())) {
					obj.put("pwd", m.getPwd());
					return jsonStr = mapper.writeValueAsString(obj);
				} else {
					message = "일치하는 비밀번호 없음";
					return jsonStr = mapper.writeValueAsString(message);
				}
			}
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}

}