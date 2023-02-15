package com.relo.likes;

import com.relo.exception.FindException;

public interface LikesDao {
	/**
	 * 좋아요 +1
	 * 
	 * @param vo : LikesVo
	 * @throws FindException
	 */
	public void addLikes(LikesVo vo) throws FindException;
	/**
	 * 좋아요 -1
	 * 
	 * @param vo : LikesVo
	 * @throws FindException
	 */
	public void delLikes(LikesVo vo) throws FindException;
	
	public LikesVo checkLikes(LikesVo vo) throws FindException;
}