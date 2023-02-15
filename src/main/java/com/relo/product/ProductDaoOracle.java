package com.relo.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class ProductDaoOracle implements ProductDao {
	private SqlSessionFactory sqlSessionFactory;

	public ProductDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	// 관리자가 재고에서 상품으로 등록
	@Override
	public void insertProduct(Map map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao mapper = (ProductDao) session.getMapper(ProductDao.class);
		mapper.insertProduct(map);
		session.commit();
		session.close();
	}

	// 판매자 판매내역 진행중 페이지
	public List<ProductVo> selectByIdProduct(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao mapper = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = mapper.selectByIdProduct(id);
		session.close();
		return list;
	}

	// 판매자 판매내역 진행중 상세 페이지
	public ProductVo selectByIdProductDetail(Map map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao mapper = (ProductDao) session.getMapper(ProductDao.class);
		ProductVo vo = mapper.selectByIdProductDetail(map);
		session.close();
		return vo;
	}

	// 판매자 판매내역 종료 페이지
	public List<ProductVo> selectByEndProduct(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao mapper = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = mapper.selectByEndProduct(id);
		session.close();
		return list;
	}

	// 판매자 판매내역 종료 페이지 디테일
	public ProductVo selectByEndProductDetail(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		// ProductDao mapper = (ProductDao) session.getMapper(ProductDao.class);
		ProductVo vo = session.selectOne("com.relo.product.ProductDao.selectByEndProductDetail", id);
		session.close();
		return vo;
	}

	@Override
	public void updateStatus8(int aNum) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.relo.product.ProductDao.update8", aNum);
		session.commit();
		session.close();
	}

	// 상품 전체 목록(전체/상의/하의/신발 + 최신순/마감순)
	@Override
	public List<ProductVo> searchProdList(Map<String, Object> condition) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.searchProdList(condition);
		return list;
	}

	// 찜하기 순 전체 상품목록(필터없음)
	@Override
	public List<ProductVo> searchProdListZzim(Map<String, Object> condition) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.searchProdListZzim(condition);
		return list;
	}

	// 상품 목록 중 한번도 입찰되지 않은 상품목록을 조회
	@Override
	public List<ProductVo> searchProdListNoTender(Map<String, Object> condition) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.searchProdListNoTender(condition);
		return list;
	}

	// 상품 이름으로 목록 검색
	@Override
	public List<ProductVo> searchProdListByName(String searchvalue) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.searchProdListByName(searchvalue);
		return list;
	}

	// 선택한 상품의 최근 입찰 내역
	@Override
	public List<ProductVo> recentTender(int pNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.recentTender(pNum);
		session.close();
		return list;
	}

	// 상품 상세화면에서 상품 정보 보기
	@Override
	public ProductVo productDetail(int pNum) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		ProductVo vo = dao.productDetail(pNum);
		session.close();
		return vo;
	}

	// 상품 입찰하기
	@Override
	public void insertTender(Map<String, Object> tmap) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		dao.insertTender(tmap);
		session.commit();
		session.close();
	}

	// 상품 입찰가 수정
	@Override
	public void updateTender(Map<String, Object> tMap) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		dao.updateTender(tMap);
		session.commit();
		session.close();
	}

	// 총 상품수 구하기
	@Override
	public int totalCnt() throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		int totalCnt = dao.totalCnt();
		session.close();
		return totalCnt;
	}

	@Override
	public int selectSNumByPNum(int pNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		int sNum = dao.selectSNumByPNum(pNum);
		session.close();
		return sNum;
	}

	@Override
	public List<ProductVo> selectAllByPStatusIs8() throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		List<ProductVo> list = dao.selectAllByPStatusIs8();
		session.close();
		return list;
	}

	@Override
	public void updateWhenReBid(int sNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		dao.updateWhenReBid(sNum);
		session.commit();
		session.close();
	}

	@Override
	public void deleteReBiddingProduct(int pNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ProductDao dao = (ProductDao) session.getMapper(ProductDao.class);
		dao.deleteReBiddingProduct(pNum);
		session.commit();
		session.close();
	}

//	public static void main(String[] args) {
//		ProductDaoOracle dao = new ProductDaoOracle();
	// Map map = new HashMap<>();
//	    map.put("sHopePrice", 700088);
	// map.put("sNum", 8);
//		StockDaoOracle sdao = new StockDaoOracle();
//		StockVo vo = sdao.selectBySNum(13);

//		Map map = new HashMap<>();
//		map.put("sNum", 13);
//		map.put("pEndDate", vo.getSHopeDays());
//		dao.insertProduct(map);

//		dao.insertProduct(map);

//		map.put("pNum", 10);
//		map.put("id", "bbb");
//		map.put("price", 900000);
//		map.put("sHopePrice",900000);

//		System.out.println(dao.selectByIdProductDetail(map));

//		System.out.println(dao.selectByIdProduct("bbb"));

//		System.out.println(dao.selectByEndProduct("aaa"));
//		System.out.println(dao.selectByEndProductDetail("aaa"));
//}

}