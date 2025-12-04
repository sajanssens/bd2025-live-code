<%@ page import="java.time.LocalDateTime" %>
<html>
    <% String i = LocalDateTime.now().toLocalTime().toString(); %>
    <jsp:include page="header.jsp?title=JEE-Demos"/>
    <body>
        <h1>JEE-demos</h1>
        <a href="beers">Go to my Beers <%= i %>
        </a>
    </body>
</html>