package com.relo.handler.zzim;

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
import com.relo.product.ProductVo;
import com.relo.sizes.SizesVo;
import com.relo.stock.StockVo;
import com.relo.zzim.ZzimService;
import com.relo.zzim.ZzimVo;

public class ZzimList implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// String cp = request.getParameter("currentPage");
		// http://localhost:8888/myback/productlist ==> cp=null
		// http://localhost:8888/myback/productlist?currentPage= ==>"".equals(cp))
		// int currentPage = 1;
		// if (cp != null && !"".equals(cp)) {
		// currentPage = Integer.parseInt(cp);
		// }
		String id = request.getParameter("id");

		ObjectMapper mapper = new ObjectMapper();
		ZzimService service = new ZzimService();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일");
		JSONArray arr = new JSONArray();
		List<ZzimVo> list;
		try {
			list = service.getById(id);
			for (ZzimVo vo : list) {
				List<ProductVo> pli = vo.getProduct();
				for (ProductVo p : pli) {
					JSONObject product = new JSONObject();
					product.put("id", id);
					product.put("pNum", p.getPNum());
					String strEndDate = dateformat.format(p.getPEndDate());
					product.put("pEndDate", strEndDate);
					StockVo svo = p.getStock();
					product.put("sFile", svo.getSFile());
					product.put("sBrand", svo.getSBrand());
					product.put("sName", svo.getSName());
					product.put("sType", svo.getSType());
					product.put("sColor", svo.getSColor());
					product.put("sGrade", svo.getSGrade());
					product.put("sHopePrice", svo.getSHopePrice());
					SizesVo szvo = svo.getSizes();
					product.put("sizeCategoryName", szvo.getSizeCategoryName());
					List<AuctionVo> ali = p.getAuction();
					for (AuctionVo a : ali) {
						product.put("aPrice", a.getAPrice());
					}
					arr.add(product);
				}
			}
			System.out.println(list);
			String jsonStr = mapper.writeValueAsString(arr);
			return jsonStr;
		} catch (

		FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}

	}

}