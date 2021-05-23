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

public class BusinessCardUpdateProcessAction implements Action {
	
	private ActionForward forward;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");

		// 로그인 검증
		if(user == null) {
			return forward = new ActionForward("/signin", true);
		} 
		
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
		if(user.getId().equals(multi.getParameter("userId")) == false) {
			// 수정자와 작성자가 동일하지 않을 경우

		} else {
			// 수정자와 작성자가 동일한 경우
			// 파라미터 꺼내서 빈 객체 생성
			int result = 0;
			
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
			businessCard.setCompanyCI(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			businessCard.setUserId(user.getId());
			
			// DAO 호출
			BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
			result = businessCardDAO.updateBusinessCard(businessCard);
			
			// 포워드 반환
			if(result != 0) {
				forward = new ActionForward("/businessCardDetail?businessCardNo=" + businessCard.getBusinessCardNo(), true);
			} else {
				forward = new ActionForward("/businessCardUpdate?businessCardNo=" + businessCard.getBusinessCardNo(), true);
			}
		}
		
		return forward;
	}

}
