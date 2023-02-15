package com.relo.handler.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.auction.AuctionService;
import com.relo.exception.FindException;
import com.relo.handler.Handler;

public class AuctionAdd implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String id = request.getParameter("id");
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		String aPrice = request.getParameter("aPrice");
		
		AuctionService service = new AuctionService();
		
		Map map = new HashMap();
		
		map.put("id", id);
		map.put("pNum", pNum);
		
		Integer aNum = 0;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//입찰 시도시, 기존에 입찰한 이력이 있는지 조회
			aNum = service.getById(map);
			map.put("aPrice", aPrice);
			Map result = new HashMap();
			result.put("aPrice", aPrice);
			result.put("id", id);
			if (aNum != null) {
				Map map1 = new HashMap();
				map1.put("aPrice", aPrice);
				map1.put("aNum", aNum);
				service.editAuction(map1);

				result.put("msg", "재입찰 완료");
				String jsonStr = mapper.writeValueAsString(result);
				return jsonStr;
			}
			else {
				service.addAuction(map);
				result.put("msg", "입찰 완료");
				String jsonStr = mapper.writeValueAsString(result);
				
				return jsonStr;
			}
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
