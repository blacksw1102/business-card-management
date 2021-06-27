package com.blacksw.bcm.action;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blacksw.bcm.dao.BusinessCardDAO;
import com.blacksw.bcm.dao.UserDAO;
import com.blacksw.bcm.extra.RandomFileNamePolicy;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;
import com.blacksw.bcm.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BusinessCardWriteProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// �α��� ����
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			forward = new ActionForward("/signin", true);
		} else {
			UserVO user = (UserVO) session.getAttribute("loginUser");
			int result = 0;
			
			// MultipartRequest ��ü �ʱ�ȭ
			String uploadPath = request.getServletContext().getRealPath("/upload");
			int fileSize = 5*1024*1024;
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					fileSize,
					"UTF-8",
					new RandomFileNamePolicy()
			);
			
			Enumeration<String> files = multi.getFileNames();
			String file =  files.nextElement();
			
			// �� ��ü ����
			BusinessCardVO businessCard = new BusinessCardVO();
			businessCard.setName(multi.getParameter("name"));
			businessCard.setCompanyName(multi.getParameter("companyName"));
			businessCard.setDepartment(multi.getParameter("department"));
			businessCard.setPosition(multi.getParameter("position"));
			businessCard.setEmail(multi.getParameter("email"));
			businessCard.setTel(multi.getParameter("tel"));
			businessCard.setPhone(multi.getParameter("phone"));
			businessCard.setAddress(multi.getParameter("address"));
			businessCard.setCompanyCI(multi.getFilesystemName(file));
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
