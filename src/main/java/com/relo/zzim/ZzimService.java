package com.relo.zzim;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public class ZzimService {
	private ZzimDao dao;

	public ZzimService() {
		dao = new ZzimDaoOracle();
	}

	public List<String> getAll(Map<String, Object> map) throws FindException {
		return dao.selectAll(map);
	}

	public List<ZzimVo> getById(String id) throws FindException {
		return dao.selectById(id);
	}

	public void addZzim(Map<String, Object> map) throws FindException {
		dao.insertZzim(map);
	}

	public void delZzim(Map<String, Object> map) throws FindException {
		dao.deleteZzim(map);
	}

	public void delZzimAll(int pNum) throws FindException {
		dao.deleteZzimAll(pNum);
	}
}