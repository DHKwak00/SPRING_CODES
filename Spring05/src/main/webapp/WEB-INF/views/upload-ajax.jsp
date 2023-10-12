<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Ajax</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style type="text/css">
.file-drop {
	width : 100;
	height : 100px;
	border : 1px solid grey;
}
</style>
</head>
<body>
	<h1>Ajax를 이용한 파일 업로드</h1>
	<div class="file-drop"></div>
	<div class="upload-list"></div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			// 파일 끌어다 놓을 때(drag&drop)
			// 브라우저가 파일을 자동으로 열어주는 기능을 막음
			$('.file-drop').on('dragenter dragover', function(event){
				event.preventDefault();
				console.log('drag 테스트');
			});
			
			$('.file-drop').on('drop', function(event) {
				event.preventDefault();
				console.log('drop 테스트');
				
				// Ajax를 이용하여 서버로 파일 업로드
				// multipart/form-data 타입으로 파일을 업로드하는 객체
				var formData = new FormData();
				
				// 드래그한 파일 정보를 갖고 있는 객체
				var files = event.originalEvent.dataTransfer.files; // 자바스크립트 파일 드래그 객체 가져오기
				
				var i = 0;
				for(i=0; i<files.length; i++){
					console.log(files[i]);
					formData.append("files", files[i]);
				}
				
				$.ajax({
					type : 'post',
					url : '/ex05/upload-ajax',
					data : formData,
					processData : false,
					contentType : false,
					success : function(data) {
						console.log(data);
						
						var str = '';
						str += '<div>'
							+ '<img width="100px" height="100px" src="display?fileName='
							+ data
							+ '" />'
							+ '</div>';
						$('.upload-list').html(str);
					}
				});
				
			});
			
		}); // end document
	</script>
	
</body>
</html>







