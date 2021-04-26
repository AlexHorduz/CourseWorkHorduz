<%--
  Created by IntelliJ IDEA.
  User: 38095
  Date: 26.04.2021
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Topics</title>
</head>
<body>
    <br>
    <table border="1">
        <c:forEach var="topic" items="${requestScope.topics}">
            <tr>
                <td><c:out value="${topic.title}"/></td>
                <td><c:out value="${topic.description}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
