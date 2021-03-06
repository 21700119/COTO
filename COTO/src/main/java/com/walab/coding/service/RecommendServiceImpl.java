package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.RecomCommentDAO;
import com.walab.coding.repository.RecomCountDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	RecommendDAO recommendDAO;
	@Autowired
	RecomCountDAO recomCountDAO;
	@Autowired
	RecomCommentDAO recomCommentDAO;
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<RecommendDTO> readRecom() {
		List<RecommendDTO> recoms = recommendDAO.readRecom();
		
		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));
			
			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}
		
		return recoms;
	}
	
	/**
	 * RecommendController
	 * 
	 */
	public RecommendDTO readRecommend(int recomID) {
		RecommendDTO recommend = recommendDAO.readRecommend(recomID);
		
		recommend.setRecomCount(recomCountDAO.readRecomCount(recommend.getId()));
		recommend.setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		
		return recommend;
	}
	
	@Override
	public List<RecommendDTO> readRecentRecommendList() {
		List<RecommendDTO> result = recommendDAO.readRecentRecommendList();
		return result;
	}
	
	@Override
	public List<RecomProblemDTO> readRecomProblems(int recomID) {
		List<RecomProblemDTO> recomProblems = recommendDAO.readRecomProblems(recomID);
		
		return recomProblems;
	}
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public int createRecomProblem(RecommendDTO recommend) {
		int recomID = recommendDAO.createRecomProblem(recommend);
		
		return recomID;
	}
	
	@Override
	public List<RecommendDTO> search(String searchValue, String orderValue) {
		searchValue = "%".concat(searchValue).concat("%");
		if(orderValue == null)
			orderValue ="recom.regdate desc";
		else if(orderValue.compareTo("difficulty ") == 0) {
			orderValue = "recom.".concat(orderValue).concat("desc");
		}
		else if(orderValue.compareTo("recom.recomCount ") == 0) {
			orderValue = orderValue.concat("desc");
		}
		
		List<RecommendDTO> recoms = recommendDAO.searchProblemByContents(searchValue, orderValue);
		
		return recoms;
	}
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public int deleteRecom(int recomID) {
		return recommendDAO.deleteRecom(recomID);
	}
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public int updateRecommend(RecommendDTO r) {
		return recommendDAO.updateRecommend(r);
	}

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, int s_point, int list) {
		List<RecommendDTO> recoms = recommendDAO.readRecomByPage(searchValue, orderValue, s_point, list);
		
		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));
			
			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}
		
		return recoms;
	}

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public int readRecomListCnt() {
		return recommendDAO.readRecomListCnt();
	}
}
