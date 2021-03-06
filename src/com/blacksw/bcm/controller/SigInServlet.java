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
import com.blacksw.bcm.action.SigninFormAction;
import com.blacksw.bcm.action.SigninProcessAction;
import com.blacksw.bcm.action.SignupFormAction;
import com.blacksw.bcm.vo.ActionForward;
import com.sun.glass.ui.Application;

@WebServlet("/signin")
public class SigInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActionForward forward = null;
	
    public SigInServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SignInServlet doGet 진입");
		setAction(request, response, new SigninFormAction());
		forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SignInServlet doPost 진입");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		setAction(request, response, new SigninProcessAction());
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
		if(forward == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
	
}
