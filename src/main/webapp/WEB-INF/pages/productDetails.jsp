<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<jsp:useBean id="cartItems" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="${product.description}">
    <span>Cart: </span>
    <c:out value="${cartItems.toString()}"/>
    <c:if test="${not empty param.success}">
        <div style="color: green">Successfully added</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div style="color: red">${errorMessage}</div>
    </c:if>
    <h1>Details</h1>

    <table>
        <tr>
            <td>
                Description
            </td>
            <td>
                    ${product.description}
            </td>
        </tr>
        <tr>
            <td>
                Price
            </td>
            <td class="price">
                <a href="${product.id}/price-history">
                    <fmt:formatNumber value="${product.currentPriceData.price}" type="currency"
                                      currencySymbol="${product.currentPriceData.currency.symbol}"/>
                </a>
            </td>
        </tr>
        <tr>
            <td>
                Stock
            </td>
            <td>
                    ${product.stock}
            </td>
        </tr>
        <tr>
            <td>
                Image
            </td>
            <td>
                <img width="300px"
                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
            </td>
        </tr>
    </table>
    <form method="post" action="${product.id}">
        <p>
            <span>Quantity: </span>
            <input name="quantity" value="${empty quantity ? 1 : quantity}" style="text-align: right"/>
            <button type="submit">Add</button>
        </p>
    </form>

    <tags:recentlyViewed/>
</tags:master>