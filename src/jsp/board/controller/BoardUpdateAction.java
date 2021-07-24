package jsp.board.controller;
 
import java.util.Enumeration;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
 
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardUpdateAction implements Action
{	
	BoardService bs;
	
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // ��� �ۼ� �� ���� �������� ���ư��� ���� ������ ��ȣ�� �ʿ��ϴ�.
        String pageNum = request.getParameter("page");
        
        // ���ε� ���� ������
        int fileSize= 5*1024*1024;
        // ���ε�� ���� ������
        String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
        
        try {
            MultipartRequest multi = new MultipartRequest  // ���� ���ε带 ó���� MultipartRequest ��ü�̴�.
                    (request, uploadPath, fileSize, "euc-kr", new DefaultFileRenamePolicy());
            
            // �ĸ����� ���� �����´�.
            int num = Integer.parseInt(multi.getParameter("board_num")); // �� ��ȣ
            String subject = multi.getParameter("board_subject");    // �� ����
            String content = multi.getParameter("board_content");    // �� ����
            String existFile = multi.getParameter("existing_file");    // ���� ÷�� ����
            
            // �Ķ���� ���� �ڹٺ� �����Ѵ�.
            BoardBean border = new BoardBean();
            border.setBoard_num(num);
            border.setBoard_subject(subject);
            border.setBoard_content(content);
            
            // �� ���� �� ���ε�� ���� ��������
            Enumeration<String> fileNames = multi.getFileNames();
            if(fileNames.hasMoreElements())
            {
                String fileName = fileNames.nextElement();
                String updateFile = multi.getFilesystemName(fileName);
                
                if(updateFile == null)    // ������ ���ο� ������ ÷�� ���ߴٸ� ���� ���ϸ��� ����
                    border.setBoard_file(existFile);
                else    // ���ο� ������ ÷������ ���
                    border.setBoard_file(updateFile);
            }
            
            BoardService bs = BoardService.getInstance();
            boolean result = bs.updateBoard(border);
            
            if(result){
                forward.setRedirect(true); 
                // �����ִ� �������� ���ư��� ���� ��������ȣ�� �����Ѵ�.
                forward.setNextPath("BoardListAction.bo?page="+pageNum); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�� ���� ���� : " + e.getMessage());
        }
 
        return forward;
    }
}
