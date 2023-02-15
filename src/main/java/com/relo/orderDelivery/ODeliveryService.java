package com.relo.orderDelivery;

import java.util.Map;

import com.relo.exception.FindException;

public class ODeliveryService {
	private ODeliveryDaoOracle dao;

	public ODeliveryService() {
		dao = new ODeliveryDaoOracle();
	}

	public void addODelivery(Map map) throws FindException {
		try {
			dao.insert(map);
		} catch (FindException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			e.printStackTrace();
		}
	}

	public void editStatus3(int oNum) throws FindException {
		try {
			dao.updateStatus3(oNum);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}