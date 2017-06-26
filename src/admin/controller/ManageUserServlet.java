package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Library;
import beans.User;
import service.LibraryService;
import service.UserService;


@WebServlet(urlPatterns = {"/admin/manageUser"})
public class ManageUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> users = new UserService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

		if(request.getParameter("isSearching") != null){

			String freeWord = request.getParameter("freeWord");
			String selectedLibrary = request.getParameter("selectedLibrary");

			request.setAttribute("freeWord", freeWord);
			request.setAttribute("selectedLibrary", selectedLibrary);

			users = new UserService().getRefinedUser(freeWord, selectedLibrary);
		}

		request.setAttribute("users", users);
		request.setAttribute("libraries", libraries);

		request.getRequestDispatcher("/admin/manageUser.jsp").forward(request, response);

	}

}
