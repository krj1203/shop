package jsp.board.comment.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.controller.MemberLoginAction;


public class CommentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private HashMap<String,Action> commandMap;
	
	

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
	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// �Ѿ�� Ŀ�ǵ带 �����ϴ� ����
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIdx);

		// URI, command Ȯ��
		 System.out.println("requestURI : "+requestURI);
		//System.out.println("Board cmd : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		//String form = "MainForm.jsp?contentPage=member/";
		
		try {					 
			// ���� ó�� �׼�
			if(command.equals("CommentWriteAction.co")) 
			{
				action = new CommentWriteAction();
				forward = action.execute(request, response);
			}
						
			else if(command.equals("CommentReplyAction.co")) 
			{
				action = new CommentReplyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentDeleteAction.co")) 
			{
				action = new CommentDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentUpdateAction.co")) 
			{
				action = new CommentUpdateAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentUpdateFormAction.co")) 
			{
				action = new CommentUpdateFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentReplyFormAction.co")) 
			{
				action = new CommentReplyFormAction();
				forward = action.execute(request, response);
			}
			
			/*
			 * ȭ���̵� - isRedirext() ���� ���� sendRedirect �Ǵ� forward�� ���
			 * sendRedirect : ���ο� ������������ request�� response��ü�� ���Ӱ� �����ȴ�.
			 * forward : ���� �������� �������� forwad�� ���� ȣ��� �������� request�� response ��ü�� ����
			 */
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
