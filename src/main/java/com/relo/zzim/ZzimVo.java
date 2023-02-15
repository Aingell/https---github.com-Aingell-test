package com.relo.zzim;

import java.util.List;

import com.relo.product.ProductVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZzimVo {
	private String id;
	private List<ProductVo> product;
}