package com.relo.account;

import com.relo.exception.FindException;

public interface AccountDao {
	//1-2 판매자 계좌등록
	public void insertAccount(AccountVo vo) throws FindException;
	
	//계좌정보 가져오기
	public AccountVo selectAccount(String id) throws FindException;
	
	//정산계좌 변경
	public void updateAccount(AccountVo vo) throws FindException;
}