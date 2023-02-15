package com.relo.reply;

import java.util.List;

import com.relo.exception.FindException;

public class ReplyService {
	
	private ReplyDao dao;

	public ReplyService() {
		dao = new ReplyDaoOracle();
	}
	public void addReply(ReplyVo vo) throws FindException{
		dao.addReply(vo);
	}
	
	public void delReply(ReplyVo vo) throws FindException{
		dao.delReply(vo);
	}
	
	public int cntReply(int styleNum) throws FindException{
		return dao.cntReply(styleNum);
	}
	
	public List<ReplyVo> detailRep(int styleNum) throws FindException{
		return dao.detailRep(styleNum);
	}
	
	public void updateRep(ReplyVo vo) throws FindException{
		dao.updateRep(vo);
	}
}