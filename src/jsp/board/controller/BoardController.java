 package jsp.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

/**
 * �Խ��ǰ��� Controller
 *
 */
public class BoardController extends HttpServlet{
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
		String form = "MainForm.jsp?contentPage=board/";
		
		// Ŀ�ǵ忡 �ش��ϴ� �׼��� �����Ѵ�.
		try {
			// ȭ����ȯ
			if(command.equals("BoardWriteForm.bo")) // �۾��� ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardWriteForm.jsp");
			}
			else if(command.equals("BoardListForm.bo"))	// �Խ��Ǹ�� ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardListForm.jsp");
			}
			else if(command.equals("BoardDetailForm.bo"))	// �Խ��� �� �󼼺��� ȭ�� �̵�
			{
				System.out.println(">>> success");
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardDetailForm.jsp"); //BoardUpdateAction.bo
			}
			else if(command.equals("BoardReplyForm.bo"))	// �亯 �� �ۼ� ȭ�� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardReplyForm.jsp"); 
			}			
			else if(command.equals("BoardUpdateForm.bo"))	//
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardUpdateForm.jsp"); 
			}
	
			
			
			// ���� ó�� �׼�
			else if(command.equals("BoardWriteAction.bo")) // �Խ��Ǳ� ���� ó��
			{
				action = new BoardWriteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardListAction.bo")) // �Խ��� ��� �ҷ����� ó��
			{
				action = new BoardListAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardDetailAction.bo")) // �Խ��� �� �󼼺��� ó��
			{
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("FileDownloadAction.bo")) // ����÷�� ó��
			{
				action = new FileDownloadAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardReplyFormAction.bo")) // �亯 �� �ۼ� ȭ������ �̵��ϴ� Action�̴�
			{
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardReplyAction.bo")) // �亯 ���� �ۼ��ϴ� Action�̴�
			{
				action = new BoardReplyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardDeleteAction.bo")) //
			{
				action = new BoardDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardUpdateFormAction.bo")) // 
			{
				action = new BoardUpdateFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardUpdateAction.bo")) // 
			{
				action = new BoardUpdateAction();
				forward = action.execute(request, response);
			}
			
			// ȭ���̵� - isRedirext() ���� ���� sendRedirect �Ǵ� forward�� ���
			// sendRedirect : ���ο� ������������ request�� response��ü�� ���Ӱ� �����ȴ�.
			// forward : ���� �������� �������� forwad�� ���� ȣ��� �������� request�� response ��ü�� ����
			if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess
}
