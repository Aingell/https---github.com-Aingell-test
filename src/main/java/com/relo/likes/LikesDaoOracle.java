package com.relo.likes;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class LikesDaoOracle implements LikesDao {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public LikesDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	//좋아요+1
	@Override
	public void addLikes(LikesVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		LikesDao dao = (LikesDao) session.getMapper(LikesDao.class);
		dao.addLikes(vo);
		session.commit();
		session.close();
	}
	//좋아요-1
	@Override
	public void delLikes(LikesVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		LikesDao dao = (LikesDao) session.getMapper(LikesDao.class);
		dao.delLikes(vo);
		session.commit();
		session.close();
	}
	//좋아요 했는지 체크
	@Override
	public LikesVo checkLikes(LikesVo vo) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		LikesDao dao = (LikesDao) session.getMapper(LikesDao.class);
		LikesVo vo1 = dao.checkLikes(vo);
		session.close();
		return vo1;
	}
// 테스트 확인 완료	
//	public static void main(String[] args) throws FindException {
//		LikesDaoOracle dao = new LikesDaoOracle();
//		dao.addLikes(new LikesVo(7,"ddd"));
//		dao.delLikes(new LikesVo(3,"ccc"));
//		LikesVo vo = dao.checkLikes(new LikesVo(2,"ddd"));
//		System.out.print(vo);
//	}
}