package com.relo.styletag;

import java.util.List;

import com.relo.exception.FindException;

public interface StyleTagDao {
	/**
	 * 해시태그 추가
	 * 
	 * @param vo : StyleTagVo 
	 * @throws FindException
	 */
	public void addStyleTag(StyleTagVo vo) throws FindException;
	/**
	 * 해시태그 리스트 (해시태그 cnt 많은 순) 5개만
	 * 
	 * @return : List<String> 해시태그 이름 5개 출력
	 * @throws FindException
	 */
	public List<String> styleTagList() throws FindException;
	/**
	 * 해당 게시물에 대한 태그 리스트
	 * 
	 * @param styleNum : int 
	 * @return : List<StyleTagVo> 해당 게시물에 대한 해시태그 리스트 출력
	 * @throws FindException
	 */
	//해당 게시물에 대한 태그 리스트
	public List<StyleTagVo> styleTagDetail(int styleNum) throws FindException;
	
	public void delStyleTag(int styleNum) throws FindException;
}