package com.relo.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public class AuctionService {
	private AuctionDaoOracle dao;

	public AuctionService() {
		dao = new AuctionDaoOracle();
	}
	
	public AuctionVo maxPriceByPNum(int pNum) throws FindException{
		return dao.maxPriceByPNum(pNum);
	}

	public void addAuction(Map map) throws FindException {
		try {
			dao.insert(map);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer getById(Map map) throws FindException {
		Integer aNum = 0;
		try {
			aNum = dao.selectById(map);
			System.out.println(aNum);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aNum;
	}
	
	public void editAuction(Map map) throws FindException {
		try {
			dao.update(map);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<AuctionVo> getIngListById(String id) throws FindException {
		try {
			List<AuctionVo> list = dao.selectIngListById(id);
			for (AuctionVo vo : list) {
				System.out.println(vo);
			}
			return list;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AuctionDTO> getEndListById(String id) throws FindException {
		try {
			List<AuctionDTO> list = dao.selectEndListById(id);
			for (AuctionDTO dto : list) {
				System.out.println(dto);
			}
			return list;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}