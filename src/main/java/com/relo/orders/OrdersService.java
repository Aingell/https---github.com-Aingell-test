package com.relo.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public class OrdersService {
	private OrdersDaoOracle dao;
	public OrdersService() {
		dao = new OrdersDaoOracle();
	}
	
	public List<OrdersVo> getOrderListById(String id) throws FindException{
		List<OrdersVo> list = new ArrayList<>();
		try {
			list = dao.selectOrderListById(id);
			return list;
		} catch (FindException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return null;
	}
	
	public OrdersVo getOrderDetailByNum(int oNum) throws FindException{
		try {
			OrdersVo vo = dao.selectOrderDetailByNum(oNum);
			return vo;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OrdersVo> getByDStatus()  throws FindException{
		List<OrdersVo> list = new ArrayList<>();
		try {
			list = dao.selectByDStatus();
			for (OrdersVo vo : list) {
				System.out.println(vo);
			}
			return list;
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int addOrders(Map map) throws FindException {
		int oNum = 0;
		try {
			dao.insert(map);
			oNum = (int) map.get("oNum");
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oNum;
	}
	
	public void editAddrNum(Map map) throws FindException {
		try {
			dao.updateAddrNum(map);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
