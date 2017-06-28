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

@WebFilter("/admin/*")
public class ManageFilter implements Filter {

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException{
			String target = String.valueOf(((HttpServletRequest)request).getRequestURI());
			HttpSession session =((HttpServletRequest)request).getSession();
			List<String> messages = new ArrayList<String>();
			User loginUser = (User)session.getAttribute("loginUser");
			if(loginUser == null) {
				messages.add("指定されたURLは存在しません");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse)response).sendRedirect("../");
				return;
			}
			if (loginUser.getId() != 0 ){
				/* まだ認証されていない */
					messages.add("指定されたURLは存在しません");
					session.setAttribute("errorMessages", messages);
					session.setAttribute("target", target);

					((HttpServletResponse)response).sendRedirect("../");
					return;
				//}
			}
			chain.doFilter(request, response);
		}

//		public boolean isLogined(HttpServletRequest request){
//
//			User user = (User)request.getSession().getAttribute("loginUser");
//			if(Integer.parseInt(user.getDepartment_id()) != 1) return false;
//			else return true;
//		}

		@Override
		public void init(FilterConfig paramFilterConfig) throws ServletException {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void destroy() {
			// TODO 自動生成されたメソッド・スタブ

		}

	}

