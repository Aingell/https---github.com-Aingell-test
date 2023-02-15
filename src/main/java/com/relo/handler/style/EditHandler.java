package com.relo.handler.style;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.style.StyleService;
import com.relo.style.StyleVo;
import com.relo.styletag.StyleTagService;
import com.relo.styletag.StyleTagVo;

public class EditHandler implements Handler {
	//스타일 게시판 수정
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json; charset=UTF-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		String saveDirectory = "/Users/skyleeb95/eclipse-workspace/Myweb/relo/imgs/style";
		int maxPostSize = 1000*1024;
		String encoding = "UTF-8";
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding,new DefaultFileRenamePolicy());
		
//		String styleContent= request.getParameter("styleContent");
//		int styleNum = Integer.parseInt(request.getParameter("styleNum"));
//		String file = request.getParameter("f");
//		String fileName = file.getName();
//		String imgPath = "/img/"+fileName;
		String id = mr.getParameter("id");
		String styleContent = mr.getParameter("styleContent");
		File file = mr.getFile("f");
		String fileName = file.getName();
		String imgPath = fileName;
		int styleNum = Integer.parseInt(mr.getParameter("styleNum"));
		StyleTagService Tservice = new StyleTagService();
		StyleService service = new StyleService();
		ObjectMapper mapper = new ObjectMapper();
		
		StyleTagVo tagVo = new StyleTagVo();
		ArrayList<String> styleTag = new ArrayList<>();
		//게시판 수정이 되면 해당 태그들 다 삭제 되고 새롭게 insert 된다.
		
		StringTokenizer stk = new StringTokenizer(styleContent,"#");
		while(stk.hasMoreTokens()) {
		styleTag.add(stk.nextToken().trim());
		}
		try {
			StyleVo vo = service.styleDetail(styleNum);
			String originFile = vo.getStyleFile();
			File originF = new File(saveDirectory,originFile);
			if(originF.exists()) {
				originF.delete();
				System.out.println("파일삭제");
			}
			service.updateStyle(new StyleVo(styleNum,null,styleContent,imgPath));
			Tservice.delStyleTag(styleNum);
			
			for(String s : styleTag) {
				tagVo = new StyleTagVo(0,vo.getStyleNum(),s);
				Tservice.addStyleTag(tagVo);
				}
			String jsonStr = mapper.writeValueAsString("수정완료");
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