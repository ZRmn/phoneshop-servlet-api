<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="recentlyViewed" type="java.util.ArrayDeque" scope="request"/>

<c:if test="${recentlyViewed.size() > 0}">
    <h3>Recently viewed products: </h3>
    <table border="1">
        <tr>
            <c:forEach var="viewed" items="${recentlyViewed}">
                <td align="center"  width="150">
                    <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${viewed.imageUrl}" align = "center">
                    <p>
                        <a href="${pageContext.servletContext.contextPath}/products/${viewed.id}">${viewed.description}</a>
                    </p>
                </td>
            </c:forEach>
        </tr>
    </table>
</c:if>