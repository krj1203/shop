package jsp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberService;


/**
 * ȸ������ �۾��� ó���ϴ� Action Ŭ����
 */
public class MemberDeleteAction implements Action
{
	
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// ������ �������ִ� �α����� ID ������ �����´�
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();
		String password = request.getParameter("password");
		
		MemberService dao = MemberService.getInstance();
		int check = dao.deleteMember(id, password);
		
		if(check == 1){
			session.invalidate(); // ȸ������ ��� ���� ����
			forward.setRedirect(true);
			forward.setNextPath("Result.do");
		}
		else{
			System.out.println("ȸ�� ���� ����");
			return null;
		}
		
		return forward;
	}
}

