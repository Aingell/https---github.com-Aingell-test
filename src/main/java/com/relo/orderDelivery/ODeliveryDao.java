package com.relo.orderDelivery;



import java.util.Map;

import com.relo.exception.FindException;


public interface ODeliveryDao {
	// 결제완료시 운송장번호 정보 받아서 해당 주문번호와 배송상태 0으로 insert
	// 랜덤한 숫자 6개-랜덤숫자 7개로 생성해서 넣어줘야 함
	public void insert(Map map) throws FindException;
	public void updateStatus3(int oNum) throws FindException;
		
}