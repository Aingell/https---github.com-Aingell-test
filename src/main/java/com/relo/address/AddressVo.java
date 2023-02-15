package com.relo.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressVo {
	private int addrNum;
	private String id;
	private String addrName;
	private int addrPostNum;
	private String addrTel;
	private String addr;
	private String addrDetail;
	private String addrRecipient;
	private int addrType;
}