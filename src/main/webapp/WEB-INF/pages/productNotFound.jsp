<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="code" type="java.lang.String" scope="request"/>

<tags:master pageTitle="Product not found">
    <h1>Product with code ${code} not found</h1>
</tags:master>