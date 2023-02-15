package com.relo.stock;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;



public interface StockDao {
	public List<StockVo> selectAll() throws FindException;
	//1-1 판매자 상품 등록
	public void insertStock(StockVo vo) throws FindException;
	
	//2.판매자 마이페이지-> 판매내역 -> 판매대기
	public List<StockVo> selectById(String id) throws FindException;
	
	// 3. 관리자 상품등록 승인요청 목록
	// 관리자 상품 최종 등록 목록 
	public List<StockVo> selectBySReturn(int sReturn) throws FindException;
	
	// 3-1 관리자 상품등록 승인요청 상세 -> 등급과 comment를 판매자에게 넘기는 단계
	//	관리자 상품 최종 등록 목록 상세
	public StockVo selectBySNum(int sNum) throws FindException;
	
	
	// 4. 판매자 판매내역 ->  판매대기  -> 상세페이지
	public StockVo selectByIdDeatil(Map map) throws FindException;
	
	// 5. 판매자가 상품등급보고 판매희망여부 판단 5-1 희망 5-2 거부
	
	// 5-1,3-2 하나로 합침 
	public void updateSetSReturn(StockVo vo) throws FindException;
	
	// 5-2. 판매자 판매내역 ->  판매대기  -> 상세페이지 -> 취소버튼클릭
	// address에서 주소 처리
	
	// 판매등록 취소한 재고 상태 5로 변경
	public void updateByCancleSReturn5(int sNum) throws FindException;
	
}