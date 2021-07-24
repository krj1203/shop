<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<html>
<head>
	<title>글 상세보기</title>
	
	<style type="text/css">
		#wrap {
			width: 800px;
			margin: 0 auto 0 auto;	
		}
	
		#detailBoard{
			text-align :center;
		}
		
		#title{
			height : 16;
			font-family :'돋움';
			font-size : 12;
			text-align :center;
			background-color: #F7F7F7;
		}
		
		#btn{
			font-family :'돋움';
			font-size : 14;
			text-align :center;
		}

	</style>
	
	<script type="text/javascript">
		function change(value)
		{
			if(value == 0)	//목록
				location.href='BoardListAction.bo?page=${pageNum}';
			else if(value == 1)//답글
				location.href='BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}';
				//현재 게시글 번호, 페이지 번호를 같이 전송한다.
		}
		
		function doAction(value)
		{
			if(value == 0) // 수정
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			else if(value == 1) // 삭제
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
					
		//댓글 등록	
		function writeCmt() {
			
			var form = document.getElementById("writeCommentForm");
			
			var board = form.comment_board.value
			var id = form.comment_id.value
			var content = form.comment_content.value;
			
			if(!content)
			{
				alert("내용을 입력하세요.");
				return false;
			}
						
			var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
			
		    $.ajax({
			    url : "CommentWriteAction.co",	
				type : "post",
				data : param,
				success : function(data){		       
					if(data == 1){						
						location.reload(); // 상세보기 창 새로고침
					}
		        },
		        error : function(){
		            alert("통신실패");
		        }  
			})
		}
		
		// 댓글 답변창
		function cmReplyOpen(comment_num){
			var userId = '${sessionScope.sessionID}';
			
			if(userId == "" || userId == null){
				alert("로그인후 사용가능합니다.");
				return false;
			}
			else{
				// 댓글 답변창 open
				window.name = "parentForm";
				window.open("CommentReplyFormAction.co?num="+comment_num,
							"replyForm", "width=570, height=350, resizable = no, scrollbars = no");
			}
		}
		
		// 댓글 삭제창
		function cmDeleteOpen(comment_num){
			var msg = confirm("댓글을 삭제합니다.");	
			if(msg == true){ // 확인을 누를경우
				deleteCmt(comment_num)
			}
			else{
				return false; // 삭제취소
			}
		}
					
		//댓글 삭제	
		function deleteCmt(comment_num) {
			
			var param="comment_num="+comment_num;
											
		    $.ajax({
			    url : "CommentDeleteAction.co",	
				type : "post",
				data : param,
				success : function(data){		       
					if(data == 1){						
						location.reload(); // 상세보기 창 새로고침
					}
		        },
		        error : function(){
		            alert("통신실패");
		        }  
			})
		}
		
		// 댓글 수정창
		function cmUpdateOpen(comment_num){
			window.name = "parentForm";
			window.open("CommentUpdateFormAction.co?num="+comment_num,
						"updateForm", "width=570, height=350, resizable = no, scrollbars = no");
		}
		
	</script>
</head>
<body>

<div id="wrap">
	<br><br>
	<div id="board">
		<table id="detailBoard" width="800" border="3" bordercolor="lightgray">
		
			<tr>
				<td>작성일</td>
				<td>${board.board_date}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.board_id}</td>
			</tr>
			<tr>
				<td>제 목</td>
				<td>
					${board.board_subject}
				</td>		
			</tr>
			<tr>
				<td>내 용</td>
				<td>
					${board.board_content}
				</td>		
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a>
				</td>	
			</tr>
	
			<tr align="center" valign="middle">
				<td colspan="5">
				<c:if test="${sessionScope.sessionID !=null}">
					<c:if test="${sessionScope.sessionID == board.board_id}">
						<input type="button" value="수정" onclick="doAction(0)">
						<input type="button" value="삭제" onclick="doAction(1)">
					</c:if>
						<input type="button" value="답글" onclick="change(1)" >
				</c:if>		
					<input type="button" value="목록" onclick="change(0)">			
				</td> <!-- javascript:location.href='BoardListAction.bo?page=${pageNum}' -->
			</tr>
		</table>
	</div>
	<br><br>
	
	<!-- 댓글 부분 -->
	<div id="comment">
		<table border="1" bordercolor="lightgray">
	<!-- 댓글 목록 -->	
	<c:if test="${requestScope.commentList != null}">
		<c:forEach var="comment" items="${requestScope.commentList}">
			<tr>
				<!-- 아이디, 작성날짜 -->
				<td width="150">
					<div>
						<c:if test="${comment.comment_level > 1}">
							&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
							RE:
						</c:if>				
						${comment.comment_id}<br>
						
						<font size="2" color="lightgray">${comment.comment_date}</font>
					</div>
				</td>
				<!-- 본문내용 -->
				<td width="550">
					<div class="text_wrapper">
						${comment.comment_content}
					</div>
				</td>
				<!-- 버튼 -->
				<td width="100">
					<div id="btn" style="text-align:center;">
						<a href="#" onclick="cmReplyOpen(${comment.comment_num})">[답변]</a><br>
					<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->	
					<c:if test="${comment.comment_id == sessionScope.sessionID}">
						<a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[수정]</a><br>	
						<a href="#" onclick="cmDeleteOpen(${comment.comment_num})">[삭제]</a>
					</c:if>		
					</div>
				</td>
			</tr>			
		</c:forEach>
	</c:if>
			
			<!-- 로그인 했을 경우만 댓글 작성가능 -->
			<c:if test="${sessionScope.sessionID !=null}">
			<tr bgcolor="#F5F5F5">
			<form id="writeCommentForm">
				<input type="hidden" name="comment_board" value="${board.board_num}">
				<input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
				<!-- 아이디-->
				<td width="150">
					<div><font color="black">
						${sessionScope.sessionID}</font>
					</div>
				</td>
				<!-- 본문 작성-->
				<td width="550">
					<div>
						<textarea name="comment_content" rows="2" cols="70" ></textarea>
					</div>
				</td>
				<!-- 댓글 등록 버튼 -->
				<td width="100">
					<div id="btn" style="text-align:center;">					
						<p><a href="#" id="insert" onclick="writeCmt()"><font color="black">[댓글등록]</font></a></p>	
					</div>
				</td>
			</form>
			</tr>
			</c:if>	
		</table>
	</div>
</div>



</body>
</html>
