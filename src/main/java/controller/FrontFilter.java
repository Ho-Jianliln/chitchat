package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class FrontFilter implements javax.servlet.Filter {
    public void init(FilterConfig config) throws ServletException {
    }
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httprequest = (HttpServletRequest) request;
        String uri =httprequest.getRequestURI();
        if(uri.contains("/login.jsp")||uri.contains("/register.jsp")||uri.contains("/forgetPass.jsp")
                ||uri.contains("/FrontServlet")||uri.contains("/VcodeServlet")|| uri.contains("/css/")
                || uri.contains("/js/") || uri.contains("/fonts/")){
            chain.doFilter(request, response);
        }else{
            Object user = httprequest.getSession().getAttribute("account");
            if(user!=null){
                chain.doFilter(request, response);
            }else{
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }

    }
}
