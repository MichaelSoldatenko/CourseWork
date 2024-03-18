package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addinventorybutton;

    @FXML
    private AnchorPane mainwindowanchorpane;

    @FXML
    private ScrollPane mainwindowscrollpane;

    @FXML
    private TextField searchtextfield;

    @FXML
    private AnchorPane uppinkanchorpane;

    @FXML
    private TableView<Item> itemstableview;

    @FXML
    private TableColumn<Item, String> categorycolumn;

    @FXML
    private TableColumn<Item, String> descriptioncolumn;

    @FXML
    private TableColumn<Item, String> namecolumn;

    @FXML
    private TableColumn<Item, Double> pricecolumn;

    @FXML
    private TableColumn<Item, Integer> quantitycolumn;

    private ObservableList<Item> itemObservableList;
    private ListView<Item> itemListView;

    @FXML
    void initialize() {
       addinventorybutton.setOnAction(event -> {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/add-inventory.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
       try {
           DatabaseHandler set_handler = new DatabaseHandler();
           itemObservableList = set_handler.getItemsList();
           itemstableview.setItems(itemObservableList);

           for (Item item: itemObservableList) {
               System.out.println(item);
           }
       } catch (SQLException | ClassNotFoundException exception) {
           exception.printStackTrace();
       }

       namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       quantitycolumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
       descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
       categorycolumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    public void updateItemsList(ObservableList<Item> itemObservableList){
        itemstableview.setItems(itemObservableList);
    }
}