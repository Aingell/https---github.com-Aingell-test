package com.relo.stock;


import com.relo.member.MemberVo;
import com.relo.sizes.SizesVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class StockVo {

	private int sNum;
	private MemberVo m;
	private SizesVo sizes;
	private String sBrand;
	private String sName;
	private int sOriginPrice;
	private int sHopePrice;
	private String sColor;
	private String sType;
	private String sGrade;
	private String sFile;
	private int sHopeDays;
	private String sellerComment;
	private String managerComment;
	private int sReturn;
	
	
	public StockVo(int sNum, MemberVo m, SizesVo sizes, String sBrand, String sName, int sOriginPrice,
			String sColor, String sType, String sFile, int sHopeDays, String sellerComment, int sReturn) {
		this.sNum = sNum;
		this.m = m;
		this.sizes = sizes;
		this.sBrand = sBrand;
		this.sName = sName;
		this.sOriginPrice = sOriginPrice;
		this.sColor = sColor;
		this.sType = sType;
		this.sFile = sFile;
		this.sHopeDays = sHopeDays;
		this.sellerComment = sellerComment;
		this.sReturn = sReturn;
	}





	
	
	
}
