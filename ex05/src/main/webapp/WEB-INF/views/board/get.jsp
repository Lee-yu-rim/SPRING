<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!--  ./panel-heading  -->
			<div class="panel-body">
				<div class = "form-group">
					<label>Bno</label>
					<input class="form-control" name='bno' value='<c:out value = "${board.bno }"/>' readonly="readonly">
				</div>
				<div class = "form-group">
					<label>Title</label>
					<input class="form-control" name='title' value='<c:out value = "${board.title }"/>' readonly="readonly">
				</div>
				<div class = "form-group">
					<label>Text Area</label>
					<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value = "${board.content }" /> </textarea>
				</div>
				<div class = "form-group">
					<label>Writer</label>
					<input class="form-control" name='writer' value='<c:out value = "${board.writer }"/>' readonly="readonly">
				</div>
				<%--<button data-oper ='modify' class="btn btn-default" onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">Modify</button>
				<button data-oper ='list' class="btn btn-info" onclick="location.href ='/board/list'">List</button> --%>
				
				<button data-oper ='modify' class="btn btn-default">Modify</button>
				<button data-oper ='list' class="btn btn-info">List</button>
				
				<form id='operForm' action = "/board/modify" method="get">
					<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
					<input type='hidden' name='pageNum' value = '<c:out value="${cri.pageNum }"/>'>
					<input type='hidden' name='amount' value = '<c:out value="${cri.amount }"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}" />'>
					<input type='hidden' name='type' value='<c:out value="${cri.type}" />'>
				</form>
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	
	</div>
</div>

<style>
	.uploadResult {
	  width:100%;
	  background-color: gray;
	}
	.uploadResult ul{
	  display:flex;
	  flex-flow: row;
	  justify-content: center;
	  align-items: center;
	}
	.uploadResult ul li {
	  list-style: none;
	  padding: 10px;
	  align-content: center;
	  text-align: center;
	}
	.uploadResult ul li img{
	  width: 100px;
	}
	.uploadResult ul li span {
	  color:white;
	}
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
	  background:rgba(255,255,255,0.5);
	}
	.bigPicture {
	  position: relative;
	  display:flex;
	  justify-content: center;
	  align-items: center;
	}
	
	.bigPicture img {
	  width:600px;
	}
</style>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Files</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				
				<div class='uploadResult'>
					<ul></ul>
				</div>
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->




<div class='row'>
	<div class="col-lg-12">
	
		<!-- /.panel -->
		<div class="panel panel-default">
<!-- 			<div class="panel-heading"> -->
<!-- 				<i class="fa fa-comments fa-fw"></i>Reply -->
<!-- 			</div> -->
		
		<div class="panel-heading">
			<i class="fa fa-comments fa-fw"></i>Reply
			<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
		</div>
			
			<!-- /.panel-heading -->
			<div class="panel-body">
				<ul class="chat">
				
				</ul>
				<!-- ./ end ul -->
			</div>
			<!-- .panel .chat-panel -->
		</div>
	</div>
	<!-- ./ end row -->
</div>

<!-- Modal start-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
              </div>
              <div class="modal-body">
	              <div class="form-group">
	                <label>Reply</label> 
	                <input class="form-control" name='reply' value='New Reply!!!!'>
	              </div>      
	              <div class="form-group">
	                <label>Replyer</label> 
	                <input class="form-control" name='replyer' value='replyer'>
	              </div>
	              <div class="form-group">
	                <label>Reply Date</label> 
	                <input class="form-control" name='replyDate' value=''>
	              </div>
              </div>
			  <div class="modal-footer">
			        <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
			        <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
			        <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
			        <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			  </div>          
			</div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
     </div>
<!-- Modal end-->


<script type="text/javascript" src="/resources/js/reply.js"></script>


