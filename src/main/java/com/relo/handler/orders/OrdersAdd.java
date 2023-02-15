package com.relo.handler.orders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.orderDelivery.ODeliveryService;
import com.relo.orders.OrdersService;
import com.relo.orders.OrdersVo;

public class OrdersAdd implements Handler {

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

		int aNum = Integer.parseInt(request.getParameter("aNum"));
		int addrNum = Integer.parseInt(request.getParameter("addrNum"));
		String oMemo = request.getParameter("oMemo");

		Map map = new HashMap();
		map.put("aNum", aNum);
		map.put("addrNum", addrNum);
		map.put("oMemo", oMemo);

		OrdersService service = new OrdersService();
		ODeliveryService service2 = new ODeliveryService();
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
        
		try {
			int oNum = service.addOrders(map);
			
			Map map3 = new HashMap();
			map3.put("oNum", oNum);

			// 운송장 번호 랜덤으로 발생시키기 6개, 7개 순
			int preValue = (int) (Math.random() * 1000000);
			int postValue = (int) (Math.random() * 10000000);

			// 운송장 번호
			String trackin = String.valueOf(preValue) + "-" + String.valueOf(postValue);

			map3.put("dTrackinInfo", trackin);
			service2.addODelivery(map3);
			
			OrdersVo vo = service.getOrderDetailByNum(oNum);
			
			Map result = new HashMap();
			result = mapper.convertValue(vo, Map.class);
			
			result.put("msg", "주문이 완료되었습니다.");
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