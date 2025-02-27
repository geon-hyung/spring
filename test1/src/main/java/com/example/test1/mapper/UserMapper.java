package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Member;
import com.example.test1.model.User;

@Mapper  //필수 xml 에있는 함수 호출이 가능해진다 
public interface UserMapper {
	
	User userMapperLogin(HashMap<String, Object> map);
           
	// 하나의 행으로 담기 때문에 리스트안에 멤버를 담아서 여러개를 사용할 수 있다  
	List<Member> getUserList(HashMap<String, Object> map);
	
	// int형 데이터 타입으로 리턴을 해준다 , 셀렉트 제외 int 사용 
	void memberDelete(HashMap<String, Object> map);

	void testDelete(HashMap<String, Object> map);


	
	
}
 