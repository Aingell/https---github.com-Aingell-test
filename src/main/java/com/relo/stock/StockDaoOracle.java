package com.relo.stock;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class StockDaoOracle implements StockDao {
	private SqlSessionFactory sqlSessionFactory;

	public StockDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	@Override
	public List<StockVo> selectAll() throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
	//	List<StockVo> list = session.selectList("com.relo.StockDao.selectAll");	
		List<StockVo> list = mapper.selectAll();
		
		return list;
	}
	
	// 1-1.판매자 상품등록 (재고 정보 입력)
	@Override
	public void insertStock(StockVo vo) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		mapper.insertStock(vo);
		session.commit();
		session.close();
	}
	
	// 1-2.판매자 정산계좌 등록 -> AccountMapper.xml로 구현
	
	// 2.판매자 마이페이지-> 판매내역 -> 판매대기
	@Override
	public List<StockVo> selectById(String id) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		List<StockVo> list = mapper.selectById(id);
		session.close();
		return list;
	}
	
	// 3. 관리자 상품등록 승인요청 목록
	//관리자 상품 최종 등록 목록 
	@Override
	public List<StockVo> selectBySReturn(int sReturn) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		List<StockVo> list = mapper.selectBySReturn(sReturn);
		session.close();
		return list;
	}
	
	// 3-1. 관리자 상품등록 승인요청 상세 -> 등급과 comment를 판매자에게 넘기는 단계
	//	관리자 상품 최종 등록 목록 상세
	@Override
	public StockVo selectBySNum(int sNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		StockVo vo = mapper.selectBySNum(sNum);
		session.close();
		return vo;
	}
	
	
	
	// 4. 판매자 판매내역 ->  판매대기  -> 상세페이지
	public StockVo selectByIdDeatil(Map map) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
	    StockVo vo = mapper.selectByIdDeatil(map);
	    session.close();
	    return vo;
	}
	
	// 5. 판매자가 상품등급보고 판매희망여부 판단 5-1 희망 5-2 거부
	
	// 5-1. 판매자 판매내역 ->  판매대기  -> 상세페이지 -> 희망가격 입력 
	// 3-2. 관리자 상품등록 승인요청 상세에서 등급과 comment를 판매자에게 넘기는 단계
	// 5-1 ,3-2 하나로 합침
	public void updateSetSReturn(StockVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		mapper.updateSetSReturn(vo);
		session.commit();
		session.close();
	}


	// 5-2. 판매자 판매내역 ->  판매대기  -> 상세페이지 -> 취소버튼클릭
	// 판매등록 취소한 재고 상태 5로 변경
	public void updateByCancleSReturn5(int sNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StockDao mapper = (StockDao) session.getMapper(StockDao.class);
		mapper.updateByCancleSReturn5(sNum);
		session.commit();
		session.close();
	}
	
//	public static void main(String[] args) {
//		StockDaoOracle dao = new StockDaoOracle();
//        Map map = new HashMap<>();
//		map.put("id", "bbb");
//		map.put("sNum", 13);
//		System.out.println(dao.selectBySNumDeatil(map));
		
		// 1-1.판매자 상품등록 (재고 정보 입력)
//		MemberVo vo = new MemberVo("bbb", null, null, null, 0, null, null);
//		SizesVo svo = new SizesVo(113, "230");
//		dao.insertStock(new StockVo(0, vo, svo, "나이키", "23s신발",50000, "화이트", "신발", "파일경로", 5, "새상품이에요", 0));
		
		// 2.판매자 마이페이지-> 판매내역 -> 판매대기
//		System.out.println(dao.selectById("bbb"));
		
		//3.
//		System.out.println(dao.selectBySReturn(3));
//		System.out.println(dao.selectBySNum(8));

		//3-1
		//dao.updateSetSReturn(new StockVo(13, null, null, null, null, 0, 900000, null, null, null, null, 0, null, null, 0));
		
//	}
}
