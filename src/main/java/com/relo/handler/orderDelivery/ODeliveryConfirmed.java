package com.relo.handler.orderDelivery;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.orderDelivery.ODeliveryService;

public class ODeliveryConfirmed implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		int oNum = Integer.parseInt(request.getParameter("oNum"));
		
		ODeliveryService service = new ODeliveryService();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			service.editStatus3(oNum);
			Map<String, String> map = new HashMap<>();
			map.put("dStatus", "3");
			map.put("msg", "구매확정이 완료되었습니다.");
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}
}
