<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page language="java" import="jsp.product.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<html>
<TITLE>selectProduct</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<body>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>

<TD WIDTH='480' ALIGN='left'>
    
    <P><FONT color=#0000ff face=���� size=3><CENTER><STRONG>[��ǰ ������]</STRONG><CENTER></FONT></P> 
    <jsp:useBean id='product' class='jsp.product.model.Product' scope='request' />
    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
    <table width=450>
    <tr>
        <td rowspan=5 align=center><img  border="0" src="images/<jsp:getProperty name='product' property='photoDir'/>" width="250" height=200"></td>
        <td width=210 colspan=2 align=center><font color="red"><jsp:getProperty name='product' property='productName'/></font></td>
    </tr>
    <tr>
        <td><font size=3>������/���Կ�</font></td>
        <td><font size=3><jsp:getProperty name='product' property='company'/></font></td>
    </tr>
    <tr>
        <td><font size=3>�Ϲݰ���</font></td>
        <td><font size=3><strike><jsp:getProperty name='product' property='price1S'/>��</strike></font></td>
    </tr>
    <tr>
        <td><font size=2>�ǸŰ���</font></td>
        <td><font color='red' face=arial>��&nbsp;<jsp:getProperty name='product' property='price2S'/>��</font></td>
    </tr>
    <tr>
        <td><font size=2>�Һο���</font></td>
        <td>
            <%
            if(product.getInstall().trim().equals("1")) {
                out.println("����");
            } else {
                out.println("�Ұ���");
            }
            %>
        </td>
    </tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td colspan=3 align=center><div align=center><center><table width=70>
            <tr>
                <td><font size=3>��ǰ����</font></td>
            </tr>
            <tr>
                <td><textarea rows=4 name=detail cols=40><jsp:getProperty name='product' property='detail'/></textarea></td>
            </tr>
            <tr>
                <td align=center><font size=4><br>
                	<c:if test="${sessionScope.sessionID != null}">
                    <font color="red">�����Ͻ÷��� ���Ű����� �Է��Ͻð� <br>
                    	��ٱ��� ��ư�� �����ּ���</font><br><br>
                    </c:if>
                    <c:if test="${sessionScope.sessionID == null}">
						 <font color="red">�����Ͻ÷��� �α����� ���ּ��� <br></font>
					</c:if>	
                    <form method="post" action="putOnebasket.pr">
                        ���Ű��� &nbsp;&nbsp;<input type='text' name='quantity' size=2 value='1'>                                        
                        <input type='hidden' name='productId' value='<jsp:getProperty name='product' property='productId'/>'>
                        <br><br>
                        <c:if test="${sessionScope.sessionID != null}">
                        	<input type=submit value="��ٱ��ϳֱ�" onclick="logincheck()" >
                        </c:if>
                        &nbsp;<input type="button" value="��ǰ��Ϻ���" onclick="history.go(-1)">                     
                    </form>								<!--selectAllproduct.pr?page=${pageNum}}-->
                </td>
            </font></td>
        </tr>
    </table>
    </center></div></td>
</tr>
</table>		
<br>

</DIV>
</TD>
<!-- END of main conten t-->
</TR>
<TR>
    <TD WIDTH='160'>
    	 <jsp:include page="MainForm.jsp" />
    </TD>
  
</TR>	
</TABLE>
</body>
</html>