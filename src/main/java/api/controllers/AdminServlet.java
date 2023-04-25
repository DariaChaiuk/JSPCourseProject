package api.controllers;

import api.models.OfferFormParameters;
import api.services.AdminService;
import api.services.PageService;
import contracts.models.OfferResponse;
import contracts.services.OfferService;
import database.models.HouseType;
import database.models.Offer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "Admin", value = "/Admin")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AdminServlet extends HttpServlet {

    public OfferService offerService;
    public AdminService adminService;

    public AdminServlet() {
        adminService = new AdminService();
        offerService = new OfferService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OfferResponse> offers;
        boolean ignoreFilters = Boolean.TRUE == request.getAttribute("ignoreFilters");
        try {
            if (ignoreFilters) {
                offers = offerService.getAllOffers(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                request.setAttribute("ignoreFilters", null);
            } else {
                String city = request.getParameter("city");
                String country = request.getParameter("country");
                String minSquare = request.getParameter("minSquare");
                String maxSquare = request.getParameter("maxSquare");
                String minPrice = request.getParameter("minPrice");
                String maxPrice = request.getParameter("maxPrice");
                offers = offerService.getAllOffers(null, country, city, minPrice, maxPrice, minSquare, maxSquare);
                System.out.println(offers.toArray().length);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("offers", offers);
        PageService.goToPage(request, response, "views/admin/admin.jsp", "- Admin", null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfferFormParameters offerFormParameters = getDateFromParameters(request);

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var now = LocalDateTime.now();
        var currentDate = formatter.format(now);

        Offer offer = new Offer(Integer.parseInt(offerFormParameters.getId()), Float.parseFloat(offerFormParameters.getPrice()),
                offerFormParameters.getTitle(), offerFormParameters.getCity(), offerFormParameters.getCountry(),
                Float.parseFloat(offerFormParameters.getSquare()), Boolean.parseBoolean(offerFormParameters.getBalcony()),
                HouseType.valueOf(offerFormParameters.getType()), currentDate, offerFormParameters.getDescription());

        var action = request.getParameter("offerAction");
        switch (action) {
            case "update":
                var updateAction = request.getParameter("itemAction");
                switch (updateAction){
                    case "apply":
                        adminService.updateOffer(offer, offerFormParameters.getOldPhoto(), offerFormParameters.getPhoto(), request);
                        break;
                    case "delete":
                        adminService.deleteOffer(offerFormParameters.getId(), offerFormParameters.getPhoto());
                        break;
                }
                break;
            case "add":
                adminService.addOffer(offer, request);
                break;
        }

        request.setAttribute("ignoreFilters", Boolean.TRUE);
        doGet(request, response);
    }
    private OfferFormParameters getDateFromParameters(HttpServletRequest request){
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String description = request.getParameter("description");
        String square = request.getParameter("square");
        String title = request.getParameter("title");
        String id = request.getParameter("id");
        String balcony = request.getParameter("balcony");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String[] photo = request.getParameterValues("photo");
        String[] oldPhoto = request.getParameterValues("oldPhoto");
        return new OfferFormParameters(city, country, photo, oldPhoto, description, square, title, id, balcony, type, price);
    }
}
