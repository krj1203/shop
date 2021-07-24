<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<html>
<head>
	<title>�� �󼼺���</title>
	
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
			font-family :'����';
			font-size : 12;
			text-align :center;
			background-color: #F7F7F7;
		}
		
		#btn{
			font-family :'����';
			font-size : 14;
			text-align :center;
		}

	</style>
	
	<script type="text/javascript">
		function change(value)
		{
			if(value == 0)	//���
				location.href='BoardListAction.bo?page=${pageNum}';
			else if(value == 1)//���
				location.href='BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}';
				//���� �Խñ� ��ȣ, ������ ��ȣ�� ���� �����Ѵ�.
		}
		
		function doAction(value)
		{
			if(value == 0) // ����
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			else if(value == 1) // ����
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
					
		//��� ���	
		function writeCmt() {
			
			var form = document.getElementById("writeCommentForm");
			
			var board = form.comment_board.value
			var id = form.comment_id.value
			var content = form.comment_content.value;
			
			if(!content)
			{
				alert("������ �Է��ϼ���.");
				return false;
			}
						
			var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
			
		    $.ajax({
			    url : "CommentWriteAction.co",	
				type : "post",
				data : param,
				success : function(data){		       
					if(data == 1){						
						location.reload(); // �󼼺��� â ���ΰ�ħ
					}
		        },
		        error : function(){
		            alert("��Ž���");
		        }  
			})
		}
		
		// ��� �亯â
		function cmReplyOpen(comment_num){
			var userId = '${sessionScope.sessionID}';
			
			if(userId == "" || userId == null){
				alert("�α����� ��밡���մϴ�.");
				return false;
			}
			else{
				// ��� �亯â open
				window.name = "parentForm";
				window.open("CommentReplyFormAction.co?num="+comment_num,
							"replyForm", "width=570, height=350, resizable = no, scrollbars = no");
			}
		}
		
		// ��� ����â
		function cmDeleteOpen(comment_num){
			var msg = confirm("����� �����մϴ�.");	
			if(msg == true){ // Ȯ���� �������
				deleteCmt(comment_num)
			}
			else{
				return false; // �������
			}
		}
					
		//��� ����	
		function deleteCmt(comment_num) {
			
			var param="comment_num="+comment_num;
											
		    $.ajax({
			    url : "CommentDeleteAction.co",	
				type : "post",
				data : param,
				success : function(data){		       
					if(data == 1){						
						location.reload(); // �󼼺��� â ���ΰ�ħ
					}
		        },
		        error : function(){
		            alert("��Ž���");
		        }  
			})
		}
		
		// ��� ����â
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
				<td>�ۼ���</td>
				<td>${board.board_date}</td>
			</tr>
			<tr>
				<td>�ۼ���</td>
				<td>${board.board_id}</td>
			</tr>
			<tr>
				<td>�� ��</td>
				<td>
					${board.board_subject}
				</td>		
			</tr>
			<tr>
				<td>�� ��</td>
				<td>
					${board.board_content}
				</td>		
			</tr>
			<tr>
				<td>÷������</td>
				<td>
					<a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a>
				</td>	
			</tr>
	
			<tr align="center" valign="middle">
				<td colspan="5">
				<c:if test="${sessionScope.sessionID !=null}">
					<c:if test="${sessionScope.sessionID == board.board_id}">
						<input type="button" value="����" onclick="doAction(0)">
						<input type="button" value="����" onclick="doAction(1)">
					</c:if>
						<input type="button" value="���" onclick="change(1)" >
				</c:if>		
					<input type="button" value="���" onclick="change(0)">			
				</td> <!-- javascript:location.href='BoardListAction.bo?page=${pageNum}' -->
			</tr>
		</table>
	</div>
	<br><br>
	
	<!-- ��� �κ� -->
	<div id="comment">
		<table border="1" bordercolor="lightgray">
	<!-- ��� ��� -->	
	<c:if test="${requestScope.commentList != null}">
		<c:forEach var="comment" items="${requestScope.commentList}">
			<tr>
				<!-- ���̵�, �ۼ���¥ -->
				<td width="150">
					<div>
						<c:if test="${comment.comment_level > 1}">
							&nbsp;&nbsp; <!-- �亯���ϰ�� ���̵� �տ� ������ �ش�. -->
							RE:
						</c:if>				
						${comment.comment_id}<br>
						
						<font size="2" color="lightgray">${comment.comment_date}</font>
					</div>
				</td>
				<!-- �������� -->
				<td width="550">
					<div class="text_wrapper">
						${comment.comment_content}
					</div>
				</td>
				<!-- ��ư -->
				<td width="100">
					<div id="btn" style="text-align:center;">
						<a href="#" onclick="cmReplyOpen(${comment.comment_num})">[�亯]</a><br>
					<!-- ��� �ۼ��ڸ� ����, ���� �����ϵ��� -->	
					<c:if test="${comment.comment_id == sessionScope.sessionID}">
						<a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[����]</a><br>	
						<a href="#" onclick="cmDeleteOpen(${comment.comment_num})">[����]</a>
					</c:if>		
					</div>
				</td>
			</tr>			
		</c:forEach>
	</c:if>
			
			<!-- �α��� ���� ��츸 ��� �ۼ����� -->
			<c:if test="${sessionScope.sessionID !=null}">
			<tr bgcolor="#F5F5F5">
			<form id="writeCommentForm">
				<input type="hidden" name="comment_board" value="${board.board_num}">
				<input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
				<!-- ���̵�-->
				<td width="150">
					<div><font color="black">
						${sessionScope.sessionID}</font>
					</div>
				</td>
				<!-- ���� �ۼ�-->
				<td width="550">
					<div>
						<textarea name="comment_content" rows="2" cols="70" ></textarea>
					</div>
				</td>
				<!-- ��� ��� ��ư -->
				<td width="100">
					<div id="btn" style="text-align:center;">					
						<p><a href="#" id="insert" onclick="writeCmt()"><font color="black">[��۵��]</font></a></p>	
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