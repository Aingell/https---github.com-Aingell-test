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
import com.relo.member.MemberVo;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;
import com.relo.stock.StockVo;

public class ProductList implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.105:5500");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		System.out.print(loginId);
		
		String sType = request.getParameter("sType"); // 전체, 상의, 신발, 하의
		System.out.println(sType);
		String orderkind = request.getParameter("orderkind"); // 정렬 순서
		System.out.println(orderkind);
		String condition = request.getParameter("condition"); // 찜순, 입찰수=0, 최신순,
		System.out.println(condition);
		Map<String, Object> scondition = new HashMap<>();
		ProductService service = new ProductService();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일");
		JSONArray arr = new JSONArray();
		List<ProductVo> plist = null;
		try {
			switch (condition) {
			case "notender":
				scondition.put("type", sType);
				plist = service.getProdListNoTender(scondition);
				break;
			case "zzim":
				scondition.put("type", sType);
				plist = service.getProdListZzim(scondition);
				break;
			case "normal":
				scondition.put("type", sType);
				scondition.put("orderkind", orderkind);
				plist = service.getProdList(scondition);
				break;
			}
			// List<ProductVo> plist = service.getProdListZzim();
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
