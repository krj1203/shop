<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=euc-kr" %> 
<html>
    <TITLE>����������</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <body>
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
            <TR HEIGHT='100'>		
                
            </TR>
            <TR VALIGN=\'top\'>
                
                <!-- START of main conten t-->
                <TD WIDTH='480' ALIGN='left'>
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
                        <FORM ACTION='insertbasketPurchaser.pr' METHOD='POST'>
                            <jsp:useBean id="member" class="jsp.member.model.MemberBean" scope="request"  />
                            
                            <TABLE BORDER='0' CELLSPACING='0' CELLPADDING='5' WIDTH='100%' width=400>
                                <TR>
                                    <TD colspan=2><font size='1' color="red">����������(��ǰ�� ������ �ּҿ� ����ó�� �������ּ���)</font></TD>
                                </TR>
                                <TR>
                                    <TD colspan=2 align=center><font size='2' color="#0000FF">
                                            <P><FONT color=#0000ff face=���� size=3><CENTER><STRONG>[����������]</STRONG><CENTER></FONT></P> 
                                    </font></td>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>������ ��������:</font> </TD>
                                    <TD><font size=1>����<INPUT TYPE='radio' NAME='place' value='1' selected checked>
                                         	����<INPUT TYPE='radio' NAME='place' value='2' ></font>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>ȸ��ID:</font></TD>
                                    <TD><%= (String)session.getAttribute("sessionID") %></TD>
                                    <input type=hidden name=sessionID value=<%= (String)session.getAttribute("sessionID") %>>
                                    </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>�����θ�:</font></TD>
                                    <TD><font size=2><INPUT TYPE='text' NAME='name' SIZE='20' value='<jsp:getProperty name="member" property="name"/>'></font></TD>
                                </TR>                                                           
                                <TR>
                                    <TD ALIGN='right'><font size=1>��ȭ��ȣ:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='tel' SIZE='20' value='<jsp:getProperty name="member" property="phone"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>�̸���:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='mail' SIZE='20' value='<jsp:getProperty name="member" property="mail1"/>@<jsp:getProperty name="member" property="mail2"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>�ּ�:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='address' SIZE='40' value='<jsp:getProperty name="member" property="address"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>������:</font></TD>
                                    <TD><font size=1>�¶��� �Ա�<input type='radio' name='paytype' value='remit' checked >
                                         	ī�����<input type='radio' name='paytype' value='card'></font>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>ī�� ȸ��:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='cardtype' SIZE='40'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>ī�� ��ȣ:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='cardnumber' SIZE='40'></TD>
                                </TR>
                                
                                <TR>
                                    <TD colspan=2 align=center>
                                        <!--<input type=hidden name="choice" value="do-Purchase">-->
                                        <input type="button" value="���ΰ��" onClick="location='mainController?choice=selectAll-product'">&nbsp;
                                        <input type="submit" value="�����ϱ�"> &nbsp; 
                                        <input type="button" value="��ٱ��� ����" onClick="location='basketProduct.jsp'">
                                    </TD>
                                </TR>
                            </TABLE>
                        </FORM>
                    </DIV>
                </TD>
                <!-- END of main conten t-->
         	<TD WIDTH='160'>
    			 <jsp:include page="MainForm.jsp" />
    		</TD>
            </TR>
           	
        </TABLE>
    </body>
</html>