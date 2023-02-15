package com.relo.orders;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;


public interface OrdersDao {
	// 각 회원의 주문상품 목록
	public List<OrdersVo> selectOrderListById(String id) throws FindException;
	
	// 각 회원의 주문상품 목록 페이징
	public List<OrdersVo> selectListPageById(Map map) throws FindException;
	
	// 각 회원의 주문상품 디테일
	public OrdersVo selectOrderDetailByNum(int oNum) throws FindException;

	// 회원이 결제완료할 시 주문tb에 값 insert
	public void insert(Map map) throws FindException;
	
	// 회원이 주소지 변경시 주소키값 변경
	public void updateAddrNum(Map map) throws FindException;
	
	// 구매확정된 상품들 리스트, 관리자 페이지에서 구매확정된 상품들을 확인
	// 판매자에게 정산을 해주기 위함
	public List<OrdersVo> selectByDStatus()  throws FindException;
	
	// 구매확정된 상품들 리스트, 관리자 페이지에서 구매확정된 상품들을 확인 페이징
	public List<OrdersVo> selectPageByDStatus(Map map)  throws FindException;
}