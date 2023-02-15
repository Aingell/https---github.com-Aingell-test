package com.relo.handler.stock;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.member.MemberVo;
import com.relo.sizes.SizesVo;
import com.relo.stock.StockService;
import com.relo.stock.StockVo;

public class StockAdd implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		StockService service = new StockService();
		//HttpSession session = request.getSession(false);
	
	//	String id = (String) session.getAttribute("loginId");
		String id = "bbb";
		
		//String saveDirectory = "C:\\Users\\kosta\\Desktop\\255\\attach";
//		String saveDirectory = "C:\\Users\\jgh\\git\\relo\\relofront\\imgs";
		
		String saveDirectory = "C:\\255\\MyWEB\\myfront\\relo\\imgs\\stock";
		
		int maxPostSize = 100*100*1024;
		String encoding = "UTF-8";
		//파일첨부
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);
		
		//String id = mr.getParameter("id");
		int sizeCategoryNum = Integer.parseInt(mr.getParameter("sizeCategoryNum"));
		String sBrand = mr.getParameter("sBrand");
		String sName = mr.getParameter("sName");
		int sOriginPrice = Integer.parseInt(mr.getParameter("sOriginPrice"));
		String sColor = mr.getParameter("sColor");
		String sType = mr.getParameter("sType");
		
		int sHopeDays = Integer.parseInt(mr.getParameter("sHopeDays"));
		String sellerComment = mr.getParameter("sellerComment");
		File f = mr.getFile("sFile");
		
		try {
			String sFile = f.getName();
			MemberVo mvo = new MemberVo(id, null, null, null, 0, null, null);
			SizesVo svo = new SizesVo(sizeCategoryNum, null);
			StockVo vo = new StockVo(0, mvo, svo, sBrand, sName, sOriginPrice, sColor, sType, sFile, sHopeDays, sellerComment,1);
			service.addStock(vo);
			return null;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String>map = new HashMap<>();
			map.put("msg", e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
		
		
	}

}