package com.relo.handler.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;
import com.relo.sizes.SizesVo;
import com.relo.stock.StockVo;

public class ProducIsPStatus8tList implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		ObjectMapper mapper = new ObjectMapper();
		ProductService service = new ProductService();
		JSONArray arr = new JSONArray();
		List<ProductVo> list = null;
		try {
			list = service.getAllPStatusIs8();
			int cnt = 0;
			for (ProductVo p : list) {
				JSONObject obj = new JSONObject();
				JSONObject obj1 = new JSONObject();
				
				obj.put("product"+p.getPNum()+"day", p.getPEndDate());
				obj.put("product"+p.getPNum(), p.getPNum());
				
				StockVo s = p.getStock();
				obj1.put("stock"+cnt, s);
				
				arr.add(obj);
				arr.add(obj1);

				cnt++;
			}
			String jsonStr = mapper.writeValueAsString(arr);
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
