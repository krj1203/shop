package jsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

/**
 * ȸ������ Controller
 *
 */
public class MemberController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * GET ����� ��� doGet()
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}  	
		
	/**
	 * POST ����� ��� doPost()
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}   	  	      	   
		
	/**
	 * ��ɾ ���� �ش� Action�� ������ �ش�.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) 
		 	throws ServletException, IOException {
		
		// �Ѿ�� Ŀ�ǵ带 �����ϴ� ����
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/")+1;
		
		String command = requestURI.substring(cmdIdx);
		
		// URI, command Ȯ��
		System.out.println("requestURI : "+requestURI);
		System.out.println("command : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		// ������ ȭ�� URL
		String form = "MainForm.jsp?contentPage=member/";
		
		// Ŀ�ǵ忡 �ش��ϴ� �׼��� �����Ѵ�.
		try {
			// ȭ����ȯ
			if(command.equals("MainForm.do")) // ����ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("MainForm.jsp");
			}
			else if(command.equals("LoginForm.do"))	// �α���ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"LoginForm.jsp");
			}
			else if(command.equals("UserInfoForm.do"))	// ������ Ŭ�� - ȸ������ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"UserInfoForm.jsp");
			}
			else if(command.equals("ModifyFrom.do")) // ȸ������ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"ModifyFrom.jsp");
			}
			else if(command.equals("DeleteForm.do")) // ȸ������ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"DeleteForm.jsp");
			}
			else if(command.equals("Result.do")) // ���� ó����� ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"ResultForm.jsp");
			}
			else if(command.equals("MemberListForm.do")) // ���ȸ�� ��ȸ ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"MemberListForm.jsp");
			}
			else if(command.equals("JoinForm.do")) // 
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"JoinForm.jsp");
			}
			
			
			
			
			// ���� ó�� �׼�
			else if(command.equals("MemberLoginAction.do")) // �α��� ó��
			{
				action = new MemberLoginAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberLogoutAction.do")) // �α׾ƿ� ó��
			{
				action = new MemberLogoutAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberJoinAction.do")) // ȸ������ ó��
			{
				action = new MemberJoinAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberInfoAction.do")) // ȸ������ȭ�鿡 ������ ���� ó��
			{
				action = new MemberInfoAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberModifyFormAction.do")) // ȸ������ȭ�鿡 ������ ���� ó��
			{
				action = new MemberModifyFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberModifyAction.do")) // ȸ������ ó��
			{
				action = new MemberModifyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberDeleteAction.do")) // ȸ������ ó��
			{
				action = new MemberDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberListAction.do")) // ��üȸ������
			{
				action = new MemberListAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("MemberIdCheckAction.do")) // ���̵� �ߺ� üũ
			{
				action = new MemberIdCheckAction();
				forward = action.execute(request, response);
			}

			// ȭ���̵� - isRedirext() ���� ���� sendRedirect �Ǵ� forward�� ���
			// sendRedirect : ���ο� ������������ request�� response��ü�� ���Ӱ� �����ȴ�.
			// forward : ���� �������� �������� forwad�� ���� ȣ��� �������� request�� response ��ü�� ����
			if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess
}
