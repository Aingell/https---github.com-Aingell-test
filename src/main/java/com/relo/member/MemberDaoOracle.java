package com.relo.member;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class MemberDaoOracle implements MemberDao {
	private SqlSessionFactory sqlSessionFactory;

	public MemberDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	@Override
	public void insert(MemberVo m) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);

		dao.insert(m);

		session.commit();
		session.close();
	}
	
	@Override
	public MemberVo findId(Map<String,String> param) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		MemberVo m = dao.findId(param);
		session.close();
		return m;
	}

	@Override
	public MemberVo select(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		MemberVo m = dao.select(id);
		session.close();
		return m;
	}

	@Override
	public void update(MemberVo m) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		dao.update(m);
		session.commit();
		session.close();
	}
	
	
	@Override
	public int checkOutTerms(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		int num = dao.checkOutTerms(id);
		session.close();
		return num;
	}
	
	@Override
	public void delete(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		dao.delete(id);
		session.commit();
		session.close();
	}
	public static void main(String[] args) throws FindException {
		MemberDao dao = new MemberDaoOracle();
		System.out.println(dao.checkOutTerms("ddd"));
		MemberVo m = dao.select("aaa");
		System.out.print(m);
	}
}