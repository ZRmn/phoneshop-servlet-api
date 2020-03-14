package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.HttpSessionCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/delete/*")
public class CartItemDeleteServlet extends HttpServlet {

    private CartService cartService = HttpSessionCartService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getServletContext().getContextPath() + "/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = extractId(request);
        Cart cart = cartService.getCart(request.getSession());
        cartService.delete(cart, productId);

        doGet(request, response);
    }

    private Long extractId(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        return Long.valueOf(path[1]);
    }
}
