package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.HttpSessionCartService;
import com.es.phoneshop.model.cart.OutOfStockException;
import com.es.phoneshop.model.product.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

@WebServlet("/products/*")
public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao = ArrayListProductDao.getInstance();
    private CartService cartService = HttpSessionCartService.getInstance();
    private RecentlyViewedService recentlyViewedService = HttpSessionRecentlyViewedService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] path = request.getPathInfo().split("/");
        Long productId = Long.valueOf(path[1]);
        Product product = productDao.getProduct(productId);

        request.setAttribute("product", product);

        if (path.length > 2 && path[2].equals("price-history")) {
            request.getRequestDispatcher("/WEB-INF/pages/productPriceHistory.jsp").forward(request, response);
        } else {
            request.setAttribute("cartItems", cartService.getCart(request.getSession()).getCartItems());
            request.setAttribute("recentlyViewed", recentlyViewedService.getRecentlyViewed(request.getSession()).getProducts());

            recentlyViewedService.add(request.getSession(), product);

            request.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = extractId(request);
        NumberFormat format = NumberFormat.getInstance(request.getLocale());

        try {
            Cart cart = cartService.getCart(request.getSession());
            int quantity = format.parse(request.getParameter("quantity")).intValue();
            if (quantity <= 0) {
                throw new IllegalStateException();
            }
            cartService.add(cart, productId, quantity);
        } catch (OutOfStockException e) {
            doGetWithErrorMessage("Error. Max quantity is " + productDao.getProduct(productId).getStock(), request, response);
            return;
        } catch (ParseException e) {
            doGetWithErrorMessage("Quantity should be a number", request, response);
            return;
        } catch (IllegalStateException e) {
            doGetWithErrorMessage("Quantity should be positive", request, response);
            return;
        }

        response.sendRedirect(request.getRequestURI() + "?success=true");
    }

    private void doGetWithErrorMessage(String errorMessage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("quantity", request.getParameter("quantity"));
        doGet(request, response);
    }

    private Long extractId(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        return Long.valueOf(path[1]);
    }
}
