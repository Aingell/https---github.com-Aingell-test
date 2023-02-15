package com.relo.zzim;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class ZzimDaoOracle implements ZzimDao {
	private SqlSessionFactory sqlSessionFactory;

	public ZzimDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	@Override // 찜목록 전체 조회
	public List<String> selectAll(Map<String, Object> map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ZzimDao dao = (ZzimDao) session.getMapper(ZzimDao.class);
		List<String> list = dao.selectAll(map);
		session.close();
		return list;
	}

	// 사용자의 찜 목록 조회
	@Override
	public List<ZzimVo> selectById(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		// 구현체 객체를 받아옴
		ZzimDao dao = (ZzimDao) session.getMapper(ZzimDao.class);
		List<ZzimVo> list = dao.selectById(id);
		session.close();
		return list;
	}

	// 사용자의 찜 추가
	@Override
	public void insertZzim(Map<String, Object> map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ZzimDao dao = (ZzimDao) session.getMapper(ZzimDao.class);
		dao.insertZzim(map);
		session.commit();
		session.close();

	}

	// 사용자의 찜 삭제
	@Override
	public void deleteZzim(Map<String, Object> map) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ZzimDao dao = (ZzimDao) session.getMapper(ZzimDao.class);
		dao.deleteZzim(map);
		session.commit();
		session.close();
	}

	// 상품이 삭제되었을때 전체 사용자의 찜목록에서 삭제
	@Override
	public void deleteZzimAll(int pNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		ZzimDao dao = (ZzimDao) session.getMapper(ZzimDao.class);
		dao.deleteZzimAll(pNum);
		session.commit();
		session.close();
	}

	// 테스트용
//	public static void main(String[] args) {
	// ZzimDaoOracle dao = new ZzimDaoOracle();
	// selectById TEST
	/*
	 * try { List<ZzimVo> list = dao.selectById("bbb"); for (ZzimVo vo : list) {
	 * System.out.println(vo); } } catch (FindException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 */

	/*
	 * insertzzim TEST Map<String, Object> imap = new HashMap<>(); String id =
	 * "bbb"; int pNum = 6; imap.put("id", id); imap.put("pNum", pNum); try {
	 * dao.insertZzim(imap); System.out.println("찜 추가완료"); } catch (FindException
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * 
	 */
	/*
	 * deleteZzim, deleteZzimAll Test try { dao.deleteZzim(imap); } catch
	 * (FindException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * System.out.println("찜 삭제완료");
	 * 
	 * 
	 * try { dao.deleteZzimAll(pNum); } catch (FindException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * System.out.println("찜하기 정상 삭제됨"); }
	 */
}