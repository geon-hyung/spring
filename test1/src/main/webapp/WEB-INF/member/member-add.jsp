<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
	<title>회원가입</title>
</head>
<style>
</style>
<body>
	<div id="app">
        <div>
            아이디 : <input v-model="userId" >
            <button @click="fnCheck" >중복체크</button>
        </div>
        <div>
            비밀번호: <input v-model="userPwd">
        </div>
        <div>
            이름 : <input v-model="userName">
        </div>
        <div>
            주소: <input v-model="userAddr">
            <button @click="fnSearchAddr">주소검색</button>
        </div>
        <div>
            <input v-model="phoneNum" placeholder="번호 입력">
            <button @click="fnSmsAuth">문자인증</button>
        </div>
        <div v-if="authFlg">
            <div v-if="joinFlg" style="color: red;">
                문자인증완료
            </div>
            <input v-model="authInputNum" :placeholder="timer">
            <button @click="fnNumAuth">인증</button>
        </div>
        <div>
          권한 : 
            <input type="radio" name="status" value="C" v-model="status"> 일반  
            <input type="radio" name="status" value="A" v-model="status"> 관리자   
        </div>
     
        <div>
            <button @click="fnSave">회원가입</button>
            
        </div>
	</div>
</body>
</html>
<script>     //뷰 문법이 아니므로 바깥 쪽에서 함수를 만들어줘야 한다
function jusoCallBack(roadFullAddr, 
                    roadAddrPart1, addrDetail, roadAddrPart2, 
                    engAddr, jibunAddr, zipNo, admCd, rnMgtSn, 
                    bdMgtSn, detBdNmList, bdNm, bdKdcd, siNm, 
                    sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
                    buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
                window.vueObj.fnResult(roadAddrPart1, addrDetail, engAddr, detBdNmList);        
	//
}
    const app = Vue.createApp({
        data() {
            return {
                              //유저라는 맵안에 저장해서 명확하게 관리할 수 있다 
                userId : "",
                userPwd : "",
                userName : "",
                userAddr : "",
                status : "C",
                phoneNum : "",
                
                authNum : "", // 서버에서 만든 랜덤 숫자 
                authInputNum : "", //사용자가 문자로 받고 입력한 숫자 
                authFlg : false, // 인증번호 입력 상태 인증 클릭시 생성 됨 
                joinFlg : false, //문자인증 완료 상태 
                timer : "",
                count : 180
            };
        },
        methods: {
            fnSave(){
				var self = this;
                if(sele.joinFlg == false){
                    alert("문자인증우선");
                    return;
                }
				var nparmap = {
                    userId : self.userId,
                    userPwd : self.userPwd,
                    userName : self.userName,
                    userAddr : self.userAddr,
                    status : self.status
                }            //유저 맵안에 저장 했을땐 이렇게 사용할 수 있다  user 맵으로 선언하고{} 파라미터로 
                                //보낼때 self.user 로 보내면 사용이 가능 
				$.ajax({
					url:"/member/add.dox",
					dataType:"json",	
					type : "POST", 
					data : nparmap,
					success : function(data) { 
						console.log(data);
                       
					}
				});
            },
            fnCheck : function(){
                var self = this;
                if(self.userId == ""){
                    alert("아이디입력");
                    return;
                }
				var nparmap = { 
                    userId :self.userId  
                }            
				$.ajax({
					url:"/member/check.dox",
					dataType:"json",	
					type : "POST", 
					data : nparmap,
					success : function(data) { 
                    if(data.count == 0 ){
                        alert("사용")
                    }else{
                        alert("불가능")
                    }

					}
				});
            },
            fnSearchAddr : function(){
                //팝업으로 addr.do 를 호출 
                window.open("/addr.do","addr", "width=500, height=500")

            },
            fnResult : function(roadAddrPart1, addrDetail, engAddr, detBdNmList){
                let self = this;
                self.address = roadFullAddr;
                console.log(roadAddrPart1);
                console.log(addrDetail);
                console.log(engAddr);
                console.log(detBdNmList);
            },
            fnSmsAuth : function(){
                var self = this;
				var nparmap = {
                    phoneNum : self.phoneNum
                }       
                                
				$.ajax({
					url:"/send-one",
					dataType:"json",	
					type : "POST", 
					data : nparmap,
					success : function(data) { 
						console.log(data);
                        if(data.response.statusCode == 2000){
                            alert("문자 발송 완료");
                            self.authNum = data.ranStr;
                            self.authFlg = true;
                            setInterval(self.fnTimer, 1000);
                        }else{
                            alert("잠시 후 다시 시도 해주세요 ")
                        }
					}
				});
            },
            fnNumAuth : function(){
                let self = this;
                if(self.authNum == self.authInputNum){
                    alert("인증되었습니다");
                    self.joinFlg = true;              //인증되었을때 조인 플래그를 트루로 바꿔준다 
                }else {
                    alert("인증 번호 다시 확인바람")
                }
            },
            fnTimer : function(){
                let self = this;
                let min = "";
                let sec = "";
                min = parseInt(self.count / 60);       //숫자로 변경시켜준다 
                sec = parseInt(self.count % 60);
                //10보다 작을때 문자열 0 
                min = min < 10 ? "0" + min : min
                sec = sec < 10 ? "0" + sec : sec
                
                self.timer = min + ":" + sec;
                
                self.count--;

            }
            
            
        },
        mounted() {
            var self = this;
            window.vueObj = this;
        }
    });
    app.mount('#app');
</script>
​