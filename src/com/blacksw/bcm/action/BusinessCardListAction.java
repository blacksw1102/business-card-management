package com.blacksw.bcm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.vo.ActionForward;

public class BusinessCardListAction implements Action {

	private ActionForward forward;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			forward = new ActionForward("/businessCard/businessCardList.jsp", false);
		} else {
			forward = new ActionForward("/signin", true);
		}
		
		return forward;
	}

}
