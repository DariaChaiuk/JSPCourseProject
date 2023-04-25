package api.services;

import contracts.services.OfferService;
import contracts.services.S3Util;
import database.models.Offer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Request;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminService {

    private static OfferService offerService;
    public AdminService(){
        offerService = new OfferService();
    }

    public void updateOffer(Offer offer, String[] oldPhoto, String[] photo, HttpServletRequest request){
        Offer newOffer = offerService.updateOffer(offer);
        List<String> files = new ArrayList<>();
        if(photo == null){
            photo = new String[0];
        }
        for (String photoItem: oldPhoto ) {
            if(!Arrays.stream(photo).anyMatch(photoItem::equals)){
                String[] urls = photoItem.split("/");
                String name = urls[urls.length - 1];
                files.add(name);
            }
        }
        if(files.size() > 0) {
            S3Util.deleteFiles(files, String.valueOf(offer.id));
        }
        UploadFileService.uploadFile(request, String.valueOf(offer.id));
    }

    public void addOffer(Offer offer, HttpServletRequest request){
        Offer newOffer = offerService.addOffer(offer);
        UploadFileService.uploadFile(request, String.valueOf(newOffer.id));
    }

    public void deleteOffer(String id, String[] photos){
        offerService.deleteOffer(id);
        if(photos != null) {
            List<String> files = new ArrayList<>();
            for (String photoItem : photos) {
                String[] urls = photoItem.split("/");
                String name = urls[urls.length - 1];
                files.add(name);
            }
            if (files.size() > 0) {
                S3Util.deleteFiles(files, id);
            }
        }

    }
}
