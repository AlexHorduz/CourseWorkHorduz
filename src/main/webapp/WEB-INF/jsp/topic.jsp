<%--
  Created by IntelliJ IDEA.
  User: 38095
  Date: 27.04.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Topic</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-lg-12">
            <div id="postlist">
                <div>
                    <div class="text-center">
                        <div class="row">
                            <div class="col-sm-9">
                                <c:if test="${!empty user && user.isAdmin}">
                                    <form action="deleteTopic" method="post" style="display: flex; align-content: flex-start">
                                        <input type="hidden" name="id" value="${topic.id}">
                                        <input type="submit" style="color: red" value="Delete topic">
                                    </form>

                                    <form action="editTopic" method="post" style="display: flex; align-content: flex-start">
                                        <input type="hidden" name="id" value="${topic.id}">
                                        <input type="submit" style="color: cornflowerblue" value="Edit topic">
                                    </form>
                                </c:if>
                                <h3 class="float-left"><c:out value="${topic.title}"/></h3>
                            </div>
                        </div>
                    </div>


                    <div>
                        <c:out value="${topic.description}"/>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</div>
<div style="display:flex; justify-content:center">
    <h4>Comments</h4>
</div>
<div class="container mt-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-8">
            <div class="d-flex flex-column comment-section">


                <c:forEach var="comment" items="${requestScope.comments}">
                    <div class="bg-white p-2">
                        <div class="d-flex flex-row user-info">
                            <div class="d-flex flex-column justify-content-start ml-2">
                                <span class="d-block font-weight-bold name"><c:out
                                        value="${comment.user.login}"/></span>
                                <span class="date text-black-50"><c:out value="${comment.instant}"/></span>
                            </div>
                        </div>
                        <div class="mt-2">
                            <p class="comment-text"><c:out value="${comment.text}"/></p>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${!empty sessionScope.user}">
                    <div class="bg-light p-2">
                        <form action="comment" method="post">
                            <div class="d-flex flex-row align-items-start">
                                <textarea name="text" class="form-control ml-1 shadow-none textarea"></textarea>
                            </div>
                            <input type="hidden" name="id" value="${topic.id}">
                            <div class="mt-2 text-right">
                                <button class="btn btn-primary btn-sm shadow-none" type="submit">Post comment</button>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

</body>
</html>
