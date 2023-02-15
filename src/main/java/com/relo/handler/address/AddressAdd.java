package com.relo.handler.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.address.AddressService;
import com.relo.address.AddressVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;

public class AddressAdd implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.105:5500");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		
		String addrName = request.getParameter("addrName");
		int addrPostNum = Integer.parseInt(request.getParameter("addrPostNum"));
		String addrTel = request.getParameter("addrTel");
		String addr = request.getParameter("addr");
		String addrDetail = request.getParameter("addrDetail");
		String addrRecipient = request.getParameter("addrRecipient");
		int addrType = Integer.parseInt(request.getParameter("addrType"));
		
//		// flag 값이 1인 경우 주소지는 그냥 주소지로 등록, 그 외의 경우(0인 경우)는 insert 후 type을 0으로 업데이트 
//		int flag = Integer.parseInt(request.getParameter("flag"));

		ObjectMapper mapper = new ObjectMapper();
		AddressVo address = new AddressVo(0, id, addrName, addrPostNum, addrTel, addr, addrDetail, addrRecipient, addrType);
		AddressService service = new AddressService();
		try {
			if(addrType == 0) {
				service.changeAddrTypeIs0(id);
			}
			service.addAddress(address);
			return null;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
	}
}