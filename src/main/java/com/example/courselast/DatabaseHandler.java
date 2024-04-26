package com.example.courselast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


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
        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" + Constants.USER_EMAIL + "," + Constants.USER_SURNAME + "," + Constants.USER_NAME + "," + Constants.USER_PASSWORD + "," + Constants.USER_COUNTRY + ")" + "VALUES(?,?,?,?,?)";


        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCountry());


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


    public void addInventory(Item item, byte[] imageData) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Constants.INVENTORY_TABLE + "(" + Constants.INVENTORY_NAME + "," + Constants.INVENTORY_QUANTITY + "," + Constants.INVENTORY_PRICE + "," + Constants.INVENTORY_DESCRIPTION + "," + Constants.INVENTORY_CATEGORY + "," + Constants.INVENTORY_IMAGE + ")" + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setString(5, item.getCategory());
            preparedStatement.setBytes(6, imageData);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] extractImageData(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", stream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return stream.toByteArray();
    }


    public ObservableList<Item> getItemsList() throws SQLException, ClassNotFoundException {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + Constants.INVENTORY_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            ResultSet set = preparedStatement.executeQuery();


            while (set.next()) {
                String name = set.getString(Constants.INVENTORY_NAME);
                int quantity = set.getInt(Constants.INVENTORY_QUANTITY);
                double price = set.getDouble(Constants.INVENTORY_PRICE);
                String description = set.getString(Constants.INVENTORY_DESCRIPTION);
                String category = set.getString(Constants.INVENTORY_CATEGORY);
                byte[] imageData = set.getBytes(Constants.INVENTORY_IMAGE);

                Item item = new Item(name, quantity, price, description, category);
                item.setImage(imageData);
                itemObservableList.add(item);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return itemObservableList;
    }


    public ObservableList<Item> searchItem (String query_text) throws SQLException, ClassNotFoundException {
        ObservableList<Item> resultSearch = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + Constants.INVENTORY_TABLE + " WHERE " + Constants.INVENTORY_NAME + " LIKE ? OR " + Constants.INVENTORY_DESCRIPTION + " LIKE ? OR " + Constants.INVENTORY_CATEGORY + " LIKE ?";


        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + query_text + "%");
            preparedStatement.setString(2, "%" + query_text + "%");
            preparedStatement.setString(3, "%" + query_text + "%");


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("inventoryname"), resultSet.getInt("inventoryquantity"), resultSet.getDouble("inventoryprice"), resultSet.getString("inventorydescription"), resultSet.getString("inventorycategory"));
                resultSearch.add(item);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw exception;
        }


        return resultSearch;
    }


    public void deleteItem (String name) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM " + Constants.INVENTORY_TABLE + " WHERE " + Constants.INVENTORY_NAME + " = ?";


        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }


    public void updateItem(Item item) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Constants.INVENTORY_TABLE + " SET " + Constants.INVENTORY_QUANTITY + " =?, " + Constants.INVENTORY_PRICE + " =?, " + Constants.INVENTORY_DESCRIPTION + " =?, " + Constants.INVENTORY_CATEGORY + " =? " + "WHERE " + Constants.INVENTORY_NAME + " =?";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(update)) {
            preparedStatement.setInt(1, item.getQuantity());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setString(4, item.getCategory());
            preparedStatement.setString(5, item.getName());
            preparedStatement.executeUpdate();
        }
    }
}