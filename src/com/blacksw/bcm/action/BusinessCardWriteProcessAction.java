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

public class BusinessCardWriteProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			forward = new ActionForward("/signin", true);
		} else {
			UserVO user = (UserVO) session.getAttribute("loginUser");
			int result = 0;
			
			/*
			// �Ķ���� ������ �� ��ü ����
			/*
			String saveFolder="/ciUpload";
			String realFolder = request.getServletContext().getRealPath(saveFolder);
			int fileSize = 5*1024*1024;
			MultipartRequest multi = new MultipartRequest(
					request,
					realFolder,
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			*/
			
			BusinessCardVO businessCard = new BusinessCardVO();
			businessCard.setName(request.getParameter("name"));
			businessCard.setCompanyName(request.getParameter("companyName"));
			businessCard.setDepartment(request.getParameter("department"));
			businessCard.setPosition(request.getParameter("position"));
			businessCard.setEmail(request.getParameter("email"));
			businessCard.setTel(request.getParameter("tel"));
			businessCard.setPhone(request.getParameter("phone"));
			businessCard.setAddress(request.getParameter("address"));
			businessCard.setCompanyCI(request.getParameter("companyCI"));
			businessCard.setUserId(user.getId());
			
			// DAO ȣ��
			BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
			result = businessCardDAO.insertBusinessCard(businessCard);
			
			// ������ ��ȯ
			if(result != 0) {
				forward = new ActionForward("/businessCardList?page=1", true);
			} else {
				forward = new ActionForward("/businessCardWrite", true);
			}
		}
		return forward;
	}

}
