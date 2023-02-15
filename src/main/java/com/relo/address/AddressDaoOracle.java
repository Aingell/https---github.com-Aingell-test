package com.relo.address;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class AddressDaoOracle implements AddressDao{
	private SqlSessionFactory sqlSessionFactory;

	public AddressDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	@Override
	public void insert(AddressVo a) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);

		dao.insert(a);

		session.commit();
		session.close();
	}

	@Override
	public List<AddressVo> selectAllById(String id) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		List<AddressVo> list = dao.selectAllById(id);
		session.close();
		return list;
	}

	@Override
	public int selectNewAfterInsert(AddressVo a) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		int newAddrNum = dao.selectNewAfterInsert(a);
		session.commit();
		session.close();
		return newAddrNum;
	}

	@Override
	public void update(AddressVo a) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		dao.update(a);
		session.commit();
		session.close();
	}
	
	@Override
	public void changeAddrTypeIs0(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		dao.changeAddrTypeIs0(id);
		session.commit();
		session.close();
	}

	@Override
	public void delete(int addrNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		dao.delete(addrNum);
		session.commit();
		session.close();
	}

	@Override
	public AddressVo selectByNum(int addrNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		AddressDao dao = (AddressDao) session.getMapper(AddressDao.class);
		AddressVo vo = dao.selectByNum(addrNum);
		session.close();
		return vo;
	}

}
