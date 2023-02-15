package com.relo.notice;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public interface NoticeDao {
	/**
	 * 공지사항을 추가한다.
	 * @param n NoticeVo
	 */
	void insert(NoticeVo n) throws FindException;
	
	/**
	 * 공지사항 목록을 최신순으로 검색한다.
	 * @return 공지사항 목록(최신순)
	 */
	List<NoticeVo> selectAll() throws FindException;
	
	/**
	 * 공지사항 번호로 공지사항 목록을 검색한다.
	 * @param n_num 공지사항 글 번호
	 * @return NoticeVo
	 */
	NoticeVo select(int nNum) throws FindException;
	
	/**
	 * 제목으로 공지사항 목록을 검색한다.
	 * @param n_title 공지사항 제목
	 * @return 제목으로 검색된 공지사항 목록
	 */
	List<NoticeVo> selectByTitle(String nTitle) throws FindException;
	
	/**
	 * 카테고리별로 공지사항 목록을 검색한다.
	 * @param n_category 공지사항 카테고리 번호
	 * @return 카테고리별로 검색된 공지사항 목록
	 */
	List<NoticeVo> selectByCategory(int nCategory) throws FindException;

	/**
	 * 다음 공지사항을 검색한다.
	 * @param nNum 현재 공지사항 글 번호
	 * @return 다음 공지사항 
	 */
	NoticeVo selectNextByNNum(int nNum) throws FindException;
	
	/**
	 * 이전 공지사항을 검색한다.
	 * @param nNum 현재 공지사항 글 번호
	 * @return 이전 공지사항 
	 */
	NoticeVo selectPreByNNum(int nNum) throws FindException;
	
	/**
	 * 총 공지사항 글 개수를 검색한다
	 * @return 총 공지사항 글 개수
	 */
	public int totalCnt() throws FindException;
	
	public List<NoticeVo> selectNoticeFromTo(Map<String, Integer> idx) throws FindException;
	
	/**
	 * 공지사항을 수정한다.
	 * @param n NoticeVo
	 */
	void update(NoticeVo n) throws FindException;

	/**
	 * 공지사항을 삭제한다.
	 * @param n_num 공지사항 글 번호
	 */
	void delete(int nNum) throws FindException;
}
