package com.blacksw.bcm.action;

import java.io.File;
import java.util.Enumeration;

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

public class BusinessCardUpdateProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// �α��� ����
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return forward = new ActionForward("/signin", true);
		} 
		
		UserVO user = (UserVO) session.getAttribute("loginUser");
		int result = 0;
		
		// MultipartRequest ��ü �ʱ�ȭ
		String uploadPath = request.getServletContext().getRealPath("/upload");
		System.out.println("upload path : " + uploadPath);
		int fileSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadPath,
				fileSize,
				"UTF-8",
				new RandomFileNamePolicy()
		);
		
		System.out.println("loginUserId : " + ((UserVO) session.getAttribute("loginUser")).getId());
		System.out.println("articleUserId : " + multi.getParameter("userId"));
		// �α��� ������ �� �ۼ��ڰ� ��ġ���� ����
		if(!((UserVO) session.getAttribute("loginUser")).getId().equals(multi.getParameter("userId"))) {
			return forward = new ActionForward("/businessCardList?page=1", true);
		}
		
		Enumeration<String> files = multi.getFileNames();
		String file =  files.nextElement();
		
		BusinessCardVO businessCard = new BusinessCardVO();
		businessCard.setBusinessCardNo(Integer.parseInt(multi.getParameter("businessCardNo")));
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
		result = businessCardDAO.updateBusinessCard(businessCard);
		
		// new image ���� ������ old image�� /upload ���� �����.
		System.out.println("oldCompanyCI : " + multi.getParameter("oldCompanyCI"));
		System.out.println("newCompanyCI : " + multi.getFilesystemName(file));
		if(!multi.getParameter("oldCompanyCI").equals(multi.getFilesystemName(file))) {
			File uploadfile = new File(uploadPath + File.separator + multi.getParameter("oldCompanyCI"));
			if(uploadfile.exists()) {
				uploadfile.delete();
				System.out.println("���� ���� �Ϸ�");
			} else {
				System.out.println("���� ���� ����");
			}
		}
		
		// ������ ��ȯ
		if(result != 0) {
			forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCard.getBusinessCardNo(), true);
		} else {
			forward = new ActionForward("/businessCardUpdate?businessCardNo=" + businessCard.getBusinessCardNo(), true);
		}
		
		return forward;
	}

}
