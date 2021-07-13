<%-- 
    Document   : greeting.jsp
    Created on : 23 janv. 2021, 08:23:12
    Author     : Florian Martin, Nathan Renaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Greeting page: (dirty style)</h1>

            <%
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
%>
            <div><%= "Hello " + firstName + " " + lastName + " !" %></div>

        <h1>Greeting page: (clean style with EL)</h1>
        <div>Hello ${param.firstname} ${param.lastname} !</div>
    </body>
</html>
