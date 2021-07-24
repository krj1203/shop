package jsp.board.comment.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentDAO;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class CommentDeleteAction implements Action
{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		
		CommentDAO dao = CommentDAO.getInstance();
		boolean result = dao.deleteComment(comment_num);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();

		// ���������� ����� ����������� 1�� �����Ѵ�.
		if(result) out.println("1");
		
		out.close();
		return null;
	}
}
