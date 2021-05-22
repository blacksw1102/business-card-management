package com.blacksw.bcm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blacksw.bcm.action.Action;
import com.blacksw.bcm.action.BusinessCardListAction;
import com.blacksw.bcm.vo.ActionForward;

@WebServlet("/businessCardList")
public class BusinessCardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActionForward forward;
	
    public BusinessCardListServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAction(request, response, new BusinessCardListAction());
		forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
