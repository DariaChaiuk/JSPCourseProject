<%@ page import="java.util.Objects" %>
<%@ page import="api.models.User" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/styles/nav.css">
<%
    User userEntity = (User) request.getSession().getAttribute("currentUser");
    String user = null;
    String roleId = null;
    if(userEntity!= null){
        user = userEntity.getLogin();
        roleId = String.valueOf(userEntity.getRoleId());
        Cookie cookie = new Cookie("user", user);
        cookie.setMaxAge(3600 * 24 * 7);
        response.addCookie(cookie);
        Cookie roleCookie = new Cookie("roleId", roleId);
        roleCookie.setMaxAge(3600 * 24 * 7);
        response.addCookie(roleCookie);
    }
    if(user == null) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if(Objects.equals(cookie.getName(), "user")){
                    user = cookie.getValue();
                }
                if(Objects.equals(cookie.getName(), "roleId")){
                    roleId = cookie.getValue();
                }
            }
        }
    }
    pageContext.setAttribute("roleId", roleId);
    pageContext.setAttribute("user", user);
%>
<div class="nav-block">
    <div class="d-flex flex-row align-items-center">
        <a href="./" class="home-img">
            <img src="static/img/home.png" class="home-icon"/>
        </a>
        <div class="menu-button">
            <button class="btn btn-secondary dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Меню
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="./">Головна</a></li>
                <li><a class="dropdown-item" href="./Contacts">Контакти</a></li>
                <li><a class="dropdown-item" href="./About">Про нас</a></li>
                <c:if test="${roleId == 1}">
                    <li><a class="dropdown-item" href="./Admin">Адміністрування</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="company-name-label">
        <span>WORLD HOME COMPANY</span>
    </div>
    <div class="user-block">
        <div class="dropdown">
            <button class="btn dropdown-toggle d-flex bg-transparent border-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                <img src="static/img/user.png" class="home-icon"/>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <span class="dropdown-item">
                        Привіт,
                        <c:if test="${user != null}">
                            <span style="color: darkcyan">
                                <c:out value="${user}"></c:out>
                            </span>
                        </c:if>
                        <c:if test="${user == null}">
                            <span style="color: yellow">
                                Гість!
                            </span>
                        </c:if>
                    </span>
                </li>
                <c:if test="${user == null}">
                    <li><a class="dropdown-item" href="Auth?page=signin">Вхід</a></li>
                    <li><a class="dropdown-item" href="Auth?page=signup">Реєстрація</a></li>
                </c:if>
                <c:if test="${user != null}">
                    <li><a class="dropdown-item" href="Auth?page=signout">Вихід</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

