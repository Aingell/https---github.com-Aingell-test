package com.relo.address;

import java.util.List;

import com.relo.exception.FindException;

public class AddressService {
	private AddressDao dao;

	public AddressService() {
		dao = new AddressDaoOracle();
	}

	public void addAddress(AddressVo a) throws FindException {
		dao.insert(a);
	}

	public int getNewAddrNum(AddressVo a) throws FindException {
		return dao.selectNewAfterInsert(a);
	}

	public List<AddressVo> getAllById(String id) throws FindException {
		return dao.selectAllById(id);
	}

	public void changeAddrTypeIs0(String id) throws FindException {
		dao.changeAddrTypeIs0(id);
	}
	
	public void editAddress(AddressVo a) throws FindException {
		dao.update(a);
	}

	public void delAddress(int addrNum) throws FindException {
		dao.delete(addrNum);
	}
	
	public AddressVo selectByNum(int addrNum) throws FindException{
		return dao.selectByNum(addrNum);
	}
	
}