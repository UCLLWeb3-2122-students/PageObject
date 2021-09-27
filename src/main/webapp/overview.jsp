<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bekijk alle dieren</title>
    <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Overzicht"/>
    </jsp:include>
    <main>
        <c:choose>
            <c:when test="${not empty animals}">
                <h2>Bekijk alle dieren</h2>

                <table>
                    <thead>
                    <tr>
                        <th>Naam</th>
                        <th>Soort</th>
                        <th>Voedsel</th>
                    </thead>
                    <tbody>
                        <c:if test="$not empty animals"></c:if>
                    <c:forEach var="animal" items="${animals}">
                        <tr>
                            <td>${animal.name}
                            </td>
                            <td>${animal.type}
                            </td>
                            <td>${animal.food}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Er zijn geen dieren </p>
            </c:otherwise>
        </c:choose>
        <p>Het laatste dier dat je hebt toegevoegd heet ${last.name} </p>
        <p>Het laatste dier dat je hebt toegevoegd heet ${sessionScope.lastAddedAnimal.name} </p>

    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>