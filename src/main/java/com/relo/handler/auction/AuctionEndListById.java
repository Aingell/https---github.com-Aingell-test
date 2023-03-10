package com.relo.handler.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.auction.AuctionDTO;
import com.relo.auction.AuctionService;
import com.relo.exception.FindException;
import com.relo.handler.Handler;

public class AuctionEndListById implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		AuctionService service = new AuctionService();
		
		List<AuctionDTO> list = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
		
		try {
			list = service.getEndListById(id);
			String jsonStr = mapper.writeValueAsString(list);
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
