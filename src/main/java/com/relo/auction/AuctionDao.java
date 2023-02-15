package com.relo.auction;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public interface AuctionDao {
	
	//상품번호 별 최고가
	public AuctionVo maxPriceByPNum(int pNum) throws FindException;
	
	// 입찰할 시 정보 insert
	public void insert(Map map) throws FindException;
	
	//이미 입찰 시도를 했는지 체크하는 sql 문 추가
	public Integer selectById(Map map) throws FindException;
	
	//위의 값이 존재하면 update 문
	public void update(Map map) throws FindException;
	
	/*회원의 입찰 시도한 내역 sql - 필터링은 나중에 ^^,,
	상품 사진, 상품명, 상품 사이즈, 입찰가, 상품번호
	입찰시도한 것들 중 진행중인 경매*/
	public List<AuctionVo> selectIngListById(String id) throws FindException;
	
	//회원의 입찰 시도한 내역 중 경매 종료, 해당 회원이 낙찰됐는지 상태
	public List<AuctionDTO> selectEndListById(String id) throws FindException;
}