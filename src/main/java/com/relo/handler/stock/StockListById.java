package com.relo.handler.stock;

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
import com.relo.sizes.SizesVo;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class StockListById implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		
	//	String id = (String) session.getAttribute("loginId");
	//	String id = request.getParameter("id");
		String id = "bbb";
		ObjectMapper mapper = new ObjectMapper();
		StockService service = new StockService();
		JSONArray arr = new JSONArray();
		List<StockVo> list;
		try {
			list = service.getById(id);
			for(StockVo vo : list) {
				JSONObject stock = new JSONObject();
				stock.put("sNum", vo.getSNum());
				SizesVo svo = vo.getSizes();
				stock.put("sizeCategoryName", svo.getSizeCategoryName());
				stock.put("sName", vo.getSName());
				stock.put("sGrade", vo.getSGrade());
				stock.put("sFile", vo.getSFile());
				stock.put("sReturn", vo.getSReturn());
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