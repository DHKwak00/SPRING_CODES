<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.boardTitle }</title>
</head>
<body>
	<h2>글 수정 페이지</h2>
	<form action="update.do" method="post">
		<div>
			<p>글 번호 : ${vo.boardId }</p>
			<input type="hidden" name="boardId" value="${vo.boardId }"><!-- 보내때 필요는 한데 보이기 싫음 "hidden" -->
		</div>
		<div>
			<p>제목 : </p>
			<input type="text" name="boardTitle" value="${vo.boardTitle }">
		</div>
		<div>
			<p>작성자 : ${vo.memberId }</p> <!-- 왜 hidden 안했나? query ? 확인 -->
			<p>작성일 : ${vo.boardDateCreated }</p>
		</div>
		<div>
			<textarea rows="20" cols="120" name="boardContent">${vo.boardContent }</textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>