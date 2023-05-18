package api.controllers;

import api.services.EmailService;
import api.services.PageService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@WebServlet(name = "Email", value = "/Email")
public class EmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        ServletContext servletContext = getServletContext();
        String user = servletContext.getInitParameter("user");
        String pass = servletContext.getInitParameter("pass");
        String host = servletContext.getInitParameter("host");
        String port = servletContext.getInitParameter("port");

        String topic = "Request for offer";

        String html = "<h2 style=\"text-align: center; width: 100%\">Вам надійшов запит щодо однієї з пропозицій!</h2>";
        html += "<div>Будь-ласка, зконтактуйте з клієнтом безпосередньо за цим емейлом: " + email + "</div>";
        html += "<div>Цей користувач надіслав вам наступне повідомлення: </div>";
        html += "<div>" + message + "</div>";
        html += "<div>Імʼя користувача: " + name + " " + surname + ".</div>";

        HttpSession session = request.getSession();
        String resultMessage;
        try{
            EmailService.sendEmail(host, port, user, pass, email, topic, html);
            resultMessage = "Ваше повідомлення успішно відправлено!";
            session.setAttribute("color", "black");
            session.setAttribute("message", resultMessage);
            PageService.goToPage(request, response, "/views/contacts/confirmation.jsp", "- Контакти", null);

        } catch (Exception ex){
            System.out.println("Messaging Exception - " + ex.getMessage());
            resultMessage = "Щось пішло не так в процесі відправки! Будь-ласка, спробуйте ще раз.";
            session.setAttribute("color", "red");
            session.setAttribute("message", resultMessage);
            PageService.goToPage(request, response, "/views/contacts/confirmation.jsp", "- Контакти", null);
        }
    }
}
