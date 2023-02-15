package com.relo.successfulBid;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class ScBidDaoOracle implements ScBidDao {
	private SqlSessionFactory sqlSessionFactory;
	public ScBidDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	@Override
	public List<ScBidVo> selectListByPStatus ()  throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
//		Auction mapper = (Auction) session.getMapper(Auction.class);
		List<ScBidVo> list = session.selectList("com.relo.mybatis.scBid.ScBidDao.selectByPStatus");		
		session.close();
		return list;
	}
	
	//페이징 보류
	@Override
	public List<ScBidVo> selectListPageByPStatus(Map map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
//		Auction mapper = (Auction) session.getMapper(Auction.class);
		List<ScBidVo> list = session.selectList("com.relo.mybatis.scBid.ScBidDao.selectPageByPStatus", map);		
		session.close();
		return list;
	}
	// trigger 로 변경했지만 일단 남겨둠
	@Override
	public void insert(int aNum) throws FindException{
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.relo.mybatis.scBid.ScBidDao.insertCatch", aNum);
		session.commit();
		session.close();
	}

	@Override
	public void delete(int aNum) throws FindException{
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.delete("com.relo.mybatis.scBid.ScBidDao.deleteCatch", aNum);
		session.commit();
		session.close();
	}
	
	public static void main(String[] args) {
		ScBidDaoOracle dao = new ScBidDaoOracle();
		try {
			dao.insert(6);
			dao.delete(6);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}