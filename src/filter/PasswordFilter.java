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

@WebFilter("/renewPassword")
public class PasswordFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{

		String target = String.valueOf(((HttpServletRequest)request).getQueryString());
		HttpSession session =((HttpServletRequest)request).getSession();
		User loginUser = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
		if(loginUser == null) {
			List<String> messages = new ArrayList<String>();
			messages.add("指定されたURLは存在しません");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse)response).sendRedirect("./");
			return;
		}
		/*ログインしている場合*/
		int id =loginUser.getId();
		if(!target.equals("id="+id) && !target.matches(".*css$")){
			List<String> messages = new ArrayList<String>();
			messages.add("指定されたURLは存在しません");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse)response).sendRedirect("./");
			return;
		}
		chain.doFilter(request, response);
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
