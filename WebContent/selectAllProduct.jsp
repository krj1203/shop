<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<html>
    <TITLE>selectAllProduct</TITLE>
     <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <style type="text/css">
		#wrap {
			width: 600px;
			margin: 0 auto 0 auto;
		}

		#topForm{
			text-align :right;
		}

		#board, #pageForm, #searchForm{
			text-align :center;
		}
		
		#bList{
			text-align :center;
		}
		TABLE{
			margin: 0 auto 0 auto;
		}
	</style>
    <body>  							 
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>               
            <TR VALIGN=\'top\'>
                <TD WIDTH='480' ALIGN="center"> 
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in;
                    margin-bottom: 0.1in; margin-right: 0.1in'>
                    	<br><br><br>                                                
                        <P><FONT color=#0000ff face=���� size=3><CENTER><STRONG>[��ǰ ����Ʈ����]</STRONG><CENTER></FONT></P> 
                        
                        
                        <CENTER>
                        <TABLE border=0 width=640 cellpadding=3 cellspacing=3 style="font-size:15pt">
                            <TR>
                                <TH width=10% bgcolor=#287fb5> <FONT color=white face="����"> <NOBR>��ȣ</NOBR></FONT></TH>
                                <TH width=10% bgcolor=#287fb5> <FONT color=white face="����"> <NOBR>��ǰ��</NOBR></FONT></TH>
                                <TH width=10% bgcolor=#287fb5> <FONT color=white face="����"> <NOBR>�ǸŰ���</NOBR></FONT></TH>
                                <TH width=15% bgcolor=#287fb5> <FONT color=white face="����"> <NOBR>�̹���</NOBR></FONT></TH>
                                <TH width=10% bgcolor=#287fb5> <FONT color=white face="����"> <NOBR>����ȸ��</NOBR></FONT></TH>
                            </TR>
                            
                           
                          <c:forEach var="product" items="${requestScope.list}">      		
                            <tr align="left">
                                <TD width=10% >
                                    ${product.seq}
                                </TD>
                                
                                <TD> 
                                    <a href='selectproduct.pr?productId=${product.productId}'>
                                    ${product.productName}</a>
                                </TD>
                                <TD>
                                    ${product.price2S}
                                </TD>												
                                <TD><a href='selectproduct.pr?productId=${product.productId}'>
                                    <img border="0" src="images/${product.photoDir}" width="150" height="80"></a>
                                </TD>
                                <TD>
                                    ${product.company}
                                </TD>
                            </tr>
                            </c:forEach> 
                        </table>  
                        
    <!-- ������ �ѹ� �κ� -->
	<br>
	<div id="pageForm">
	
		<c:if test="${startPage != 1}">
			<a href='selectAllproduct.pr?page=${startPage-1}'>[����]</a>
		</c:if>
		
		<c:forEach var="pageNum" begin="${startpage=1}" end="${endPage}"><!-- ��������ȣ -->
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage}">
				<a href='selectAllproduct.pr?page=${pageNum}'>${pageNum}&nbsp;</a> <!-- �Խ��� �������̵� -->
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage != maxPage }">
			<a href='selectAllproduct.pr?page=${endPage+1 }'>[����]</a>
		</c:if>
	</div>
	
	<!--  �˻� �κ� -->
	<br>
	<div id="searchForm">
		<form accept-charset="utf-8">
			<select name="opt">
				<option value="0">��ǰ��</option>
				<option value="1">����ȸ��</option>
			</select>
			<br>
			<input type="text" size="20" name="condition"/>&nbsp;
			<br>			
			<input type="submit" value="�˻�"/>
		</form>	
	</div>
                            
                            
                        </div>
                        
                    </DIV>
                </TD>
                
            </TR>
            <TR>
                <TD WIDTH='160'>
                <jsp:include page="MainForm.jsp" />
                </TD>
               
            </TR>	
        </TABLE>
    </body>
</html>