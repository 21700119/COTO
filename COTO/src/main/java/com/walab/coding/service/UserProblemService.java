package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;

public interface UserProblemService {
	List<UserProblemDTO> read(int userID);
	
	int readSolvedP(int userID);
	
	public List<RankDTO> readRankList();
	
	public int update(UserProblemDTO upd);
	
	public int delete(int userProblemID);
}
