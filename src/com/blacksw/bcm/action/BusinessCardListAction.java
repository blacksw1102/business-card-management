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
		// 유저가 로그인 상태인지 검증
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			forward = new ActionForward("/signin", true);
		} else {
			UserVO user = (UserVO) session.getAttribute("loginUser");
			String keyword = request.getParameter("keyword");
			int page;
			
			// 페이지 파라미터 초기화
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
	
	// businessCardList 데이터 구하기
	public ArrayList<BusinessCardVO> getBusinessCardList(String userId, int page, String keyword) {
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		ArrayList<BusinessCardVO> businessCardList = businessCardDAO.selectBusinessCardList(userId, page, keyword);
		return businessCardList;
	}
	
	// 페이징 데이터 구하기 (파라미터:page)
	public PageInfoVO getPageInfo(String userId, int page) {
		return getPageInfo(userId, page, "");
	}
	
	// 페이징 데이터 구하기 (파라미터: page, keyword)
	public PageInfoVO getPageInfo(String userId, int page, String keyword) {
		int countList = 5;		// 페이지 당 레코드 수
		int countPage = 5;	// 페이징 수
		
		int totalCount = BusinessCardDAO.getInstance().selectBusinessCardCountUsingKeyword(userId, keyword); // 총 레코드 개수
		int totalPage = totalCount / countList; // 총 페이지 개수
		
		// (총 레코드 수 / 페이지 당 레코드 수) 계산 후, 남는 레코드가 있을 경우
		if(totalCount % countList > 0)
		    totalPage++;

		// 선택한 페이지가 마지막 페이지보다 클 경우
		if(page > totalPage)
		    page = totalPage;

		int startPage = ((page - 1) / countList) * countList + 1;
		int endPage = startPage + countPage - 1;

		// endPage 값이 총 페이지 값을 넘어설 경우
		if (endPage > totalPage) {
		    endPage = totalPage;
		}

		// 페이징 데이터 저장
		PageInfoVO pageInfo = new PageInfoVO();
		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(countPage);
		pageInfo.setMaxPage(totalPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	

		return pageInfo;
	}

}
