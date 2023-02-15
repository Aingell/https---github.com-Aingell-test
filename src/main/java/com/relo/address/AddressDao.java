package com.relo.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.relo.exception.FindException;

public interface AddressDao {
	/**
	 * 주소록을 추가한다.
	 * @param a AddressVo
	 */
	void insert(AddressVo a) throws FindException;
	
	/**
	 * 회원 아이디와 일치하는 모든 주소록 목록을 검색한다.
	 * @param id 회원 아이디
	 * @return 회원 아이디와 일치하는 모든 주소록 목록
	 */
	List<AddressVo> selectAllById(@Param("id") String id) throws FindException;
	
	/**
	 * 새 주소록 추가 후, 새로 생성된 주소록을 기본 주소로 선택하기 위해 해당 주소록을 검색한다. 
	 * @param a AddressVo
	 */
	int selectNewAfterInsert(AddressVo a) throws FindException;
	
	/**
	 * 주소록을 수정한다.
	 * @param a AddressVo
	 */
	void update(AddressVo a) throws FindException;
	
	/**
	 * 기본 주소지 변경시, 기존의 기본 주소지 타입을 1로 변경한다.
	 * @param id 회원 아이디
	 */
	void changeAddrTypeIs0(String id) throws FindException;
	
	/**
	 * 주소록을 삭제한다.
	 * @param addr_num 주소록 번호
	 */
	void delete(int addrNum) throws FindException;
	/**
	 * 주소록을 검색한다.
	 * @param addrNum 주소록 번
	 * @return AddressVo
	 * @throws FindException
	 */
	AddressVo selectByNum(int addrNum) throws FindException;
	
}