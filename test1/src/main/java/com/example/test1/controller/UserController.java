package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.UserService;
import com.google.gson.Gson;

@Controller       //주소를 만드는부분 리턴으로 jsp파일을 리턴해준다 
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/login.do") //.do 
    public String login(Model model) throws Exception{
						 
        return "/login"; //login.jsp 파일을 실행하겠다는 문법 
    }
	
	@RequestMapping("/member/list.do") //주소 만들기  
    public String list(Model model) throws Exception{
						 
        return "/member-list"; //member.jsp 파일을 실행하겠다는 문법 
    }
	@RequestMapping("/test.do") //주소 만들기  
    public String test(Model model) throws Exception{
						 
        return "/test1"; //test1.jsp 파일을 실행하겠다는 문법 jsp 는 생략 가능  
    }
	
	// ajax로 접근할 주소를 입력한다 
	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
//		System.out.println(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = userService.userLogin(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	
	
	@RequestMapping(value = "/member/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String memberList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
//		System.out.println(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = userService.memberList(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	
	@RequestMapping(value = "/member/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String memberRemove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = userService.memberRemove(map);  //삭제 버튼시 해당 사용자의 정보가 담겨있다 
		return new Gson().toJson(resultMap);    
	}
	
	@RequestMapping(value = "/test/Remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String testRemove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = userService.testRemove(map); 
		return new Gson().toJson(resultMap);    
	}
	
	
} // usercontroller 클래스에 대한 중괄호 






