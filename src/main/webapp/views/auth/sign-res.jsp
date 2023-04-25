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
<link rel="stylesheet" href="static/styles/sign-res.css">
<%
    String message = (String) request.getSession().getAttribute("message");
    String color = (String) request.getSession().getAttribute("color");
    pageContext.setAttribute("message", message);
    pageContext.setAttribute("color", color);
%>
<main>
  <div class="login-box">
    <div class="content-box">
        <div>
            <h3 style="color: ${color}">
              ${message}
            </h3>
        </div>
    </div>
    <div>
       <img src="static/img/login.jpg" class="login-img">
    </div>
  </div>
</main>
<footer class="container text-center">
  <%@include file="../../views/layouts/footer.jsp"%>
</footer>
</body>
</html>

