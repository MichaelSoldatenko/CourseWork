package com.example.courselast;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.ByteArrayOutputStream;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AddInventoryController {


    @FXML
    private ResourceBundle resources;


    @FXML
    private URL location;


    @FXML
    private Button addbutton;


    @FXML
    private Label addinventorylabel;


    @FXML
    private AnchorPane backgrounganchorpane;


    @FXML
    private AnchorPane frontgroundanchorpane;


    @FXML
    private TextField inventorycategory;


    @FXML
    private TextField inventorydescription;


    @FXML
    private TextField inventoryname;


    @FXML
    private TextField inventoryprice;


    @FXML
    private TextField inventoryquantity;


    @FXML
    private Label itemcategorylabel;


    @FXML
    private Label itemcountlabel;


    @FXML
    private Label itemdescriptionlabel;


    @FXML
    private Label itemnamelabel;


    @FXML
    private Label itempricelabel;


    @FXML
    private ImageView imageview;


    @FXML
    void initialize() {


    }


    @FXML
    void addButton(ActionEvent event) {
        System.out.println("Button clicked");


        String inventoryName = inventoryname.getText();
        int inventoryQuantity = Integer.parseInt(inventoryquantity.getText());
        double inventoryPrice = Double.parseDouble(inventoryprice.getText());
        String inventoryDescription = inventorydescription.getText();
        String inventoryCategory = inventorycategory.getText();


        System.out.println("Inventory name: " + inventoryName);


        Image image = imageview.getImage();
        byte[] imageData = null;
        if (image != null) {
            imageData = imageToByteArray(image);
        }

        Item item = new Item(inventoryName, inventoryQuantity, inventoryPrice, inventoryDescription, inventoryCategory);
        item.setImage(imageData);

        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.addInventory(item, imageData);
            System.out.println("Inventory added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
        try {
            Parent root = fxmlLoader.load();
            MainWindowController controller = fxmlLoader.getController();
            DatabaseHandler updateHandler = new DatabaseHandler();
            controller.updateItemsList(updateHandler.getItemsList());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        addbutton.getScene().getWindow().hide();
    }


    private byte[] imageToByteArray(Image image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            InputStream inputStream = new URL(image.getUrl()).openStream();
            byte[] data = new byte[1024];
            int readByte;
            while ((readByte = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, readByte);
            }


            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed", e);
        }
    }


    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageview.setImage(image);
        }
    }
}