package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
        try {
            Parent root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainWindowController controller = fxmlLoader.getController();

        try {
            DatabaseHandler update_handler = new DatabaseHandler();
            controller.updateItemsList(update_handler.getItemsList());
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}