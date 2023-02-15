package com.relo.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class NoticeDaoOracle implements NoticeDao {

	private SqlSessionFactory sqlSessionFactory;

	public NoticeDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	@Override
	public void insert(NoticeVo n) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);

		dao.insert(n);

		session.commit();
		session.close();
	}

	@Override
	public int totalCnt() throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		int totalCnt = dao.totalCnt();
		session.close();
		return totalCnt;
	}

	@Override
	public List<NoticeVo> selectNoticeFromTo(Map<String, Integer> idx) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		List<NoticeVo> list = dao.selectNoticeFromTo(idx);
		session.close();
		return list;
	}

	@Override
	public List<NoticeVo> selectAll() throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		List<NoticeVo> list = dao.selectAll();
		session.close();
		return list;
	}

	@Override
	public NoticeVo select(int nNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		NoticeVo n = dao.select(nNum);
		session.close();
		return n;
	}

	@Override
	public List<NoticeVo> selectByTitle(String nTitle) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		List<NoticeVo> list = dao.selectByTitle(nTitle);
		session.close();
		return list;
	}

	@Override
	public List<NoticeVo> selectByCategory(int nCategory) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		List<NoticeVo> list = dao.selectByCategory(nCategory);
		session.close();
		return list;
	}

	@Override
	public NoticeVo selectNextByNNum(int nNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		NoticeVo n = dao.selectNextByNNum(nNum);
		session.close();
		return n;
	}

	@Override
	public NoticeVo selectPreByNNum(int nNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		NoticeVo n = dao.selectPreByNNum(nNum);
		session.close();
		return n;
	}

	@Override
	public void update(NoticeVo n) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		dao.update(n);
		session.commit();
		session.close();
	}

	@Override
	public void delete(int nNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeDao dao = (NoticeDao) session.getMapper(NoticeDao.class);
		dao.delete(nNum);
		session.commit();
		session.close();
	}
}
