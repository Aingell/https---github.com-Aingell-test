package com.relo.member;

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
public class MemberVo {
	private String id;
	private String pwd;
	private String tel;
	private String email;
	private int type;
	private String birth;
	private String mName;
	private int outTerm;

	public MemberVo(String id, String pwd, String tel, String email, int type, String birth, String mName) {
		this.id = id;
		this.pwd = pwd;
		this.tel = tel;
		this.email = email;
		this.type = type;
		this.birth = birth;
		this.mName = mName;
	}
}