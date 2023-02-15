package com.relo.likes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//	   style_num   number NOT NULL ,
//	   id   varchar2(50) ,
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LikesVo {
	private int styleNum;
	private String id;
}