package com.relo.notice;

import java.util.List;

import com.relo.exception.FindException;

public class NoticeService {
	private NoticeDao dao;

	public NoticeService() {
		dao = new NoticeDaoOracle();
	}
	
	public void addNotice(NoticeVo n) throws FindException{
		dao.insert(n);
	}
	
	public NoticeVo getOne(int nNum) throws FindException{
		return dao.select(nNum);
	}
	
//	public PageBean<NoticeVo> findAll(int currentPage) throws FindException {
//		int cntPerPage = 3;
//		int startRow = (currentPage-1)*cntPerPage + 1;
//        int endRow = currentPage*cntPerPage;
//        Map
//        dao.selectNoticeFromTo();
//	}
	
	public List<NoticeVo> getAll() throws FindException{
		return dao.selectAll();
	}
	public List<NoticeVo> getAllByTitle(String nTitle) throws FindException{
		return dao.selectByTitle(nTitle);
	}
	public List<NoticeVo> getAllByCategory(int nCategory) throws FindException{
		return dao.selectByCategory(nCategory);
	}
	
	public NoticeVo getPreByNNum(int nNum) throws FindException{
		return dao.selectPreByNNum(nNum);
	}
	
	public NoticeVo getNextByNNum(int nNum) throws FindException{
		return dao.selectNextByNNum(nNum);
	}
	
	public void editNotice(NoticeVo n) throws FindException{
		dao.update(n);
	}
	public void delNotice(int nNum) throws FindException{
		dao.delete(nNum);
	}
}
