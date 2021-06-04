package com.blacksw.bcm.action;

import java.io.File;

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

		// �α��� ����
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return forward = new ActionForward("/signin", true);
		} 
		
		if(request.getParameter("userId") == null || request.getParameter("businessCardNo") == null || request.getParameter("companyCI") == null) {
			return null;
		}

		String uploadPath = request.getServletContext().getInitParameter("file-upload-ubuntu");
		UserVO user = (UserVO) session.getAttribute("loginUser");
		String userId = request.getParameter("userId");
		int businessCardNo = Integer.parseInt(request.getParameter("businessCardNo"));
		String companyCI = request.getParameter("companyCI");
		int result = 0;
		
		// �����ڿ� �ۼ��ڰ� �������� ���� ���
		if(!user.getId().equals(userId)) {
			return forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCardNo, true);
		} 

		// DAO ȣ��
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		result = businessCardDAO.deleteBusinessCard(businessCardNo, user.getId());
		
		// /upload ���� ���� ����
		File uploadfile = new File(uploadPath + companyCI);
		if(uploadfile.exists()) {
			uploadfile.delete();
			System.out.println("���� (" + companyCI + ") ���� �Ϸ�");
		} else {
			System.out.println("���� (" + companyCI +  ") ���� ����");
		}
		
		// ������ ��ȯ
		if(result != 0) {
			forward = new ActionForward("/businessCardList?page=1", true);
			System.out.println("BussinessCard ���� �Ϸ�");
		} else {
			forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCardNo, true);
			System.out.println("BussinessCard ���� ����");
		}
		
		return forward;
	}

}
