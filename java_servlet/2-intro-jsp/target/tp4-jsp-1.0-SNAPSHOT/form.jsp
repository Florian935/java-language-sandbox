<%-- 
    Document   : form.jsp
    Created on : 23 janv. 2021, 08:23:34
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
        <h1>Hello World!</h1>
        <form action="greeting.jsp" method="post">
            <%-- <form action="UserServlet" method="post"> fonctionne aussi --%>
            <div>
                <label for="firstname">Enter your first name </label>
                <input type="text" name="firstname" id="firstname" required>
            </div>
            <div>
                <label for="lastname">Enter your last name: </label>
                <input type="text" name="lastname" id="lastname" required>
            </div>
            <div>
                <input type="submit" value="Subscribe">
            </div>
        </form>
    </body>
</html>
