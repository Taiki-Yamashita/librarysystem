package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Circulation;
import beans.Reservation;
import beans.User;
import service.BookService;
import service.CirculationService;
import service.ReservationService;
import service.UserService;



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
		int userId = Integer.parseInt(request.getParameter("userId"));
		String num = request.getParameter("num");
		Circulation circulation = new Circulation();
		User user = new UserService().select(userId);
		int point = Integer.parseInt(user.getPoint());
		new UserService().point(point, userId);

		if(num.matches("1")) {
			circulation.setUserId(request.getParameter("userId"));
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));

			/*circulationsに本を追加*/
			new CirculationService().insert(circulation);

			/*booksのフラグを貸し出し中に変更*/
			new CirculationService().lendingFlag(bookId);

			/*ユーザーが予約していたら予約を解除する*/
			Reservation userReserving = new ReservationService().selectUserReserving(circulation.getUserId());
			if(userReserving.getUserId() != null){
				new ReservationService().updateDeliveringStatus(userReserving.getId());

				/*予約解除で予約が0になったらbooksのreservingを0に*/
				List<Reservation> reserving = new ReservationService().selectReservingBook(circulation.getBookId());
				if(reserving == null) new BookService().updateReserving(circulation.getBookId());
			}

		} else {
			circulation.setUserId(request.getParameter("userId"));
			circulation.setBookId(request.getParameter("bookId"));
			circulation.setLibraryId(request.getParameter("libraryId"));
			user.setPoint(request.getParameter("userId"));

			new CirculationService().returning(circulation, num);
			new CirculationService().returningFlag(bookId);
			new BookService().returningBook(bookId);
		}

		response.sendRedirect("/LibrarySystem/test");
	}

}
