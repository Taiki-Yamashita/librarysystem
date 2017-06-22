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
		nortification.setTitle(request.getParameter("title"));
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

			Notification newNotification = getNewNotification(request);
			request.setAttribute("newNotification", newNotification);


			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/admin/notification.jsp").forward(request, response);
		}
	}
	private Notification getNewNotification (HttpServletRequest request)
			throws IOException, ServletException {

		Notification newNotification = new Notification();
		newNotification.setTitle(request.getParameter("title"));
		newNotification.setMessage(request.getParameter("message"));
		newNotification.setLibraryId(request.getParameter("libraryId"));

		return newNotification;

	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String message = request.getParameter("message");
		String title = request.getParameter("title");

		if(StringUtils.isBlank(message) == true) {
				messages.add("投稿の中身を入力してください");
		}
		if(StringUtils.isBlank(title) == true) {
			messages.add("タイトルを入力してください");
		}
		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
}
