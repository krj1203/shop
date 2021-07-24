package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberService;




public class SelectMember implements Action {

  	/**
	*	ȸ��(Member)������ ��ȸ�Ѵ�(�ֹ��� �������).
	*	1.  �α��λ��¿��� Session��ü�� ���� session.getAttribute("Member.id")  �� memId �� �о�´�.
	*	2.  MemberService�� select()�� ȣ���Ѵ�. memId�� argument �� �����Ѵ�. 
	*		select()�� return value�� Member ��ü�� �޴´�.
	*  3.  request Scope�� Member��ü�� �����Ѵ�.
	*	
	*/
		public ActionForward execute(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			ActionForward forward = new ActionForward();
			
			// ������ �������ִ� �α����� ID ������ �����´�
			HttpSession session = request.getSession();
			String id = session.getAttribute("sessionID").toString();
			
			// ������ ȸ�������� �����´�.
			MemberService dao = MemberService.getInstance();
			MemberBean member = dao.getUserInfo(id);
			
			// ModifyFrom.jsp�� ȸ�������� �����ϱ� ���� request�� MemberBean�� �����Ѵ�.
			request.setAttribute("member", member);
			
			forward.setRedirect(false);
			forward.setNextPath("purchaseProduct.jsp");
			
		return forward;
	}//execute
}