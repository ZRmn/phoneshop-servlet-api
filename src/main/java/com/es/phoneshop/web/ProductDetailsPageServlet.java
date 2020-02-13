package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/*")
public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] path = request.getPathInfo().split("/");
        Long productId = Long.valueOf(path[1]);

        request.setAttribute("product", productDao.getProduct(productId));

        if(path.length > 2 && path[2].equals("price-history"))
        {
            request.getRequestDispatcher("/WEB-INF/pages/productPriceHistory.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(request, response);
        }
    }
}
