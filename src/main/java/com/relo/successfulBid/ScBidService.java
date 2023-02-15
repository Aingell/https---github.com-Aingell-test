package com.relo.successfulBid;

import java.util.List;

import com.relo.exception.FindException;

public class ScBidService {
	private ScBidDaoOracle dao;
	public ScBidService() {
		dao = new ScBidDaoOracle();
	}
	
	//낙찰tb에 insert 넣는 거 트리거로 있음!!!
	public void addCatch(int aNum) throws FindException{
		try {
			dao.insert(aNum);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delCatch(int aNum) throws FindException{
		try {
			dao.delete(aNum);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ScBidVo> getListByPStatus ()  throws FindException {
		return dao.selectListByPStatus();
	}
}