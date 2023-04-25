package contracts.services;

import com.google.inject.internal.Nullable;
import contracts.models.OfferResponse;
import database.models.Offer;
import database.repositories.OfferRepository;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OfferService {
    private OfferRepository offerRepository;
    public OfferService() {
        offerRepository = new OfferRepository();
    }

    public List<OfferResponse> getAllOffers (
            @Nullable String creationDate,
            @Nullable String country,
            @Nullable String city,
            @Nullable String minPrice,
            @Nullable String maxPrice,
            @Nullable String minSquare,
            @Nullable String maxSquare
    ) throws ParseException {
        List<Offer> dbOffers;
        if(creationDate!= null){
            dbOffers =
                    offerRepository.getAllOffers((Date)(
                            new SimpleDateFormat("dd/MM/yyyy").parse(creationDate)),
                            country, city, minPrice, maxPrice, minSquare, maxSquare);
        }
        else{
            dbOffers = offerRepository.getAllOffers(
                    null, country, city, minPrice, maxPrice, minSquare, maxSquare);
        }
        List<OfferResponse> offerResponses = new ArrayList<>();
        for (Offer offer:dbOffers) {
            List<String> urls = new ArrayList<>();
            try {
                urls = S3Util.getFiles(offer.id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            OfferResponse offerResponse = new OfferResponse(offer, urls);
            offerResponses.add(offerResponse);
        }

        return offerResponses;
    }

    public Offer updateOffer(Offer offer){
        return offerRepository.updateOffer(offer);
    }

    public OfferResponse getOffer(String id) throws IOException {
        Offer offer = offerRepository.getOffer(id);

        List<String> urls = S3Util.getFiles(offer.id);
        OfferResponse offerResponse = new OfferResponse(offer, urls);
        return offerResponse;
    }

    public Offer addOffer(Offer offer){
        return offerRepository.addOffer(offer);
    }

    public void deleteOffer(String id) { offerRepository.deleteOffer(id); }
}
