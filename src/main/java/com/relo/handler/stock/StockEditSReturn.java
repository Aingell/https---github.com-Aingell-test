package com.relo.handler.stock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class StockEditSReturn implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		int sNum = Integer.parseInt(request.getParameter("sNum"));
		try {
			
			String sHopePrice = request.getParameter("sHopePrice");
			String sGrade = request.getParameter("sGrade");
			String managerComment = request.getParameter("managerComment");
			if(sHopePrice == null) {
				
				StockService service = new StockService();
				StockVo vo = new StockVo(sNum, null, null, null, null, 0, 0, null, null, sGrade, null, 0, null,
						managerComment, 0);
				service.editSetSReturn(vo);
				return null;	
			}else{
				StockService service = new StockService();
				int sHopePri = Integer.parseInt(sHopePrice);
				StockVo vo = new StockVo(sNum, null, null, null, null, 0, sHopePri, null, null, null, null, 0, null,
						null, 0);
				service.editSetSReturn(vo);
				return null;
			}
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		} 
	}

}