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
    private Button refreshbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button changebutton;

    @FXML
    private Button logoutbutton;

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
        mainwindowanchorpane.prefWidthProperty().bind(mainwindowscrollpane.widthProperty());
        uppinkanchorpane.prefWidthProperty().bind(mainwindowscrollpane.widthProperty());

       refreshbutton.setOnAction(this::refreshWindow);
       searchtextfield.setOnAction(this::searchMethod);
       deletebutton.setOnAction(this::deleteItemFromTable);
       changebutton.setOnAction(this::openChangeWindow);

       logoutbutton.setOnAction(event -> {
           logoutbutton.getScene().getWindow().hide();

           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/hello-view.fxml"));
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

    public void refreshWindow (ActionEvent event) {
        DatabaseHandler handler = new DatabaseHandler();
        try {
            itemObservableList = handler.getItemsList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        itemstableview.setItems(itemObservableList);
    }

    public void searchMethod (ActionEvent event) {
        String query_text = searchtextfield.getText();
        if (!query_text.isEmpty()) {
            DatabaseHandler handler = new DatabaseHandler();
            ObservableList<Item> search = null;
            try {
                search = handler.searchItem(query_text);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            itemstableview.setItems(search);
        } else {
            DatabaseHandler handler = new DatabaseHandler();
            try {
                itemObservableList = handler.getItemsList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            itemstableview.setItems(itemObservableList);
        }
    }

    public void deleteItemFromTable (ActionEvent event) {
        Item selected = itemstableview.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("Nothing selected!");
        }
        String name = selected.getName();
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.deleteItem(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void openChangeWindow(ActionEvent event) {
        Item selected = itemstableview.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("Nothing selected!");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/change-inventory.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChangeInventoryController controller = fxmlLoader.getController();
        controller.setNewData(selected);

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}