package lex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lex.dao.LoginDAO;
import lex.entity.Member;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LoginDAO loginDAO = new LoginDAO();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Member member = new Member();
		member.setName(username);
		member.setPassword(password);
		HttpSession session = req.getSession();
		boolean flag = false;
		
		try {
			if (loginDAO.validate(member)) {
				flag = true;
				if (flag) {
					session.setAttribute("flag", flag);
				}
                res.sendRedirect("loginConsole.jsp");
                
			}else {
				if (!flag) {
					session.setAttribute("flag", flag);
				}
                res.sendRedirect("loginConsole.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
