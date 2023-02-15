package com.relo.handler.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.account.AccountService;
import com.relo.account.AccountVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;

public class AccountInfo implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("loginId");
	//	String id = request.getParameter("id");
		AccountService service = new AccountService();
		
		try {
			AccountVo vo = service.getByIdAccount(id);
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(vo);
			return jsonStr;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String>map = new HashMap<>();
			map.put("msg", e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}

}