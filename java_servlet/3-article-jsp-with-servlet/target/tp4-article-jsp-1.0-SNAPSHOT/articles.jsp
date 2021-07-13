<%-- 
    Document   : articles
    Created on : 23 janv. 2021, 10:27:28
    Author     : Florian Martin, Nathan Renaud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>My cart: </h1>
        <c:forEach items="${carts}" var="cart">
            <c:out value="${cart.name}"/><br>
        </c:forEach>

        <h1>Articles list: </h1>
        <form method="post" action="ArticleServlet">
            <c:forEach items="${articles}" var="article">
                <input type="checkbox" name="id" value="${article.id}">${article.name}<br>
            </c:forEach>
            <input type="submit" value="Add to cart">
        </form>
    </body>
</html>
