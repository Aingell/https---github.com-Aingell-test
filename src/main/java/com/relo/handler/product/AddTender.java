package com.relo.handler.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;

public class AddTender implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String id = request.getParameter("id");
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		int aPrice = Integer.parseInt(request.getParameter("aPrice"));
		int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		Map<String, Object> tmap = new HashMap<>();
		tmap.put("id", id);
		tmap.put("pNum", pNum);
		tmap.put("aPrice", aPrice);
		if (aPrice > maxPrice) {
			ProductService service = new ProductService();
			try {
				service.addTender(tmap);
				System.out.println("입찰 완료");

			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (aPrice <= maxPrice) {
			String txt = "최고 입찰가 보다 낮습니다";
			return txt;// 연결된 뷰페이지로 return
		}
		return null; // 연결된 뷰페이지로 return

	}
}
