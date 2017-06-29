package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

<<<<<<< HEAD
@WebFilter(urlPatterns = {"/require", "/user/*", "/require"})
=======
<<<<<<< HEAD
=======

>>>>>>> cbaf9890aa2dc620587523dd356dcc61c1e47253

@WebFilter(urlPatterns = {"/require", "/user/*", "/require","/renewPassword"})

>>>>>>> 2966823eb967244563c7186712f586ffca0cf34d
public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{


		String target = String.valueOf(((HttpServletRequest)request).getRequestURI());

		HttpSession session =((HttpServletRequest)request).getSession();


		if (!isLogined((HttpServletRequest)request)){
			/* まだ認証されていない */
				if(!target.equals("/LibrarySystem/login") && !target.matches(".*css$")){
					List<String> messages = new ArrayList<String>();
					messages.add("ログインしてください");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse)response).sendRedirect("./login");
					return;
				}

		}
		chain.doFilter(request, response);
	}

	public boolean isLogined(HttpServletRequest request){

		User user = (User)request.getSession().getAttribute("loginUser");
		if(user == null) return false;
		return true;
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
