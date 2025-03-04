package com.example.test1.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Member;

@Mapper
public interface MemberMapper {



	Member getMember(HashMap<String, Object> map);

	static int insertMember(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	static Member checkMember(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}


	


		
	

}
