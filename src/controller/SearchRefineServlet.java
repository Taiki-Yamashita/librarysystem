package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import service.BookService;

@WebServlet(urlPatterns = { "/searchRefine" })
public class SearchRefineServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		List<String> newBooks = getNewBooks(request);
		List<String> libraries = getLibraries(request);
		List<String> categories = getCategories(request);
		List<String> types = getTypes(request);

		List<Book> books = new BookService().getRefinedBooks(newBooks, libraries, categories, types);

		request.getSession().setAttribute("newBooks", newBooks);
		request.getSession().setAttribute("libraries", libraries);
		request.getSession().setAttribute("categories", categories);
		request.getSession().setAttribute("types", types);
		request.getSession().setAttribute("refinedBooks", books);

		response.sendRedirect("./search");
	}

	public List<String> getNewBooks(HttpServletRequest request){

		List<String> newBooks = new ArrayList<>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -31);
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());

		if(request.getParameter("newBooks") != null) newBooks.add(currentDate);
		else newBooks.add("1500-01-01 00:00:00");

		return newBooks;
	}

	public List<String> getLibraries(HttpServletRequest request){

		List<String> libraries = new ArrayList<>();
		if(request.getParameter("library1") != null) libraries.add(request.getParameter("library1"));
		if(request.getParameter("library2") != null) libraries.add(request.getParameter("library2"));
		if(request.getParameter("library3") != null) libraries.add(request.getParameter("library3"));
		if(request.getParameter("library4") != null) libraries.add(request.getParameter("library4"));
		if(request.getParameter("library5") != null) libraries.add(request.getParameter("library5"));

		return libraries;
	}

	public List<String> getCategories(HttpServletRequest request){

		List<String> categories = new ArrayList<>();
		if(request.getParameter("category1") != null) categories.add(request.getParameter("category1"));
		if(request.getParameter("category2") != null) categories.add(request.getParameter("category2"));
		if(request.getParameter("category3") != null) categories.add(request.getParameter("category3"));
		if(request.getParameter("category4") != null) categories.add(request.getParameter("category4"));
		if(request.getParameter("category5") != null) categories.add(request.getParameter("category5"));
		if(request.getParameter("category6") != null) categories.add(request.getParameter("category6"));
		if(request.getParameter("category7") != null) categories.add(request.getParameter("category7"));
		if(request.getParameter("category8") != null) categories.add(request.getParameter("category8"));
		if(request.getParameter("category9") != null) categories.add(request.getParameter("category9"));

		return categories;
	}

	public List<String> getTypes(HttpServletRequest request){

		List<String> types = new ArrayList<>();
		if(request.getParameter("type1") != null) types.add(request.getParameter("type1"));
		if(request.getParameter("type2") != null) types.add(request.getParameter("type2"));
		if(request.getParameter("type3") != null) types.add(request.getParameter("type3"));
		if(request.getParameter("type4") != null) types.add(request.getParameter("type4"));

		return types;
	}

}