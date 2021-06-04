package com.blacksw.bcm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.BusinessCardDAO;
import com.blacksw.bcm.dao.UserDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;
import com.blacksw.bcm.vo.LoginVO;
import com.blacksw.bcm.vo.UserVO;

public class BusinessCardDetailProcessAction implements Action {

	private ActionForward forward = null;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null ) {
			forward = new ActionForward("/signin", true);
		}
		
		if(request.getParameter("businessCardNo") != null) {
			int businessCardNo = Integer.parseInt(request.getParameter("businessCardNo"));
			BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
			BusinessCardVO businessCard = businessCardDAO.selectOneBusinessCard(businessCardNo);
			
			if(businessCard != null) {
				request.setAttribute("businessCard", businessCard);
				forward = new ActionForward("/WEB-INF/view/businessCard/businessCardDetail.jsp", false);
			}
		}
		
		return forward;
	}

}
