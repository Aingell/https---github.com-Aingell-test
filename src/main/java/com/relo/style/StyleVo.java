package com.relo.style;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.relo.reply.ReplyVo;
import com.relo.styletag.StyleTagVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//style_num   number      PRIMARY KEY,
//id   varchar2(50)    REFERENCES member(id) ON DELETE CASCADE,
//style_content   varchar2(200)      NULL,
//style_file   varchar2(50)      NOT NULL,
//style_date   Date   DEFAULT sysdate   NOT NULL,
//style_likes   number      NULL
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StyleVo {
	private int styleNum;
	private String id;
	private String styleContent;
	private String styleFile;
	@JsonFormat(timezone = "Asia/Seoul", pattern = "yy-MM-dd")
	private Date styleDate;
	private int styleLikes;
	private List<StyleTagVo> tagList;
	private List<ReplyVo> repList;

	
	public StyleVo(int styleNum, String id, String styleContent, String styleFile) {
		this.styleNum= styleNum;
		this.id = id;
		this.styleContent = styleContent;
		this.styleFile = styleFile;
	}
}