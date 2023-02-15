package com.relo.orders;

import java.sql.Date;

import com.relo.address.AddressVo;
import com.relo.orderDelivery.ODeliveryVo;
import com.relo.successfulBid.ScBidVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrdersVo {
	private int oNum;
	private ScBidVo scbid;
	private AddressVo address;
	private String oMemo;
	private Date oDate;
	private ODeliveryVo od;
}

//p.p_num, o.o_num, s.s_file, 
//s.s_name, sz.size_category_name, 
//a.a_price, o.o_date, od.d_status

/*o.o_num, o.o_date, p.p_num , 
 * s.s_name , sz.size_category_name, 
 * a.a_price, od.d_status,
 * od.d_trackin_info, 
 * od.d_complete_day, 
 * ad.addr_num, ad.addr_recipient, 
 * ad.addr_tel, ad.addr, 
 * ad.addr_detail*/