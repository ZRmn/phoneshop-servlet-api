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

@WebServlet("/miniCart")
public class MiniCartServlet extends HttpServlet {

    private CartService cartService = HttpSessionCartService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        request.setAttribute("totalQuantity", cartService.calculateTotalQuantity(cart));
        request.setAttribute("totalPrice", cartService.calculateTotalPrice(cart));
        request.getRequestDispatcher("/WEB-INF/pages/miniCart.jsp").include(request, response);
    }
}
