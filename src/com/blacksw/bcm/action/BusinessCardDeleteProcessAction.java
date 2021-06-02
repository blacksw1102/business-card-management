package com.blacksw.bcm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.BusinessCardDAO;
import com.blacksw.bcm.dao.UserDAO;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;
import com.blacksw.bcm.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BusinessCardDeleteProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");

		// �α��� ����
		if(user == null) {
			return forward = new ActionForward("/signin", true);
		} 
		
		if(user.getId().equals(request.getParameter("userId")) == false) {
			// �����ڿ� �ۼ��ڰ� �������� ���� ���

		} else {
			// �����ڿ� �ۼ��ڰ� ������ ���
			// �Ķ���� ������ �� ��ü ����
			int businessCardNo = Integer.parseInt(request.getParameter("businessCardNo"));
			int result = 0;

			// DAO ȣ��
			BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
			result = businessCardDAO.deleteBusinessCard(businessCardNo, user.getId());
			
			// ������ ��ȯ
			if(result != 0) {
				forward = new ActionForward("/businessCardListl?page=1", true);
			} else {
				forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCardNo, true);
			}
		}
		
		return forward;
	}

}
