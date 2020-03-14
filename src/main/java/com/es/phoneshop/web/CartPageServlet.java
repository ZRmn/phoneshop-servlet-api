package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.HttpSessionCartService;
import com.es.phoneshop.model.cart.OutOfStockException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Objects;

@WebServlet("/cart")
public class CartPageServlet extends HttpServlet {

    private CartService cartService = HttpSessionCartService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        request.setAttribute("cart", cart);
        request.setAttribute("totalPrice", cartService.calculateTotalPrice(cart));
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        NumberFormat format = NumberFormat.getInstance(request.getLocale());
        Cart cart = cartService.getCart(request.getSession());
        String[] errors = updateItems(cart, productIds, quantities, format);
        boolean isSuccessfully = Arrays.stream(errors).allMatch(Objects::isNull);

        if (isSuccessfully) {
            response.sendRedirect(request.getRequestURI() + "?success=true");
        } else {
            request.setAttribute("quantities", quantities);
            request.setAttribute("errors", errors);
            doGet(request, response);
        }
    }

    private String[] updateItems(Cart cart, String[] productIds, String[] quantities, NumberFormat format) {
        String[] errors = new String[productIds.length];

        for (int i = 0; i < productIds.length; i++) {
            try {
                Long productId = format.parse(productIds[i]).longValue();
                int quantity = format.parse(quantities[i]).intValue();

                if (quantity <= 0) {
                    throw new IllegalStateException();
                }

                cartService.update(cart, productId, quantity);
            } catch (ParseException e) {
                errors[i] = "Quantity should be a number";
            } catch (OutOfStockException e) {
                errors[i] = "Not enough stock";
            } catch (IllegalStateException e) {
                errors[i] = "Quantity should be positive";
            }
        }

        return errors;
    }
}
