<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <jsp:include page="header.jsp?title=Beers"/>
    <body class="bg-info text-light">
        <div class="container mt-4 bg-dark">
            <h2>Beers🍻</h2>
            <c:if test="${empty beers}">
                <p>No beers found.</p>
            </c:if>
            <ul>
                <c:forEach var="beer" items="${beers}">
                    <li>
                        <c:out value="${beer.brand}"/>
                    </li>
                </c:forEach>
            </ul>
            <hr/>
            <h5>Add 🍺</h5>
            <form action="beers" method="post" class="d-flex align-items-center">
                <label for="brand" class="form-label m-1">Brand</label>
                <input type="text" id="brand" name="brand" class="form-control m-1" style="width:140px;">
                <input type="submit" value="OK" class="btn btn-secondary m-1">
            </form>
        </div>
    </body>
</html>
