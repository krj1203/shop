package jsp.board.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardUpdateFormAction implements Action
{	
	
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // ������ ��ȣ�� �� ��ȣ�� �����´�.
        String pageNum = request.getParameter("page");
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
 
        BoardService bs = BoardService.getInstance();
        BoardBean board = bs.getDetail(boardNum);
        
        request.setAttribute("board", board);
        request.setAttribute("pageNum", pageNum);
        
        forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
        forward.setNextPath("BoardUpdateForm.bo");
        
        return forward;
    }
}
