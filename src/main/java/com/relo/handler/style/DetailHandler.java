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
import com.relo.likes.LikesService;
import com.relo.likes.LikesVo;
import com.relo.reply.ReplyService;
import com.relo.reply.ReplyVo;
import com.relo.style.StyleService;
import com.relo.style.StyleVo;
import com.relo.styletag.StyleTagService;
import com.relo.styletag.StyleTagVo;

public class DetailHandler implements Handler {
	//스타일 상세보기 
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json;charset=UTF-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://192.168.123.103:5500");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		ObjectMapper mapper = new ObjectMapper();
		StyleService sService = new StyleService();
		ReplyService rService = new ReplyService();
		StyleTagService tService = new StyleTagService();
		LikesService lService = new LikesService();
		int styleNum = Integer.parseInt(request.getParameter("styleNum"));
		
		try {
			//해당 게시판 댓글리스트 불러오기
			List<ReplyVo> repList = rService.detailRep(styleNum);
			//해당 게시판 태그리스트 불러오기
			List<StyleTagVo> tagList = tService.styleTagDetail(styleNum);
			//해당 게시판 상세 불러오기
			StyleVo vo = sService.styleDetail(styleNum);
			//게시판 객체에 댓글리스트 넣어주기
			vo.setRepList(repList);
			//게시판 객체에 태그리스트 넣어주기
			vo.setTagList(tagList);
			//게시판 댓글 개수 불러오기
			int repCnt = rService.cntReply(styleNum);
			//좋아요 눌렀는 지 확인 불러오기
			LikesVo lVo = lService.checkLikes(new LikesVo(styleNum, loginId));
			int likeCheck = 0;
			if(lVo != null) {
				likeCheck = 1;
			}
			
			Map map = new HashMap<>();
			map.put("vo", vo);
			map.put("replyCnt", repCnt);
			map.put("loginId", loginId);
			map.put("likeCheck", likeCheck);
//			String jsonStr = mapper.writeValueAsString(vo);
//			jsonStr += "댓글 개수:"+repCnt;
			String jsonStr = mapper.writeValueAsString(map);
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