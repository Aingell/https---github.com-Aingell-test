package com.relo.style;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class StyleDaoOracle implements StyleDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public StyleDaoOracle() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	//스타일게시물 추가
	@Override
	public void addStyle(StyleVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		dao.addStyle(vo);
		session.commit();
		session.close();
	}
	//스타일게시물 삭제
	@Override
	public void delStyle(StyleVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		dao.delStyle(vo);
		session.commit();
		session.close();
	}
	//스타일게시글 리스트 -> 동적쿼리 도전
	@Override
	public List<StyleVo> selectStyleList(HashMap<String,Object> condition) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		List<StyleVo> list = dao.selectStyleList(condition);
		session.close();
		return list;
	}
	//스타일게시글 내 글 리스트
	@Override
	public List<StyleVo> selectStyleId(String id) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		List<StyleVo> list = dao.selectStyleId(id);
		session.close();
		return list;
	}
	//스타일게시글 해시태그별 리스트
	@Override
	public List<StyleVo> selectStyleTag(String hashName) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		List<StyleVo> list = dao.selectStyleTag(hashName);
		session.close();
		return list;
	}
	//스타일게시글 수정
	@Override
	public void updateStyle(StyleVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		dao.updateStyle(vo);
		session.commit();
		session.close();

	}
	//스타일게시글 상세보기
	@Override
	public List<StyleVo> selectStyleDetail(int styleNum) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		List<StyleVo> list = dao.selectStyleDetail(styleNum);
		session.close();
		return list;
	}
	
	//스타일게시글 상세보기
		@Override
		public StyleVo styleDetail(int styleNum) throws FindException{
			SqlSession session = sqlSessionFactory.openSession();
			StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
			StyleVo vo = dao.styleDetail(styleNum);
			session.close();
			return vo;
		}
	
	//게시물 총 개수
	@Override
	public int cntStyleList() throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
		int cnt = dao.cntStyleList();
		session.close();
		return cnt;
	}
	//페이징 테스트
		@Override
		public List<StyleVo> StyleListPage(int start, int end) throws FindException {
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			map.put("start",start);
			map.put("end", end);
			
			SqlSession session = sqlSessionFactory.openSession();
			List<StyleVo> list = session.selectList("StyleVo.selectStyleList", map);
			//selectList("StyleVo.selectStyleList",map);
			session.close();
			return list;
		}
	//style_likes -1 으로 변경 시키기	
		@Override
		public void styleLikesChange(int styleNum) throws FindException {
			SqlSession session = sqlSessionFactory.openSession();
			StyleDao dao = (StyleDao) session.getMapper(StyleDao.class);
			dao.styleLikesChange(styleNum);
			session.commit();
			session.close();
		}
		

//	테스트완료	
	public static void main(String[] args) throws FindException {
		StyleDaoOracle dao = new StyleDaoOracle();
//		dao.addStyle(new StyleVo(0,"ddd","#나이키","daily.jpg",null,0));
//		dao.delStyle(new StyleVo(5,"fff",null,null,null,0));
//		dao.updateStyle(new StyleVo(4,null,"#아디다스",null,null,0));
//		dao.updateStyle(new StyleVo(4,null,null,null,"adidas.jpg",null,0));
//		String styleCode = "styleLikes";
//		HashMap<String, Object> condition = new HashMap<String, Object>();
//		condition.put("styleCode", styleCode);
//		List<StyleVo> list = dao.selectStyleList(condition);
//		int cnt = dao.cntStyleList();
//		int start = 0;
//		int end = 3;
//		condition.put(1, start);
//		condition.put(3, end);
//		List<StyleVo> list = dao.selectStyleList(condition);
//		list = dao.selectStyleId("ccc");
//		StyleVo vo = dao.styleDetail(1);
//		dao.styleLikesZero(0);
//		System.out.println(vo);
//		System.out.println(list);
//		list = dao.selectStyleTag("운동화");
//		for(StyleVo vo: list) {
//			System.out.println(vo);
//		}
//		System.out.println(cnt);
	}

}