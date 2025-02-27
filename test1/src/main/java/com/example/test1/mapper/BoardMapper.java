package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Board;

@Mapper
public interface BoardMapper {
 // list board 외부클래스임으로 임포트 꼭 
	List<Board> selectBoardList(HashMap<String, Object> map);


	void insertBoard(HashMap<String, Object> map);


	Board selectBoard(HashMap<String, Object> map);
	
	


}
