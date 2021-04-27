<%--
  Created by IntelliJ IDEA.
  User: 38095
  Date: 27.04.2021
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add topic</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
<form style="padding: 2%" action="addTopic" method="post" >
    <div class="form-group">
        <label >Title</label>
        <input type="text" name="title" class="form-control" placeholder="Some title">
    </div>
    <div class="form-group">
        <label>Description</label>
        <textarea name="description" class="form-control" rows="6" placeholder="Some description"></textarea>
    </div>
    <div class="form-group">
        <input type="submit" value="Add topic">
    </div>
</form>
</body>
</html>
