package database.repositories;

import com.google.inject.internal.Nullable;
import database.models.HouseType;
import database.models.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseRepository {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    protected int status;

    public BaseRepository() {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;
        this.preparedStatement = null;
        this.status = 0;
    }

    public ResultSet RunSelectQuery(String sqlStatement){
        try {
            connection = DbProvider.getMySQLConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
            return resultSet;
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception" + ex.getMessage());
        }
        catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
        return null;
    }

    public void CloseConnection(){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Runtime SQLException" + ex.getMessage());
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (SQLException ex) {
                System.out.println("Final SQLException" + ex.getMessage());
            }
        }
    }
}
