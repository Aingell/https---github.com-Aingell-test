package com.relo.style;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
@Getter@ToString
public class StylePageBean<T> {
	public final static int CNT_PER_PAGE=8;
	public final static int CNT_PER_PAGE_GROUP=5;
	private List<T> list;	
	private int totalCnt;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	
	public StylePageBean(int currentPage, List<T>list, int totalCnt) {
		this.currentPage = currentPage;
		this.list = list;
		this.totalCnt = totalCnt;
		totalPage = (int) Math.ceil((double) totalCnt/CNT_PER_PAGE);
		startPage = (currentPage-1)/CNT_PER_PAGE_GROUP*CNT_PER_PAGE_GROUP+1; 
		endPage = startPage + CNT_PER_PAGE_GROUP -1;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
	}
}