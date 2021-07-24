package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Product;
import jsp.product.model.ProductService;


public class SelectProduct implements Action {
	
	/**
	*	������ ��ǰ�� �������� ��ȸ�Ѵ�.
	*	1.  listProduct.jsp�� request�� �޴´�.
	*   2.  request��ü���� "keyword" �� ���� �����´�.
	*	3.  ProductService�� selectProduct(keyword) �� ȣ���Ѵ�.
	*   4.  return  Collection ��ü�� request scope�� �����Ѵ�.
	*/
	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println("SearchKeywordProduct execute called");
		ProductService productservice = new ProductService();	

        String productId=request.getParameter("productId");
		System.out.println("Select Product name " + productId);
        Product product = (Product)productservice.selectProduct(productId);
        
        request.setAttribute("product", product);
        forward.setRedirect(false);
        forward.setNextPath("ProductDetailForm.pr");

		return forward;
	}
}