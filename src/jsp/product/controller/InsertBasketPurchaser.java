package jsp.product.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Basket;
import jsp.product.model.BasketCart;
import jsp.product.model.BasketPurchaserService;
import jsp.product.model.Purchaser;



public class InsertBasketPurchaser implements Action {
	
    BasketPurchaserService basketPurchaserService;
    Basket basket;
		
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String memID = request.getParameter("sessionID");

		HttpSession session = request.getSession();		
		BasketCart basketCart = (BasketCart)session.getAttribute("BasketCart");
		Collection collection = basketCart.getItems();
		Iterator iterator = collection.iterator();
		
		while(iterator.hasNext()) {
		    basket = (Basket)iterator.next();
		    orderInsert(request, response, basket, memID);
		}
		
		selectBasket(request, memID);
		//����īƮ clear
		basketCart.clear();
		
		forward.setRedirect(false);	
		forward.setNextPath("selectmemberPurchaserForm.pr");
				
		return forward;
	}
	/**
	* ������ ������ �Է¹��� ����� ������ basket,purchaser���̺� �����Ѵ�.
	*
	* @param Basket basket,String memID
	*
	*/
	public void orderInsert(HttpServletRequest request,HttpServletResponse response,Basket basket, String memID)
	{
	    basketPurchaserService = new BasketPurchaserService();
	    Purchaser purchaser = new Purchaser();
	    
	    java.text.DecimalFormat nf2 = new java.text.DecimalFormat("#0000");
	    
	    String OrderNo = nf2.format(basketPurchaserService.OrderMaxNo());
	    String CardNumber = request.getParameter("cardnumber");
	    String CardType = request.getParameter("cardtype");
	    
	    if(CardNumber == "" | CardType== ""){
	        CardNumber = "N";
	        CardType = "N";
	    }
	    
	    System.out.println(OrderNo);	    
	    basket.setOrderNum(OrderNo);
	    
	    purchaser.setOrderNum(OrderNo);
	    purchaser.setMemID(memID);
	    purchaser.setAddress(request.getParameter("address"));
	    purchaser.setName(request.getParameter("name"));
	    purchaser.setEmail(request.getParameter("mail"));
	    purchaser.setTel(request.getParameter("tel"));
	    purchaser.setPlace(request.getParameter("place"));
	    purchaser.setCardNumber(CardNumber);
	    purchaser.setCardType(CardType);
	    purchaser.setPayStatus("N");
	    purchaser.setPayType(request.getParameter("paytype"));
	    purchaser.setAmount(basket.getQuantity()*basket.getPrice());
	    purchaser.setTcount(1);
	    
	    basketPurchaserService.insertBasketPurchaser(basket,purchaser,memID);
	}
	
	/**
	* �ֹ��� ������� �ֹ� ������ delivery������ ������ request��ü�� ��� �Ѱ� �ش�.
	* @param String memID
	*/
	public void selectBasket(HttpServletRequest request, String memID)
	{
	    
	    Collection collection = basketPurchaserService.listBasketPurchaser(memID);
	    request.setAttribute("collection", collection);
	}

}