package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Circulation;
import service.BookService;
import service.CirculationService;



@WebServlet(urlPatterns = { "/admin/lendingBook" })
public class LendingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("manageBook.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		Circulation circulation = new Circulation();
		circulation.setUserId(request.getParameter("userId"));
		circulation.setBookId(request.getParameter("bookId"));
		circulation.setLibraryId(request.getParameter("libraryId"));
		System.out.println(circulation.getBookId());
		System.out.println(request.getParameter("userId"));
		new CirculationService().insert(circulation);


		int lending = Integer.parseInt(request.getParameter("bookId"));
		int num = Integer.parseInt(request.getParameter("num"));
		new BookService().lendingBook(lending, num);

		System.out.println(lending);


		response.sendRedirect("./manageBook");
	}
}
