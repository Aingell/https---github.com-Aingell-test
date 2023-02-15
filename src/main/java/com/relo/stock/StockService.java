package com.relo.stock;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public class StockService {
	private StockDao dao;
	
	public StockService() {
		dao = new StockDaoOracle();
	}
	
	public List<StockVo> selectAll() throws FindException{
		return dao.selectAll();
	};
	//1-1 판매자 상품 등록
	public void addStock(StockVo vo) throws FindException{
		dao.insertStock(vo);
	};
	
	//2.판매자 마이페이지-> 판매내역 -> 판매대기
	public List<StockVo> getById(String id) throws FindException{
		return dao.selectById(id);
	};
	
	// 3. 관리자 상품등록 승인요청 목록
	// 관리자 상품 최종 등록 목록 
	public List<StockVo> getBySReturn(int sReturn) throws FindException{
		return dao.selectBySReturn(sReturn);
	};
	
	// 3-1 관리자 상품등록 승인요청 상세 -> 등급과 comment를 판매자에게 넘기는 단계
	//	관리자 상품 최종 등록 목록 상세
	public StockVo getBySNum(int sNum) throws FindException{
		return dao.selectBySNum(sNum);
	};
	
	
	// 4. 판매자 판매내역 ->  판매대기  -> 상세페이지
	public StockVo selectByIdDeatil(Map map) throws FindException{
		return dao.selectByIdDeatil(map);
	};
	
	// 5. 판매자가 상품등급보고 판매희망여부 판단 5-1 희망 5-2 거부
	
	// 5-1,3-2 하나로 합침 
	public void editSetSReturn(StockVo vo) throws FindException{
		dao.updateSetSReturn(vo);
	};
	
	// 5-2. 판매자 판매내역 ->  판매대기  -> 상세페이지 -> 취소버튼클릭
	// address에서 주소 처리
	
	// 판매등록 취소한 재고 상태 5로 변경
	public void editByCancleSReturn5(int sNum) throws FindException{
		dao.updateByCancleSReturn5(sNum);
	};
}
