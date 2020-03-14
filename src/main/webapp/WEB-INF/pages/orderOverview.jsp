<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="order" type="com.es.phoneshop.model.order.Order" scope="request"/>

<tags:master pageTitle="Order overview">
    <h3>Order overview</h3>
    <table>
        <tr>
            <td>Image</td>
            <td>Description</td>
            <td>Quantity</td>
            <td>Price</td>
        </tr>
        <c:forEach var="cartItem" items="${order.cartItems}">
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
                <fmt:formatNumber value="${order.totalPrice}" type="currency"
                                  currencySymbol="${not empty order.cartItems ? order.cartItems.get(0).product.currentPriceData.currency.symbol : '$'}"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" class="price">
                Delivery costs:
            </td>
            <td>
                <fmt:formatNumber value="${order.deliveryCosts}" type="currency"
                                  currencySymbol="${not empty order.cartItems ? order.cartItems.get(0).product.currentPriceData.currency.symbol : '$'}"/>
            </td>
        </tr>
    </table>
    <p>
        <span>Full name: ${order.customerData.fullName}</span>
    </p>
    <p>
        <span>Address: ${order.customerData.address}</span>
    </p>
    <p>
        <span>Delivery date: ${order.customerData.deliveryDate}</span>
    </p>
    <p>
        <span>Phone number: ${order.customerData.phoneNumber}</span>
    </p>
    <p>
        <span>Payment method: ${order.customerData.paymentMethod}</span>
    </p>
</tags:master>