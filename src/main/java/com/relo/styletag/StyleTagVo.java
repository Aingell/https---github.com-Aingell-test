package com.relo.styletag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//hash_num   number      NOT NULL PRIMARY KEY,
//style_num   number NOT NULL REFERENCES style(style_num) ON DELETE CASCADE,
//hash_name   varchar(40)      NOT NULL
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StyleTagVo {
	private int hashNum;
	private int styleNum;
	private String hashName;
}