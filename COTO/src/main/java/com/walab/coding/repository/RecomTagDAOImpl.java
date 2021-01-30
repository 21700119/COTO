package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;

@Repository
public class RecomTagDAOImpl implements RecomTagDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "tag";
	
	@Override
	public void createTag(RecomTagDTO t) {
		sqlSession.insert(namespace + ".createTag", t);
	}
	
	@Override
	public List<RecomTagDTO> readTagOrderByCount() {
		return sqlSession.selectList(namespace+".searchTagOrderByCount");
	}
	
	@Override
	public List<RecomTagDTO> readProblemTag() {
		return sqlSession.selectList(namespace+".readProblemTagList");
	}
	
	@Override
	public int deleteRecomTag(int recomID) {
		Map<String, Object> recomTagListParam = new HashMap<String, Object>();
		recomTagListParam.put("recomID", recomID);
		
		return sqlSession.delete(namespace+".deleteRecomTag", recomTagListParam);
	}
}
