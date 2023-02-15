package com.relo.handler.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.relo.auction.AuctionVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;
import com.relo.sizes.SizesVo;
import com.relo.stock.StockVo;

public class ProductDetail implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.105:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		System.out.print(loginId);
		
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		ProductService service = new ProductService();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일");
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		try {
			ProductVo pvo = service.getProductDetail(pNum);
			StockVo svo = pvo.getStock();
			SizesVo szvo = svo.getSizes();
			obj.put("sizeCategoryName", szvo.getSizeCategoryName());
			List<AuctionVo> alist = pvo.getAuction();
			obj.put("pNum", pNum);
			obj.put("sFile", svo.getSFile());
			String strEndDate = dateformat.format(pvo.getPEndDate());
			obj.put("pEndDate", strEndDate);
			obj.put("sBrand", svo.getSBrand());
			obj.put("sName", svo.getSName());
			obj.put("sColor", svo.getSColor());
			obj.put("sOriginPrice", svo.getSOriginPrice());
			obj.put("sHopePrice", svo.getSHopePrice());
			obj.put("sGrade", svo.getSGrade());
			for (AuctionVo avo : alist) {
				obj.put("aPrice", avo.getAPrice());
			}
			obj.put("sManagerComment", svo.getManagerComment());
			arr.add(obj);
			System.out.println(obj);
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
