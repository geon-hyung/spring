<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<script src="https://code.jquery.com/jquery-3.7.1.js"
			integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
		<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
		<title>첫번째 페이지</title>
		<style>
			table,
			tr,
			td,
			th {
				border: 1px solid black;
				border-collapse: collapse;
				padding: 5px 10px;
				text-align: center;
			}
		</style>
	</head>
	<style>
	</style>

	<body>
		<div id="app">
			<table>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>주소</th>
					<th>삭제</th>
				</tr>
				<tr v-for="item in list">
					<td>{{item.userId}}</td>
					<td>{{item.userName}}</td></a>
					<td>{{item.address}}</td>
					<td><button @click="fnRemove(item.userId)">삭제</button></td>
					<!--fnremove 라는 함수를 만들어서 파라미터로 pk를 보내준다 -->
				</tr>
			</table>
		</div>
	</body>

	</html>
	<script>
		const app = Vue.createApp({
			data() {
				return {
					list: []
				};
			},
			methods: {
				fnMemberList() {
					var self = this;
					var nparmap = {};
					$.ajax({
						url: "list.dox",
						dataType: "json",
						type: "POST",
						data: nparmap,
						success: function (data) {
							console.log(data);
							self.list = data.list;
						}
					});
				},
				//키     :   밸류 
				fnRemove: function (userId) {
					var self = this;
					var nparmap = {
						userId : userId
					};
					$.ajax({
						url: "remove.dox",
						dataType: "json",
						type: "POST",
						data: nparmap,
						success: function (data) {
							console.log(data);
							self.list = data.list;
							if(data.result == "success"){
								alert("삭제되었음");
								self.fnMemberList();
							}
						}
					});
				}

			},
			mounted() {
				var self = this;
				self.fnMemberList();
			}
		});
		app.mount('#app');
	</script>