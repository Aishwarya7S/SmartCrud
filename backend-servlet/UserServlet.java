package com.smartcrud.servlet;

import com.smartcrud.DAO.UserDAO;
import com.smartcrud.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userdao;
    
    public UserServlet() {
        this.userdao = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
		try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd = request.getRequestDispatcher("form.jsp");
	    rd.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String country = request.getParameter("country");
	    
	    User newUser = new User(name, email, country);
	    userdao.insert(newUser);
	    response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    User existingUser = userdao.selectById(id);
	    RequestDispatcher rd = request.getRequestDispatcher("form.jsp");
	    request.setAttribute("user", existingUser);
	    rd.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String country = request.getParameter("country");

	    User user = new User(id, name, email, country);
	    userdao.update(user);
	    response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    userdao.delete(id);
	    response.sendRedirect("list");
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    List<User> listUser = userdao.selectAll();
	    request.setAttribute("userList", listUser);
	    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
	}
  
}
