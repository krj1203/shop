package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.BasketCart;


public class EmptyOneBasket implements Action {
	
	/**
	*	��ٱ����� ��ǰ�� �����Ѵ�.
	*	1.  basketProduct.jsp�� request�� �޴´�.
	*   2.  request��ü���� "productName" �� ���� �����´�.
	*	3.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
	*   4.  ��ǰ�� �̸��� argument �� �־� BasketCart�� removeAll(productName)�� ȣ���Ѵ�.
	*/
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println("DeleteBasket execute called");

		HttpSession session = request.getSession(false);

		//BasketCart bc = null;
		if(session != null) {
			BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
                        String productId=request.getParameter("productId");
			bc.remove(productId);
		}
		
		forward.setRedirect(false);
		forward.setNextPath("putOnebasketForm.pr");
		
		return forward;
	}

}