package database.models;

import javax.xml.crypto.Data;
import java.util.Date;

public class Offer {
    public int id;
    public float price;
    public String title;
    public String city;
    public String country;
    public String description;
    public float square;
    public boolean balcony;
    public HouseType type;
    public String creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public HouseType getType() {
        return type;
    }

    public void setType(HouseType type) {
        this.type = type;
    }

    public Offer(int id, float price, String title, String city,
                 String country, float square, boolean balcony,
                 HouseType type, String creationDate, String description) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.city = city;
        this.country = country;
        this.square = square;
        this.balcony = balcony;
        this.type = type;
        this.creationDate = creationDate;
        this.description = description;
    }
}
