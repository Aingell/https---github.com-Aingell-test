package com.relo.handler.stock;

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
import com.relo.sizes.SizesVo;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class StockListBySReturn implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		
		int sReturn = Integer.parseInt(request.getParameter("sReturn"));
		ObjectMapper mapper = new ObjectMapper();
		StockService service = new StockService();
		JSONArray arr = new JSONArray();
		List<StockVo> list;
		try {
			list = service.getBySReturn(sReturn);
			for(StockVo vo : list) {
				JSONObject stock = new JSONObject();
				stock.put("sNum", vo.getSNum());
				SizesVo svo = vo.getSizes();
				stock.put("sizeCategoryName", svo.getSizeCategoryName());
				stock.put("sName", vo.getSName());
				stock.put("sColor", vo.getSColor());
				stock.put("sFile", vo.getSFile());
				arr.add(stock);
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