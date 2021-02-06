package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;

public interface ProblemDAO {
	
	public int readProblemID(int siteID, String problem);
	
	public List<ProblemDTO> readProblem();
	
	public List<ProblemDTO> readRecentProblem();
	
	public int readProblemListCnt();
	
	public List<ProblemDTO> searchProblemByContents(String searchValue, String orderValue);
	
	public List<ProblemDTO> readProblemByPage(PaginationDTO page);

	public List<Map<String, Object>> readRatioBySiteid();


}
