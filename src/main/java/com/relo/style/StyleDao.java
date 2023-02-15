package com.relo.style;

import java.util.HashMap;
import java.util.List;

import com.relo.exception.FindException;

public interface StyleDao {
	/**
	 * 게시물 작성
	 * 
	 * @param vo : StyleVo 
	 * @throws FindException
	 */
	public void addStyle(StyleVo vo) throws FindException; 
	/**
	 * 게시물 삭제
	 * 
	 * @param vo : StyleVo 
	 * @throws FindException
	 */
	public void delStyle(StyleVo vo) throws FindException;
	/**
	 * 게시물 내 글 모아보기(최신순)
	 * 
	 * @param id : String 
	 * @return : List<StyleVo> 
	 * @throws FindException
	 */
	public List<StyleVo> selectStyleId(String id) throws FindException;
	/**
	 * 게시물 해시태그별 모아보기(인기순 -> 최신순)
	 * 
	 * @param hashName : String 
	 * @return : List<StyleVo> 
	 * @throws FindException
	 */
	public List<StyleVo> selectStyleTag(String hashName) throws FindException;
	/**
	 * 게시물 상세보기
	 * 
	 * @param styleNum : int 
	 * @return : List<StyleVo> (style_num, id, style_file, style_date, style_likes, tagList, repList)
	 * @throws FindException
	 */
	public List<StyleVo> selectStyleDetail(int styleNum) throws FindException;
	/**
	 * 게시물 상세보기
	 * 
	 * @param vo : StyleVo 
	 * @return : List<StyleVo> (style_num, id, style_file, style_date, style_likes, tagList, repList)
	 * @throws FindException
	 */
	public StyleVo styleDetail(int styleNum) throws FindException;
	/**
	 * 게시물 수정
	 * 
	 * @param vo : StyleVo 
	 * @throws FindException
	 */
	public void updateStyle(StyleVo vo) throws FindException;
	/**
	 * 게시물 리스트 
	 * 
	 * @param styleCode : HashMap<String,Object> 
	 * styleCode = styleLikes 이면 좋아요순으로 리스트 출력,
	 * styleCode != styleLikes 이면 최신순으로 리스트 출력
	 * @return : List<StyleVo> 
	 * @throws FindException
	 */
	public List<StyleVo> selectStyleList(HashMap<String,Object> styleCode) throws FindException;
	/**
	 * 게시물 총 개수
	 * 
	 * @return : int  
	 * @throws FindException
	 */
	public int cntStyleList() throws FindException;
	
	public void styleLikesChange(int styleNum) throws FindException;
	
	//페이징 테스트
	public List<StyleVo> StyleListPage(int start, int end) throws FindException;
	//게시물 인기순 리스트 (인기순 -> 최신순)
//	public ArrayList<StyleVo> selectStyleLikes();
	//게시물 최신순 리스트
//	public ArrayList<StyleVo> selectStyleNum();
}
