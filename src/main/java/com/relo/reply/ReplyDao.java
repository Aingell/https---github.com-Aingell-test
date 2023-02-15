package com.relo.reply;

import java.util.List;

import com.relo.exception.FindException;

public interface ReplyDao {
	/**
	 * 댓글 추가
	 * 
	 * @param vo : ReplyVo
	 * @throws FindException
	 */
	public void addReply(ReplyVo vo) throws FindException;
	/**
	 * 댓글 삭제
	 * 
	 * @param vo : ReplyVo
	 * @throws FindException
	 */
	public void delReply(ReplyVo vo) throws FindException;
	/**
	 * 댓글 개수 출력
	 * 
	 * @param int : styleNum
	 * @return : int 해당하는 게시물의 댓글 총 개수 출력
	 * @throws FindException
	 */
	public int cntReply(int styleNum) throws FindException;
	/**
	 * 댓글 상세 출력
	 * 
	 * @param int : styleNum
	 * @return : List<ReplyVo> 해당하는 게시물의 댓글 리스트 출력
	 * @throws FindException
	 */
	public List<ReplyVo> detailRep(int styleNum) throws FindException;
	/**
	 * 댓글 수정
	 * 
	 * @param vo : ReplyVo
	 * @throws FindException
	 */
	public void updateRep(ReplyVo vo) throws FindException;
}