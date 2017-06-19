package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Library;
import beans.User;
import service.LibraryService;
import service.UserService;

@WebServlet(urlPatterns = {"/admin/addUser"})
public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Library> libraries = new LibraryService().selectAll();
		request.setAttribute("libraries", libraries);


		request.getRequestDispatcher("/admin/addUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		if (isValid(request, messages)) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			//user.setPassword(request.getParameter("checkPassword"));
			user.setAddress(request.getParameter("address"));
			user.setTel(request.getParameter("tel"));
			user.setMail(request.getParameter("mail"));
			user.setLibraryId(request.getParameter("libraryId"));
			new UserService().insert(user);

			response.sendRedirect("./manage");
		} else {
			session.setAttribute("errorMessages", messages);

			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);

			User newUser = getNewUser(request);
			request.setAttribute("newUser", newUser);
			request.getRequestDispatcher("/admin/addUser.jsp").forward(request, response);
		}

	}

	private User getNewUser(HttpServletRequest request) {
		User newUser = new User();;

		newUser.setName(request.getParameter("name"));
		newUser.setLoginId(request.getParameter("loginId"));
		newUser.setPassword(request.getParameter("password"));
		//newUser.setPassword(request.getParameter("checkPassword"));
		newUser.setAddress(request.getParameter("address"));
		newUser.setTel(request.getParameter("tel"));
		newUser.setMail(request.getParameter("mail"));
		newUser.setLibraryId(request.getParameter("libraryId"));

		return newUser;

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
			return false;
		}

		return true;

	}
}
