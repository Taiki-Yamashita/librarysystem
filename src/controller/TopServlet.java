package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Library;
import beans.Notification;
import service.LibraryService;
import service.NotificationService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Notification> informations = new NotificationService().selectAll();
		List<Library> libraries = new LibraryService().selectAll();

		request.setAttribute("informations", informations);
		request.setAttribute("libraries", libraries);

		/*ページ遷移管理*/
		request.setAttribute("pageCountList", getPageCount(informations.size()));
		if(request.getParameter("pageNumber") == null) request.setAttribute("pageNumber", "1");
		else request.setAttribute("pageNumber", request.getParameter("pageNumber"));

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	public List<String> getPageCount(int informationCount){

		List<String> pageCountList = new ArrayList<>();
		int pageCount = informationCount / 4 + 1;
		if(informationCount % 4 == 0) pageCount--;

		for(int i = 1; i <= pageCount; i++){
			pageCountList.add(String.valueOf(i));
		}

		return pageCountList;
	}

}