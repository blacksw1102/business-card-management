package com.blacksw.bcm.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.BusinessCardDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;
import com.blacksw.bcm.vo.PageInfoVO;

public class BusinessCardListAction implements Action {

	private ActionForward forward;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ������ �α��� �������� ����
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			forward = new ActionForward("/signin", true);
		} else {
			int page = Integer.parseInt(request.getParameter("page"));
			String keyword = request.getParameter("keyword");
			request.setAttribute("businessCardList", getBusinessCardList(page, keyword));
			request.setAttribute("pageInfo", getPageInfo(page));			
			forward = new ActionForward("/businessCard/businessCardList.jsp", false);
		}
		
		return forward;
	}
	
	// businessCardList ������ ���ϱ�
	public ArrayList<BusinessCardVO> getBusinessCardList(int page, String keyword) {
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		ArrayList<BusinessCardVO> businessCardList = businessCardDAO.selectBusinessCardList(page, keyword);
		return businessCardList;
	}
	
	// ����¡ ������ ���ϱ�
	public PageInfoVO getPageInfo(int page) {
		int countList = 10;		// ������ �� ���ڵ� ��
		int countPage = 10;	// ����¡ ��
		
		int totalCount = BusinessCardDAO.getInstance().selectBusinessCardCount(); // �� ���ڵ� ����
		int totalPage = totalCount / countList; // �� ������ ����
		
		// (�� ���ڵ� �� / ������ �� ���ڵ� ��) ��� ��, ���� ���ڵ尡 ���� ���
		if(totalCount % countList > 0)
		    totalPage++;

		// ������ �������� ������ ���������� Ŭ ���
		if(page > totalPage)
		    page = totalPage;

		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = startPage + countPage - 1;

		// endPage ���� �� ������ ���� �Ѿ ���
		if (endPage > totalPage) {
		    endPage = totalPage;
		}

		// ����¡ ������ ����
		PageInfoVO pageInfo = new PageInfoVO();
		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(countPage);
		pageInfo.setMaxPage(totalPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	

		return pageInfo;
	}

}
