package lex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lex.dao.UserDAO;
import lex.entity.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	@Override
    public void init() {
        userDAO = new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getServletPath();
		
		try {
			switch (action) {
			case "/newForm":
				showNewFrom(req, res);
				break;
			case "/insert":
				createUser(req, res);
				break;
			case "/delete":
				deleteUser(req, res);
				break;
			case "/edit":	
				showEditForm(req, res);
				break;
			case "/update":
				updateUser(req, res);
				break;
			default:
				// show list
				getAll(req, res);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showNewFrom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(req, res);
	}
	
	public void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		String theId = req.getParameter("theId");
		User exsitUser = userDAO.findById(Integer.parseInt(theId));
		req.setAttribute("user", exsitUser);
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(req, res);
	}
	
	public void getAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<User> listUsers = userDAO.getAll();
		req.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(req, res);
	}

	public void createUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		User user = new User(name, email, country);
		userDAO.createUser(user);
		res.sendRedirect("list");
	}

	public void updateUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		Integer theId = Integer.parseInt(req.getParameter("theId"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		User user = new User(theId, name, email, country);
		userDAO.updateUser(user);
		res.sendRedirect("list");
	}
	
	public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer theId = Integer.parseInt(req.getParameter("theId"));
		userDAO.deleteUser(theId);
		res.sendRedirect("list");
	}
}
