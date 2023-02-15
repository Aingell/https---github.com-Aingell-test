package com.relo.account;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class AccountDaoOracle implements AccountDao {
	private SqlSessionFactory sqlSessionFactory;

	public AccountDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	// 1-2.판매자 정산계좌 등록  [계좌 등록]
	@Override
	public void insertAccount(AccountVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AccountDao mapper = (AccountDao) session.getMapper(AccountDao.class);
		mapper.insertAccount(vo);
		session.commit();
		session.close();
	}
	
	//회원 자신의 계좌보기
	@Override
	public AccountVo selectAccount(String id) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		AccountDao mapper = (AccountDao) session.getMapper(AccountDao.class);
		AccountVo vo = mapper.selectAccount(id);
		session.close();
		return vo;
	}
	
	//정산계좌 변경
	@Override
	public void updateAccount(AccountVo vo) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		AccountDao mapper = (AccountDao) session.getMapper(AccountDao.class);
		mapper.updateAccount(vo);
		session.commit();
		session.close();
	}
	
//	public static void main(String[] args) {
//		AccountDaoOracle dao = new AccountDaoOracle();
//		
//		MemberVo mvo = new MemberVo("aaa", null, null, null, 0, null, null);
//		try {
//			dao.insertAccount(new AccountVo(mvo,"123-123","국민은행"));
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		System.out.println(dao.selectAccount("aaa"));
//		MemberVo mvo = new MemberVo("aaa", null, null, null, 0, null, null);
//		try {
//			dao.updateAccount(new AccountVo(mvo, "1234-1234", "농협"));
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
