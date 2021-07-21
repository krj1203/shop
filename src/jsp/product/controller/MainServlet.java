package jsp.product.controller;


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
public class MainServlet extends HttpServlet{
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
		//String form = "MainForm.jsp?contentPage=product/";
		
		// Ŀ�ǵ忡 �ش��ϴ� �׼��� �����Ѵ�.
		try {
			// ȭ����ȯ
			if(command.equals("ProductListForm.pr")) // ��ǰ��Ϻ���
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("selectAllProduct.jsp");
			}
			else if(command.equals("ProductDetailForm.pr")) // ��ǰ�ڼ��� ����
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("selectProduct.jsp");
			}									
			else if(command.equals("userError.pr")) // ����
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("userError.jsp");
			}
			else if(command.equals("putOnebasketForm.pr")) // ��ٱ��Ϸ� �̵�
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("basketProduct.jsp");
			}
			else if(command.equals("selectmemberPurchaserForm.pr")) // 
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("orderProduct.jsp");
			}
			
			
			
			
			
									
			// ���� ó�� �׼�
			else if(command.equals("selectAllproduct.pr")) // ��ǰ���ó��
			{
				action = new SelectAllProduct();
				forward = action.execute(request, response);
			}
			else if(command.equals("selectproduct.pr")) // 
			{
				action = new SelectProduct();
				forward = action.execute(request, response);
			}
			else if(command.equals("putOnebasket.pr")) 
			{
				action = new PutOneBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("selectmemberPurchaser.pr"))  //�������� ����ϱ��� ȸ������ ��ȸ ó��
			{
				action = new SelectMember();
				forward = action.execute(request, response);
			}
			else if(command.equals("emptyAllbasket.pr")) //��ٱ��� ��ü���� ó�� 
			{
				action = new EmptyAllBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("emptyOnebasket.pr")) //��ٱ��� ���ú��� ó�� 
			{
				action = new EmptyOneBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("insertbasketPurchaser.pr")) // �������� ��� ó��
			{
				action = new InsertBasketPurchaser();
				forward = action.execute(request, response);
			}
			else if(command.equals("deletebasket.pr")) // ���ſϷ��� ��ٱ��� ����
			{
				action = new DeleteBasket();
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
