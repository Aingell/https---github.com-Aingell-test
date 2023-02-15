package com.relo.handler.stock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class StockDetailById implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map m = new HashMap<>();

		HttpSession session = request.getSession();
		session.setAttribute("loginId", "bbb");
		String id = (String) session.getAttribute("loginId");
		m.put("id", id);

		int sNum = Integer.parseInt(request.getParameter("sNum"));
		m.put("sNum", sNum);
		StockService service = new StockService();

		try {
			StockVo vo = service.selectByIdDeatil(m);
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(vo);
			return jsonStr;
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