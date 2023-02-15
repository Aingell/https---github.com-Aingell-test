package com.relo.successfulBid;

import java.sql.Date;

import com.relo.auction.AuctionVo;

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

//catch c, auction a, product p, stock s
public class ScBidVo {
	private AuctionVo a;
	private Date cTime;
}