<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Beers</title>
    </head>
    <body>
        <h2>Beers</h2>
        <ul>
            <c:forEach var="beer" items="${beers}">
                <li>
                    <c:out value="${beer.brand}"/>
                </li>
            </c:forEach>
        </ul>
        <h2>Add beer</h2>
        <form action="beers" method="post">
            Brand <br> <input type="text" name="brand"> <br>
            <input type="submit" value="OK">
        </form>
    </body>
</html>
