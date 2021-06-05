package com.blacksw.bcm.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.BusinessCardDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;
import com.blacksw.bcm.vo.PageInfoVO;
import com.blacksw.bcm.vo.UserVO;

public class BusinessCardListAction implements Action {

	private ActionForward forward;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ������ �α��� �������� ����
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			forward = new ActionForward("/signin", true);
		} else {
			UserVO user = (UserVO) session.getAttribute("loginUser");
			String keyword = request.getParameter("keyword");
			int page;
			
			// ������ �Ķ���� �ʱ�ȭ
			if(request.getParameter("page") == null) {
				page = 1;
			} else {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			ArrayList<BusinessCardVO> businessCardList = getBusinessCardList(user.getId(), page, keyword);
			request.setAttribute("businessCardList", businessCardList);
			
			PageInfoVO pageInfo;

			if(keyword != null && !keyword.isEmpty()) {
				pageInfo = getPageInfo(user.getId(), page, keyword);
				request.setAttribute("pageInfo", pageInfo);
				request.setAttribute("keyword", keyword);
			} else {
				pageInfo = getPageInfo(user.getId(), page);
				request.setAttribute("pageInfo", pageInfo);
			}
			
			forward = new ActionForward("/WEB-INF/view/businessCard/businessCardList.jsp", false);
		}
		
		return forward;
	}
	
	// businessCardList ������ ���ϱ�
	public ArrayList<BusinessCardVO> getBusinessCardList(String userId, int page, String keyword) {
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		ArrayList<BusinessCardVO> businessCardList = businessCardDAO.selectBusinessCardList(userId, page, keyword);
		return businessCardList;
	}
	
	// ����¡ ������ ���ϱ� (�Ķ����:page)
	public PageInfoVO getPageInfo(String userId, int page) {
		return getPageInfo(userId, page, "");
	}
	
	// ����¡ ������ ���ϱ� (�Ķ����: page, keyword)
	public PageInfoVO getPageInfo(String userId, int page, String keyword) {
		int countList = 5;		// ������ �� ���ڵ� ��
		int countPage = 5;	// ����¡ ��
		
		int totalCount = BusinessCardDAO.getInstance().selectBusinessCardCountUsingKeyword(userId, keyword); // �� ���ڵ� ����
		int totalPage = totalCount / countList; // �� ������ ����
		
		// (�� ���ڵ� �� / ������ �� ���ڵ� ��) ��� ��, ���� ���ڵ尡 ���� ���
		if(totalCount % countList > 0)
		    totalPage++;

		// ������ �������� ������ ���������� Ŭ ���
		if(page > totalPage)
		    page = totalPage;

		int startPage = ((page - 1) / countList) * countList + 1;
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
