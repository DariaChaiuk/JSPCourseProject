package database.repositories;

import api.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String query;
    private int status;

    public UserRepository() {
        this.connection = null;
        this.resultSet = null;
        this.preparedStatement = null;
        query = "";
        status = 0;
    }

    public boolean addUser(String login, String password, String email, String regdate, int roleId, int statusId) {
        boolean success = false;
        try {
            query = "insert into users";
            query += " (login, password, email, regdate, role_id, status_id)";
            query += " values (?, ?, ?, ?, ?, ?)";
            connection = DbProvider.getMySQLConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, regdate);
            preparedStatement.setInt(5, roleId);
            preparedStatement.setInt(6, statusId);

            status = preparedStatement.executeUpdate();
            if(status != 0){
                success = true;
            }

            preparedStatement.close();
            connection.close();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("addUser-classNotFoundException: " + ex.getMessage());
            success = false;
        }
        catch (SQLException ex) {
            System.out.println("addUser-SQLException: " + ex.getMessage());
            success = false;
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (SQLException ex) {
                System.out.println("addUser-Finally-SQLException: " + ex.getMessage());
            }
        }
        return success;
    }

    public User getUser (String login, String passwordMD5) {
        User user = null;
        try {
            query = "select * from users where password=? and login=?";
            connection = DbProvider.getMySQLConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, passwordMD5);
            preparedStatement.setString(2, login);

            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                user = new User(
                        res.getInt("id"),
                        res.getString("login"),
                        res.getString("password"),
                        res.getString("email"),
                        res.getString("regdate"),
                        res.getInt("role_id"),
                        res.getInt("status_id"),
                        res.getInt("mail_confirm")
                );
            }

            preparedStatement.close();
            connection.close();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("getUser-classNotFoundException: " + ex.getMessage());
        }
        catch (SQLException ex) {
            System.out.println("getUser-SQLException: " + ex.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (SQLException ex) {
                System.out.println("getUser-Finally-SQLException: " + ex.getMessage());
            }
        }
        return user;
    }

}

