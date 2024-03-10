package com.example.courselast;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

        Item item = new Item(inventoryName, inventoryQuantity, inventoryPrice, inventoryDescription, inventoryCategory);

        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.addInventory(item);
            System.out.println("Inventory added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}