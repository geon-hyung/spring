package com.example.test1.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.BoardService;
import com.google.gson.Gson;

@Controller
public class BoardController {
	
	@Autowired              //서비스와 연결해주는것! 
	BoardService boardService;                 //첫 글자를 소문자로 
	
	@RequestMapping("/board/list.do") //.do 
    public String login(Model model) throws Exception{
						 
        return "/board/board-list"; //login.jsp 파일을 실행하겠다는 문법 
    }
	
	@RequestMapping("/board/add.do") //.do 
    public String add(Model model) throws Exception{
						 
        return "/board/board-add"; 
    }
	
	@RequestMapping("/board/view.do") //.do 
    public String view(HttpServletRequest request,Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);                     //http 사용해서 맵에다가 사용자가 클릭한 정보를 넣어준다
		request.setAttribute("map", map);		//map 이라는 이름으로 보내준다 .  
        return "/board/board-view"; 
    }
	@RequestMapping("/board/edit.do") //.do 
    public String edit(HttpServletRequest request,Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);                    
		request.setAttribute("map", map);		
        return "/board/board-edit"; 
    }
	
	
	
	//게시글 목록
	@RequestMapping(value = "/board/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String memberList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
//		System.out.println(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = boardService.getBoardList(map);
		//유저서비스안에유저로그인이라는 함수를 호출하고 있다 (객체)
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	
	
	@RequestMapping(value = "/board/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardAdd(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
//		System.out.println(map);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = boardService.addBoard(map); // 여기가 넣는이름 변경 

		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	@RequestMapping(value = "/board/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = boardService.getBoard(map);
		return new Gson().toJson(resultMap);     //맵을 제이선 형태로 바꿔서 리턴 해준다 
	}
	
	
}
