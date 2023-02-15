package com.relo.handler.style;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.style.StylePageBean;
import com.relo.style.StyleService;
import com.relo.style.StyleVo;
import com.relo.styletag.StyleTagService;

public class ListHandler implements Handler {

//		//내가 쓴 글 리스트 (id)
//		service.selectStyleId(id);
//		//해시태그별 리스트 (hashName)
//		service.selectStyleTag(hashName);
//		//동적 쿼리 1.인기순(styleCode==styleLikes)-->default 2. 최신순(styleCode==null)
//		service.selectStyleList(null);
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//스타일 게시판 메인
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		ObjectMapper mapper = new ObjectMapper();
		StyleService service = new StyleService();
		StyleTagService tService = new StyleTagService();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String styleCode = request.getParameter("styleCode");
		String hashName = request.getParameter("hashName");
		String id = request.getParameter("id");
		List<String> tagList = null;
		Map map = new HashMap<>();
		
		try {
			tagList = tService.styleTagList();
		} catch (FindException e) {
			e.printStackTrace();
			map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			return jsonStr;
		}
		
		if(id!=null) {
			try {
				StylePageBean<StyleVo> pb = service.selectStyleId(currentPage,loginId);
				map.put("pb", pb);
				map.put("tagList", tagList);
				map.put("loginId", loginId);
				String jsonStr = mapper.writeValueAsString(map);
//				jsonStr += mapper.writeValueAsString(pb);
				return jsonStr;
			} catch (FindException e) {
				e.printStackTrace();
				map.put("msg", e.getMessage());
				String jsonStr = mapper.writeValueAsString(map);
				return jsonStr;
			}
		}else if(hashName!=null) {
			try {
				StylePageBean<StyleVo> pb = service.selectStyleTag(currentPage,hashName);
				map.put("pb", pb);
				map.put("tagList", tagList);
				map.put("loginId", loginId);
				String jsonStr = mapper.writeValueAsString(map);
//				jsonStr += mapper.writeValueAsString(pb);
				return jsonStr;
			} catch (FindException e) {
				e.printStackTrace();
				map.put("msg", e.getMessage());
				String jsonStr = mapper.writeValueAsString(map);
				return jsonStr;
			}
		}else {
			HashMap<String, Object> condition = new HashMap<String, Object>();
			condition.put("styleCode", styleCode);
			try {
				StylePageBean<StyleVo> pb = service.selectStyleList(currentPage,condition);
				map.put("pb", pb);
				map.put("tagList", tagList);
				map.put("loginId", loginId);
				String jsonStr = mapper.writeValueAsString(map);
//				jsonStr += mapper.writeValueAsString(pb);
				return jsonStr;
			
				
			} catch (FindException e) {
				e.printStackTrace();
				map = new HashMap<>();
				map.put("msg", e.getMessage());
				String jsonStr = mapper.writeValueAsString(map);
				return jsonStr;
			}
		}
	}

}