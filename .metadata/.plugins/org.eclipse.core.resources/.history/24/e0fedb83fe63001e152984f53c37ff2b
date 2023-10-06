<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>${vo.boardTitle }</title>
</head>
<body>
	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${vo.boardId }</p>
	</div>
	<div>
		<p>제목 :</p>
		<p>${vo.boardTitle }</p>
	</div>
	<div>
		<p>작성자 : ${vo.memberId }</p>
		<p>작성일 : ${vo.boardDateCreated }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.boardContent }</textarea>
	</div>

	<a href="index.jsp"><input type="button" value="글 목록"></a>
	
	<c:if test="${sessionScope.memberId == vo.memberId }"> <!-- session이 없을때 -->
		<a href="update.do?boardId=${vo.boardId }"><input type="button" value="글 수정"></a>
		<form action="delete.do" method="POST">
			<input type="hidden" id="boardId" name="boardId" value="${vo.boardId }">
			<input type="submit" value="글 삭제">
		</form>
	</c:if>
	
	<input type="hidden" id="boardId" name="boardId" value="${vo.boardId }">
	
	<c:if test="${empty sessionScope.memberId }">
		<br> * 댓글은 로그인이 필요한 서비스입니다.
		<a href="login.go">로그인하기</a>
	</c:if>
	
	<c:if test="${not empty sessionScope.memberId }">
		<br><br>${sessionScope.memberId }님, 이제 댓글 작성할 수 있어요!
		<div style="text-align: center">
			<input type="text" id="memberId" value="${sessionScope.memberId }" readonly="readonly"> <!-- id:카멜 class:언더스코어로 씀 -->
			<input type="text" id="replyContent">
			<button id="btnAdd">작성</button>
		</div>
	</c:if>
	
	
	<hr>
	<div style="text-align: center"> <!-- 댓글 보여주기 -->
		<div id="replies"></div>
	</div>
	
	<div>
		<br><br><br><br><br><br><br><br><br><br>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			getAllReplies() // 함수를 만들어 놓고 호출을 안함 ***** 매우 중요
			
			$('#btnAdd').click(function(){ // 버튼을 클릭했을떄..
				var boardId = $('#boardId').val(); // 게시판 번호 데이터 꺼내기
				var memberId = $('#memberId').val(); // 작성자 데이터
				var replyContent = $('#replyContent').val(); // 댓글 내용
				var obj = {
						'boardId' : boardId,
						'memberId' : memberId,
						'replyContent' : replyContent
				};
				console.log(obj);
				
				// $.ajax로 송수신
				$.ajax({
					type : 'POST',
					url : 'replies/add',
					data : {'obj' : JSON.stringify(obj)}, // JSON으로 변환
					success : function(result) {
						console.log(result);
						if(result == 'success') {
							alert('댓글 입력 성공');
							getAllReplies(); // 작성 수정 삭제 후 리프레쉬
						}
					}
				}); // end ajax()
				
			}); // end btnAdd.click()
			
			// 게시판 댓글 전체 가져오기
			function getAllReplies() {
				var boardId = $('#boardId').val(); // select 임플 수행을 위한 선언
				
				var url = 'replies/all?boardId=' + boardId; // 1개라 jSON 보내기 애매 -> 쿼리 스트링으로
				$.getJSON(
						url,
						function(data){ // callback 함수
							// data : 서버에서 전송받은 list 데이터가 저장되어 있음
							// getJSON()에서 json 데이터는
							// javascript object로 자동 parsing됨
							console.log(data);
						
							var memberId = $('#memberId').val(); // session 상의 id 값
							
							var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
							
							// jQuery에서 foreach 기능 
							// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
							$(data).each(function(){
								// this : 컬렉션의 각 인덱스 데이터를 의미 
								console.log(this); /* obj, [{"replyId":2,"boardId":10,"replyContent":"test","memberId":"test","replyDateCreated":"2023-09-22 11:00:42.0"},{"replyId":1,"boardId":10,"replyContent":"test","memberId":"test","replyDateCreated":"2023-09-22 10:59:40.0"}] */
								
								var replyDateCreated = new Date(this.replyDateCreated);
								var disabled = 'disabled';
								var readonly = 'readonly';
								
								if(memberId == this.memberId) { // memberId : 로그인한 id | this.memberId : 댓 작성자
									disabled = '';
									readonly = '';
								}
								
								list += '<div class="reply_item">' 
									+ '<pre>'
									/* + this.replyId // 댓글 번호 */
									+ '<input type="hidden" id="replyId" value="' + this.replyId + '">'
									+ this.memberId
									+ '&nbsp;&nbsp;' // 공백
									+ '<input type="text" ' + readonly + ' id="replyContent" value="' + this.replyContent + '">'
									+ '&nbsp;&nbsp;' // 공백
									+ replyDateCreated // 위에서 선언해서 this 안함
									+ '&nbsp;&nbsp;' // 공백
									+ '<button class="btn_update" ' + disabled + '>수정</button>'
									+ '<button class="btn_delete" ' + disabled + '>삭제</button>'
									+ '</pre>'
									+ '</div>';
							}); // end each()
							
							$('#replies').html(list);
							
						}
				); // end getJSON()
				
			} // end getAllReplies()
			
			// 수정 버튼을 클릭하면 선택된 댓글 수정 ******************* 매우 중요
			$('#replies').on('click', '.reply_item .btn_update', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이번에 있는 모든 형제 노드를 접근
				var replyId = $(this).prevAll('#replyId').val();
				var replyContent = $(this).prevAll('#replyContent').val();
				console.log("선택된 댓글 번호 : " + replyId + ", 댓글 내용 : " + replyContent);
				
				// ajax 요청
				$.ajax({
					type : 'POST',
					url : 'replies/update',
					data : {
						'replyId' : replyId,
						'replyContent' : replyContent
					},
					success : function(result){
						console.log(result);
						if(result == 'success'){
							alert('댓글 수정 성공');
							getAllReplies();
						}
					}
				});
				
			}); // end replies.on() div id가 replies 인
			
			// 삭제 버튼을 클릭하면 선태고딘 댓글 삭제
			$('#replies').on('click', '.reply_item .btn_delete', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId값을 저장
				// prevAll() : 선택된 노드 이번에 있는 모든 형제 노드를 접근
				var replyId = $(this).prevAll('#replyId').val();
				console.log("선택된 댓글 번호 : " + replyId);
				
				// ajax 요청
				$.ajax({
					type : 'POST',
					url : 'replies/delete',
					data : {
						'replyId' : replyId
					},
					success : function(result){
						console.log(result);
						if(result == 'success'){
							alert('댓글 삭제 성공');
							getAllReplies();
						}
					}
				});
				
			});
			
			
		}); // end document
	</script>
</body>
</html>









