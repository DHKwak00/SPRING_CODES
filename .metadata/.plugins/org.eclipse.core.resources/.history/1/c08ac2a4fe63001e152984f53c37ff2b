<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	<h2>글 작성 페이지</h2>
	<form action="register.do" method="post">
		<div>
			<p>제목 : </p>
			<input type="text" name="boardTitle" placeholder="제목 입력" required="required"> <!-- required는 value가 있으면 안넣어도 된다. -->
		</div>
		<div>
			<p>작성자 : </p>
			<input type="text" name="memberId" required="required" value="${sessionScope.memberId} " readonly="readonly">
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="boardContent" placeholder="내용 입력"></textarea> <!-- name을 VO 변수명과 똑같이 한다. (중요) -->
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>