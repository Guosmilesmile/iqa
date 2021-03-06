package cn.edu.xmu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.xmu.entity.User;

public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String userid ="";
		Cookie[] cookies =request.getCookies();
		for(Cookie cookie : cookies){
		    if(cookie.getName().equals("userid"))
		    	userid=cookie.getValue();
		}
		if (userid != null && !userid.equals("")) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendRedirect("/IQA/login.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
