package com.relo.auction;

import com.relo.product.ProductVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

//product p, auction a, sizes sz, stock s, a_max am
public class AuctionVo {
	private int aNum;
	private String id;
	private ProductVo product;
	private int aPrice;
	
}

