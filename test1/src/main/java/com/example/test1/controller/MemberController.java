package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/member/login.do") //.do 
    public String login(Model model) throws Exception{
						 
        return "/member/member-login"; //login.jsp 파일을 실행하겠다는 문법 
    }
	
	@RequestMapping("/member/add.do") //.do 
    public String add(Model model) throws Exception{
						 
        return "/member/member-add"; 
    }
	@RequestMapping("/addr.do") //.do 
    public String addr(Model model) throws Exception{
						 
        return "/jusoPopup"; 
    }
	
	
	//로그인 
	@RequestMapping(value = "/member/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = memberService.memberLogin(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	//회원가입 
	@RequestMapping(value = "/member/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = memberService.memberAdd(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	//중복체크 
	@RequestMapping(value = "/member/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = memberService.memberCheck(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
}
