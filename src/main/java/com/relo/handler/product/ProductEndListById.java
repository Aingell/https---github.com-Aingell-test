package com.relo.handler.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;
import com.relo.sizes.SizesVo;
import com.relo.stock.StockVo;

public class ProductEndListById implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		
	//	String id = (String) session.getAttribute("loginId");
		String id = request.getParameter("id");
		ProductService service = new ProductService();
		ObjectMapper mapper = new ObjectMapper();
		JSONArray arr = new JSONArray();
		List<ProductVo> list;
		try {
			list = service.getByEndProduct(id);
			for(ProductVo vo : list) {
				JSONObject product = new JSONObject();
				product.put("pStatus", vo.getPStatus());
				product.put("pNum", vo.getPNum());
				StockVo svo = vo.getStock();
				product.put("sFile", svo.getSFile());
				product.put("sName", svo.getSName());
				SizesVo siVo = svo.getSizes();
				product.put("sizeCategoryName", siVo.getSizeCategoryName());
				arr.add(product);
			}
			String jsonStr = mapper.writeValueAsString(arr);
			return jsonStr;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String>map = new HashMap<>();
			map.put("msg", e.getMessage());
			mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}

}
