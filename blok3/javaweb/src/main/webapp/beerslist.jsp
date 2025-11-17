<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Beers</title>
    </head>
    <body>
        <ul>
            <c:forEach var="beer" items="${beers}">
                <li>
                    <c:out value="${beer.brand}"/>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
