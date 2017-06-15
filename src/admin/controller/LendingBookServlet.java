package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;



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


		int lending = Integer.parseInt(request.getParameter("id"));
		int num = Integer.parseInt(request.getParameter("num"));
		new BookService().lendingBook(lending, num);

		System.out.println(lending);

			response.sendRedirect("./manageBook");
	}
}
