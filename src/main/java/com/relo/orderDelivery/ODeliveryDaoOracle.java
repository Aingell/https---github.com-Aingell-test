package com.relo.orderDelivery;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class ODeliveryDaoOracle implements ODeliveryDao {
	private SqlSessionFactory sqlSessionFactory;
	public ODeliveryDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	@Override
	public void insert(Map map) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.relo.mybatis.odelivery.ODeliveryDao.insertODelivery", map);
		session.commit();
		session.close();
	}

	@Override
	public void updateStatus3(int oNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.relo.mybatis.odelivery.ODeliveryDao.updateStatus3", oNum);
		session.commit();
		session.close();
	}

	public static void main(String[] args) {
		ODeliveryDaoOracle dao = new ODeliveryDaoOracle();
		
		try {
			dao.updateStatus3(4);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * Map map = new HashMap(); map.put("o_num", 4);
		 * 
		 * //운송장 번호 랜덤으로 발생시키기 6개, 7개 순 int preValue = (int) (Math.random() * 1000000);
		 * int postValue = (int) (Math.random() * 10000000);
		 * 
		 * //운송장 번호 String trackin = String.valueOf(preValue) + "-" +
		 * String.valueOf(postValue);
		 * 
		 * map.put("d_trackin_info", trackin); try { dao.insert(map); } catch
		 * (FindException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
		
	}
}