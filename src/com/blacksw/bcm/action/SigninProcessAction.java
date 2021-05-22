package com.blacksw.bcm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.UserDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.LoginVO;
import com.blacksw.bcm.vo.UserVO;

public class SigninProcessAction implements Action {

	private ActionForward forward;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id  = request.getParameter("id");
		String pw  = request.getParameter("pw");
		LoginVO login = new LoginVO(id, pw);
		
		UserDAO userDAO = UserDAO.getInstance();
		UserVO user = userDAO.selectOneUser(login);
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			forward = new ActionForward("/businessCardList", true);
		} else {
			forward = new ActionForward("/signin", true);
		}
		
		return forward;
	}

}
