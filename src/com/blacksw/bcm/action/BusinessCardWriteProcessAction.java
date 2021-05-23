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
			
			// 파라미터 꺼내서 빈 객체 생성
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
			
			BusinessCardVO businessCard = new BusinessCardVO();
			businessCard.setName(multi.getParameter("name"));
			businessCard.setCompanyName(multi.getParameter("companyName"));
			businessCard.setDepartment(multi.getParameter("department"));
			businessCard.setPosition(multi.getParameter("position"));
			businessCard.setEmail(multi.getParameter("email"));
			businessCard.setTel(multi.getParameter("tel"));
			businessCard.setPhone(multi.getParameter("phone"));
			businessCard.setAddress(multi.getParameter("address"));
//			String name = (String) multi.getFileNames().nextElement();
//			String filename = multi.getFilesystemName(name);
//			String original = multi.getOriginalFileName(name);
//			String type = multi.getContentType(name);
//			System.out.println("name : " + name);
//			System.out.println("filename : " + filename);
//			System.out.println("original : " + original);
//			System.out.println("type : " + type);
			businessCard.setCompanyCI(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			businessCard.setUserId(user.getId());
			
			
			// DAO 호출
			BusinessCardDAO businessCardDAO = BusinessCardDAO.getInstance();
			result = businessCardDAO.insertBusinessCard(businessCard);
			
			// 포워드 반환
			if(result != 0) {
				forward = new ActionForward("/businessCardList", true);
			} else {
				forward = new ActionForward("/businessCardWrite", true);
			}
		}
		
		return forward;
	}

}
