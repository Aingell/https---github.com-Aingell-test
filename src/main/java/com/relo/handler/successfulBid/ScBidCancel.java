package com.relo.handler.successfulBid;

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
import com.relo.product.ProductService;
import com.relo.successfulBid.ScBidService;

public class ScBidCancel implements Handler {

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
		ScBidService service = new ScBidService();
		ProductService pService = new ProductService();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			pService.editStatus8(aNum);
			service.delCatch(aNum);
			
			Map<String, String> map = new HashMap<>();
			map.put("msg", "낙찰 포기가 완료되었습니다.");
			map.put("flag", "true");
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