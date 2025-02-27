package com.example.test1.model;

import lombok.Data;     //lombok get,set 을처리해준다 

@Data
public class Board {
	private String boardNo;
	private String title;
	private String contents;
	private String userId;
	private String kind;
	private String favorite;
	private String cnt;
	private String subtitle;
	private String deleteYn;
	private String cDateTime;
	private String udateTime;
}
