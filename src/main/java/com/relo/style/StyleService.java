package com.relo.style;

import java.util.HashMap;
import java.util.List;

import com.relo.exception.FindException;

public class StyleService {

	private StyleDao dao;

	public StyleService() {
		dao = new StyleDaoOracle();
	}
	//게시물 추가
	public void addStyle(StyleVo vo) throws FindException{
		dao.addStyle(vo);
	}
	//게시물 삭제
	public void delStyle(StyleVo vo) throws FindException{
		dao.delStyle(vo);
	}
	//게시물 수정
	public void updateStyle(StyleVo vo) throws FindException{
		dao.updateStyle(vo);
	}
	//게시물 아이디 검색해서 목록 출력
	public StylePageBean<StyleVo> selectStyleId(int currentPage,String id) throws FindException{
		List<StyleVo> list = dao.selectStyleId(id);
		int totalCnt = dao.cntStyleList();
		StylePageBean<StyleVo> pb = new StylePageBean(currentPage, list, totalCnt);
		return pb;
	}
	//게시물 태그 검색해서 목록 출력
	public StylePageBean<StyleVo> selectStyleTag(int currentPage,String hashName) throws FindException{
		List<StyleVo> list = dao.selectStyleTag(hashName);
		int totalCnt = dao.cntStyleList();
		StylePageBean<StyleVo> pb = new StylePageBean(currentPage, list, totalCnt);
		return pb;
	}
	//게시물 목록 출력 + 페이지 테스트
	public StylePageBean<StyleVo> selectStyleList(int currentPage,HashMap<String,Object> condition) throws FindException{
		List<StyleVo> list = dao.selectStyleList(condition);
		int totalCnt = dao.cntStyleList();
		StylePageBean<StyleVo> pb = new StylePageBean(currentPage, list, totalCnt);
		return pb;
	}
	//게시물 상세조회
	public List<StyleVo> selectStyleDetail(int styleNum) throws FindException{
		return dao.selectStyleDetail(styleNum);
	}

	//게시물 상세조회2
	public StyleVo styleDetail(int styleNum) throws FindException{
		return dao.styleDetail(styleNum);
	}
	
	//style_likes -1 으로 변경 시키기
	public void styleLikesChange(int styleNum) throws FindException{
		dao.styleLikesChange(styleNum);
	}

}