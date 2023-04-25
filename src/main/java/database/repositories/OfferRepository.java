package database.repositories;
import com.google.inject.internal.Nullable;
import database.models.HouseType;
import database.models.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository extends BaseRepository {
    private String tableName = "offers";
    public OfferRepository(){}

    public List<Offer> getAllOffers (
            @Nullable Date creationDate,
            @Nullable String country,
            @Nullable String city,
            @Nullable String minPrice,
            @Nullable String maxPrice,
            @Nullable String minSquare,
            @Nullable String maxSquare
    ) {
        List<Offer> offers = new ArrayList<>();
        try {
            connection = DbProvider.getMySQLConnection();
            statement = connection.createStatement();

            String query = "select * from " + tableName;
            Boolean isWherePresent = false;
            if (creationDate != null) {
                query += " where creation_date>" + creationDate;
                isWherePresent = true;
            }
            if (country!= null && !country.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " country =\"" + country + "\"";
                isWherePresent = true;
            }
            if (city!= null && !city.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " city = \"" + city + "\"";
                isWherePresent = true;
            }
            if (minPrice!= null && !minPrice.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " price > \"" + minPrice + "\"";
                isWherePresent = true;
            }
            if (maxPrice!= null && !maxPrice.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " price < \"" + maxPrice + "\"";
                isWherePresent = true;
            }
            if (minSquare!= null && !minSquare.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " square > \"" + minSquare + "\"";
                isWherePresent = true;
            }
            if (maxSquare!= null && !maxSquare.isBlank()) {
                query += (isWherePresent ? " and" : " where") + " square < \"" + maxSquare + "\"";
            }
            query += " order by id";

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                float price = resultSet.getFloat("price");
                String resCity = resultSet.getString("city");
                String resCountry = resultSet.getString("country");
                float square = resultSet.getFloat("square");
                boolean balcony = resultSet.getBoolean("balcony");
                HouseType type = HouseType.valueOf(resultSet.getString("home_type"));
                String date = resultSet.getString("creation_date");
                String description = resultSet.getString("description");
                var offer = new Offer(id, price, title, resCity, resCountry, square, balcony, type, date, description);
                offers.add(offer);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
        return offers;
    }

    public Offer getOffer (String id) {
        try {
            connection = DbProvider.getMySQLConnection();
            statement = connection.createStatement();

            String query = "select * from " + tableName;
            query += " where Id = " + id;
            query += " order by id";

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int resId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                float price = resultSet.getFloat("price");
                String city = resultSet.getString("city");
                String country = resultSet.getString("country");
                float square = resultSet.getFloat("square");
                boolean balcony = resultSet.getBoolean("balcony");
                HouseType type = HouseType.valueOf(resultSet.getString("home_type"));
                String date = resultSet.getString("creation_date");
                String description = resultSet.getString("description");
                return new Offer(resId, price, title, city, country, square, balcony, type, date, description);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
        return null;
    }

    public Offer updateOffer(Offer newOffer){
        try {
            connection = DbProvider.getMySQLConnection();

            String query = "UPDATE " + tableName;
            query += " SET title = ?";
            query += ", price = ?";
            query += ", city = ?";
            query += ", country = ?";
            query += ", square = ?";
            query += ", home_type = ?";
            query += ", description = ?";
            if(newOffer.balcony) {
                query += ", balcony = ?";
            }
            query += " WHERE id = ?";

            connection = DbProvider.getMySQLConnection();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, newOffer.title);
            preparedStatement.setFloat(2, newOffer.price);
            preparedStatement.setString(3, newOffer.city);
            preparedStatement.setString(4, newOffer.country);
            preparedStatement.setFloat(5, newOffer.square);
            preparedStatement.setString(6, newOffer.type.toString());
            preparedStatement.setString(7, newOffer.description);
            if(newOffer.balcony){
                preparedStatement.setInt(8, newOffer.balcony ? 1 : 0);
                preparedStatement.setInt(9, newOffer.id);
            } else{
                preparedStatement.setInt(8, newOffer.id);
            }

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating offer failed, no rows affected.");
            }

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
        return newOffer;
    }

    public Offer addOffer(Offer newOffer){
        try {
            String query = "insert into " + tableName;
            query += " (price, title, city, country, square, balcony, home_type, creation_date, description)";
            query += " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connection = DbProvider.getMySQLConnection();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setFloat(1, newOffer.price);
            preparedStatement.setString(2, newOffer.title);
            preparedStatement.setString(3, newOffer.city);
            preparedStatement.setString(4, newOffer.country);
            preparedStatement.setFloat(5, newOffer.square);
            preparedStatement.setBoolean(6, newOffer.balcony);
            preparedStatement.setString(7, newOffer.type.toString());
            preparedStatement.setString(8, newOffer.creationDate);
            preparedStatement.setString(9, newOffer.description);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creation offer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newOffer.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creation offer failed, no ID obtained.");
                }
            }
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
        return newOffer;
    }

    public void deleteOffer(String id){
        try {
            connection = DbProvider.getMySQLConnection();

            String query = "DELETE FROM " + tableName + " WHERE id=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deletion offer failed, no rows affected.");
            }

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
    }
}
