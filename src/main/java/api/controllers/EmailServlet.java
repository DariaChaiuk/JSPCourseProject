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

        String html = "<h2 style=\"text-align: center; width: 100%\">You received request for one of your offers!</h2>";
        html += "<div>Please, connect the sender directly via this email: " + email + "</div>";
        html += "<div>This customer sent you a message: </div>";
        html += "<div>" + message + "</div>";
        html += "<div>Customer name: " + name + " " + surname + ".</div>";

        HttpSession session = request.getSession();
        String resultMessage;
        try{
            EmailService.sendEmail(host, port, user, pass, email, topic, html);
            resultMessage = "Your message was successfully sent to us!";
            session.setAttribute("color", "black");
            session.setAttribute("message", resultMessage);
            PageService.goToPage(request, response, "/views/contacts/confirmation.jsp", "Contact", null);

        } catch (Exception ex){
            System.out.println("Messaging Exception - " + ex.getMessage());
            resultMessage = "Something went wrong in sending process! Please, try again.";
            session.setAttribute("color", "red");
            session.setAttribute("message", resultMessage);
            PageService.goToPage(request, response, "/views/contacts/confirmation.jsp", "Contact", null);
        }
    }
}
