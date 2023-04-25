package api.controllers;

import java.io.*;
import java.text.ParseException;
import java.util.List;

import contracts.models.OfferResponse;
import contracts.services.OfferService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet()
public class HomeServlet extends HttpServlet {

    public OfferService offerService;

    public HomeServlet() {
        offerService = new OfferService();
    }
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<OfferResponse> offers;
        try {
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String minSquare = request.getParameter("minSquare");
            String maxSquare = request.getParameter("maxSquare");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            offers = offerService.getAllOffers(null, country, city, minPrice, maxPrice, minSquare, maxSquare);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("offers", offers);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}