<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.cart.Cart" scope="request"/>
<jsp:useBean id="totalPrice" type="java.math.BigDecimal" scope="request"/>
<tags:master pageTitle="Cart">
    <c:if test="${not empty param.success}">
        <p style="color: green">Updated successfully</p>
    </c:if>
    <c:if test="${not empty errors}">
        <p style="color: red">Updating error</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty cart.cartItems}">
            <h3>Cart</h3>

            <form method="post" action="cart">
                <table>
                    <tr>
                        <td>
                            Image
                        </td>
                        <td>
                            Description
                        </td>
                        <td>
                            Quantity
                        </td>
                        <td>
                            Price
                        </td>
                        <td>
                            Action
                        </td>
                    </tr>

                    <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="status">
                        <tr>
                            <td>
                                <img class="product-tile"
                                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                            </td>
                            <td>
                                <input name="productId" type="hidden" value="${cartItem.product.id}"/>
                                <a href="products/${cartItem.product.id}">${cartItem.product.description}</a>
                            </td>
                            <td>
                                <input name="quantity"
                                       value="${not empty errors[status.index] ? quantities[status.index]: cartItem.quantity}"
                                       style="text-align: right"/>
                                <c:if test="${not empty errors[status.index]}">
                                    <p style="color: red">${errors[status.index]}</p>
                                </c:if>
                            </td>
                            <td>
                                <fmt:formatNumber value="${cartItem.product.currentPriceData.price}" type="currency"
                                                  currencySymbol="${cartItem.product.currentPriceData.currency.symbol}"/>
                            </td>
                            <td>
                                <button formaction="cart/delete/${cartItem.product.id}">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="4" class="price">
                            Total Price:
                        </td>
                        <td>
                            <fmt:formatNumber value="${totalPrice}" type="currency"
                                              currencySymbol="${not empty cart.cartItems ? cart.cartItems.get(0).product.currentPriceData.currency.symbol : '$'}"/>
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="submit" value="Update"/>
                </p>
            </form>
        </c:when>
        <c:otherwise>
            <h3>Cart is empty</h3>
        </c:otherwise>
    </c:choose>


</tags:master>