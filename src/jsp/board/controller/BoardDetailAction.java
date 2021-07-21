package jsp.board.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class BoardDetailAction implements Action
{
	BoardService bs;
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// �Ķ���ͷ� �Ѿ�� �۹�ȣ�� �����´�.
		String num = request.getParameter("num");
		System.out.println("�۹�ȣ:"+num);
		int boardNum = Integer.parseInt(num);
		
		//��������ȣ�� �����´�.
		String pageNum = request.getParameter("page");
		
		BoardService bs = BoardService.getInstance();
		BoardBean board = bs.getDetail(boardNum); // �� �ڼ��� ����
		boolean result = bs.updateCount(boardNum); //��ȸ������		
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		ArrayList<CommentBean> commentList = commentDAO.getCommentList(boardNum);
		
		// ����� 1���� �ִٸ� request�� commentList�� �����Ѵ�.
		if(commentList.size() > 0) {
			request.setAttribute("commentList", commentList);
		}
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
			
			
		if(result){
			forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
			forward.setNextPath("BoardDetailForm.bo");
		}
		
		return forward;
	}
}
