<%--
  Created by IntelliJ IDEA.
  User: 38095
  Date: 26.04.2021
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link type="text/css" rel="stylesheet" href="../../CSS/styles.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>

<%@ include file="../jspf/nav.jspf" %>
<div class="wrapper ">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class=" ">
            <img src="/login-icon.png" id="icon" height="90" alt="User Icon"/>
        </div>

        <!-- Login Form -->
        <form action="signup" method="post">
            <input type="text" id="login" name="login" placeholder="login">
            <input type="password" name="password1" placeholder="password">
            <input type="password" name="password2" placeholder="repeat password">
            <div>
                <c:if test="${!empty errorMessage}">
                    <div style="color: red">
                        <c:out value="${errorMessage}"/>
                    </div>
                </c:if>
            </div>
            <div style="padding-top: 20px; padding-bottom: 0px">
                <input type="submit" value="Sign Up">
            </div>
        </form>
    </div>
</div>

</body>
</html>
