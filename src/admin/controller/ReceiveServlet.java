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
			response.sendRedirect("./receive");
			return;
		}

//既読or未読表示
		if (request.getParameter("num") == null) {
			response.sendRedirect("./receive");
			return;
		}
		else if(request.getParameter("num").matches("0") || request.getParameter("num").matches("1")) {
			List<Require> receives = new RecieveService().select(Integer.parseInt(request.getParameter("num")));
			request.setAttribute("receives", receives);
			request.setAttribute("num", request.getParameter("num"));
			request.getRequestDispatcher("./receive.jsp").forward(request, response);
		}
		else if(request.getParameter("num").matches("2")) {
			List<Require> receives = new RecieveService().select();
			request.setAttribute("receives", receives);
			request.getRequestDispatcher("./receive.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("./receive");
			return;
		}

	}
}

