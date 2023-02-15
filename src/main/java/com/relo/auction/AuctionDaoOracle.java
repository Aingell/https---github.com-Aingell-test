package com.relo.auction;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class AuctionDaoOracle implements AuctionDao {
	private SqlSessionFactory sqlSessionFactory;
	//private Auction auctionMybatis;
	public AuctionDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	
	//상품번호 별 최고가
	@Override
	public AuctionVo maxPriceByPNum(int pNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AuctionDao mapper = (AuctionDao) session.getMapper(AuctionDao.class);
		AuctionVo vo = mapper.maxPriceByPNum(pNum);
		session.close();
		return vo;
	};

	@Override
	public void insert(Map map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.relo.mybatis.auction.AuctionDao.insertAuction", map);
		session.commit();
		session.close();
		
	}
	@Override
	public Integer selectById(Map map) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		Integer aNum  = session.selectOne("com.relo.mybatis.auction.AuctionDao.selectById", map);		
		session.close();
		return aNum;
	}


	@Override
	public void update(Map map) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.relo.mybatis.auction.AuctionDao.updateAPrice", map);
		session.commit();
		session.close();
	}


	@Override
	public List<AuctionVo> selectIngListById(String id) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
//		Auction mapper = (Auction) session.getMapper(Auction.class);
		List<AuctionVo> list = session.selectList("com.relo.mybatis.auction.AuctionDao.selectIngListById", id);		
		session.close();
		return list;
	}


	@Override
	public List<AuctionDTO> selectEndListById(String id) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		List<AuctionDTO> list = session.selectList("com.relo.mybatis.auction.AuctionDao.selectEndListById", id);		
		session.close();
		return list;
	}


	public static void main(String[] args) {
		AuctionDaoOracle dao = new AuctionDaoOracle();
		
		//selectById
//		Map map = new HashMap();
//		map.put("id", "aaa");
//		map.put("p_num", 3);
//		try {
//			int aNum = dao.selectById(map);
//			System.out.println(aNum);
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		//update test
//		Map map = new HashMap();
//		map.put("a_num", 7);
//		map.put("a_price", 230000);
//		try {
//			dao.update(map);
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		//selectIngListById
//		try {
//			List<AuctionVo> list = dao.selectIngListById("aaa");
//			for (AuctionVo vo : list) {
//				System.out.println(vo);
//			}
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		//selectEndListById
//		try {
//			List<AuctionDTO> list = dao.selectEndListById("aaa");
//			for (AuctionDTO dto : list) {
//				System.out.println(dto);
//			}
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		selectListByPStatus6 구매자
//		List<ProductVo> list;
//		try {
//			list = dao.selectByPStatus();
//			for (ProductVo vo : list) {
//				System.out.println(vo);
//			}
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		insert 부분
//		Map map = new HashMap();
//		map.put("id", "bbb");
//		map.put("pNum", 4);
//		map.put("aPrice", 870000);
//		
//		try {
//			dao.insert(map);
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
