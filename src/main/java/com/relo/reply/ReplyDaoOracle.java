package com.relo.reply;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class ReplyDaoOracle implements ReplyDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public ReplyDaoOracle(){
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	//댓글 추가
	@Override
	public void addReply(ReplyVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		ReplyDao dao = (ReplyDao) session.getMapper(ReplyDao.class);
		dao.addReply(vo);
		session.commit();
		session.close();
	}
	//댓글 삭제
	@Override
	public void delReply(ReplyVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		ReplyDao dao = (ReplyDao) session.getMapper(ReplyDao.class);
		dao.delReply(vo);
		session.commit();
		session.close();
	}
	//댓글 개수 반환
	@Override
	public int cntReply(int styleNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		ReplyDao dao = (ReplyDao) session.getMapper(ReplyDao.class);
		int cnt =dao.cntReply(styleNum);
		session.close();
		return cnt;
	}
	//댓글 리스트
	@Override
	public List<ReplyVo> detailRep(int styleNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		ReplyDao dao = (ReplyDao) session.getMapper(ReplyDao.class);
		List<ReplyVo> list = dao.detailRep(styleNum);
		session.close();
		return list;
	}
	//댓글 수정
	@Override
	public void updateRep(ReplyVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		ReplyDao dao = (ReplyDao) session.getMapper(ReplyDao.class);
		dao.updateRep(vo);
		session.commit();
		session.close();
	}
//	테스트 완료
	public static void main(String[] args) throws FindException {
		ReplyDaoOracle dao = new ReplyDaoOracle();
		dao.addReply(new ReplyVo(0,16,"bbb","예쁘다",null));
//		dao.delReply(new ReplyVo(0,16,"aaa",null,null));
		int cnt = dao.cntReply(16);
		System.out.println(cnt);
		List<ReplyVo> list = dao.detailRep(16);
		System.out.println(list);
//		dao.updateRep(new ReplyVo(1,0,"bbb","짱",null));
	}

}