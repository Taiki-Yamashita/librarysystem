package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import admin.service.RecieveService;
import beans.Require;
import service.RequireService;

@WebServlet(urlPatterns = {"/admin/receive"})
public class ReceiveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Require> receives = new RecieveService().select();
		//List<Require> receives = new RecieveService().select(Integer.parseInt(request.getParameter("num")));

		request.setAttribute("receives", receives);
		request.getRequestDispatcher("./receive.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {
		//未読既読送信
		if(request.getParameter("flag") != null &&
				(request.getParameter("receiveId") != null)) {
			String[] idList = request.getParameterValues("receiveId");
			for(String id: idList) {
				new RecieveService().update(1, Integer.parseInt(id));
			}

		}
		if(request.getParameter("flag") != null &&
				(request.getParameter("receiveId2") != null)){
			String[] idList2 = request.getParameterValues("receiveId2");
			for(String id2: idList2) {
				new RecieveService().update(0, Integer.parseInt(id2));
			}
		}

		//問い合わせ削除
		if(!StringUtils.isEmpty(request.getParameter("deleteId"))) {
			new RequireService().delete(request.getParameter("deleteId"));
		}

		if(request.getParameter("num") == null) {
			response.sendRedirect("./receive");
			return;
		}

		//フリーワードかつ既読or未読絞込み
		if(request.getParameter("num") != null) {

			String freeWord = request.getParameter("freeWord");
			String num = request.getParameter("num");

			List<Require> selectedBooks = new RecieveService().getSelectedBooks(freeWord, num);

			if(num.equals("0") && selectedBooks.isEmpty()){
				request.getSession().setAttribute("errorMessages", "未読はありません");
			}
			if(num.equals("1") && selectedBooks.isEmpty()){
				request.getSession().setAttribute("errorMessages", "既読はありません");
			}

			request.setAttribute("num", num);
			request.setAttribute("freeWord", freeWord);
			request.setAttribute("books", selectedBooks);
			//response.sendRedirect("./receive");
			//response.sendRedirect("./receive");
			request.getRequestDispatcher("./receive.jsp").forward(request, response);
			return;
		}

	}
}

