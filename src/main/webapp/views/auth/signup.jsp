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
<link rel="stylesheet" href="static/styles/signup.css">
<main>
    <div class="login-box">
        <div class="content-box">
            <h2>Sign up</h2>
            <form id="signupForm" action="Auth?page=signup" method="post" class="sign-up-container" onsubmit="return true">
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" id="login" name="login" class="form-control" required>
                    <span id="login-err" class="error"></span>
                </div>
                <div class="form-group">
                    <label for="password1">Password:</label>
                    <input type="password" id="password1" name="password1" class="form-control" required>
                    <span id="password1-err" class="error"></span>
                </div>
                <div class="form-group">
                    <label for="password2">Confirm password:</label>
                    <input type="password" id="password2" name="password2" class="form-control" required>
                    <span id="password2-err" class="error"></span>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                    <span id="email-err" class="error"></span>
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
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>--%>
</body>
</html>
