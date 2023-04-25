package api.models;

public class OfferFormParameters {
    private String city;
    private String country;
    private String[] photo = new String[0];
    private String[] oldPhoto = new String[0];
    private String description;
    private String square;
    private String title;
    private String id;
    private String balcony;
    private String type;
    private String price;

    public OfferFormParameters(String city, String country, String[] photo, String[] oldPhoto, String description,
                               String square, String title, String id, String balcony, String type, String price) {
        this.city = city;
        this.country = country;
        if(oldPhoto != null){
            this.oldPhoto = oldPhoto;
        }
        if(photo != null){
            this.photo = photo;
        }
        this.description = description;
        this.square = square;
        this.title = title;
        this.id = id != null ? id : "0";
        this.balcony = balcony;
        this.type = type;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getPhoto() {
        return photo;
    }

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }

    public String[] getOldPhoto() {
        return oldPhoto;
    }

    public void setOldPhoto(String[] oldPhoto) {
        this.oldPhoto = oldPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
