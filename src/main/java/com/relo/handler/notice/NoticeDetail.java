package com.relo.handler.notice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.notice.NoticeService;
import com.relo.notice.NoticeVo;

public class NoticeDetail implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		int nNum = Integer.parseInt(request.getParameter("nNum"));

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
		NoticeService service = new NoticeService();
		
		try {
			NoticeVo n = service.getOne(nNum);
			NoticeVo nPre = service.getPreByNNum(nNum);
			NoticeVo nNext = service.getNextByNNum(nNum);

			JSONObject obj = new JSONObject();

			obj.put("n", n);
			obj.put("nPre", nPre);
			obj.put("nNext", nNext);

			String jsonStr = mapper.writeValueAsString(obj);
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