package api.controllers;

import api.services.AuthService;
import api.services.PageService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Auth", value = "/Auth")
public class AuthServlet extends HttpServlet {
    private AuthService authService;
    public AuthServlet() {
        super();
        authService = new AuthService();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var page = request.getParameter("page");
        switch (page) {
            case "signup":
                PageService.goToPage(request, response, "views/auth/signup.jsp", "- Registration", null);
                break;
            case "signin":
                PageService.goToPage(request, response, "views/auth/signin.jsp", "- Authorization", null);
                break;
            case "signout":
                authService.handleSignOut(request, response);
                break;
            case "result":
                PageService.goToPage(request, response, "views/auth/sign-res.jsp", "- Authorization Result", null);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var page = request.getParameter("page");
        switch (page) {
            case "signup":
                authService.handleSignup(request, response);
                break;
            case "signin":
                authService.handleSignin(request, response);
                break;
        }
    }
}
