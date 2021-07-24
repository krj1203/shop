<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<html>
<head>
	<title>��ü �Խñ�</title>
		
	<script type="text/javascript">
		function writeForm(){
			location.href="BoardWriteForm.bo";
		}
	</script>
	
</head>
<body>
<div id="wrap">
	
	<c:if test="${sessionScope.sessionID == null}">
			<script type="text/javascript">alert("�α����� ���ۼ��� �����մϴ�.")</script>
	</c:if>	
	
	
	<!-- �۸�� �� �κ�-->
	<div id="topForm">
		<c:if test="${sessionScope.sessionID!=null}">
			<input type="button" value="�۾���" onclick="writeForm()">
		</c:if>	
	</div>
	<br>
	<!-- �Խñ� ��� �κ� -->
	<div id="board">
		<table id="bList" width="800" border="3" bordercolor="lightgray">
			<tr heigh="30">
				<td>�۹�ȣ</td>
				<td>����</td>
				<td>�ۼ���</td>
				<td>�ۼ���</td>
				<td>��ȸ��</td>
			</tr>
		<c:forEach var="board" items="${requestScope.list}">
			<tr>
				<td>${board.board_num}</td>
				
				<td align="left">
					<c:if test="${board.board_re_lev > 1}">
						<c:forEach begin="1" end="${board.board_re_lev}">
							&nbsp; <!-- �亯���ϰ�� �� ���� �տ� ������ �ش�. -->
						</c:forEach>
						RE:
					</c:if>
					<a href="BoardDetailAction.bo?num=${board.board_num}&page=${spage}">
					${board.board_subject}
					</a>
				</td>
				
				<td>${board.board_id}</td>
				<td>${board.board_date}</td>
				<td>${board.board_count}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	
	<!-- ������ �ѹ� �κ� -->
	<br>
	<div id="pageForm">
	
		<c:if test="${startPage != 1}">
			<a href='BoardListAction.bo?page=${startPage-1}'>[����]</a>
		</c:if>
		
		<c:forEach var="pageNum" begin="${startpage=1}" end="${endPage}"><!-- ��������ȣ -->
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage}">
				<a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a> <!-- �Խ��� �������̵� -->
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage != maxPage }">
			<a href='BoardListAction.bo?page=${endPage+1 }'>[����]</a>
		</c:if>
	</div>
	
	<!--  �˻� �κ� -->
	<br>
	<div id="searchForm">
		<form>
			<select name="opt">
				<option value="0">����</option>
				<option value="1">����</option>
				<option value="2">����+����</option>
				<option value="3">�۾���</option>
			</select>
			<br>
			<input type="text" size="20" name="condition"/>&nbsp;
			<br>			
			<input type="submit" value="�˻�"/>
		</form>	
	</div>
</div>	

</body>
</html>