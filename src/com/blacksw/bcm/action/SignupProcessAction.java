package com.blacksw.bcm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blacksw.bcm.dao.UserDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.UserVO;

public class SignupProcessAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		ActionForward forward;
		
		// 파라미터 꺼내서 빈 객체 생성
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		UserVO user = new UserVO(id, pw, name);
		
		// DAO 호출
		UserDAO userDAO = UserDAO.getInstance();
		result = userDAO.insertUser(user);
		
		// 포워드 반환
		if(result != 0) {
			forward = new ActionForward("/signin", true);
		} else {
			forward = new ActionForward("/signup", true);
		}
		
		return forward;
	}

}
