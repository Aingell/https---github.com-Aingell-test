package com.relo.reply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//rep_num   number      PRIMARY KEY,
//style_num   number      NOT NULL REFERENCES style(style_num) ON DELETE CASCADE,
//id   varchar2(50)      NOT NULL REFERENCES member(id) ON DELETE CASCADE,
//rep_content   varchar2(200)      NOT NULL,
//rep_date   date   DEFAULT sysdate   NOT NULL
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyVo {
	private int repNum;
	private int styleNum;
	private String id;
	private String repContent;
	@JsonFormat(timezone = "Asia/Seoul", pattern = "yy-MM-dd") 
	private Date repDate;
}