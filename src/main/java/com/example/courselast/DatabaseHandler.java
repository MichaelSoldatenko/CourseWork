package com.example.courselast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connection, dbUser, dbPassword);
        return dbConnection;
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
}
