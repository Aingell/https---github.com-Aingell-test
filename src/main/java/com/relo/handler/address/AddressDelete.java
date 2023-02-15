package com.relo.handler.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.address.AddressService;
import com.relo.address.AddressVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;

public class AddressDelete implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.105:5500");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용

		int addrNum = Integer.parseInt(request.getParameter("addrNum"));

		ObjectMapper mapper = new ObjectMapper();
		AddressService service = new AddressService();
		try {
			service.delAddress(addrNum);
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