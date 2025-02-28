package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.BoardMapper;
import com.example.test1.model.Board;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	                              
	//get, select	 짝지어서 사용하면 좋음 
	//add, insert
	//edit, update
	//remove delete

	public HashMap<String, Object> getBoardList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Board> list = boardMapper.selectBoardList(map);
			resultMap.put("list", list);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());   // 어떤 오류가 발생했는지 확인 용도 
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

	public HashMap<String, Object> addBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		boardMapper.insertBoard(map);       //서비스와 컨트롤러에서 명칭을 다르게해서 뭘 하는지 이해하기쉽도록한다 
		resultMap.put("result", "success");
		
		return resultMap;
	}

	public HashMap<String, Object> getBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		 //조건문을통해 옵션이라는 키를 가져와서 이뭘스를 사용해서 셀렉트인 값이 나올때만 조회수를 증가한다 
		if(map.get("option").equals("SELECT")) {
			boardMapper.updateCnt(map);
		}	
		Board info = boardMapper.selectBoard(map);
		resultMap.put("info", info);
		resultMap.put("result", "success");
		return resultMap;
	}


	public HashMap<String, Object> editBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		boardMapper.updateBoard(map);
		resultMap.put("result", "success");
		return resultMap;
	}

	public HashMap<String, Object> boardRemove(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		boardMapper.deleteBoard(map);
		resultMap.put("result", "success");
		return resultMap;
	}




}
