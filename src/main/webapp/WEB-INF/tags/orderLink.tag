<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ attribute name="sortBy" required="true" %>
<%@ attribute name="orderBy" required="true" %>

<a class="order-link" href="products?query=${param.query}&sortBy=${sortBy}&orderBy=${orderBy}"
   <c:if test="${param.sortBy == sortBy && param.orderBy == orderBy}">style="color:red"</c:if>>
    <jsp:doBody/>
</a>