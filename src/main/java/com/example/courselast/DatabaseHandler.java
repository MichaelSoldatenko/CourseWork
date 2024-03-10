package com.example.courselast;
import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connection, dbUser, dbPassword);
            return dbConnection;
        }
        catch (SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
            throw exception;
        }
    }

    public void SignUpUser(User user) {
            String insert = "INSERT INTO " + Constants.USER_TABLE + "(" + Constants.USER_EMAIL + "," + Constants.USER_SURNAME + "," + Constants.USER_NAME + "," + Constants.USER_PASSWORD + "," + Constants.USER_COUNTRY + "," + Constants.USER_GENDER + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser (User user) {
        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " + Constants.USER_EMAIL + " = ? AND " + Constants.USER_PASSWORD + " = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public void addInventory(Item item) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Constants.INVENTORY_TABLE + "(" + Constants.INVENTORY_NAME + "," + Constants.INVENTORY_QUANTITY + "," + Constants.INVENTORY_PRICE + "," + Constants.INVENTORY_DESCRIPTION + "," + Constants.INVENTORY_CATEGORY + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setString(5, item.getCategory());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
