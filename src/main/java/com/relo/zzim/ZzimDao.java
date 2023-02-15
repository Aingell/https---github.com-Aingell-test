package com.relo.zzim;

import java.util.List;
import java.util.Map;

import com.relo.exception.FindException;

public interface ZzimDao {

	/**
	 * 사용자의 상품 찜목록을 검색한다
	 * 
	 * 
	 * @return : List<ZzimVo> 찜목록 전체(id, p_num)
	 * @throws FindException
	 */
	public List<String> selectAll(Map<String, Object> map) throws FindException;

	/**
	 * 사용자의 상품 찜목록을 검색한다
	 * 
	 * @param id : 사용자 ID
	 * @return : List<ZzimVo> 사용자의 상품 찜목록(id, p_num, s_name, s_type, s_color,
	 *         s_hope_price, s_grade, p_end_date, a.max_price)
	 * @throws FindException
	 */

	public List<ZzimVo> selectById(String id) throws FindException;

	/**
	 * 사용자가 상품을 찜목록에 추가한다
	 * 
	 * @param map(id : 사용자 ID ,pNum : 상품번호)
	 * @throws FindException
	 */
	public void insertZzim(Map<String, Object> map) throws FindException;

	/**
	 * 사용자가 상품을 찜목록에서 삭제한다
	 * 
	 * 
	 * @param map(id : 사용자 ID ,pNum : 상품번호)
	 * @throws FindException
	 */
	public void deleteZzim(Map<String, Object> ma) throws FindException;

	/**
	 * 상품 목록에서 삭제되었을 때 모든 사용자의 찜목록에서 삭제한다
	 * 
	 * @param p_num : 상품번호
	 * @throws FindException
	 */
	public void deleteZzimAll(int pNum) throws FindException;

}
