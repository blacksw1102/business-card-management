package com.blacksw.bcm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blacksw.bcm.action.Action;
import com.blacksw.bcm.action.BusinessCardDeleteProcessAction;
import com.blacksw.bcm.action.BusinessCardDetailProcessAction;
import com.blacksw.bcm.action.BusinessCardListAction;
import com.blacksw.bcm.action.BusinessCardUpdateFormAction;
import com.blacksw.bcm.action.BusinessCardUpdateProcessAction;
import com.blacksw.bcm.vo.ActionForward;
import com.blacksw.bcm.vo.BusinessCardVO;

@WebServlet("/businessCardUpdate")
public class BusinessCardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActionForward forward;
	
    public BusinessCardDeleteServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		setAction(request, response, new BusinessCardDeleteProcessAction());
		forward(request, response);
	}
	
	// 서비스 지정
	public void setAction(HttpServletRequest request, HttpServletResponse response, Action action) {
		try {
			this.forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 포워딩
	public void forward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

}
