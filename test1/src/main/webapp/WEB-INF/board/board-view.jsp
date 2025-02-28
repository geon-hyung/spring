<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
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
            <div>
                조회수 : {{info.cnt}}
            </div>
        <div v-if="info.userId == sessionId || sessionStatus == 'A' ">     
               <!-- - v-if 를 사용해서 게시글을 작성한 사람(info.userid)과 
             info안에 있는userid(info.userid) 를  
             로그인한 id  (sessionid) 를 비교해서 같으면 수정삭제 버튼을 
            화면에 나오게 한다   -->
                <button @click="fnEdit">수정</button>
                <button @click="fnRemove()">삭제</button>
            </div>
        </div>
    </body>

    </html>
    <script>
        const app = Vue.createApp({
            data() {
                return {
                    boardNo: "${map.boardNo}",           //한가지만 넘겨받아서 꺼내려고할때  map. 컬럼
                    info: {},
                    sessionId: "${sessionId}",
                    sessionStatus: "${sessionStatus}"
                    


                };
            },
            methods: {
                fnGetBoard() {
                    var self = this;
                    var nparmap = {
                        boardNo: self.boardNo,
                        option: "SELECT"
                    };
                    $.ajax({
                        url: "/board/info.dox",
                        dataType: "json",
                        type: "POST",
                        data: nparmap,
                        success: function (data) {
                            console.log(data);
                            self.info = data.info;
                        }
                    });
                },
                fnEdit: function (boardNo) {
                    pageChange("/board/edit.do", { boardNo: this.boardNo });

                },

                fnRemove: function () {
                    var self = this;
                    var nparmap = {
                        boardNo: self.boardNo

                    };
                    $.ajax({
                        url: "/board/remove.dox",
                        dataType: "json",
                        type: "POST",
                        data: nparmap,
                        success: function (data) {
                            alert("삭제완");
                            location.href = "/board/list.do";
                        }
                    });
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