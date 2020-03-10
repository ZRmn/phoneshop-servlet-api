<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Product List">
    <p>
        <jsp:include page="/miniCart"/>
    </p>

    <form method="get" action="products">
        <input type="text" name="query" value="${param.query}">
        <input type="submit" value="Search">
    </form>
    <table>
        <thead>
        <tr>
            <td>
                Image
            </td>
            <td>
                Description
                <tags:orderLink sortBy="description" orderBy="asc">▲</tags:orderLink>
                <tags:orderLink sortBy="description" orderBy="desc">▼</tags:orderLink>
            </td>
            <td class="price">
                Price
                <tags:orderLink sortBy="price" orderBy="asc">▲</tags:orderLink>
                <tags:orderLink sortBy="price" orderBy="desc">▼</tags:orderLink>
            </td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                </td>
                <td>
                    <a href="products/${product.id}">${product.description}</a>
                </td>
                <td class="price">
                    <a href="products/${product.id}/price-history">
                        <fmt:formatNumber value="${product.currentPriceData.price}" type="currency"
                                          currencySymbol="${product.currentPriceData.currency.symbol}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <tags:recentlyViewed/>
</tags:master>