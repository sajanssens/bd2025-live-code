<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String beers = (String) request.getAttribute("beers");
%>
<html>
    <head>
        <title>Beers</title>
    </head>
    <body>
        <ul>
            <%=beers%>
        </ul>
    </body>
</html>
