package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    void initialize() {
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-inventory.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Додати товар");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       addinventorybutton.setOnAction(event -> {
            //addinventorybutton.getScene().getWindow().hide();//

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
    }
}