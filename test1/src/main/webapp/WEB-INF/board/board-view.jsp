<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="../js/page-change.js"></script>
	<title>첫번째 페이지</title>
</head>
<style>
</style>
<body>
	<div id="app">
        <div>
            제목 : {{info.title}}
        </div>
        
        <div>
            내용 : {{info.contents}}
        </div>
        <button @click="fnEdit">수정</button>
	</div>
</body>
</html>
<script>
    const app = Vue.createApp({
        data() {
            return {
              boardNo : "${map.boardNo}",           //한가지만 넘겨받아서 꺼내려고할때  map. 컬럼
              info : {}
            };
        },
        methods: {
            fnGetBoard(){
				var self = this;
				var nparmap = {
                    boardNo : self.boardNo
                };
				$.ajax({
					url:"/board/info.dox",
					dataType:"json",	
					type : "POST", 
					data : nparmap,
					success : function(data) { 
						console.log(data);
                        self.info = data.info;
					}
				});
            },
            fnEdit:function(boardNo){
                pageChange("/board/edit.do",{boardNo : this.boardNo});
                
            }
        },
        mounted() {
            var self = this;
            self.fnGetBoard();
        }
    });
    app.mount('#app');
</script>
​