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
<link rel="stylesheet" href="static/styles/signin.css">
<main class="">
    <div class="login-box">
        <div class="content-box">
            <h2>Вхід</h2>
            <form id="signupForm" action="Auth?page=signin" method="post" class="sign-in-container" onsubmit="return true">
                <div class="form-group">
                    <label for="login">Логін:</label>
                    <input type="text" id="login" name="login" class="form-control" required>
                    <span id="login-err" class="error"></span>
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                    <span id="password-err" class="error"></span>
                </div>
                <div class="form-group text-center mt-3">
                    <input type="submit" id="submit" value="Confirm" class="btn btn-success my-btn">
                    <input type="reset" id="reset" value="Clear" class="btn btn-danger my-btn">
                </div>
           </form>
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
