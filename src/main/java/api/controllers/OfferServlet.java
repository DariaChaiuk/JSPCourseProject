package api.controllers;

import api.services.PageService;
import com.google.gson.Gson;
import contracts.models.OfferResponse;
import contracts.services.OfferService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Offer", value = "/Offer")
public class OfferServlet extends HttpServlet {
    private OfferService offerService;
    public OfferServlet() {
        super();
        offerService = new OfferService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        OfferResponse offer = offerService.getOffer(id);
        request.setAttribute("offer", offer);
        PageService.goToPage(request, response, "/views/offer/offer-details.jsp", "Деталі", null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        OfferResponse offer = offerService.getOffer(id);
        request.setAttribute("offer", offer);
        PageService.goToPage(request, response, "offer-details.jsp", "Деталі", null);
    }
}
