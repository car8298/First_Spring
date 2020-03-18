package com.taihan.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taihan.vo.BoardVO;
import com.taihan.vo.SearchCriteria;

public interface BoardService {
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception;
	
	public List<BoardVO> list(SearchCriteria scri) throws Exception;
	
	public int listCount(SearchCriteria scri) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void update(BoardVO boardVO,
					   String[] files,
					   String[] fileNames,
					   MultipartHttpServletRequest mpRequest) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
}
