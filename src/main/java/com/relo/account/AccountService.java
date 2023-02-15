package com.relo.account;

import com.relo.exception.FindException;

public class AccountService {
	private AccountDao dao;
	
	public AccountService() {
		dao = new AccountDaoOracle();
	}
	
	//1-2 판매자 계좌등록
	public void addAccount(AccountVo vo) throws FindException{
		dao.insertAccount(vo);
	};
	
	//계좌정보 가져오기
	public AccountVo getByIdAccount(String id) throws FindException{
		return dao.selectAccount(id);
	};
	
	//정산계좌 변경
	public void editAccount(AccountVo vo) throws FindException{
		dao.updateAccount(vo);
	};
}