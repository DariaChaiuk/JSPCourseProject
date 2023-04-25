package api.services;

import com.google.inject.internal.Nullable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;

import java.io.IOException;

public class PageService {
    public static void goToPage(
            @NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull String pagePath,
            @NotNull String pageTittle, @Nullable Object service) throws ServletException, IOException {
        request.setAttribute("title", pageTittle);
        request.setAttribute("service", service);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
        dispatcher.forward(request, response);
    }

    public static void goToAuthResult(HttpServletRequest request, HttpServletResponse response,
                                      String pageTitle, String resultMessage, boolean resultSuccess)
            throws ServletException, IOException {

        var color = "";
        var message = resultMessage;
        if(resultSuccess) {
            color = "green";
        }
        else {
            color = "red";
        }
        HttpSession session = request.getSession();

        session.setAttribute("title", pageTitle);
        session.setAttribute("color", color);
        session.setAttribute("message", message);
        response.sendRedirect("./Auth?page=result");
    }
}
