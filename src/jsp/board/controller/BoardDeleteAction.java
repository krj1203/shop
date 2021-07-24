package jsp.board.controller;
 
import java.io.File;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardDeleteAction implements Action
{
	BoardService bs;
	
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // �۹�ȣ�� �����´�.
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
        
        BoardService bs = BoardService.getInstance();
        // ������ �ۿ� �ִ� ���� ������ �����´�.
        String fileName = bs.getFileName(boardNum);
        // �� ���� - ����� ������� ��۵� ��� �����Ѵ�.
        boolean result = bs.deleteBoard(boardNum);
        
        // ���ϻ��� 
        if(fileName != null)
        {
            // ������ �ִ� ������ �����θ� �����´�.
            String folder = request.getServletContext().getRealPath("UploadFolder");
            // ������ �����θ� �����.
            String filePath = folder + "/" + fileName;
 
            File file = new File(filePath);
            if(file.exists()) file.delete(); // ������ 1���� ���ε� �ǹǷ� �ѹ��� �����ϸ� �ȴ�.
        }
        
        if(result){
            forward.setRedirect(false);
            forward.setNextPath("BoardListAction.bo");
        }
        else
            return null;
 
        return forward;
    }
}
