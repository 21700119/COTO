package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendService {

	List<RecommendDTO> readRecom();
	
	List<RecomProblemDTO> readRecomProblems(int recomID);
	
	public List<RecommendDTO> readRecentRecommendList();
	
	int createRecomProblem(RecommendDTO recommend);
	
	public List<RecommendDTO> search(String searchValue, String orderValue);

	public int deleteRecom(int recomID);
	
	public int updateRecommend(RecommendDTO r);
	
	public RecommendDTO readRecommend(int recomID);

	public List<RecommendDTO> readRecomByPage(int s_point, int list);

	public int readRecomListCnt();
}
