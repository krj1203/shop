package jsp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class BoardReplyFormAction implements Action
{	
	BoardService bs;
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		BoardService bs = BoardService.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		// ��� �ۼ� �� ���� �������� ���ư��� ���� ������ ��ȣ�� �ʿ��ϴ�.
		String pageNum = request.getParameter("page");
		
		BoardBean board = bs.getDetail(num);
		request.setAttribute("board", board); // �θ� ������ ��������ȣ�� request�� �����Ѵ�
		request.setAttribute("pageNum", pageNum);
	
		
		forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
		forward.setNextPath("BoardReplyForm.bo");
		
		return forward;
	}
}
