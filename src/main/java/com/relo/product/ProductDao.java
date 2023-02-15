package com.relo.product;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public interface ProductDao {

	// 관리자가 재고에서 상품으로 등록
	public void insertProduct(Map map) throws FindException;

	// 판매자 판매내역 진행중 페이지
	public List<ProductVo> selectByIdProduct(String id) throws FindException;

	// 판매자 판매내역 진행중 상세 페이지
	public ProductVo selectByIdProductDetail(Map map) throws FindException;

	// 판매자 판매내역 종료 페이지
	public List<ProductVo> selectByEndProduct(String id) throws FindException;

	// 판매자 판매내역 종료 페이지 디테일
	public ProductVo selectByEndProductDetail(String id) throws FindException;

	// 낙찰자가 구매를 포기할 경우 상태 값 유찰됨(8)으로 변경
	public void updateStatus8(int aNum) throws FindException;

	/**
	 * 상품 전체 목록 리스트를 본다((전체/상의/하의/신발 + 최신순/마감순))
	 * 
	 * @param condition
	 * @return
	 * @throws FindException
	 */
	public List<ProductVo> searchProdList(Map<String, Object> condition) throws FindException;

	/**
	 * 상품 목록 찜하기순 리스트를 본다(필터 미구현)
	 * 
	 * @param condition
	 * @return
	 * @throws FindException
	 */
	public List<ProductVo> searchProdListZzim(Map<String, Object> condition) throws FindException;

	/**
	 * 상품 목록 중 한번도 입찰되지 않은 상품목록을 조회한다(전체/상의/하의/신발 + 마감임박순 동적SQL)
	 * 
	 * @param sType : 상품타입
	 * @return 한번도 입찰되지 않은 상품목록
	 * @throws FindException
	 */
	public List<ProductVo> searchProdListNoTender(Map<String, Object> condition) throws FindException;

	/**
	 * 상품 목록으로 검색한다
	 * 
	 * @param prodName : 상품이름 이나 상품이름의 일부분
	 * @return 일치하는 상품목록
	 * @throws findException
	 */
	public List<ProductVo> searchProdListByName(String searchvalue) throws FindException;

	/**
	 * 상품 상세화면에서 최근 입찰 내역을 입찰가 순으로 조회 한다
	 * 
	 * @param pNum(상품 번호)
	 * @return 입찰 리스트
	 * @throws FindException
	 */
	public List<ProductVo> recentTender(int pNum) throws FindException;

	/**
	 * 상품 상세화면에서 상품 정보를 보여준다
	 * 
	 * @param pNum(상품 번호)
	 * @return 상품 정보
	 * @throws FindException
	 */
	public ProductVo productDetail(int pNum) throws FindException;

	/**
	 * 상품을 입찰한다
	 * 
	 * @param tMap 입찰때 필요한 변수(id, p_num, a_price)
	 * @throws FindException
	 */
	public void insertTender(Map<String, Object> tMap) throws FindException;

	/**
	 * 상품 입찰가를 수정한다
	 * 
	 * @param tMap 입찰수정때 필요한 변수(id, p_num, a_price)
	 * @throws FindException
	 */
	public void updateTender(Map<String, Object> tMap) throws FindException;

	/**
	 * 총 상품 수를 구한다
	 * 
	 * @return 총 상품 수
	 * @throws FindException
	 */

	public int totalCnt() throws FindException;

	/*
	 * 유찰된 상품 번호로 재고 번호를 검색한다.
	 */
	int selectSNumByPNum(int pNum) throws FindException;

	/**
	 * 유찰된 상품 목록을 조회한다.
	 * 
	 * @return 유찰된 상품 목록
	 */
	List<ProductVo> selectAllByPStatusIs8() throws FindException;

	/**
	 * 유찰된 상품을 재입찰을 한다.
	 * 
	 * @param sNum 유찰된 재고 번호
	 */
	void updateWhenReBid(int sNum) throws FindException;

	/**
	 * 유찰된 상품을 재입찰할 경우, 해당 상품을 삭제한다.
	 * 
	 * @param pNum 유찰된 상품 번호
	 */
	void deleteReBiddingProduct(int pNum) throws FindException;

}
