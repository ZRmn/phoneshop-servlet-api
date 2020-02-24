package com.es.phoneshop.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageNotFound")
public class ErrorPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        response.setStatus(404);
        request.setAttribute("code", throwable.getMessage());
        request.getRequestDispatcher("/WEB-INF/pages/productNotFound.jsp").forward(request, response);
    }
}
