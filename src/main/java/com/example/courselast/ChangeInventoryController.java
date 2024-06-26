package com.example.courselast;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

public class ChangeInventoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane backgroundanchorpane;

    @FXML
    private Label changeinventorylabel;

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
    private Button savebutton;

    @FXML
    private ImageView inventoryimage;

    private MainWindowController mainWindowController;

    public void setMainWindowController(MainWindowController controller) {
        this.mainWindowController = controller;
    }

    @FXML
    void initialize() {
        savebutton.setOnAction(event -> saveChangedItem());
    }
    public void saveChangedItem () {
        String name = inventoryname.getText();
        int quantity = Integer.parseInt(inventoryquantity.getText());
        double price = Double.parseDouble(inventoryprice.getText());
        String description = inventorydescription.getText();
        String category = inventorycategory.getText();

        Image image = inventoryimage.getImage();

        Item item = new Item(name, quantity, price, description, category);
        item.setImage(image);

        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.updateItem(item);
            ObservableList<Item> updatedList = handler.getItemsList();
            mainWindowController.updateItemsList(updatedList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        savebutton.getScene().getWindow().hide();
    }

    public void setNewData (Item item) {
        inventoryname.setText(item.getName());
        inventoryquantity.setText(String.valueOf(item.getQuantity()));
        inventoryprice.setText(String.valueOf(item.getPrice()));
        inventorydescription.setText(item.getDescription());
        inventorycategory.setText(item.getCategory());
        if (item.getImage() != null) {
            inventoryimage.setImage(item.getImage());
        }
    }
}