package com.relo.handler.orders;

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
import com.relo.orders.OrdersService;
import com.relo.orders.OrdersVo;

public class OrdersEdit implements Handler {

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
		
		int addrNum = Integer.parseInt(request.getParameter("addrNum"));
		int oNum = Integer.parseInt(request.getParameter("oNum"));
		
		Map<String, Integer> map = new HashMap();
		map.put("addrNum", addrNum);
		map.put("oNum", oNum);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersService service = new OrdersService();
		
		try {
			service.editAddrNum(map);
			Map result = new HashMap();
			
			OrdersVo vo = service.getOrderDetailByNum(oNum);
			result = mapper.convertValue(vo, Map.class);
			result.put("msg", "배송지 주소 수정이 완료되었습니다.");
			String jsonStr = mapper.writeValueAsString(result);
			return jsonStr;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map1 = new HashMap<>();
			map1.put("msg", e.getMessage());
			
			String jsonStr = mapper.writeValueAsString(map1);
			return jsonStr;
		}
	}

}