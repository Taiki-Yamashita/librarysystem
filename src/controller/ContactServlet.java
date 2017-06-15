package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contact;
import beans.User;
import service.ContactService;

public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		User user = (User) request.getSession().getAttribute("loginUser");

		//request.setAttribute("user", user);

		Contact contact = new Contact();

		contact.setUserId(String.valueOf(user.getId()));
		contact.setBookId(request.getParameter("bookId"));
		contact.setContactDate(request.getParameter("contactDate"));
		contact.setLimitedDate(request.getParameter("limitedDate"));
		contact.setFinishing(request.getParameter("finishing"));

		new ContactService().insert(contact);


		//response.sendRedirect("./addBook");
	}
}
