<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
<head>
    <title>��� ����</title>   
    <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <style type="text/css">
    	@import url(css/button.css);
    
        #wrap{
            text-align: center;
            width: 650px;
            height: 100px;
        }      
        .container{
         text-align: center;
        }
        
    </style>
    
    <script type="text/javascript">
        
        function changeView(value){
            
            if(value == "0") // HOME ��ư Ŭ���� ùȭ������ �̵�
            {
                location.href="MainForm.do";
            }
            else if(value == "1") // �α��� ��ư Ŭ���� �α��� ȭ������ �̵�
            {
                location.href="LoginForm.do";
            }
            else if(value == "2") // ȸ������ ��ư Ŭ���� ȸ������ ȭ������ �̵�
            {
                location.href="member/JoinForm.jsp";
            }
            else if(value == "3") // �α׾ƿ� ��ư Ŭ���� �α׾ƿ� ó��
            {
                location.href="MemberLogoutAction.do";
            }
            else if(value == "4") // ������ ��ư Ŭ���� ȸ������ �����ִ� ȭ������ �̵�
            {
                location.href="MemberInfoAction.do";
            }
            else if(value == "5")
            {
                location.href="MemberListAction.do";
            }
            else if(value == "6")
            {
                location.href="BoardListAction.bo";
            }
            else if(value == "7")
            {
                location.href="selectAllproduct.pr";
            }
            else if(value == "8")
            {
                location.href="basketProduct.jsp";
            }
            
        }
    </script>
    
</head>
<body>
    <div class="container">
    <%
		if(session.getAttribute("sessionID") == null) // �α����� �ȵǾ��� ��
		{ 
			
		}else{ // �α��� ���� ���	
	%>
			<br><br>						
			<font size=5 color="skyblue"><%=session.getAttribute("sessionID") %></font>
			<font size=5>�� ȯ���մϴ�.</font>
	<%	} %>
		<p>       
    </div>
</body>
</html>