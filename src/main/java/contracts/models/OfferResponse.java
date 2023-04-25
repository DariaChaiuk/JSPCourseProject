package contracts.models;

import database.models.Offer;

import java.util.List;

public class OfferResponse {
    public Offer offer;
    public List<String> photos;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public OfferResponse(Offer offer, List<String> photos) {
        this.offer = offer;
        this.photos = photos;
    }

}
