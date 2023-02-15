package com.relo.handler.successfulBid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.successfulBid.ScBidService;
import com.relo.successfulBid.ScBidVo;

public class ScBidListByPstatus implements Handler {

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
		
		ScBidService service = new ScBidService();
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
		try {
			List<ScBidVo> list = service.getListByPStatus();
			String jsonStr = mapper.writeValueAsString(list);
			return jsonStr;
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