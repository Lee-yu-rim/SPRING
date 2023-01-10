<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.uploadResult {
	   width: 100%;
	   background-color: gray;
	}
	
	.uploadResult ul {
	   display: flex;
	   flex-flow: row;
	   justify-content: center;
	   align-items: center;
	}
	
	.uploadResult ul li {
	   list-style: none;
	   padding: 10px;
	}
	
	.uploadResult ul li img {
	   width: 100px;
	}
	</style>
	
	<style>
	.bigPictureWrapper {
	  position: absolute;
	  display: none;
	  justify-content: center;
	  align-items: center;
	  top:0%;
	  width:100%;
	  height:100%;
	  background-color: gray; 
	  z-index: 100;
	}
	
	.bigPicture {
	  position: relative;
	  display:flex;
	  justify-content: center;
	  align-items: center;
	}
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>
	</div>

	<div class='bigPictureWrapper'>
		<div class='bigPicture'>
		
		</div>
	</div>
	
	<button id='uploadBtn'>Upload</button>
	
	<div class='uploadResult'>
		<ul>
			
		</ul>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script>
	
	//이미지 섬네일 누르면 원본이미지 보여주기
	function showImage(fileCallPath){
		//alert(fileCallPath);
		$(".bigPictureWrapper").css("display","flex").show();
		
		$(".bigPicture")
		.html("<img src='/display?fileName=" + fileCallPath + "'>")
		.animate({width:'100%', height:'100%'}, 1000);
	}
	
	$(".bigPictureWrapper").on("click", function(e) {
		$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
		setTimeout(() => {
			$(this).hide();
		}, 1000);
	});
	
	//x 버튼 누르면 파일 삭제하기
	$(".uploadResult").on("click","span", function(e){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName: targetFile, type:type},
			dataType: 'text',
			type: 'POST',
			success: function(result){
				alert(result);
			}
		}); //$.ajax
	});
	
		$(function(){
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");  //정규 표현식 정의 - 업로드 불가 파일 확장자 정의
			var maxSize = 5242880; //5MB  파일 최대 크기
			
			//업로드 파일의 확장자와 크기를 확인하는 메소드 생성
			function checkExtension(fileName, fileSize){ 
				
				if(fileSize >= maxSize){0
					alert("파일 사이즈 초과");
					return false;
				}
				
				if(regex.test(fileName)){ //regex에 있는 확장자가 포함된 fileName은 업로드 불가
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			var cloneObj = $(".uploadDiv").clone();  //clone - 기존 요소를 복제할 때 사용
			
			//upload 버튼 누르면 파일 업로드되는 이벤트
			$("#uploadBtn").on("click", function(e){
				
				var formData = new FormData();  //jquery에서 파일업로드를 하는 방법 -> FormData 객체 이용(가상의 form 태그)
				
				var inputFile = $("input[name='uploadFile']"); //동적으로 파일을 업로드
				
				var files = inputFile[0].files;
				
				console.log(files);
				
				//서버에 파일 업로드하는 기능 추가하기
				for(var i=0; i < files.length; i++) {
					
					if(!checkExtension(files[i].name, files[i].size)){  //올려진 파일들을 checkExtension 메소드를 통해 사이즈와 확장자 확인
						return false;
					}
					
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,	//파라미터 값이 넘어갈 때 String 타입으로 넘어가지 않도록 false 로 설정
					contentType: false, //multipartform-data 기본 타입으로 데이터 형식을 지정하도록 false 로 설정
					data: formData,
					type: 'POST',
					dataType: 'json',
					success: function(result){
						console.log(result);
						
						showUploadedFile(result);
						
						//첨부파일 추가한 뒤에 "선택된 파일 없음" 이라고 뜨게 해주기(초기화)
						$(".uploadDiv").html(cloneObj.html());
					}
				}); //$.ajax
			});
			
			
			//업로드된 파일 보여주기
			var uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr) {
				 var str = "";
				 
				 $(uploadResultArr).each(function(i, obj) { //each - 스크립트에서 쓰는 반복문
					 
					 if(!obj.image){  //이미지 파일이 아닌 일반 파일일 경우
						var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					 	var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					 
						str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>"
								+ "<img src='/resources/img/attach.png'>" + obj.fileName + "</a>" 
								+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </<span>" 
								+ "<div></li>";
					 }else {  //이미지 파일인 경우
						var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
					 	var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;  //s_ 제거한 원본파일명
					 	originPath = originPath.replace(new RegExp(/\\/g), "/");
					 	
						str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">" + 
							   "<img src='/display?fileName=" + fileCallPath + "'></a>" +
							   "<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span>" + 
							   "<li>";
					 } //encodeURIComponent - 파일 이름에 공백이나 한글이 문제되지 않도록 인코딩 처리
				 });
				 
				 uploadResult.append(str);
			}
			
			
		});
	</script>
</body>
</html>