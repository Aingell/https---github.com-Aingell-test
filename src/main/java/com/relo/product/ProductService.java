package com.relo.product;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public class ProductService {
	private ProductDao dao;

	public ProductService() {
		dao = new ProductDaoOracle();
	}

	// 관리자가 재고에서 상품으로 등록
	public void addProduct(Map map) throws FindException {
		dao.insertProduct(map);
	};

	// 판매자 판매내역 진행중 페이지
	public List<ProductVo> getByIdProduct(String id) throws FindException {
		return dao.selectByIdProduct(id);
	};

	// 판매자 판매내역 진행중 상세 페이지
	public ProductVo getByIdProductDetail(Map map) throws FindException {
		return dao.selectByIdProductDetail(map);
	};

	// 판매자 판매내역 종료 페이지
	public List<ProductVo> getByEndProduct(String id) throws FindException {
		return dao.selectByEndProduct(id);
	};

	// 판매자 판매내역 종료 페이지 디테일
	public ProductVo getByEndProductDetail(String id) throws FindException {
		return dao.selectByEndProductDetail(id);
	};

	public void editStatus8(int aNum) throws FindException {
		dao.updateStatus8(aNum);
	}

	public List<ProductVo> getProdList(Map<String, Object> condition) throws FindException {

		return dao.searchProdList(condition);
	}

	public List<ProductVo> getProdListZzim(Map<String, Object> condition) throws FindException {
		return dao.searchProdListZzim(condition);
	}

	public List<ProductVo> getProdListNoTender(Map<String, Object> condition) throws FindException {
		return dao.searchProdListNoTender(condition);
	}

	public List<ProductVo> getProdListByName(String searchvalue) throws FindException {
		return dao.searchProdListByName(searchvalue);
	}

	public List<ProductVo> getrecentTender(int pNum) throws FindException {
		return dao.recentTender(pNum);
	}

	public ProductVo getProductDetail(int pNum) throws FindException {
		return dao.productDetail(pNum);
	}

	public void addTender(Map<String, Object> tmap) throws FindException {
		dao.insertTender(tmap);
	}

	public int getTotalCnt() throws FindException {
		return dao.totalCnt();
	}

	public int getSNumByPNum(int pNum) throws FindException {
		return dao.selectSNumByPNum(pNum);
	}

	public List<ProductVo> getAllPStatusIs8() throws FindException {

		return dao.selectAllByPStatusIs8();
	}

	public void editWhenReBid(int sNum) throws FindException {
		dao.updateWhenReBid(sNum);
	}

	public void delReBiddingProduct(int sNum) throws FindException {
		dao.deleteReBiddingProduct(sNum);
	}
}
