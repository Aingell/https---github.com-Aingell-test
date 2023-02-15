package com.relo.account;

import com.relo.member.MemberVo;

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

public class AccountVo {
	private MemberVo m;
	private String bankAccount;
	private String bankCode;
}