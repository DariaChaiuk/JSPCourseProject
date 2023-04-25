<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", -1);
%>
<!DOCTYPE html>
<html lang="uk">
<link rel="stylesheet" href="static/styles/index.css">
<head>
    <%@include file="views/layouts/head.jsp"%>
</head>
<body>
<header>
    <div>
        <%@include file="views/layouts/nav.jsp"%>
    </div>
</header>
<main>
    <div class="">
        <%@include file="views/layouts/main-large-screen.jsp"%>
    </div>
</main>
<footer class="text-center">
    <%@include file="views/layouts/footer.jsp"%>
</footer>
</body>
</html>