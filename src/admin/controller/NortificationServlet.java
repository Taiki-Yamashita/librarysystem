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
import beans.Notification;
import service.LibraryService;
import service.NotificationService;

@WebServlet(urlPatterns = { "/admin/notification" })
public class NortificationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Library> libraries = new LibraryService().selectAll();
		String libraryId = request.getParameter("libraryId");

		request.setAttribute("libraries", libraries);
		request.setAttribute("libraryId", libraryId);

		request.getRequestDispatcher("/admin/notification.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{

		Notification nortification = new Notification();
		nortification.setMessage(request.getParameter("message"));
		nortification.setLibraryId(request.getParameter("libraryId"));
		nortification.setRegisteredDate(request.getParameter("registeredDate"));

		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages ) ==true) {

		new NotificationService().insert(nortification);

		response.sendRedirect("./manage");
		}else{
			HttpSession session = request.getSession();
			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/admin/notification.jsp").forward(request, response);
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages){
		String message = request.getParameter("message");

		if(StringUtils.isBlank(message) == true) {
				messages.add("投稿の中身を入力してください");
		}
		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
}
