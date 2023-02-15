package com.relo.styletag;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.relo.exception.FindException;
import com.relo.resource.Factory;

public class StyleTagDaoOracle implements StyleTagDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public StyleTagDaoOracle(){
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	//스타일태그 추가
	@Override
	public void addStyleTag(StyleTagVo vo) throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleTagDao dao = (StyleTagDao) session.getMapper(StyleTagDao.class);
		dao.addStyleTag(vo);
		session.commit();
		session.close();
	}
	//스타일 태그 리스트 
	@Override
	public List<String> styleTagList() throws FindException{
		SqlSession session = sqlSessionFactory.openSession();
		StyleTagDao dao = (StyleTagDao) session.getMapper(StyleTagDao.class);
		List<String> list = dao.styleTagList();
		session.close();
		return list;
	}
	//해당 게시물에 대한 태그 리스트
	@Override
	public List<StyleTagVo> styleTagDetail(int styleNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		StyleTagDao dao = (StyleTagDao) session.getMapper(StyleTagDao.class);
		List<StyleTagVo> list = dao.styleTagDetail(styleNum);
		session.close();
		return list;
	}
	//해당 게시품 태그 삭제
	@Override
	public void delStyleTag(int styleNum) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		StyleTagDao dao = (StyleTagDao) session.getMapper(StyleTagDao.class);
		dao.delStyleTag(styleNum);
		session.commit();
		session.close();
	}
	//테스트완료
	public static void main(String[] args) throws FindException {
		StyleTagDaoOracle dao = new StyleTagDaoOracle();
//		dao.addStyleTag(new StyleTagVo(0, 7, "운동화"));
		List<String> list = dao.styleTagList();
//		List<StyleTagVo> list = dao.styleTagDetail(16);
		System.out.println(list);
//		for(String s : list) {
//			System.out.println(s);
//		}
	}
}