<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p>
    Items in cart: ${totalQuantity}
</p>
<p>
    Total price: <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="USD"/>
</p>