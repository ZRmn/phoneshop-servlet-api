package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.HttpSessionRecentlyViewedService;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.product.RecentlyViewedService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductListPageServlet extends HttpServlet {

    private ProductDao productDao = ArrayListProductDao.getInstance();
    private RecentlyViewedService recentlyViewedService = HttpSessionRecentlyViewedService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String sortBy = request.getParameter("sortBy");
        String orderBy = request.getParameter("orderBy");

        request.setAttribute("products", productDao.findProducts(query, sortBy, orderBy));
        request.setAttribute("recentlyViewed", recentlyViewedService.getRecentlyViewed(request.getSession()).getProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
