<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <%@include file="../../views/layouts/head.jsp"%>
</head>
<body>
<header>
    <%@include file="../../views/layouts/nav.jsp"%>
</header>
<link rel="stylesheet" href="static/styles/confirmation-email.css">
<%
    String message = (String) request.getSession().getAttribute("message");
    pageContext.setAttribute("message", message);
%>
<main>
    <div class="contacts-box">
        <div class="content-box justify-content-between">
            <h3 style="color: ${color}">
                ${message}
            </h3>
        </div>
        <div>
            <img src="static/img/email-confirm.jpg" class="contacts-img">
        </div>
    </div>
</main>
<footer class="text-center">
    <%@include file="../../views/layouts/footer.jsp"%>
</footer>
</body>
</html>

