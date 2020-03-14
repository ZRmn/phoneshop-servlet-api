package com.es.phoneshop.web;

import com.es.phoneshop.model.dos.DosService;
import com.es.phoneshop.model.dos.DosServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class DosFilter implements Filter {

    private final int TOO_MANY_REQUESTS = 429;

    private DosService dosService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dosService = DosServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = request.getRemoteAddr();

        if (dosService.isAllowed(ip)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).setStatus(TOO_MANY_REQUESTS);
        }
    }

    @Override
    public void destroy() {

    }
}
