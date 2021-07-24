package jsp.board.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardReplyAction implements Action//��� �ۼ��� ó���ϴ� action
{	
	BoardService bs;
	
    @Override
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("euc-kr");
        ActionForward forward = new ActionForward();
        
        BoardService bs = BoardService.getInstance();
        BoardBean border = new BoardBean();
               
        // ��� �ۼ� �� ���� �������� ���ư��� ���� ������ ��ȣ�� �ʿ��ϴ�.
        String pageNum = request.getParameter("page");
        
        // �ĸ����� ���� �����´�.
        int num = Integer.parseInt(request.getParameter("board_num"));
        String id = request.getParameter("board_id");
        String subject = request.getParameter("board_subject");
        String content = request.getParameter("board_content");
        int ref = Integer.parseInt(request.getParameter("board_re_ref"));
        
        //bean���������
        border.setBoard_num(bs.getSeq());
        border.setBoard_id(id);
        border.setBoard_subject(subject);
        border.setBoard_content(content);
        border.setBoard_re_ref(ref);
        border.setBoard_parent(num); //�θ���� �۹�ȣ�� ����
                
        boolean result = bs.boardInsert(border);
        
        if(result){
            forward.setRedirect(false); 
            // �����ִ� �������� ���ư��� ���� ��������ȣ�� �����Ѵ�.
            forward.setNextPath("BoardListAction.bo?page="+pageNum); 
        }
        return forward;
    }
}