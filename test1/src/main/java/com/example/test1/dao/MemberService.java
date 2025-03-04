package com.example.test1.dao;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.MemberMapper;
import com.example.test1.model.Member;

@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	HttpSession session;             //세션에 값을 넣어주면 언제든지 꺼내서 사용할 수 있다 .

	public HashMap<String, Object> memberLogin(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Member member = memberMapper.getMember(map);
		if(member != null) {
			System.out.println("성공");
			session.setAttribute("sessionId", member.getUserId());             //세션에 아이디 값을 넣는다 
			session.setAttribute("sessionName", member.getUserName());			//세션에 유저 네임을 넣는다 
//			session.setAttribute("sessionStatus", member.getStatus());			//세션에 유저 네임을 넣는다 
			 
			resultMap.put("member", member);
			resultMap.put("result", "success");
		}else {
			System.out.println("실패");
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	//로성 로실 

	public HashMap<String, Object> memberAdd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int num = MemberMapper.insertMember(map);
		resultMap.put("result", "success");
		//if num > 0 데이터 삽입 잘 된거 
		// 아니면 뭔가 문제 있는것
		return resultMap;
	}

	public HashMap<String, Object> memberCheck(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Member member = MemberMapper.checkMember(map);
		
		int count = member != null ? 1 : 0 ;      //
		resultMap.put("count", count);
// 위에 표기한것과 같은 문법 널값인 경우 중복이 없음 
//		if(member != null) {
//			count = 1;
//		}else {
//			count = 0;
//		}
		return resultMap;
	}
}
