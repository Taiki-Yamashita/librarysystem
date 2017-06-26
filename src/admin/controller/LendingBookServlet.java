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

		request.getRequestDispatcher("test.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		String bookId = request.getParameter("bookId");
		String num = request.getParameter("num");
		Circulation circulation = new Circulation();

		if(num.matches("1")) {
			circulation.setUserId(request.getParameter("userId"));
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));
			new CirculationService().insert(circulation);
			new CirculationService().lending(circulation);
			new CirculationService().lendingFlag(bookId);
			new BookService().lendingBook(bookId);
		} else {
			circulation.setUserId(request.getParameter("userId"));
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));

			new CirculationService().returning(circulation, num);
			new CirculationService().returningFlag(bookId);
			new BookService().returningBook(bookId);
		}


		response.sendRedirect("/LibrarySystem/test");
	}

}
