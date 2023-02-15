package com.relo.member;

import java.util.Map;

import com.relo.exception.FindException;

public interface MemberDao {
	/**
	 * 회원을 가입시킨다.
	 * @param m MemberVo
	 */
	void insert(MemberVo m) throws FindException;
	
	/**
	 * 회원 이름과 이메일 모두 일치하는 id 찾기
	 * @param mName 회원 이름
	 * @param email 회원 이메일
	 * @return 
	 */
	MemberVo findId(Map<String,String> param) throws FindException;
	
	/**
	 * 탈퇴 가능한지 회원의 상태 체크를 한다.
	 * @param id 회원 아이디
	 * @return 탈퇴 가능 여부(1이면 탈퇴 가능, 그 외에는 탈퇴 불가)
	 */
	int checkOutTerms(String id) throws FindException;
	
	/**
	 * 회원 한 명을 아이디로 검색한다.
	 * @param id 회원 아이디
	 * @return MemberVo
	 */
	MemberVo select(String id) throws FindException;

	/**
	 * 회원 정보를 업데이트 한다.
	 * @param m MemberVo
	 */
	void update(MemberVo m) throws FindException;
	
	/**
	 * 회원을 탈퇴시킨다.
	 * @param id 회원 아이디
	 */
	void delete(String id) throws FindException;

}