package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;




@WebServlet(urlPatterns = { "/admin/editUser" })
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("id");

		User editUser = new UserService().selectUser(userId);

		request.setAttribute("editUser", editUser);
		request.getRequestDispatcher("editUser.jsp").forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		User editUser = getEditUser(request);
		request.setAttribute("editUser", editUser);

		new UserService().update(editUser);

		response.sendRedirect("./manageUser");

	}

		private User getEditUser(HttpServletRequest request) {

			String userId = request.getParameter("id");
			//int user_id = Integer.parseInt(userId);
			User editUser = new UserService().selectUser(userId);


			editUser.setLoginId(request.getParameter("loginId"));
			editUser.setPassword(request.getParameter("password"));
			editUser.setName(request.getParameter("name"));
			editUser.setAddress(request.getParameter("address"));
			editUser.setTel(request.getParameter("tel"));
			editUser.setMail(request.getParameter("mail"));
			editUser.setPoint(request.getParameter("point"));
			editUser.setRegisterDate(request.getParameter("registerDate"));
			editUser.setLibraryId(request.getParameter("libraryId"));




		//	editBook.setDepartment_id(Integer.parseInt(request.getParameter("department")));

			return editUser;
		}
	}