package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.HttpSessionCartService;
import com.es.phoneshop.model.order.DefaultOrderService;
import com.es.phoneshop.model.order.Order;
import com.es.phoneshop.model.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet("/checkout")
public class CheckoutPageServlet extends HttpServlet {

    private CartService cartService = HttpSessionCartService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        request.setAttribute("cart", cart);
        request.setAttribute("totalPrice", cartService.calculateTotalPrice(cart));
        request.setAttribute("paymentMethods", Order.PaymentMethod.values());
        request.setAttribute("deliveryCosts", orderService.calculateDeliveryCosts(cart));
        request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        Order.CustomerData customerData = getCustomerData(request);
        Order order = orderService.placeOrder(cart, customerData);
        String orderLink = orderService.getOrderLink(order);
        response.sendRedirect(request.getContextPath() + "/order/overview/" + orderLink);
    }

    private Order.CustomerData getCustomerData(HttpServletRequest request) {
        Order.CustomerData customerData = new Order.CustomerData();

        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String deliveryDateAsString = request.getParameter("deliveryDate");
        String paymentMethodAsString = request.getParameter("paymentMethod");
        Locale locale = request.getLocale();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);
        LocalDate deliveryDate = LocalDate.parse(deliveryDateAsString, formatter);
        Order.PaymentMethod paymentMethod = Order.PaymentMethod.valueOf(paymentMethodAsString);

        customerData.setFullName(fullName);
        customerData.setPhoneNumber(phoneNumber);
        customerData.setAddress(address);
        customerData.setDeliveryDate(deliveryDate);
        customerData.setPaymentMethod(paymentMethod);

        return customerData;
    }
}
