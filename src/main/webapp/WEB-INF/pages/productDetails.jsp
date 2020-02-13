<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="${product.description}">
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
</tags:master>