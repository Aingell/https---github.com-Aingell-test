package com.relo.stockDelivery;
import java.util.Date;

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

public class StockDeliveryVo {
	private int sNum;
	private int stdStatus;
	private String stdTrackinInfo;
	private Date stdStartDate;
}
