package com.relo.styletag;

import java.util.List;

import com.relo.exception.FindException;

public class StyleTagService {
	private StyleTagDao dao;
	
	public StyleTagService() {
		dao = new StyleTagDaoOracle();
	}
	public void addStyleTag(StyleTagVo vo) throws FindException{
		dao.addStyleTag(vo);
	}

	public List<String> styleTagList() throws FindException{
		return dao.styleTagList();
	}
	
	public List<StyleTagVo> styleTagDetail(int styleNum) throws FindException{
		return dao.styleTagDetail(styleNum);
	}
	
	public void delStyleTag(int styleNum) throws FindException{
		dao.delStyleTag(styleNum);
	}
}