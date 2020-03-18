<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<tags:master pageTitle="Advanced search">
    <form method="get" action="advancedSearch" style="border: 1px black;">
        <p>
            Description
            <input name="description" value="${param.description}">
            <select name="descriptionSearchMode">
                <option value="all" <c:if test="${param.descriptionSearchMode == 'all'}">selected</c:if>>All words</option>
                <option value="any" <c:if test="${param.descriptionSearchMode == 'any'}">selected</c:if>>Any word</option>
            </select>
        </p>
        <p>
            Min price
            <input name="minPrice" value="${param.minPrice}">
            <c:if test="${not empty minPriceError}">
                ${minPriceError}
            </c:if>
        </p>
        <p>
            Max price
            <input name="maxPrice" value="${param.maxPrice}">
            <c:if test="${not empty maxPriceError}">
                ${maxPriceError}
            </c:if>
        </p>
        <input type="hidden" name="show">
        <input type="submit" value="Search">
    </form>

    <c:if test="${not empty products}">
        <table>
            <thead>
            <tr>
                <td>
                    Image
                </td>
                <td>
                    Description
                </td>
                <td class="price">
                    Price
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
    </c:if>
</tags:master>