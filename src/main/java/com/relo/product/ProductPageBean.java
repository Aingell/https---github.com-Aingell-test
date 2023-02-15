package com.relo.product;

import java.util.List;

import lombok.Getter;

@Getter
public class ProductPageBean<T> {
	public final static int CNT_PER_PAGE = 3; // 한페이지에서 보여질 개수
	public final static int CNT_PER_PAGE_GROUP = 2; // 페이지 번호에서 보여질 번호
	private List<T> list;
	private int totalCnt;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int currentPage;

	public ProductPageBean(int currentPage, List<T> list, int totalCnt) {
		this.currentPage = currentPage;
		this.list = list;
		this.totalCnt = totalCnt;
		totalPage = (int) Math.ceil((double) totalCnt / CNT_PER_PAGE);
		startPage = (currentPage - 1) / CNT_PER_PAGE_GROUP * CNT_PER_PAGE_GROUP + 1;
		endPage = startPage + CNT_PER_PAGE_GROUP - 1;
	}
}