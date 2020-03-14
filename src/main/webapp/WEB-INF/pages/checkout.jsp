<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.cart.Cart" scope="request"/>

<tags:master pageTitle="Checkout">
    <h3>Checkout</h3>
    <table>
        <tr>
            <td>Image</td>
            <td>Description</td>
            <td>Quantity</td>
            <td>Price</td>
        </tr>
        <c:forEach var="cartItem" items="${cart.cartItems}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                </td>
                <td>
                    <a href="products/${cartItem.product.id}">${cartItem.product.description}</a>
                </td>
                <td>
                    <span>${cartItem.quantity}</span>
                </td>
                <td>
                    <fmt:formatNumber value="${cartItem.product.currentPriceData.price}" type="currency"
                                      currencySymbol="${cartItem.product.currentPriceData.currency.symbol}"/>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3" class="price">
                Total Price:
            </td>
            <td>
                <fmt:formatNumber value="${totalPrice}" type="currency"
                                  currencySymbol="${not empty cart.cartItems ? cart.cartItems.get(0).product.currentPriceData.currency.symbol : '$'}"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" class="price">
                Delivery costs:
            </td>
            <td>
                <fmt:formatNumber value="${deliveryCosts}" type="currency"
                                  currencySymbol="${not empty cart.cartItems ? cart.cartItems.get(0).product.currentPriceData.currency.symbol : '$'}"/>
            </td>
        </tr>
    </table>

    <form method="post" action="checkout">
        <p>
            Full Name
            <br>
            <input name="fullName" required>
        </p>
        <p>
            Phone number
            <br>
            Format: +___(____) ___ __ __
            <br>
            <input type="tel" name="phoneNumber" pattern="^\+\d{1,3}\(\d{1,4}\) \d{3} \d{2} \d{2}$" required>
        </p>
        <p>
            Delivery date
            <br>
            <input type="date" name="deliveryDate" required>
        </p>
        <p>
            Address
            <br>
            <input name="address" required>
        </p>
        <p>
            Payment method
            <br>
            <select name="paymentMethod" required>
                <c:forEach var="paymentMethod" items="${paymentMethods}">
                    <option>
                            ${paymentMethod}
                    </option>
                </c:forEach>
            </select>
        </p>
        <p>
            <input type="submit" value="Place order"/>
        </p>
    </form>
</tags:master>