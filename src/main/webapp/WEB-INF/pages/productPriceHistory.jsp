<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Price history">

    <p>
        <h1>Price history</h1>
    </p>

    <p>
        <h3>${product.description}</h3>
    </p>

    <table>
        <tr>
            <td>
                Current price
            </td>
            <td class="price">
                <fmt:formatNumber value="${product.currentPriceData.price}" type="currency"
                                  currencySymbol="${product.currentPriceData.currency.symbol}"/>
            </td>
        </tr>

        <c:forEach var="priceData" items="${product.priceHistory}">
            <tr>
                <td>
                        ${priceData.changeDate}
                </td>
                <td class="price">
                    <fmt:formatNumber value="${priceData.price}" type="currency"
                                      currencySymbol="${priceData.currency.symbol}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</tags:master>