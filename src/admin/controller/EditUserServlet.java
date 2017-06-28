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
import beans.User;
import service.LibraryService;
import service.UserService;

@WebServlet(urlPatterns = { "/admin/editUser" })
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Library> libraries = new LibraryService().selectAll();
		request.setAttribute("libraries", libraries);

		String userId = request.getParameter("id");

		User editUser = new UserService().selectUser(userId);

		request.setAttribute("editUser", editUser);
		request.getRequestDispatcher("editUser.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages)) {
			User editUser = getEditUser(request);
			request.setAttribute("editUser", editUser);
			new UserService().update(editUser);
			response.sendRedirect("./manageUser");
		}
		else {
			HttpSession session = request.getSession();
			User editUser2 = getEditUser(request);
			session.setAttribute("errorMessages", messages);

			List<Library> libraries = new LibraryService().selectAll();
			request.setAttribute("libraries", libraries);
			request.setAttribute("editUser", editUser2);
			//response.sendRedirect("./editUser");
			request.getRequestDispatcher("./editUser.jsp").forward(request, response);
		}

	}

	private User getEditUser(HttpServletRequest request) {
		String userId = request.getParameter("id");
		User editUser = new UserService().selectUser(userId);

		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setAddress(request.getParameter("address"));
		editUser.setTel(request.getParameter("tel"));
		editUser.setMail(request.getParameter("mail"));
		editUser.setPoint(request.getParameter("point"));
		editUser.setLibraryId(request.getParameter("libraryId"));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		//String mail = request.getParameter("mail");
		String libraryId = request.getParameter("libraryId");



		if (StringUtils.isBlank(name)) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isBlank(loginId)) {
			messages.add("ログインIDを入力してください");
		}else if (!loginId.matches("\\w{6,20}")){
			messages.add("ログインIDは半角英数字6～20文字で入力してください");
		}
		if (!StringUtils.isEmpty(password) && !password.matches("\\w{6,20}")) {
			messages.add("パスワードは半角英数字6～20文字で入力してください");
		}
		if (StringUtils.isBlank(address)) {
			messages.add("住所を入力してください");
		}
		if(tel.matches("\\d")) {
			messages.add("電話番号は数字のみで入力してください");
		}
		if (libraryId.matches("0")) {
			messages.add("図書館を選択してください");
		}
		if(messages.size() ==0) return true;
		else return false;

	}
}