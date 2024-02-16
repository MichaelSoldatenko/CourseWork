package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorbackground;

    @FXML
    private AnchorPane anchorfrontground;

    @FXML
    private Label emaillabel;

    @FXML
    private TextField emailtextfield;

    @FXML
    private Button loginbutton;

    @FXML
    private Label loginlabel;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label passwordlabel;

    @FXML
    private Button signupbutton;

    @FXML
    private Label signuplabel;

    @FXML
    void initialize() {
        signupbutton.setOnAction(event -> {
            signupbutton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/sign-up.fxml"));
            try {
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        loginbutton.setOnAction(event -> {
            loginbutton.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/main-window.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