<script>

	//댓글 목록 보이기
	$(function(){	
		var bnoValue = '<c:out value="${board.bno}"/>';
  		var replyUL = $(".chat");
  
    	showList(1);
    	
    	function showList(page){
    		replyService.getList({bno:bnoValue,page: page || 1 }, function(list) {
    			 var str="";
     
			     if(list == null || list.length == 0){
			       return;
			     }
			     
			     for (var i = 0, len = list.length || 0; i < len; i++) {
			         str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
			         str +="  <div><div class='header'><strong class='primary-font'>["
			    	   +list[i].rno+"] "+list[i].replyer+"</strong>"; 
			         str +="    <small class='pull-right text-muted'>"
			           +replyService.displayTime(list[i].replyDate)+"</small></div>";
			         str +="    <p>"+list[i].reply+"</p></div></li>";
     			 }
     
    			 replyUL.html(str);
			     
    		});  //end function
    	} //end showList 
		
		//댓글 등록 시 모달창 띄우기
		var modal = $(".modal");
        var modalInputReply = modal.find("input[name='reply']");
        var modalInputReplyer = modal.find("input[name='replyer']");
        var modalInputReplyDate = modal.find("input[name='replyDate']");
        
        var modalModBtn = $("#modalModBtn");
        var modalRemoveBtn = $("#modalRemoveBtn");
        var modalRegisterBtn = $("#modalRegisterBtn");
        
        $("#modalCloseBtn").on("click", function(e){
        	
        	modal.modal('hide');
        });
        
        $("#addReplyBtn").on("click", function(e){
          
          modal.find("input").val("");
          modalInputReplyDate.closest("div").hide();
          modal.find("button[id !='modalCloseBtn']").hide();
          
          modalRegisterBtn.show();
          
          $(".modal").modal("show");
          
        });
		
		//댓글 등록하기
		modalRegisterBtn.on("click",function(e){
            
            var reply = {
                  reply: modalInputReply.val(),
                  replyer:modalInputReplyer.val(),
                  bno:bnoValue
                };
            replyService.add(reply, function(result){
              
              alert(result);
              
              modal.find("input").val("");
              modal.modal("hide");
              
              showList(1);
            
         	});
		});
		
		//댓글 클릭 시 상세보기
		$(".chat").on("click", "li", function(e){
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
				modal.data("rno",reply.rno);
				
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			});
		});
		
		//댓글 수정하기
		modalModBtn.on("click", function(e){
			var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		
		//댓글 삭제하기
		modalRemoveBtn.on("click", function(e){
			var rno = modal.data("rno");
			
			replyService.remove(rno, function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		
		
	});
</script>


<script>

	console.log("==================");
	console.log("JS TEST");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	//ajax 통신 방법을 이용한 댓글 추가하기
// 	replyService.add(
// 		{reply:"JS TEST", replyer:"tester", bno:bnoValue},  //reply.js 파일의 add() 함수의 첫번째 매개변수의 값으로 전달됨
// 		function(result){ 	//reply.js 파일의 add() 함수의 두번째 매개변수 값으로 전달
// 			alert("RESULT: " + result);    
// 		}
// 	);

	
	//ajax 통신 방법을 이용한 댓글 목록 조회하기
// 	replyService.getList({bno:bnoValue, page:1}, function(list){
// 		for(var i = 0, len = list.length||0; i < len; i++){
// 			console.log(list[i]);
// 		}
// 	});
	
	
	//ajax 통신 방법을 이용한 댓글 삭제하기
// 	replyService.remove(23, function(count) {
// 		console.log(count);
		
// 		if(count == "success") {
// 			alert("REMOVED");
// 		}
// 	}, function(err)	{
// 		alert('ERROR...');
// 	});
	
	
	//ajax 통신 방법을 이용한 댓글 수정하기
// 	replyService.update({
// 		rno: 9,
// 		bno: bnoValue,
// 		reply: "Modified Reply....."
// 	}, function(result) {
// 		alert("수정 완료....");
// 	});
	
	//ajax 통신 방법을 이용한 댓글 조회하기
// 	replyService.get(12, function(data){
// 		console.log(data);
// 	})
	
	
</script>


<script>
	$(function(){	
		
		var operForm = $("#operForm"); 
		  
		  $("button[data-oper='modify']").on("click", function(e){
		    
		    operForm.attr("action","/board/modify").submit();
		    
		  });
		  
		    
		  $("button[data-oper='list']").on("click", function(e){
		    
		    operForm.find("#bno").remove();
		    operForm.attr("action","/board/list")
		    operForm.submit();
		    
		  });  
		
	});
</script>

<script>
//첨부파일 목록 가져오기
$(document).ready(function(){
	(function(){
		var bno = '<c:out value="${board.bno}"/>';
		
		$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){  
					var fileCallPath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);
				 
					str += "<li data-path='" + attach.uploadPath + "' data-uuid='" 
						+ attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
					str += "<img src='/display?fileName=" + fileCallPath + "'>";
					str += "</div>";
					str += "</li>";
				 }else {  
					str += "<li data-path='" + attach.uploadPath + "' data-uuid='" 
						+ attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
					str += "<span>" + attach.fileName + "</span><br/>";
					str += "<img src='/resources/img/attach.png'>";
					str += "</li>";
				 } 
			});
			
			$(".uploadResult ul").html(str);
			
		});//end getJSON
		
	})(); //end function
	
	
	$(".uploadResult").on("click", "li", function(e){
		console.log("view image");
		var liObj = $(this);
		var path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_" + liObj.data("filename"));
		
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\/g), "/"));
		}else{
			//download
			self.location = "/download?fileName=" + path
		}
	});
	
	function showImage(fileCallPath){
		alert(fileCallPath);
		
		$(".bigPictureWrapper").css("display","flex").show();
		
		$(".bigPicture")
		.html("<img src='/display?fileName=" + fileCallPath + "'>")
		.animate({width:'100%', height:'100%'}, 1000);
	}
	
	$(".bigPictureWrapper").on("click", function(e) {
		$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
		setTimeout(function(){
			$('.bigPictureWrapper').hide();
		}, 1000);
	});
	
});
	
	
</script>

<%@ include file="../includes/footer.jsp" %>