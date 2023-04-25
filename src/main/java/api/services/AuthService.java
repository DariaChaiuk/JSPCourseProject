package api.services;

import api.models.User;
import database.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthService {
    private UserRepository usersService;
    public AuthService() {
        usersService = new UserRepository();
    }
    public void handleSignup (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var inputLogin = request.getParameter("login");
        var inputPass1 = request.getParameter("password1");
        var inputEmail = request.getParameter("email");

        String hashPassword = getMD5Hash(inputPass1);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var now = LocalDateTime.now();
        var regdate = formatter.format(now);
        var resultSuccess = usersService.addUser(inputLogin, hashPassword, inputEmail, regdate, 1, 1);

        String message;
        if(resultSuccess){
            message = "You was successfully registered!";
            int roleId = 1;
            request.getSession().setAttribute("currentUser",
                    new User(1,inputLogin,hashPassword, inputEmail, regdate, roleId, 1, 0));
            Cookie cookie = new Cookie("user", inputLogin);
            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
            Cookie roleCookie = new Cookie("roleId", String.valueOf(roleId));
            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(roleCookie);
        } else {
            message = "Ooops...registration exception";
            request.getSession().setAttribute("currentUser", null);
        }
        PageService.goToAuthResult(request, response, "- Authorization result",
                message,resultSuccess);
    }

    public void handleSignin (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var inputLogin = request.getParameter("login");
        var inputPass = request.getParameter("password");
        String hashPassword = getMD5Hash(inputPass);
        var user = usersService.getUser(inputLogin, hashPassword);
        String message;
        if(user!=null){
            message = "You are successfully authorized!";
            request.getSession().setAttribute("currentUser", user);
            Cookie cookie = new Cookie("user", inputLogin);
            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
            Cookie roleCookie = new Cookie("roleId", String.valueOf(user.getRoleId()));
            roleCookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(roleCookie);
        } else {
            message = "No user with provided credentials";
            request.getSession().setAttribute("currentUser", null);
        }
        PageService.goToAuthResult(request, response, "- Authorization result",
                message,user!=null);
    }

    public void handleSignOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");
        request.getCookies();
        String message = "You successfully signed out";
        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie roleCookie = new Cookie("roleId", null);
        roleCookie.setMaxAge(0);
        response.addCookie(roleCookie);
        PageService.goToAuthResult(request, response, "- Sign out",
                message,true);
    }
    private String getMD5Hash(String initPassword){
        String hashPassword = "";
        try {
            var md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(initPassword));
            hashPassword = String.format("%032x", new BigInteger(1, md5.digest()));
        }
        catch (NoSuchAlgorithmException ex){
            System.out.println(ex.getMessage());
        }
        return hashPassword;
    }
}
