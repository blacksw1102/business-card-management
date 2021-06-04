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

		// 로그인 검증
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
		
		// 수정자와 작성자가 동일하지 않을 경우
		if(!user.getId().equals(userId)) {
			return forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCardNo, true);
		} 

		// DAO 호출
		BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
		result = businessCardDAO.deleteBusinessCard(businessCardNo, user.getId());
		
		// /upload 에서 파일 제거
		File uploadfile = new File(uploadPath + companyCI);
		if(uploadfile.exists()) {
			uploadfile.delete();
			System.out.println("파일 (" + companyCI + ") 삭제 완료");
		} else {
			System.out.println("파일 (" + companyCI +  ") 삭제 실패");
		}
		
		// 포워드 반환
		if(result != 0) {
			forward = new ActionForward("/businessCardList?page=1", true);
			System.out.println("BussinessCard 삭제 완료");
		} else {
			forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCardNo, true);
			System.out.println("BussinessCard 삭제 실패");
		}
		
		return forward;
	}

}
