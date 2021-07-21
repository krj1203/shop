package jsp.product.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Product;
import jsp.product.model.ProductService;

public class SelectAllProduct implements Action {
	
	/**
	*	��ǰ�� ����Ʈ�� ��ȸ�Ѵ�.
	*   1.  ��ǰ���⸦ �����ϴ� request�� �޴´�.
	*   2.  return  Collection ��ü�� request scope�� �����Ѵ�.
	*/
	@Override
	public ActionForward execute(HttpServletRequest request,
	           HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// ���� ������ ��ȣ �����
		int spage = 1;
		String page = request.getParameter("page");

		if(page != null && !page.equals("")) {
					spage = Integer.parseInt(page);
		}
				
		// �˻����ǰ� �˻������� �����´�.
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// �˻����ǰ� ������ Map�� ��´�.
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		
		System.out.println("opt"+opt);
		System.out.println("condition"+condition);
		
		ProductService productservice = new ProductService();
		int listCount = productservice.getProductListCount(listOpt);// ���� ������ �����´�
		
		// �� ȭ�鿡 10���� �Խñ��� ����������
		// ������ ��ȣ�� �� 5��, ���ķδ� [����]���� ǥ��
				
		// ��ü ������ ��
		int maxPage = (int)(listCount/10.0 + 0.9); 

		// ���� ����ڰ� �ּ�â���� ������ ��ȣ�� maxPage ���� ���� ���� �Է½�
		// maxPage�� �ش��ϴ� ����� �����ش�.
		if(spage > maxPage) spage = maxPage;
		listOpt.put("start", spage*10-9);
					
		ArrayList<Product> list = productservice.getBoardList(listOpt);
		
		//���� ������ ��ȣ
		int startPage = (int)(spage/5.0 + 0.9) * 5 - 4; // �ִ� 5���������� �ѹ��� ǥ��
		//������ ������ ��ȣ
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		// 4�� ��������ȣ ����
		request.setAttribute("spage", spage);// ���� ������ ��ȣ 
		request.setAttribute("maxPage", maxPage);//���������� �ִ� ��ȣ
		request.setAttribute("startPage", startPage);//������������ȣ
		request.setAttribute("endPage", endPage);//������ ��������ȣ
		
		request.setAttribute("list", list);
		
		forward.setRedirect(false);
	    forward.setNextPath("ProductListForm.pr");

		return forward;
	}
}