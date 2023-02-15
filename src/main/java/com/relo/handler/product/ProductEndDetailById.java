package com.relo.handler.product;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.account.AccountService;
import com.relo.account.AccountVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;

public class ProductEndDetailById implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		HttpSession session = request.getSession(false);
		
		String id = (String) session.getAttribute("loginId");
	//	String id = request.getParameter("id");
		try {
			ProductService service = new ProductService();
			AccountService aservice = new AccountService();
			AccountVo avo =  aservice.getByIdAccount(id);
			ObjectMapper mapper = new ObjectMapper();
			ProductVo vo = service.getByEndProductDetail(id);
			JSONArray arr = new JSONArray();
			//계좌정보
			arr.add(avo);
			//판매종료된 상품정보
			arr.add(vo);
			String jsonStr = mapper.writeValueAsString(arr);
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
