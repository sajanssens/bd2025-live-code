<%@ page import="java.time.LocalDateTime" %>
<html>
    <% String i = LocalDateTime.now().toLocalTime().toString(); %>
    <head>
        <title>JEE-demos</title>
    </head>
    <body>
        <h1>JEE-demos</h1>
        <a href="beers">Go to my Beers <%= i %>
        </a>
    </body>
</html>