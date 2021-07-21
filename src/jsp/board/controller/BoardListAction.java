package jsp.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class BoardListAction implements Action
{ 
	//�� ����� �����ִ� Action�̴�. ���⼭�� DAO���� �Ѱܹ��� �� ������ �̿��Ͽ� ������ ó���� ���� �Ѵ�

	BoardService bs;
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// ���� ������ ��ȣ �����
		int spage = 1;
		String page = request.getParameter("page");

		if(page != null && !page.equals("")) {
			spage = Integer.parseInt(page);
		}
		
		// �˻����ǰ� �˻������� �����´�.
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// �˻����ǰ� ������ Map�� ��´�.
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		
		BoardService bs = BoardService.getInstance();
		int listCount = bs.getBoardListCount(listOpt);// ���� ������ �����´�
		
		// �� ȭ�鿡 10���� �Խñ��� ����������
		// ������ ��ȣ�� �� 5��, ���ķδ� [����]���� ǥ��
		
		// ��ü ������ ��
		int maxPage = (int)(listCount/10.0 + 0.9); 

		// ���� ����ڰ� �ּ�â���� ������ ��ȣ�� maxPage ���� ���� ���� �Է½�
		// maxPage�� �ش��ϴ� ����� �����ش�.
		if(spage > maxPage) spage = maxPage;
		listOpt.put("start", spage*10-9);
		
	
		
		ArrayList<BoardBean> list =  bs.getBoardList(listOpt); //�۸���� �����´�.
	
		//���� ������ ��ȣ
		int startPage = (int)(spage/5.0 + 0.9) * 5 - 4; // �ִ� 5���������� �ѹ��� ǥ��
		//������ ������ ��ȣ
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		// 4�� ��������ȣ ����
		request.setAttribute("spage", spage);// ���� ������ ��ȣ 
		request.setAttribute("maxPage", maxPage);//���������� �ִ� ��ȣ
		request.setAttribute("startPage", startPage);//������������ȣ
		request.setAttribute("endPage", endPage);//������ ��������ȣ

		// ���� �� ���� �۸�� ����
		//request.setAttribute("listCount", listCount);
		request.setAttribute("list", list); //�۸���� request�� �����Ѵ�.
		
		// �ܼ� ��ȸ�̹Ƿ� forward()��� (= DB�� ���º�ȭ �����Ƿ�) 
		forward.setRedirect(false);
		forward.setNextPath("BoardListForm.bo");
		
		return forward;
	}

}
