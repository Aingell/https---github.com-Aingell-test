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
import com.relo.auction.AuctionVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;

public class RecentTender implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		int pNum = Integer.parseInt(request.getParameter("pNum"));

		ProductService service = new ProductService();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject tender = null;
		JSONArray arr = new JSONArray();
		try {
			List<ProductVo> tlist = service.getrecentTender(pNum);
			for (ProductVo p : tlist) {
				List<AuctionVo> alist = p.getAuction();
				for (AuctionVo a : alist) {
					tender = new JSONObject();
					tender.put("pNum", pNum);
					tender.put("id", a.getId());
					tender.put("aPrice", a.getAPrice());
					arr.add(tender);

				}

			}
			String jsonStr = mapper.writeValueAsString(arr);
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
