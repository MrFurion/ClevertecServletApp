package by.clevertec.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class ClevertecFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();

        if(httpSession.getAttribute("user") == null){
            String login = httpServletRequest.getParameter("login");
            String password = httpServletRequest.getParameter("password");
            if ("admin".equalsIgnoreCase(login) && "admin".equalsIgnoreCase(password)){
                httpSession.setAttribute("user", "admin");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
