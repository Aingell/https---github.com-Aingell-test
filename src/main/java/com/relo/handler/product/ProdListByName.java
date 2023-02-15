package com.relo.handler.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.relo.stock.StockVo;

public class ProdListByName implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String prodName = request.getParameter("prodName");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일");
		ProductService service = new ProductService();
		ObjectMapper mapper = new ObjectMapper();
		JSONArray arr = new JSONArray();
		String searchvalue = "%" + prodName + "%";
		try {
			List<ProductVo> plist = service.getProdListByName(searchvalue);

			for (ProductVo pvo : plist) {
				JSONObject obj = new JSONObject();
				obj.put("pNum", pvo.getPNum());
				String strEndDate = dateformat.format(pvo.getPEndDate());
				obj.put("pEndDate", strEndDate);
				StockVo svo = pvo.getStock();
				obj.put("sName", svo.getSName());
				obj.put("sHopePrice", svo.getSHopePrice());
				obj.put("sName", svo.getSName());
				obj.put("sFile", svo.getSFile());
				List<AuctionVo> alist = pvo.getAuction();
				for (AuctionVo avo : alist) {
					obj.put("aPrice", avo.getAPrice());
				}
				arr.add(obj);
			}

			String jsonStr = mapper.writeValueAsString(arr);
			return jsonStr;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}

	}

}
