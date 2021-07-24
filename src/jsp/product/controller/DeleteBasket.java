package jsp.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.BasketPurchaserService;


public class DeleteBasket implements Action {
   
    /**
     *	��ٱ��Ͽ� ��ǰ�� ��´�
     *	1.  detailProduct.jsp�� request�� �޴´�.
     *	2.  Session ��ü���� "Member.id" �� �ش��ϴ� ���� ������ check�Ѵ�.
     *     �� �α��ο��θ� üũ�ϰ� ���Ŀ� ��ٱ��Ͽ� ��ǰ�� ��ƾ� �Ѵ�.
     *   3. �α��� ���� ���� �����̸� "�α����� �����ϼ���!! "��� �޼����� �����ְ�
     *      "userError" �� return �Ѵ�.
     *	4.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
     *   5.  request��ü���� "productName" �� ���� �����´�.
     *	6.  productName�� parameter�� �Է� �޾� �� ��ǰ�� ��ǰ������ �������� listProduct(request, response, productName) �����.
     *       ProductDAO�� listProduct(productName)�� ȣ���Ѵ�.
     *		������� Basket��ü�� �����Ѵ�.
     *   7.  ��ǰ�� �̸��� Basket��ü�� argument �� �־� BasketCart�� add(productName, basket)�� ȣ���Ѵ�.
     */
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	
    	ActionForward forward = new ActionForward();
    	
        System.out.println("DeleteBasket execute called");
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        HttpSession session = request.getSession(false);
        String memID = null;
        if(session != null) {
            memID =  (String)session.getAttribute("sessionID");
            System.out.println("memID : " + memID);
            if( memID == null  || memID.equals("") ) {
                errorMsgs.add("�α����� �����ϼ���!!");
                //System.out.println("login ERROR");
            }
        }
        
        //����īƮ clear
        //BasketCart basketCart = (BasketCart)session.getAttribute("BasketCart");
        //basketCart.clear();
        
        BasketPurchaserService basketPurchaserService = new BasketPurchaserService();
        
        basketPurchaserService.deleteBasket(memID);
        
        forward.setRedirect(true);
        forward.setNextPath("MainForm.do");
        
        
        return forward;
    }
    
    
}