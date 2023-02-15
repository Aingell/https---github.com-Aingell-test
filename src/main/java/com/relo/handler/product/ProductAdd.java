package com.relo.handler.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class ProductAdd implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		ProductService service = new ProductService();
		StockService s = new StockService();
		Map m = new HashMap<>();
		
		int sNum = Integer.parseInt(request.getParameter("sNum")) ;
		try {
			m.put("sNum", sNum);
			StockVo svo = s.getBySNum(sNum);
			int sHopeDays = svo.getSHopeDays();
			m.put("pEndDate", sHopeDays);
			service.addProduct(m);
			return null;
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
