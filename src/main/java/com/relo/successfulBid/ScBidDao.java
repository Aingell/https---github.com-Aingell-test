package com.relo.successfulBid;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public interface ScBidDao {
	// 낙찰된 상품들 리스트, 관리자 페이지에서 낙찰된 상품들을 확인
	// 낙찰자들에게 결제 요청을 보내기 위함
	public List<ScBidVo> selectListByPStatus() throws FindException;

	// 낙찰된 상품들 리스트, 관리자 페이지에서 낙찰된 상품들을 확인 페이징
	public List<ScBidVo> selectListPageByPStatus(Map map) throws FindException;

	// 낙찰자에게 결제요청 보낼 때 낙찰tb에 값 넣어주기
	// trigger 로 변경했지만 일단 남겨둠
	public void insert(int aNum) throws FindException;

	// 낙찰자가 구매 포기할 경우, 낙찰tb에서 값 삭제
	public void delete(int aNum) throws FindException;

}