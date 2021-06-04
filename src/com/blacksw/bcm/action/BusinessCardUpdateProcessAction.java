package com.blacksw.bcm.action;

import java.io.File;
import java.util.Enumeration;

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

import comblacksw.bcm.extra.RandomFileNamePolicy;

public class BusinessCardUpdateProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 검증
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return forward = new ActionForward("/signin", true);
		} 
		
		UserVO user = (UserVO) session.getAttribute("loginUser");
		int result = 0;
		
		// MultipartRequest 객체 초기화
		String uploadPath = "C:\\Users\\blacksw\\Desktop\\eclipse-workspace\\client-test2\\BusinessCardManagement\\WebContent\\upload\\";
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
		// 로그인 유저와 글 작성자가 일치한지 검증
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
					
		// DAO 호출
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		result = businessCardDAO.updateBusinessCard(businessCard);
		
		// new image 값이 들어오면 old image를 /upload 에서 지운다.
		System.out.println("oldCompanyCI : " + multi.getParameter("oldCompanyCI"));
		System.out.println("newCompanyCI : " + multi.getFilesystemName(file));
		if(!multi.getParameter("oldCompanyCI").equals(multi.getFilesystemName(file))) {
			File uploadfile = new File(uploadPath + multi.getParameter("oldCompanyCI"));
			if(uploadfile.exists()) {
				uploadfile.delete();
				System.out.println("파일 삭제 완료");
			} else {
				System.out.println("파일 삭제 실패");
			}
		}
		
		// 포워드 반환
		if(result != 0) {
			forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCard.getBusinessCardNo(), true);
		} else {
			forward = new ActionForward("/businessCardUpdate?businessCardNo=" + businessCard.getBusinessCardNo(), true);
		}
		
		return forward;
	}

}
